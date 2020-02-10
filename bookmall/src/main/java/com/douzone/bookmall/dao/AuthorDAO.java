package com.douzone.bookmall.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.douzone.bookmall.vo.AuthorVO;

public class AuthorDAO {

	
	
	public List<AuthorVO> findAll(){
		List<AuthorVO> result =new ArrayList<AuthorVO>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs=null;
		
		try {
			conn=getConnection();
			String sql= "select no,name from author";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			//5 결과 가져오기 
			while(rs.next()) {
				long no= rs.getLong(1);
				String name = rs.getString(2);
				
				AuthorVO vo=new AuthorVO();
				
				vo.setNo(no);
				vo.setName(name);
				
				result.add(vo);	
			}
		
		}catch (SQLException e) {
			System.out.println("ERROR:"+e);
		}finally {
			//6.자원정리
			try {
				if(rs != null) {
					rs.close();
				}			
				if(pstmt !=null) {
					pstmt.close();
				}
				if(conn !=null) {
					conn.close();
				}
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
			return result;
	}
	
	public boolean insert(AuthorVO authorVO) {
		
		boolean result = false;
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn=getConnection();
			
			String sql= "insert into author values(null,?)";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, authorVO.getName());
		
			int count = pstmt.executeUpdate();
			
			
			result = count ==1;
			
		
		} catch (SQLException e) {
			System.out.println("ERROR:"+e);
		}finally {
			//6.자원정리
			try {
				if(pstmt !=null) {
					pstmt.close();
				}
				if(conn !=null) {
					conn.close();
				}
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		return result;
	}
	private Connection getConnection() throws SQLException{
		
		Connection conn = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");

			String url="jdbc:mysql://127.0.0.1:3306/webdb";
			conn=DriverManager.getConnection(url,"webdb","webdb");
		}catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패:"+e);
		}
		
		return conn;
	}
	
	
}
