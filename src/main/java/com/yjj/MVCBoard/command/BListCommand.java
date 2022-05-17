package com.yjj.MVCBoard.command;

import java.util.ArrayList;

import org.springframework.ui.Model;

import com.yjj.MVCBoard.dao.BDao;
import com.yjj.MVCBoard.dto.BDto;

public class BListCommand implements BCoammand {

	@Override
	public void excute(Model model) {
		// TODO Auto-generated method stub
		
		BDao dao = new BDao();
		ArrayList<BDto> dtos = dao.list();
		
		model.addAttribute("list", dtos);
		
	}

}
