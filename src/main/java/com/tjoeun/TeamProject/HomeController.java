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
import com.tjoeun.service.RegisterService;
import com.tjoeun.service.ReservationContetnService;
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
			
//	첫 화면(대문페이지)	- 로그인 상태에서
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		System.out.println("컨트롤러의 home() 메소드 실행");
		return "HomePage";
	}

//	첫 화면- 홈페이지 소개 페이지
	@RequestMapping(value = "/IntroPage", method = RequestMethod.GET)
	public String IntroPage(Locale locale, Model model) {
		System.out.println("컨트롤러의 home() 메소드 실행");
		return "IntroPage";
	}

//	첫 화면(대문페이지) - 로그아웃 상태에서	
	@RequestMapping("/logoutMain")
	public String logoutMain(HttpServletRequest request,Model model) {
		HttpSession session = request.getSession();
		session.invalidate();
		System.out.println("컨트롤러의 logout() 메소드 실행");	
			return "HomePage";
	}	
/*
//	첫 화면(리스트 페이지)	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		System.out.println("컨트롤러의 home() 메소드 실행");
		return "redirect:list";
	}
*/
//	login 버튼 클릭시 표현할 페이지
	@RequestMapping(value = "login", method = {RequestMethod.GET, RequestMethod.POST})
	public String login(Locale locale, Model model) {
		System.out.println("컨트롤러의 login() 메소드 실행");
		return "login";
	}
	

//	회원가입 버튼 클릭 시 표현할 페이지	
	@RequestMapping(value = "register", method = RequestMethod.GET)
	public String register(Locale locale, Model model) {
		System.out.println("컨트롤러의 register() 메소드 실행");
		return "register";
	}
	
//	회원가입 시 중복검사
	@RequestMapping("/TripOfLife_Project/UserRegisterCheck")
	public void userRegisterCheck(HttpServletRequest request,HttpServletResponse response)throws ServletException, IOException{
		System.out.println("컨트롤러의 UserRegisterCheck() 메소드 실행");
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=utf-8");
		
		String userID = request.getParameter("userID");
		int result = new UserInfoDAO().registerCheck(userID);
		response.getWriter().write(result + "");
	}
		
	
//	회원가입 페이지 =>DBCP template 사용	
	@RequestMapping("/registerOK")
	public String registerOK(HttpServletRequest request,Model model) {
		System.out.println("컨트롤러의 registerOK 메소드 실행");
		HttpSession session = request.getSession();
		String userPassword = request.getParameter("userPassword");
		String confirmPassword = request.getParameter("confirmPassword");
	
		
		String msg ="";
		if(userPassword == null || userPassword.equals("")||
			confirmPassword ==  null || confirmPassword.equals("")){
				msg ="비밀번호를 입력해주세요";
				session.setAttribute("msg", msg);
				return "errorPageRegister";
			}else if(!userPassword.equals(confirmPassword)) {
				msg = "비밀번호가 일치하지 않습니다.";
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
//	회원가입 페이지-mybatis 사용 => 500 에러
	@RequestMapping("/registerOK")
	public String registerOK(HttpServletRequest request, Model model,UserInfoVO userInfoVO) {
		System.out.println("컨트롤러의 registerOK() 메소드 실행");
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
			msg = "비밀번호가 일치하지 않습니다.";
			return "errorPage";
		}

	}
*/
//	로그인 완료시 표현 할 페이지-DBCP 방식사용
	@RequestMapping("/loginOK")
	public String loginOK(HttpServletRequest request,Model model) {
		System.out.println("컨트롤러의 loginOK() 메소드 실행");
		System.out.println(request);
		
//		넘어가는 값 확인
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
			msg = "패스워드를 잘못 입력하셨습니다.";
			session.setAttribute("msg",msg);
			return "errorPage";
		} else if(loginUser == ID_NOT_EXIST) {
			msg = "존재하지 않는 아이디입니다.";
			session.setAttribute("msg",msg);
			return "errorPage";
		} else {
			msg = "시스템(SQL)에 문제가 있습니다.";
			session.setAttribute("msg",msg);
			return "errorPage";
		}
		
	}
	
// 	로그아웃시에 돌아갈 페이지
	@RequestMapping("/logout")
	public String logout(HttpServletRequest request,Model model) {
		HttpSession session = request.getSession();
		session.invalidate();
		System.out.println("컨트롤러의 logout() 메소드 실행");	
			return "redirect:/list";
	}	

