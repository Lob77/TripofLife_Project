package com.tjoeun.TeamProject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tjoeun.dao.CommentDAO;
import com.tjoeun.dao.ContentDAO;
import com.tjoeun.dao.ReservationDAO;
import com.tjoeun.dao.UserInfoDAO;
import com.tjoeun.service.ContentInsertService;
import com.tjoeun.service.ContentSelectService;
import com.tjoeun.service.ContentViewService;
import com.tjoeun.service.DeleteConfirmService;
import com.tjoeun.service.DeleteContentService;
import com.tjoeun.service.IncrementService;
import com.tjoeun.service.MyContentSelectService;
import com.tjoeun.service.MyReservationSelectService;
import com.tjoeun.service.RegisterService;
import com.tjoeun.service.TemplateService;
import com.tjoeun.service.TemplateService_content;
import com.tjoeun.service.TemplateService_reservation;
import com.tjoeun.service.UpdateConfirmService;
import com.tjoeun.service.UpdateContentService;
import com.tjoeun.service.loginService;
import com.tjoeun.vo.CommentVO;
import com.tjoeun.vo.ContentVO;
import com.tjoeun.vo.ReservationVO;
import com.tjoeun.vo.UserInfoVO;

@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	private JdbcTemplate template;
	
	@Autowired
	public void setTemplate(JdbcTemplate template) {
		this.template = template;
		Constant.template = this.template;
	}

	@Autowired
	private SqlSession sqlSession;
			
//	??? ??????(???????????????)	- ????????? ????????????
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		System.out.println("??????????????? home() ????????? ??????");
		return "HomePage";
	}

//	??? ??????- ???????????? ?????? ?????????
	@RequestMapping(value = "/IntroPage", method = RequestMethod.GET)
	public String IntroPage(Locale locale, Model model) {
		System.out.println("??????????????? home() ????????? ??????");
		return "IntroPage";
	}

//	??? ??????(???????????????) - ???????????? ????????????	
	@RequestMapping("/logoutMain")
	public String logoutMain(HttpServletRequest request,Model model) {
		HttpSession session = request.getSession();
		session.invalidate();
		System.out.println("??????????????? logout() ????????? ??????");	
			return "HomePage";
	}	
/*
//	??? ??????(????????? ?????????)	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		System.out.println("??????????????? home() ????????? ??????");
		return "redirect:list";
	}
*/
//	login ?????? ????????? ????????? ?????????
	@RequestMapping(value = "login", method = {RequestMethod.GET, RequestMethod.POST})
	public String login(Locale locale, Model model) {
		System.out.println("??????????????? login() ????????? ??????");
		return "login";
	}
	

//	???????????? ?????? ?????? ??? ????????? ?????????	
	@RequestMapping(value = "register", method = RequestMethod.GET)
	public String register(Locale locale, Model model) {
		System.out.println("??????????????? register() ????????? ??????");
		return "register";
	}
	
//	???????????? ??? ????????????
	@RequestMapping("/TripOfLife_Project/UserRegisterCheck")
	public void userRegisterCheck(HttpServletRequest request,HttpServletResponse response)throws ServletException, IOException{
		System.out.println("??????????????? UserRegisterCheck() ????????? ??????");
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=utf-8");
		
		String userID = request.getParameter("userID");
		int result = new UserInfoDAO().registerCheck(userID);
		response.getWriter().write(result + "");
	}
		
	
//	???????????? ????????? =>DBCP template ??????	
	@RequestMapping("/registerOK")
	public String registerOK(HttpServletRequest request,Model model) {
		System.out.println("??????????????? registerOK ????????? ??????");
		HttpSession session = request.getSession();
		String userPassword = request.getParameter("userPassword");
		String confirmPassword = request.getParameter("confirmPassword");
	
		
		String msg ="";
		if(userPassword == null || userPassword.equals("")||
			confirmPassword ==  null || confirmPassword.equals("")){
				msg ="??????????????? ??????????????????";
				session.setAttribute("msg", msg);
				return "errorPageRegister";
			}else if(!userPassword.equals(confirmPassword)) {
				msg = "??????????????? ???????????? ????????????.";
				session.setAttribute("msg", msg);
				return "errorPageRegister";
			}else {
				model.addAttribute("request",request);
				AbstractApplicationContext ctx = new GenericXmlApplicationContext("classpath:applicationCTX.xml");
				TemplateService service = ctx.getBean("Register",RegisterService.class);	
				service.execute(model);	
				return "redirect:list";
		}
		}
	

	
	
