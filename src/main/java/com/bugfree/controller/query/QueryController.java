package com.bugfree.controller.query;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bugfree.model.supplier.Supplier;
import com.bugfree.model.supplier.query.Query;
import com.bugfree.service.supplier.SupplierService;
import com.bugfree.service.supplier.query.QueryService;

@Controller
public class QueryController {
	@Autowired
	private QueryService queryService;
	@Autowired
	private SupplierService supplierService;
	
	@RequestMapping(value = "/raise/query", method = RequestMethod.POST)
	
	public @ResponseBody String saveQuery( Model model, HttpServletRequest request, HttpSession session, Query query) {
		
		String raiseQuery = request.getParameter("raiseQuery");
		System.out.println("***************enter  in raiseQuery query***************"+raiseQuery);
		Supplier supplier= supplierService.findByEmailId((String)SecurityUtils.getSubject().getPrincipal());
		query.setSupplier(supplier);
		queryService.save(query);
		
		return "";
	}
	
}
