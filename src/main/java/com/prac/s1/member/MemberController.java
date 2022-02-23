package com.prac.s1.member;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
@Controller
@RequestMapping(value="/member/*")
public class MemberController {
	
	@Autowired
	private MemberService memberService;
	
	//mypage
	@RequestMapping(value="mypage",method =RequestMethod.GET)
	public ModelAndView mypage(HttpSession session) throws Exception{
		ModelAndView mv = new ModelAndView();
		MemberDTO memberDTO= (MemberDTO)session.getAttribute("member");//66번줄에 MEMBERDTO로 넣어서 부모를 꺼낼때 MemberDTO를 꺼내야해서 형변환해야함
		memberDTO =memberService.mypage(memberDTO);
		mv.addObject("dto",memberDTO);
		mv.setViewName("member/mypage");
		return mv;
		
	}
	//logout
	@RequestMapping(value="logout",method=RequestMethod.GET)
	public String logout(HttpSession session)throws Exception{
		session.invalidate();
		return "redirect:../";
	}
	   @RequestMapping(value="login", method=RequestMethod.POST)
	   public String login(HttpSession session, MemberDTO memberDTO, String remember, HttpServletResponse response) throws Exception{      //내장객체 리퀘스트, 파라미터와 동일한명, 객체

	      //model.addAttribute("member",memberDTO);
	      System.out.println("Remember : "+remember);
	   
	      if(remember!=null && remember.equals("1")) {
	         //쿠키생성
	         Cookie cookie = new Cookie("remember", memberDTO.getId());
	         //cookie.setPath("/");
	         //cookie.setMaxAge(10);
	         //응답
	         response.addCookie(cookie);
	      }else {
	    	  Cookie cookie = new Cookie("remember", "");
	    	  cookie.setMaxAge(0);//쿠키 삭제
	    	  response.addCookie(cookie);
	      }
	      
	      memberDTO = memberService.login(memberDTO);//파라미터로 넘어올때 까진 NULL 이 아닌데 여기서 객체를 만들어주기때문에 NULL로 된다
	                                       //그래서 22번줄에서 여기로 넘어와야함      
	      
	      //login 실패 -> 다시 로그인창
	      String path = "redirect:./login";//get 방식
	      
	      //login 성공 -> index       멤버dto가 널이 아니면 로그인성공
	      if (memberDTO != null) {
	    	 session.setAttribute("member", memberDTO);
	         path="redirect:../"; // index로 가려면 한단께올라가면됨
	      }
	      return path;
	   }
	   
	   //FORM으로 이동
	   @RequestMapping(value="login", method=RequestMethod.GET)
	   public void login(@CookieValue(value="remember", defaultValue="",required=false)String rememberId) throws Exception{
//	      model.addAttribute("rememberId", rememberId); 생략해도 jsp 에서 ${cookie.remember.value} 로 사용 가능
	   } 

	
	
	@RequestMapping(value="join" ,method=RequestMethod.POST)
	public String join(MemberDTO memberDTO)throws Exception{
	 int result =	memberService.join(memberDTO);
	 return "redirect:../";
	}
	@RequestMapping(value="join",method=RequestMethod.GET)
	public void join() throws Exception{
		
	}

	
	
}