/*
//	???????????? ?????????-mybatis ?????? => 500 ??????
	@RequestMapping("/registerOK")
	public String registerOK(HttpServletRequest request, Model model,UserInfoVO userInfoVO) {
		System.out.println("??????????????? registerOK() ????????? ??????");
		MybatisDAO mapper = sqlSession.getMapper(MybatisDAO.class);
		System.out.println(userInfoVO);
		String userPassword=null;
		String confirmPassword=null;
		userPassword = request.getParameter(userPassword);
		confirmPassword = request.getParameter(confirmPassword);
		if(userPassword == confirmPassword) {
			mapper.registerOK(userInfoVO);
			return "redirect:list";
		}else {
			String msg ="";
			msg = "??????????????? ???????????? ????????????.";
			return "errorPage";
		}

	}
*/
//	????????? ????????? ?????? ??? ?????????-DBCP ????????????
	@RequestMapping("/loginOK")
	public String loginOK(HttpServletRequest request,Model model) {
		System.out.println("??????????????? loginOK() ????????? ??????");
		System.out.println(request);
		
//		???????????? ??? ??????
//		System.out.println(userID);
//		System.out.println(userPassword);

		model.addAttribute("request", request);
		AbstractApplicationContext ctx = new GenericXmlApplicationContext("classpath:applicationCTX.xml");
		TemplateService service = ctx.getBean("login", loginService.class);
		service.execute(model);
		
		UserInfoDAO dao = new UserInfoDAO();
		HttpSession session = request.getSession();
		UserInfoVO vo = new UserInfoVO();
		
		String userID = request.getParameter("userID");
		String userPassword = request.getParameter("userPassword");
		
		int LOGIN_OK = 1;
		int WRONG_PASSWORD = 0;
		int ID_NOT_EXIST = -1;
		vo.setUserID(userID);
		vo.setUserPassword(userPassword);	
//		System.out.println("userID = " + userID + ", userPassword= " + userPassword);
		int loginUser = dao.loginCheck(userID, userPassword);
		//	System.out.println(loginCheck);
		String msg ="";	
		if(loginUser == LOGIN_OK) {
			session.setAttribute("userID", userID);
			session.setAttribute("userPassword", userPassword);
			return "redirect:/list";
		} else if(loginUser == WRONG_PASSWORD) {
			msg = "??????????????? ?????? ?????????????????????.";
			session.setAttribute("msg",msg);
			return "errorPage";
		} else if(loginUser == ID_NOT_EXIST) {
			msg = "???????????? ?????? ??????????????????.";
			session.setAttribute("msg",msg);
			return "errorPage";
		} else {
			msg = "?????????(SQL)??? ????????? ????????????.";
			session.setAttribute("msg",msg);
			return "errorPage";
		}
		
	}
	
// 	?????????????????? ????????? ?????????
	@RequestMapping("/logout")
	public String logout(HttpServletRequest request,Model model) {
		HttpSession session = request.getSession();
		session.invalidate();
		System.out.println("??????????????? logout() ????????? ??????");	
			return "redirect:/list";
	}	

//	????????? ?????????	
	@RequestMapping("/mypageView")
	public String mypageView(HttpServletRequest request,Model model) {
		System.out.println("??????????????? mypageView() ????????? ??????");
		return "mypageView";
	}

//	???????????????	
	@RequestMapping("/errorPage")
	public String errorPage(HttpServletRequest request,Model model) {
		System.out.println("??????????????? errorPage() ????????? ??????");
		return "errorPage";
	}
	
//	???????????? ?????? ?????????
	@RequestMapping(value = "/updatePassword", method = {RequestMethod.GET, RequestMethod.POST})
	public String updatePassword(Locale locale, Model model) {
		System.out.println("??????????????? updatePassword() ????????? ??????");
		return "changePasswordForm";
	}
	
	@RequestMapping("/TripOfLife_Project/ChangePassword")
	public void ChangePassword(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
			System.out.println("ChangePassword ???????????? actionDo() ????????? ??????");
			request.setCharacterEncoding("UTF-8");
			response.setContentType("text/html; charset=utf-8");
			
			ArrayList<String> list = new ArrayList<String>();
			list.add(request.getParameter("userID"));
			list.add(request.getParameter("userPassword").trim());
			list.add(request.getParameter("originalPassword").trim());
			list.add(request.getParameter("userPassword1").trim());
			list.add(request.getParameter("userPassword2").trim());
			
	
			int result = new UserInfoDAO().userUpdate(list);
//			System.out.println(result);
			response.getWriter().write(result + "");

	}
