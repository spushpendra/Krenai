package com.bugfree.controller.encryption;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.bugfree.model.supplier.Supplier;
import com.bugfree.service.encrypt.EncryptService;
import com.bugfree.service.supplier.SupplierService;

@Controller
public class ChangePassword {
	
	@Autowired(required=true)
	private EncryptService encryptService;
	@Autowired
	private SupplierService supplierService;

	@RequestMapping(value="/profile/change_password",method = RequestMethod.GET)
    public String changePassword(HttpServletRequest request, Model model) {
	 
		return "profile/change-password";
    }
	
	@RequestMapping(value="/profile/password/requested",method = RequestMethod.POST)
    public String changePasswordRequest(RedirectAttributes redirectAttributes, HttpServletRequest request, Model model) {
	 
		String[] keepme = request.getParameterValues("keepme-loggedin");
		String oldPassword = request.getParameter("oldPassword");
		String newPassword = request.getParameter("newPassword");
		Supplier supplier = supplierService.findByEmailId((String)SecurityUtils.getSubject().getPrincipal());
		 boolean isPasswordChanged = encryptService.verifyOldPassword(supplier, oldPassword, newPassword);
		 if(isPasswordChanged==true){
			 System.out.println("***********success pswd*********");
			 redirectAttributes.addFlashAttribute("successMessage", "Password Updated Successfully.");
			  
		 }
		 else{
			 redirectAttributes.addFlashAttribute("passwordIncorectMessage", "Old Password Incorrect.");
			  
		 }
		 if(isPasswordChanged){
			 if(keepme!=null&&keepme.length>0){
				 System.out.println("***********logout*********");
				 return "redirect:/dashboard";
			 }
			 else{
				 System.out.println("***********logout else*********");
				 
				 SecurityUtils.getSubject().logout();
				 return "redirect:/login";
			 }
		 }
		 else{
			 return "profile/change-password";
		 }
		 
    }
	

	@RequestMapping(value="/profile/password/requested",method = RequestMethod.GET)
    public String changePasswordRequestGET(HttpServletRequest request, Model model) {
		return "profile/change-password";
    }
 
}
