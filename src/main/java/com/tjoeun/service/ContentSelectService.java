package com.tjoeun.service;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.ui.Model;

import com.tjoeun.dao.ContentDAO;
import com.tjoeun.vo.ContentList;
import com.tjoeun.vo.ContentVO;

public class ContentSelectService implements TemplateService_content {

	@Override
	public void execute(ContentVO contenVo) { }

	@Override
	public void execute(Model model) {
		System.out.println("ContentSelectService의 execute() 메소드 실행");
		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");

		AbstractApplicationContext ctx = new GenericXmlApplicationContext("classpath:applicationCTX.xml");
		ContentDAO contentDAO = ctx.getBean("ContentDAO",ContentDAO.class);
	
		int pageSize = 10;
		int currentPage =1;
		try {
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		}catch (Exception e) {}
		int totalCount = contentDAO.selectContentCount();
		
		ContentList contentList = ctx.getBean("ContentList",ContentList.class);
		ContentList bestContentList = ctx.getBean("BestContentList",ContentList.class);
		contentList.initContentList(pageSize, totalCount, currentPage);
		
		
		HashMap<String, Integer> hmap = new HashMap<String, Integer>();
		hmap.put("startNo", contentList.getStartNo());
		hmap.put("endNo", contentList.getEndNo());
		contentList.setList(contentDAO.selectContentList(hmap));
		bestContentList.setList(contentDAO.selectBestContentList(hmap));

//		System.out.println(contentList);
	
		
		model.addAttribute("ContentList", contentList);
		model.addAttribute("BestContentList",bestContentList);
	}
}