/*	
	******************************************************************************************
*/
	
// ????????? DB ??????
	
	@RequestMapping("/content")
	public String content(HttpServletRequest request,Model model) {
		System.out.println("??????????????? content() ????????? ??????");
		return "content";
	}

//	????????? ??????-DBCP template ?????? ??????
	@RequestMapping("/contentOK")
	public String contentOK(HttpServletRequest request,Model model) {
		System.out.println("??????????????? contentOK() ????????? ?????? - Model ??????????????? ?????? ??????");
		
		model.addAttribute("request",request);
		
//		System.out.println(request); - ???????????? ???
		System.out.println("model ???:"+model);
		
		AbstractApplicationContext ctx = new GenericXmlApplicationContext("classpath:applicationCTX.xml");
		TemplateService_content serviceContent = ctx.getBean("ContentInsert",ContentInsertService.class);
		serviceContent.execute(model);
		
		return "redirect:/list";
	}

//	??? ???????????? ????????? ??? ??????	
	@RequestMapping("/list")
	public String list(HttpServletRequest request,Model model) {
		System.out.println("??????????????? list() ????????? ??????");
		
		model.addAttribute("request",request);
		
		AbstractApplicationContext ctx = new GenericXmlApplicationContext("classpath:applicationCTX.xml");
		TemplateService_content serviceContent = ctx.getBean("ContentSelect",ContentSelectService.class);
		serviceContent.execute(model);
		
		return "list";
	}
	
//	????????? ???????????????
	@RequestMapping("/increment")
	public String increment(HttpServletRequest request,Model model) {
		System.out.println("??????????????? increment ????????? ??????");
		model.addAttribute("request",request);
		
		AbstractApplicationContext ctx = new GenericXmlApplicationContext("classpath:applicationCTX.xml");
		TemplateService_content serviceContent = ctx.getBean("Increment",IncrementService.class);
		serviceContent.execute(model);
		
		model.addAttribute("idx",request.getParameter("idx"));
		model.addAttribute("currentPage",request.getParameter("currentPage"));
		
		return "redirect:/contentView";
		
	}
// 	????????? ????????? ?????????
	@RequestMapping("/contentView")
	public String contentView(HttpServletRequest request,Model model) {
		System.out.println("??????????????? list() ????????? ??????");
		
		model.addAttribute("request",request);
		
		AbstractApplicationContext ctx = new GenericXmlApplicationContext("classpath:applicationCTX.xml");
		TemplateService_content serviceContent = ctx.getBean("ContentView",ContentViewService.class);
		serviceContent.execute(model);
		
		return "contentView";
		
	}
	
// ajax search ?????? ?????? ????????? => ?????????????????????
	@RequestMapping("/TripOfLife_Project/SearchTest")
	protected void SearchTest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		System.out.println("AjaxSearch() ????????? Post ???????????? ?????????");
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=utf-8");
		String subject = request.getParameter("subject").trim();
		System.out.println(subject);
		
		response.getWriter().write(getJSON(subject));
	}
	private String getJSON(String subject) {
		// System.out.println("AjaxSearch ???????????? getJSON() ?????????");
		if (subject == null) {
			subject = "";
		}
		
		ArrayList<ContentVO> list = new ContentDAO().search(subject);
		// System.out.println(list);
		
		StringBuffer result = new StringBuffer();
		result.append("{\"result\": ["); // json ??????
		for (ContentVO vo : list) {
			result.append("[{\"value\": \"" + vo.getIdx() + "\"},");
			result.append("{\"value\": \"" + vo.getSubject().trim() + "\"},");
			result.append("{\"value\": \"" + vo.getUserID() + "\"},");
			result.append("{\"value\": \"" + vo.getHit() + "\"},");
			result.append("{\"value\": \"" + vo.getWriteDate() + "\"}],");
		}
		result.append("]}"); // json ???
		System.out.println(result);
		
		return result.toString();
	}

		
		
		
		
		