//	사용자 페이지	
	@RequestMapping("/mypageView")
	public String mypageView(HttpServletRequest request,Model model) {
		System.out.println("컨트롤러의 mypageView() 메소드 실행");
		return "mypageView";
	}

//	에러페이지	
	@RequestMapping("/errorPage")
	public String errorPage(HttpServletRequest request,Model model) {
		System.out.println("컨트롤러의 errorPage() 메소드 실행");
		return "errorPage";
	}
	
//	비밀번호 변경 페이지
	@RequestMapping(value = "/updatePassword", method = {RequestMethod.GET, RequestMethod.POST})
	public String updatePassword(Locale locale, Model model) {
		System.out.println("컨트롤러의 updatePassword() 메소드 실행");
		return "changePasswordForm";
	}
	
	@RequestMapping("/TripOfLife_Project/ChangePassword")
	public void ChangePassword(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
			System.out.println("ChangePassword 서블릿의 actionDo() 메소드 실행");
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
	
// 컨텐츠 DB 시작
	
	@RequestMapping("/content")
	public String content(HttpServletRequest request,Model model) {
		System.out.println("컨트롤러의 content() 메소드 실행");
		return "content";
	}

//	컨텐츠 입력-DBCP template 방식 사용
	@RequestMapping("/contentOK")
	public String contentOK(HttpServletRequest request,Model model) {
		System.out.println("컨트롤러의 contentOK() 메소드 실행 - Model 인터페이스 객체 사용");
		
		model.addAttribute("request",request);
		
//		System.out.println(request); - 넘어오는 값
		System.out.println("model 값:"+model);
		
		AbstractApplicationContext ctx = new GenericXmlApplicationContext("classpath:applicationCTX.xml");
		TemplateService_content serviceContent = ctx.getBean("ContentInsert",ContentInsertService.class);
		serviceContent.execute(model);
		
		return "redirect:/list";
	}

//	한 페이지에 표시할 글 목록	
	@RequestMapping("/list")
	public String list(HttpServletRequest request,Model model) {
		System.out.println("컨트롤러의 list() 메소드 실행");
		
		model.addAttribute("request",request);
		
		AbstractApplicationContext ctx = new GenericXmlApplicationContext("classpath:applicationCTX.xml");
		TemplateService_content serviceContent = ctx.getBean("ContentSelect",ContentSelectService.class);
		serviceContent.execute(model);
		
		return "list";
	}
	
//	조회수 증가메소드
	@RequestMapping("/increment")
	public String increment(HttpServletRequest request,Model model) {
		System.out.println("컨트롤러의 increment 메소드 실행");
		model.addAttribute("request",request);
		
		AbstractApplicationContext ctx = new GenericXmlApplicationContext("classpath:applicationCTX.xml");
		TemplateService_content serviceContent = ctx.getBean("Increment",IncrementService.class);
		serviceContent.execute(model);
		
		model.addAttribute("idx",request.getParameter("idx"));
		model.addAttribute("currentPage",request.getParameter("currentPage"));
		
		return "redirect:/contentView";
		
	}
// 	게시물 표시할 메소드
	@RequestMapping("/contentView")
	public String contentView(HttpServletRequest request,Model model) {
		System.out.println("컨트롤러의 list() 메소드 실행");
		
		model.addAttribute("request",request);
		
		AbstractApplicationContext ctx = new GenericXmlApplicationContext("classpath:applicationCTX.xml");
		TemplateService_content serviceContent = ctx.getBean("ContentView",ContentViewService.class);
		serviceContent.execute(model);
		
		return "contentView";
		
	}
	
// ajax search 기능 구현 메소드 => 수정보완해야함
	@RequestMapping("/HomeController")
	protected void search(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
//		System.out.println("AjaxSearch() 함수가 GET 방식으로 요청됨");
		
				request.setCharacterEncoding("UTF-8");
				response.setContentType("text/html; charset=utf-8");// 보낼 때 한글깨짐 방지
				String subject = request.getParameter("subject").trim();
				// System.out.println(subject);
				response.getWriter().write(getJSON(subject));
	}
private String getJSON(String subject) {
	System.out.println("AjaxSearch 서블릿의 getJSON() 메소드");
	if(subject == null) {
		subject="";
	}
	
	ArrayList<ContentVO> list = new ContentDAO().search(subject);
	System.out.println(list);
	StringBuffer result = new StringBuffer();
	result.append("{\"result\": [");
	
	for(ContentVO vo : list) {
		result.append("[{\"value\": \"" + vo.getIdx() + "\"},");	
		result.append("{\"value\": \"" + vo.getSubject().trim() + "\"},");	
		result.append("{\"value\": \"" + vo.getUserID().trim() + "\"},");	
		result.append("{\"value\": \"" + vo.getHit() + "\"},");	
		result.append("{\"value\": \"" + vo.getWriteDate() + "\"},");	
		result.append("{\"value\": \"" + vo.getContent() + "\"}],");	
	}
	result.append("]}");
	//System.out.println(result);
	
	return result.toString();
}
	


//	delete 권한체크 창	
	@RequestMapping("/DeleteCheck")
	public String DeleteCheck(HttpServletRequest request,Model model) {
		System.out.println("컨트롤러의 DeleteCheck() 메소드 실행");
		return "DeleteCheck";
	}
//	delete 명령어	
	@RequestMapping("/DeleteConfirm")
	public String DeleteConfirmCheck(HttpServletRequest request, Model model) {
		System.out.println("컨트롤러의 DeleteConfirm() 메소드 실행");
	
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
			System.out.println("model 데이터:"+model);
			
			TemplateService_content serviceContent = ctx.getBean("Delete",DeleteContentService.class);
			serviceContent.execute(model);
			
			return "redirect:list";
		}else if(delete == reject) {
			String msg = "정보가 일치하지 않습니다.";
			session.setAttribute("msg", msg);
			return "errorPageDeleteContent";	
		}else {
			String msg = "시스템 오류";
			session.setAttribute("msg", msg);
			return "errorPageDeleteContent";
		}

	}
	
