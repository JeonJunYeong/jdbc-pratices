package com.douzone.bookshop.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.douzone.bookshop.vo.BookOrderVo;
import com.douzone.bookshop.vo.CartVo;
import com.douzone.bookshop.vo.MemberVo;

public class BookOrderDao {

public boolean insert(BookOrderVo vo) {
		
		boolean result = false;
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = getConnection();
			
			String sql = "insert into book_order values(?,?,?)";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setLong(1, vo.getAmount());
			pstmt.setLong(2, vo.getBookNo());
			pstmt.setLong(3, vo.getOrderNo());
			
			int count = pstmt.executeUpdate();
			result = count ==1;
			
			
		}catch (SQLException e) {
			System.out.println("ERROR:"+e);
		}finally {
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
public boolean update(BookOrderVo vo) {
	boolean result = false;
	Connection conn = null;
	PreparedStatement pstmt = null;
	
	try {
		conn = getConnection();
		
		String sql = "update book_order set amount = ? where order_no = ?";
		pstmt = conn.prepareStatement(sql);
		
		pstmt.setLong(1, vo.getAmount());
		pstmt.setLong(2, vo.getOrderNo());
		
		int count = pstmt.executeUpdate();
		result = count ==1;
		
		
	}catch (SQLException e) {
		System.out.println("ERROR:"+e);
	}finally {
		
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
public List<BookOrderVo> findAll(){
	List<BookOrderVo> result = new ArrayList<BookOrderVo>();
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs=null;
	
	try {
		conn=getConnection();
		String sql= "select b.order_no,a.no,a.name,b.amount from book a,book_order b where a.no=b.book_no";
		pstmt = conn.prepareStatement(sql);
		rs = pstmt.executeQuery();
		

		while(rs.next()) {
			
			long orderNo=rs.getLong(1);
			long bookNo=rs.getLong(2);
			String bookName=rs.getString(3);
			long bookAmount = rs.getLong(4);
			BookOrderVo vo = new BookOrderVo();
			
			vo.setOrderNo(orderNo);
			vo.setBookNo(bookNo);
			vo.setAmount(bookAmount);
			vo.setBookName(bookName);
			
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

public List<BookOrderVo> findOrderUser(long memberNo){
	List<BookOrderVo> result = new ArrayList<BookOrderVo>();
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs=null;
	
	try {
		conn=getConnection();
		String sql= "select a.no,a.name, b.amount from book a,book_order b,orders c where a.no=b.book_no and b.order_no= c.no and c.member_no = ?";
		pstmt = conn.prepareStatement(sql);
		
		pstmt.setLong(1, memberNo);
		
		rs = pstmt.executeQuery();
		

		while(rs.next()) {
			
			long bookNo=rs.getLong(1);
			String bookName =rs.getString(2);
			long amount = rs.getLong(3);
			
			BookOrderVo vo = new BookOrderVo();
			
			vo.setBookNo(bookNo);
			vo.setBookName(bookName);
			vo.setAmount(amount);
			
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