//	delete ???????????? ???	
	@RequestMapping("/DeleteCheck")
	public String DeleteCheck(HttpServletRequest request,Model model) {
		System.out.println("??????????????? DeleteCheck() ????????? ??????");
		return "DeleteCheck";
	}
//	delete ?????????	
	@RequestMapping("/DeleteConfirm")
	public String DeleteConfirmCheck(HttpServletRequest request, Model model) {
		System.out.println("??????????????? DeleteConfirm() ????????? ??????");
	
		model.addAttribute("request",request);
		AbstractApplicationContext ctx = new GenericXmlApplicationContext("classpath:applicationCTX.xml");
		TemplateService service = (TemplateService) ctx.getBean("DeleteConfirm",DeleteConfirmService.class);
		service.execute(model);		
		
		int allow = 1;
		int reject = -1;
		int sqlError = -2;
		
		HttpSession session = request.getSession();
		String userID = request.getParameter("userID");
		String userPassword = request.getParameter("userPassword");
		String userID1 = request.getParameter("userID1");
		int idx = Integer.parseInt(request.getParameter("idx"));
		System.out.println(idx);
		
		UserInfoDAO dao = new UserInfoDAO();
		int delete = dao.DeleteConfirm(userID, userPassword, userID1);
		System.out.println(delete);
		
		if(delete == allow) {  
			model.addAttribute("request" ,request);
			System.out.println("request:"+request);
			System.out.println("model ?????????:"+model);
			
			TemplateService_content serviceContent = ctx.getBean("Delete",DeleteContentService.class);
			serviceContent.execute(model);
			
			return "redirect:list";
		}else if(delete == reject) {
			String msg = "????????? ???????????? ????????????.";
			session.setAttribute("msg", msg);
			return "errorPageDeleteContent";	
		}else {
			String msg = "????????? ??????";
			session.setAttribute("msg", msg);
			return "errorPageDeleteContent";
		}

	}
	
//	??? ?????? ????????? ??????(contentViewUpdate ????????? ??????)
	@RequestMapping("/contentViewUpdate")
	public String contentViewUpdate(HttpServletRequest request,Model model) {
		System.out.println("??????????????? contentViewUpdate() ????????? ??????");
		model.addAttribute("request",request);
		
		AbstractApplicationContext ctx = new GenericXmlApplicationContext("classpath:applicationCTX.xml");
		TemplateService_content serviceContent = ctx.getBean("ContentView",ContentViewService.class);
		serviceContent.execute(model);
		
		return "contentViewUpdate";
		
	}
//	??? ?????? ?????? ??????
	@RequestMapping("/contentViewUpdateCheck")
	public String contentViewUpdateCheck(HttpServletRequest request,Model model) {
		System.out.println("??????????????? contentViewUpdateCheck() ????????? ??????");
		
		return "contentViewUpdateCheck";
		
	}
	
//	??? ?????? ?????? ??????
	@RequestMapping("/UpdateConfirm")
	public String UpdateConfirm(HttpServletRequest request,Model model) {
		System.out.println("??????????????? UpdateConfirm() ????????? ??????");
		
		model.addAttribute("request",request);
		AbstractApplicationContext ctx = new GenericXmlApplicationContext("classpath:applicationCTX.xml");
		TemplateService service = (TemplateService) ctx.getBean("UpdateConfirm",UpdateConfirmService.class);
		service.execute(model);
		
		int allow = 1;
		int reject = -1;
		int sqlError = -2;
		
		HttpSession session = request.getSession();
		String userID = request.getParameter("userID");
		String userID2 = request.getParameter("userID2");
		String subject = request.getParameter("subject");
		String content = request.getParameter("content");
		String userPassword = request.getParameter("userPassword");
		int idx = Integer.parseInt(request.getParameter("idx"));
		
		UserInfoDAO dao = new UserInfoDAO();
		int update = dao.UpdateConfirm(userID, userPassword, userID2);
		System.out.println(update);
		
		
		if(update == allow) {  
			model.addAttribute("request" ,request);
			System.out.println("request:"+request);
			System.out.println("model ?????????:"+model);
			
			TemplateService_content serviceContent = ctx.getBean("Update",UpdateContentService.class);
			serviceContent.execute(model);
			
			return "redirect:list";
		}else if(update == reject) {
			String msg = "????????? ???????????? ????????????.";
			session.setAttribute("msg", msg);
			return "errorPageDeleteContent";	
		}else {
			String msg = "????????? ??????";
			session.setAttribute("msg", msg);
			return "errorPageDeleteContent";
		}
		
	}
	
