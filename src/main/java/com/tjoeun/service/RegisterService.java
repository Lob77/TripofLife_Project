package com.tjoeun.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.ui.Model;

import com.tjoeun.dao.UserInfoDAO;
import com.tjoeun.vo.UserInfoVO;

public class RegisterService implements TemplateService {

	@Override
	public void execute(UserInfoVO userInfoVO) { }

	@Override
	public void execute(Model model) {
		System.out.println("loginService의 execute() 메소드 실행");
		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");

		String userID = request.getParameter("userID");
		String userPassword = request.getParameter("userPassword");

//		System.out.println(userID);
//		System.out.println(userPassword);

		AbstractApplicationContext ctx = new GenericXmlApplicationContext("classpath:applicationCTX.xml");
		UserInfoDAO userInfoDAO = ctx.getBean("userInfoDAO", UserInfoDAO.class);
		userInfoDAO.registerOK(userID, userPassword);
	
		
//		model.addAttribute("registerOK", userInfoDAO.registerOK(userID, userPassword));

	}

}
