package com.tjoeun.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.ui.Model;

import com.tjoeun.dao.UserInfoDAO;
import com.tjoeun.vo.ContentVO;
import com.tjoeun.vo.UserInfoVO;

public class UpdateConfirmService implements TemplateService {

	@Override
	public void execute(UserInfoVO userInfoVO) { }

	@Override
	public void execute(Model model) {
		System.out.println("userInfoService의 execute() 메소드 실행");
		System.out.println("UpdateConfirmService 실행");
		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");

		String userID = request.getParameter("userID");
 		String userPassword = request.getParameter("userPassword");
 		String userID2 = request.getParameter("userID2");
//		System.out.println(userID);
//		System.out.println(userPassword);
//		System.out.println("UpdateConfirmService 클래스의: " + userID2);
		
		AbstractApplicationContext ctx = new GenericXmlApplicationContext("classpath:applicationCTX.xml");
		UserInfoDAO userInfoDAO = ctx.getBean("userInfoDAO", UserInfoDAO.class);
		
		userInfoDAO.UpdateConfirm(userID,userPassword,userID2);
		
		
			
	}
}