//	???????????? ??????
	@RequestMapping("/InsertComment")
	public void InsertComment(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException  {
		System.out.println("InsertComment ???????????? actionDo() ?????????");
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=utf-8");

		int idx = Integer.parseInt(request.getParameter("idx").trim());
		String userID = request.getParameter("userID");
		String userComment = request.getParameter("userComment").trim();
		System.out.println("????????? idx: " + idx);
		System.out.println("????????? userID: " + userID);
		System.out.println("????????? userComment: " + userComment);
		
		
		CommentVO cmo = new CommentVO(idx, userID, userComment);
		System.out.println(cmo);
		int result = new CommentDAO().insertComment(cmo);
		response.getWriter().write(String.valueOf(result));
		response.getWriter().write(result + "");
	
	}

//	?????? ????????? ???????????? ??????
	@RequestMapping("/CommentList")
	public void CommentList(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException  {
		System.out.println("CommentList ???????????? actionDo() ?????????");
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=utf-8");
		
		int idx = Integer.parseInt(request.getParameter("idx").trim());
		String userID = request.getParameter("userID").trim();
		String userComment = request.getParameter("userComment").trim();
		System.out.println("CommentList ????????? idx: " + idx);
		response.getWriter().write(getJSON(idx));
	}
		
		private String getJSON(int idx) {
		System.out.println("CommentList ???????????? getJSON ?????????");
		CommentDAO dao = new CommentDAO();
		ArrayList<CommentVO> list = dao.commentSelectList(idx);
		
		StringBuffer result = new StringBuffer();
		result.append("{\"result\": [");
		for (CommentVO cmo : list) {
			result.append("[{\"value\": \"" + cmo.getUserID().trim() + "\"},");
			result.append("{\"value\": \"" + cmo.getUserComment() + "\"},");
			result.append("{\"value\": \"" + cmo.getWritedate() + "\"}],");
		}
		result.append("]}");
		//System.out.println(result);
		return result.toString();	
	}
		
		
//	???(Reservation) ?????? ??????
		@RequestMapping("/Reservation")
		public void Reservation(HttpServletRequest request,HttpServletResponse response)throws ServletException, IOException{
			System.out.println("??????????????? Reservation() ????????? ??????");
			request.setCharacterEncoding("UTF-8");
			response.setContentType("text/html; charset=utf-8");

			String contentOwner = request.getParameter("userID");
			String subject = request.getParameter("subject");
			int idx = Integer.parseInt(request.getParameter("idx"));
			String resID = (String) request.getParameter("resID");
			
			ReservationVO ro = new ReservationVO();
			ro.setContentOwner(contentOwner);
			ro.setIdx(idx);
			ro.setResID(resID);
			ro.setSubject(subject);
			
			
			int result = new ReservationDAO().reservation(ro);
//			System.out.println(result);
			response.getWriter().write(result + "");
		
		}
		

	

/*
 *********************************************************************************************************************************************** 
 */
// myPage ??????

//myPage ??? ?????????(reservation) ????????????		
	@RequestMapping("/myContentResGo")
	public String myContentResGo(HttpServletRequest request, Model model) {
		System.out.println("??????????????? myContentResGo() ????????? ??????");
		
//		String userID = request.getParameter("userID");
//		System.out.println("myContentResGo??? userID:" + userID);
		model.addAttribute("request",request);
		
		AbstractApplicationContext ctx = new GenericXmlApplicationContext("classpath:applicationCTX.xml");
		TemplateService_reservation serviceReservation = ctx.getBean("MyContentRes", MyReservationSelectService.class);
		serviceReservation.execute(model);
		
		return "myContentResView";
		
	}
	
 //myPage ??? ????????? ????????? ????????????
	@RequestMapping("/myContentGo")
	public String myContentGo(HttpServletRequest request, Model model) {
		System.out.println("??????????????? myContentGo() ????????? ??????");
//		String userID = request.getParameter("userID");
//		System.out.println(userID);
		
		model.addAttribute("request",request);
		AbstractApplicationContext ctx = new GenericXmlApplicationContext("classpath:applicationCTX.xml");
		TemplateService_content serviceContent = ctx.getBean("MyContent",MyContentSelectService.class);
		serviceContent.execute(model);
		
		return "myContentView";
	}
	 
	
	
}


	
	

