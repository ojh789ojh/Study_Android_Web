package com.hanul.app.controller;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.hanul.app.command.AnCommand;
import com.hanul.app.command.AnJoinCommand;

@Controller
public class AnController {
	
	AnCommand command;
	
	@RequestMapping(value="/anJoin", method = {RequestMethod.GET, RequestMethod.POST}  )
	public String anJoin(HttpServletRequest req, Model model){
		System.out.println("anJoin()");
		
		try {
			req.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} 
		
		String id = (String) req.getParameter("id");
		String passwd = (String) req.getParameter("passwd");
		String name = (String) req.getParameter("name");
		String phonenumber = (String) req.getParameter("phonenumber");
		String address = (String) req.getParameter("address");
		
		System.out.println(id);
		System.out.println(passwd);
		System.out.println(name);
		System.out.println(phonenumber);
		System.out.println(address);
		
		model.addAttribute("id", id);
		model.addAttribute("passwd", passwd);
		model.addAttribute("name", name);
		model.addAttribute("phonenumber", phonenumber);
		model.addAttribute("address", address);
		
		command = new AnJoinCommand();
		command.execute(model);
		
		
		return "anJoin";
	}
}
