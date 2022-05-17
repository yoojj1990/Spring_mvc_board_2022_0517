package com.yjj.MVCBoard.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yjj.MVCBoard.command.BCoammand;
import com.yjj.MVCBoard.command.BListCommand;

@Controller
public class BController {

	
	BCoammand command = null;

	@RequestMapping("/list")
	public String list(Model model) {
		
		command = new BListCommand();
		command.excute(model);
		
		return "list";
	}
	
	
	
	
	
	
	
	
	
	
}
