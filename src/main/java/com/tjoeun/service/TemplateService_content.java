package com.tjoeun.service;

import org.springframework.ui.Model;

import com.tjoeun.vo.ContentVO;
import com.tjoeun.vo.UserInfoVO;

public interface TemplateService_content {

	public abstract void execute(ContentVO contentVO);
	public abstract void execute(Model model);
	
}
