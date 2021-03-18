package com.project.recoder.common.service;

import static com.project.recoder.common.JDBCTemplate.*;

import java.sql.Connection;
import java.util.Map;

import com.project.recoder.common.dao.commonDAO;

public class CommonService {

	commonDAO dao = new commonDAO();
	
	/** id 중복검사 service
	 * @param id
	 * @return result
	 * @throws Exception
	 */
	public int idDupCheck(String id) throws Exception{
		Connection conn = getConnection();
		
		int result = dao.idDupCheck(conn, id);
		
		close(conn);
		
		return result;
	}

	/** 닉네임 중복검사 service
	 * @param nickname
	 * @return result
	 * @throws Exception
	 */
	public int nickDupCheck(String nickname) throws Exception {
		Connection conn = getConnection();
		
		int result = dao.nickDupCheck(conn, nickname);
		
		close(conn);
		
		return result;
	}

	/** 아이디 찾기 service
	 * @param map
	 * @return result
	 * @throws Exception
	 */
	public String searchId(Map<String, Object> map) throws Exception{
		Connection conn = getConnection();
		
		String memId = dao.searchId(conn, map);
		
		close(conn);
		
		return memId;
	}

	/** 비밀번호 찾기 service
	 * @param map
	 * @return result
	 * @throws Exception
	 */
	public int searchPw(Map<String, Object> map) throws Exception {
		Connection conn = getConnection();
		
		int result = dao.searchPW(conn, map);
		
		close(conn);
		
		return result;
	}

	/** 비밀번호 재설정 service
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public int setPw(Map<String, Object> map) throws Exception {
		Connection conn = getConnection();
		
		int result = dao.setPw(conn, map);
		
		if(result > 0) commit(conn);
		else 			rollback(conn);
		
		close(conn);
		
		return result;
	}
	
	public String random() {
		String result = "";
		
		int random = 0;
		for(int i=0; i<6; i++) {
			
			random = (int)(Math.random()*10 +1); //99999
			result += random;
		}
		//System.out.println(random);
		
		return result;
		
	}
}
