package com.javaex.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.GuestVo;

//DB나 파일같은 외부 I/O 작업을 처리함
@Repository
public class GuestDao {
	
	@Autowired
	private SqlSession sqlSession;
	
	//전체리스트 가져오기
	public List<GuestVo> ListAllGuest() {

		System.out.println("dao: guestList()");
		
		//sqlSession.selectList("이름")
		List<GuestVo> guestList = sqlSession.selectList("guestbook.selectList");
		
		return guestList;
	}
	
	//방명록 저장(insert)
	public void guestInsert(GuestVo guestVo) {
		System.out.println("dao: guestInsert");
		
		//sqlSession.insert("이름", 데이터);
		sqlSession.insert("guestbook.insert", guestVo);
	}
	
	//방명록 삭제(delete)
	public int guestDelete(GuestVo guestVo) {
		System.out.println("dao: guestDelete");
		
		//sqlSession.delete("이름", 데이터);
		int count = sqlSession.delete("guestbook.delete", guestVo);
		
		return count;
	}
	
	
}
