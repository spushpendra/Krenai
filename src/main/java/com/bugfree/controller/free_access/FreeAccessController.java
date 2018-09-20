package com.bugfree.controller.free_access;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class FreeAccessController {


	@RequestMapping(value = "/about-us", method = RequestMethod.POST)
	public @ResponseBody String commentOnFeed(Model model, HttpServletRequest request, @RequestParam (value="_feed") int feedId) {
		
		return "master/free/about-krenai";

	}
}
