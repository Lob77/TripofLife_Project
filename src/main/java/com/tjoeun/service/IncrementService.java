package com.tjoeun.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.ui.Model;

import com.tjoeun.dao.ContentDAO;
import com.tjoeun.vo.ContentVO;

public class IncrementService implements TemplateService_content {

	@Override
	public void execute(ContentVO contenVo) { }

	@Override
	public void execute(Model model) {
		System.out.println("contentService의 execute() 메소드 실행");
		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");

		int idx = Integer.parseInt(request.getParameter("idx"));
//		System.out.println(idx);
		AbstractApplicationContext ctx = new GenericXmlApplicationContext("classpath:applicationCTX.xml");
		ContentDAO contentDAO = ctx.getBean("ContentDAO",ContentDAO.class);

		contentDAO.Increment(idx);
		
		
	}
}


