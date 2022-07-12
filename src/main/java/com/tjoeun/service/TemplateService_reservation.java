package com.tjoeun.service;

import org.springframework.ui.Model;

import com.tjoeun.vo.ContentVO;
import com.tjoeun.vo.ReservationVO;
import com.tjoeun.vo.UserInfoVO;

public interface TemplateService_reservation {

	public abstract void execute(ReservationVO reservationVO);
	public abstract void execute(Model model);
	
}