//	글 수정 창으로 이동(contentViewUpdate 창으로 이동)
	@RequestMapping("/contentViewUpdate")
	public String contentViewUpdate(HttpServletRequest request,Model model) {
		System.out.println("컨트롤러의 contentViewUpdate() 메소드 실행");
		model.addAttribute("request",request);
		
		AbstractApplicationContext ctx = new GenericXmlApplicationContext("classpath:applicationCTX.xml");
		TemplateService_content serviceContent = ctx.getBean("ContentView",ContentViewService.class);
		serviceContent.execute(model);
		
		return "contentViewUpdate";
		
	}
//	글 수정 권한 체크
	@RequestMapping("/contentViewUpdateCheck")
	public String contentViewUpdateCheck(HttpServletRequest request,Model model) {
		System.out.println("컨트롤러의 contentViewUpdateCheck() 메소드 실행");
		
		return "contentViewUpdateCheck";
		
	}
	
//	글 수정 작업 진행
	@RequestMapping("/UpdateConfirm")
	public String UpdateConfirm(HttpServletRequest request,Model model) {
		System.out.println("컨트롤러의 UpdateConfirm() 메소드 실행");
		
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
			System.out.println("model 데이터:"+model);
			
			TemplateService_content serviceContent = ctx.getBean("Update",UpdateContentService.class);
			serviceContent.execute(model);
			
			return "redirect:list";
		}else if(update == reject) {
			String msg = "정보가 일치하지 않습니다.";
			session.setAttribute("msg", msg);
			return "errorPageDeleteContent";	
		}else {
			String msg = "시스템 오류";
			session.setAttribute("msg", msg);
			return "errorPageDeleteContent";
		}
		
	}
	
