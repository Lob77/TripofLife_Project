package com.tjoeun.service;

import org.springframework.ui.Model;

import com.tjoeun.vo.ContentVO;
import com.tjoeun.vo.UserInfoVO;

public interface TemplateService {

	public abstract void execute(UserInfoVO userInfoVO);
	public abstract void execute(Model model);
	
}
