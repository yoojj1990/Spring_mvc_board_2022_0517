package com.yjj.MVCBoard.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yjj.MVCBoard.command.BCoammand;
import com.yjj.MVCBoard.command.BContentCommand;
import com.yjj.MVCBoard.command.BListCommand;
import com.yjj.MVCBoard.command.BWriteCommand;

@Controller
public class BController {

	
	BCoammand command = null;
	
	
	@RequestMapping("/")
	public String root(Model model) {
		
		return "redirect:list";
	}
	
	@RequestMapping("/list")
	public String list(Model model) {
		
		command = new BListCommand();
		command.excute(model);
		
		return "list";
	}
	
	@RequestMapping("/write")
	public String write(HttpServletRequest request, Model model) {
		
		
		model.addAttribute("request", request);
		
		command = new BWriteCommand();
		command.excute(model);
		
		return "redirect:list";
	}
	
	
	@RequestMapping("/write_form")
	public String write_form() {
		
		return "write_form";
	}
	
	
	@RequestMapping("/content_view")
	public String content_view(HttpServletRequest request, Model model) {
		
		model.addAttribute("request", request);
		
		command = new BContentCommand();
		command.excute(model);
		
		return "content_view";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
