package com.tjoeun.service;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.ui.Model;

import com.tjoeun.dao.ContentDAO;
import com.tjoeun.dao.ReservationDAO;
import com.tjoeun.vo.ContentList;
import com.tjoeun.vo.ContentVO;
import com.tjoeun.vo.ReservationList;
import com.tjoeun.vo.ReservationVO;

public class ReservationContetnService implements TemplateService_reservation {

	@Override
	public void execute(ReservationVO reservationVO) { }

	@Override
	public void execute(Model model) {
		System.out.println("reservationContentService의 execute() 메소드 실행");
		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");

		AbstractApplicationContext ctx = new GenericXmlApplicationContext("classpath:applicationCTX.xml");
		ReservationDAO reservationDAO = ctx.getBean("ReservationDAO",ReservationDAO.class);
	
		int pageSize = 10;
		int currentPage =1;
		try {
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		}catch (Exception e) {}
		int totalCount = reservationDAO.selectReservationCount();
		
		ReservationList reservationList = ctx.getBean("ReservationList",ReservationList.class);
		
		reservationList.initReservationList(pageSize, totalCount, currentPage);
		
		HashMap<String, Integer> hmap = new HashMap<String, Integer>();
		hmap.put("startNo", reservationList.getStartNo());
		hmap.put("endNo", reservationList.getEndNo());
		reservationList.setList(reservationDAO.selectReservationList(hmap));
//		System.out.println(contentList);
	
		model.addAttribute("ReservationList", reservationList);
		
		
		
	}
}


