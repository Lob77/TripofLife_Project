package com.tjoeun.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.ui.Model;

import com.tjoeun.dao.ContentDAO;
import com.tjoeun.vo.ContentVO;

public class ContentInsertService implements TemplateService_content {

	@Override
	public void execute(ContentVO contenVo) { 
		System.out.println("ContentService의 execute() 메소드 실행 - VO 사용");

		AbstractApplicationContext ctx = new GenericXmlApplicationContext("classpath:applicationCTX.xml");
		ContentDAO contentDAO = ctx.getBean("ContentDAO", ContentDAO.class);
		contentDAO.insert(contenVo);
		
	}

	@Override
	public void execute(Model model) {
		System.out.println("contentService의 execute() 메소드 실행");
		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");

		String userID = request.getParameter("userID");
 		String subject = request.getParameter("subject");
		String content = request.getParameter("content");
		
//		System.out.println(userID);
//		System.out.println(subject);
//		System.out.println(content);
		AbstractApplicationContext ctx = new GenericXmlApplicationContext("classpath:applicationCTX.xml");
		ContentVO contentVO = ctx.getBean("ContentVO", ContentVO.class);
		
		contentVO.setUserID(userID);
		contentVO.setSubject(subject);
		contentVO.setContent(content);
		
		ContentDAO contentDAO = ctx.getBean("ContentDAO",ContentDAO.class);
		contentDAO.insert(contentVO);
		
		
	}
}


