package com.tjoeun.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.ui.Model;

import com.tjoeun.dao.ContentDAO;
import com.tjoeun.vo.ContentVO;

public class UpdateContentService implements TemplateService_content {

	@Override
	public void execute(ContentVO contenVo) { }

	@Override
	public void execute(Model model) {
		System.out.println("UpdateContentService의 execute() 메소드 실행");
		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");

		int idx = Integer.parseInt(request.getParameter("idx"));
		String subject = request.getParameter("subject");
		String content = request.getParameter("content");
		
//		System.out.println("updaetcontent 클래스의:" + idx);
//		System.out.println("updaetcontent 클래스의:" + subject);
//		System.out.println("updaetcontent 클래스의:" + content);
		
		AbstractApplicationContext ctx = new GenericXmlApplicationContext("classpath:applicationCTX.xml");
		ContentDAO contentDAO = ctx.getBean("ContentDAO", ContentDAO.class);
		contentDAO.update(idx,subject,content);
		
		
		ContentVO contentVO = ctx.getBean("ContentVO",ContentVO.class);
		contentVO.setIdx(idx);
		contentVO.setSubject(subject);
		contentVO.setContent(content);
		contentDAO.update(contentVO);
		
		model.addAttribute("currentPage",request.getParameter("currentPage"));
		
		
		
	}
}


