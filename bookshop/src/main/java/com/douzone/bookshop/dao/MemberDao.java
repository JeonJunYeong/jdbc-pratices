package com.douzone.bookshop.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.douzone.bookshop.vo.MemberVo;

public class MemberDao {

	
	
	public boolean insert(MemberVo vo) {
		
		boolean result = false;
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = getConnection();
			
			String sql = "insert into member values(null,?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, vo.getName());
			pstmt.setString(2, vo.getPhone());
			pstmt.setString(3, vo.getMail());
			pstmt.setString(4, vo.getPassword());
			
			int count = pstmt.executeUpdate();
			
			result = count ==1;
			
			
		}catch (SQLException e) {
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
	
	public List<MemberVo> findAll(){
		List<MemberVo> result =new ArrayList<MemberVo>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs=null;
		
		try {
			conn=getConnection();
			String sql= "select no,name,phone,mail,password from member";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			//5 결과 가져오기 
			while(rs.next()) {
				
				String name = rs.getString(2);
				String phone = rs.getString(3);
				String mail = rs.getString(4);
				String password = rs.getString(5);
				MemberVo vo=new MemberVo();
				
				
				vo.setName(name);
				vo.setPhone(phone);
				vo.setMail(mail);
				vo.setPassword(password);
				
				
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
	
	public List<MemberVo> findMemberNo(){
		List<MemberVo> result =new ArrayList<MemberVo>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs=null;
		
		try {
			conn=getConnection();
			String sql= "select no,name from member";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			//5 결과 가져오기 
			while(rs.next()) {
				
				long no = rs.getLong(1);
				String name=rs.getString(2);
				
				MemberVo vo=new MemberVo();
				
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
	

	private Connection getConnection() throws SQLException{
		
		Connection conn = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");

			String url="jdbc:mysql://127.0.0.1:3306/bookshop";
			conn=DriverManager.getConnection(url,"bookshop","bookshop");
		}catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패:"+e);
		}
		
		return conn;
	}
	
	
	
	
	
	
	
}
