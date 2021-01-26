package com.javaex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.javaex.dao.GuestDao;
import com.javaex.vo.GuestVo;

@Controller
@RequestMapping(value="/guestbook")
public class GuestbookController {
	
	//필드
	@Autowired			//자동적으로 객체를 만들어주고 사용할 수 있게 해주는 역할을 해줌.
	private GuestDao guestDao;
	//생성자
	//메소드 g/s
	
	//메소드 일반
	
	//리스트
	@RequestMapping(value="/list", method={RequestMethod.GET, RequestMethod.POST})
	public String guestbookList(Model model) {
		System.out.println("guestbook List");
		
		//dao를 통해 list를 가져온다.
		List<GuestVo> guestList = guestDao.ListAllGuest();
		
		//model을 통해 데이터 전달
		model.addAttribute("gList", guestList);
		
		return "guestList";
	}

	//등록  --> @ModelAttribute를 사용했을 경우
	@RequestMapping(value="/insert", method={RequestMethod.GET, RequestMethod.POST})
	public String guestbookInsert(@ModelAttribute GuestVo guestVo) {
		System.out.println("guestbook insert");

		//잘되는지 테스트
		//System.out.println(guestVo.toString());

		//dao --> guestInsert()
		guestDao.guestInsert(guestVo);
		
		return "redirect:/guestbook/list";
	}
	
	//삭제폼
	@RequestMapping(value="/guestDelete", method={RequestMethod.GET, RequestMethod.POST})
	public String guestDeleteForm() {
		System.out.println("guestbook DeleteForm");
		
		return "deleteForm";
	}

	//삭제(두번째 방법) --> @ModelAttribute를 사용했을 경우
	@RequestMapping(value="/delete", method={RequestMethod.GET, RequestMethod.POST})
	public String delete(@ModelAttribute GuestVo guestVo, Model model) {
		System.out.println("guestbook delete");
		
		//테스트해보자
		//System.out.println(guestVo.toString());
		
		//파라미터 -->리다이렉트 --> 요청 url 주소를 다시주고 다시시작해야함.
		//redirect:/guestbook/list?no=3
		//model --> 포워드  --> 내부 jsp 찾는것
		
		//dao --> guestDelete()
		int result = guestDao.guestDelete(guestVo);
		
		if (result == 1) {	//성공
			//리다이렉트
			return "redirect:/guestbook/list";
		} else {	//실패
			
			//deleteForm?no=guestVo.getNo()&result=0
			//리다이렉트 시켜줘야함..
			return "redirect:/guestbook/guestDelete?result=0&no=" + guestVo.getNo();

		}
	}
	
}
