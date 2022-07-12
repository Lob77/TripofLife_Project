package com.tjoeun.dao;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.transaction.annotation.Transactional;

import com.tjoeun.vo.ContentVO;
import com.tjoeun.vo.UserInfoVO;



public interface MybatisDAO {

	void registerOK(UserInfoVO userInfoVO);

	int selectContentCount();
	ArrayList<ContentVO> selectContentList(HashMap<String, Integer> hmap);
	void contentIncrement(int idx);



}