//	댓글입력 기능
	@RequestMapping("/InsertComment")
	public void InsertComment(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException  {
		System.out.println("InsertComment 클래스의 actionDo() 메소드");
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=utf-8");

		int idx = Integer.parseInt(request.getParameter("idx").trim());
		String userID = request.getParameter("userID");
		String userComment = request.getParameter("userComment").trim();
		System.out.println("서블릿 idx: " + idx);
		System.out.println("서블릿 userID: " + userID);
		System.out.println("서블릿 userComment: " + userComment);
		
		
		CommentVO cmo = new CommentVO(idx, userID, userComment);
		System.out.println(cmo);
		int result = new CommentDAO().insertComment(cmo);
		response.getWriter().write(String.valueOf(result));
		response.getWriter().write(result + "");
	
	}

//	댓글 리스트 보여주는 기능
	@RequestMapping("/CommentList")
	public void CommentList(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException  {
		System.out.println("CommentList 클래스의 actionDo() 메소드");
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=utf-8");
		
		int idx = Integer.parseInt(request.getParameter("idx").trim());
		String userID = request.getParameter("userID").trim();
		String userComment = request.getParameter("userComment").trim();
		System.out.println("CommentList 클래스 idx: " + idx);
		response.getWriter().write(getJSON(idx));
	}
		
		private String getJSON(int idx) {
		System.out.println("CommentList 클래스의 getJSON 메소드");
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
		
		
//	찜(Reservation) 기능 실행
		@RequestMapping("/Reservation")
		public void Reservation(HttpServletRequest request,HttpServletResponse response)throws ServletException, IOException{
			System.out.println("컨트롤러의 Reservation() 메소드 실행");
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
// myPage 기능

//myPage 찜 리스트(reservation) 표출기능		
	@RequestMapping("/myContentResGo")
	public String myContentResGo(HttpServletRequest request, Model model) {
		System.out.println("컨트롤러의 myContentResGo() 메소드 실행");
		
		model.addAttribute("request",request);
		
		AbstractApplicationContext ctx = new GenericXmlApplicationContext("classpath:applicationCTX.xml");
		TemplateService_reservation serviceReservation = ctx.getBean("reservationContent", ReservationContetnService.class);
		serviceReservation.execute(model);
		
		return "reservationView";
		
	}
	
 //myPage 내 콘텐츠 리스트(reservation) 표출기능
	@RequestMapping("/myContentGo")
	public String myContentGo(HttpServletRequest request, Model model) {
		System.out.println("컨트롤러의 myContentGo() 메소드 실행");
//		String userID = request.getParameter("userID");
//		System.out.println(userID);
		
		model.addAttribute("request",request);
		AbstractApplicationContext ctx = new GenericXmlApplicationContext("classpath:applicationCTX.xml");
		TemplateService_content serviceContent = ctx.getBean("MyContent",MyContentSelectService.class);
		serviceContent.execute(model);
		
		return "myContentView";
	}
	 
	
	
}

/*
// delete 권한체크	
	@RequestMapping("/DeleteConfirm")
	public String DeleteConfirmCheck(HttpServletRequest request, Model model) {
		System.out.println("컨트롤러의 DeleteConfirm() 메소드 실행");
//		String userID = request.getParameter("userID");  값 체크
//		String userPassword = request.getParameter("userPassword"); 값 체크
//		System.out.println(userID); 값 체크
//		System.out.println(userPassword); 값 체크

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
		UserInfoDAO dao = new UserInfoDAO();
		int delete = dao.DeleteConfirm(userID, userPassword);
		System.out.println(delete);
		if(delete == allow) {
			
			return "redirect:list";
		}else if(delete == reject) {
			String msg = "비밀번호가 일치하지 않습니다.";
			session.setAttribute("msg", msg);
			return "errorPage";	
		}else {
			String msg = "시스템 오류";
			session.setAttribute("msg", msg);
			return "errorPage";
		}

	}
	
//	delete 기능
	@RequestMapping("/delete")
	public String delete(HttpServletRequest request, Model model) {
		System.out.println("컨트롤러의 delete() 메소드 실행");
		
		model.addAttribute("request" ,request);
		System.out.println("request:"+request);
		System.out.println("model 데이터:"+model);
		
		AbstractApplicationContext ctx = new GenericXmlApplicationContext("classpath:applicationCTX.xml");
		TemplateService_content serviceContent = ctx.getBean("Delete",DeleteContentService.class);
		serviceContent.execute(model);
		
		return "redirect:list";	
	}
*/


	


/*	
	@RequestMapping("/delete")
	public String delete(HttpServletRequest request, Model model) {
		System.out.println("컨트롤러의 delete() 메소드 실행");
		
		model.addAttribute("request" ,request);
		System.out.println("request:"+request);
		System.out.println("model 데이터:"+model);
		
		AbstractApplicationContext ctx = new GenericXmlApplicationContext("classpath:applicationCTX.xml");
		TemplateService_content serviceContent = ctx.getBean("Delete",DeleteContentService.class);
		serviceContent.execute(model);
		
		return "redirect:list";	
	}
*/	
	
	
/*	
	@RequestMapping("/increment")
	public String contentView(HttpServletRequest request,Model model) {
		System.out.println("컨트롤러의 list() 메소드 실행");
		
		model.addAttribute("request",request);
		
		AbstractApplicationContext ctx = new GenericXmlApplicationContext("classpath:applicationCTX.xml");
		TemplateService_content serviceContent = ctx.getBean("contentView",ContentViewService.class);
		serviceContent.execute(model);
		
		return "contentView";
		
	}
*/	
/*
//	mybatis 사용-데이터가 안 넘어옴
	@RequestMapping("/list")
	public String list(HttpServletRequest request, Model model) {
		System.out.println("컨트롤러의 list() 메소드 실행");
		
		MybatisDAO mapper = sqlSession.getMapper(MybatisDAO.class);
		
		int pagesize=10;
		int currentPage = 1;
		try {
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		}catch(Exception e) {}
		int totalCount = mapper.selectContentCount();
		
		AbstractApplicationContext ctx = new GenericXmlApplicationContext("classpath:applicationCTX.xml");
		ContentList contentList = ctx.getBean("ContentList",ContentList.class);
		contentList.initContentList(pagesize, totalCount, currentPage);
		
		HashMap<String, Integer> hmap = new HashMap<String, Integer>();
		hmap.put("startNo", contentList.getStartNo());
		hmap.put("endNo", contentList.getEndNo());
		contentList.setList(mapper.selectContentList(hmap));
		
		model.addAttribute("ContentList",contentList);
		
		return "/list";
		
	}
	
//	mybatis 사용-데이터가 안 넘어옴
	@RequestMapping("/increment")
	public String increment(HttpServletRequest request,Model model) {
		System.out.println("컨트롤러의 increment() 메소드 실행");
		
		MybatisDAO mapper = sqlSession.getMapper(MybatisDAO.class);
		int idx = Integer.parseInt(request.getParameter("idx"));
		mapper.contentIncrement(idx);
		
		model.addAttribute("idx",request.getParameter("idx"));
		model.addAttribute("currentPage",request.getParameter("currentPage"));
		return "redirect:/contentView";
	}
*/	
	
/* 	트랜잭션 오류발생 => mybatis 버전문제도 아님.업데이트 해봄
	@RequestMapping("/contentOK")
	public String contentOK(HttpServletRequest request,Model model,ContentVO contentVO) {
		System.out.println("컨트롤러의 contentOK() 메소드 실행 - 커맨드 객체 사용");
		MybatisDAO mapper = sqlSession.getMapper(MybatisDAO.class);
		mapper.contentInsert(contentVO);
		System.out.println("contnetVO");
		return "redirect:/";
		
	}
*/	
	
	
	
	
	
	
/*
	@RequestMapping("/list")
	public String list(HttpServletRequest request, Model model,ContentVO contentVO) {
		System.out.println("컨트롤러의 list() 메소드 실행");
		MybatisDAO mapper = sqlSession.getMapper(MybatisDAO.class);
		
		int pageSize = 10;
		int currentPage = 1;
		try {
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		} catch (Exception e) { }
		int totalCount = mapper.selectContentCount();
		AbstractApplicationContext ctx = new GenericXmlApplicationContext("classpath:applicationCTX.xml");
		ContentList contentList = ctx.getBean("contentList", ContentList.class);
		contentList.initContentList(pageSize, totalCount, currentPage);
		
		HashMap<String, Integer> hmap = new HashMap<String, Integer>();
		hmap.put("startNo", contentList.getStartNo());
		hmap.put("endNo", contentList.getEndNo());
		contentList.setList(mapper.selectList(hmap));

		model.addAttribute("contentList", contentList);
		
		return "list";
	
	}
	
	
	
	@RequestMapping(value = "content", method = RequestMethod.GET)
	public String content(Locale locale, Model model) {
		System.out.println("컨트롤러의 content() 메소드 실행");
		return "content";
	}

	
	
	@RequestMapping("ContentOK")
	public String ContentOK(HttpServletRequest request,Model model,ContentVO contentVO) {
		System.out.println("컨트롤러의 ContentOK() 메소드 실행");
		MybatisDAO mapper = sqlSession.getMapper(MybatisDAO.class);
		mapper.contentInsert(contentVO);
		
		return "redirect:list";
	}
	
	
	*/
	
	

