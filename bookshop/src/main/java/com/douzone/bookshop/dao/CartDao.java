package com.douzone.bookshop.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.douzone.bookshop.vo.CartVo;

public class CartDao {
		
public boolean insert(CartVo vo) {
		
		boolean result = false;
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = getConnection();
			
			String sql = "insert into cart values(?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setLong(1, vo.getAmount());
			pstmt.setLong(2, vo.getPrice());
			pstmt.setLong(3, vo.getMemberNo());
			pstmt.setLong(4, vo.getBookNo());
			
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
@SuppressWarnings("resource")
public boolean update(CartVo vo) {
	boolean result = false;
	
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs=null;
	long amount=0;
	
	try {
		conn = getConnection();
		
		String sql = "select sum(amount) from cart where member_no=? and book_no=?";
		pstmt = conn.prepareStatement(sql);
		
		pstmt.setLong(1, vo.getMemberNo());
		pstmt.setLong(2, vo.getBookNo());
		rs = pstmt.executeQuery();
		while(rs.next()){
			amount = rs.getLong(1);
		}
		System.out.println(amount);
		
		
		sql ="update cart set amount=? where member_no=? and book_no=?";
		pstmt = conn.prepareStatement(sql);
		pstmt.setLong(1, vo.getAmount()+amount);
		pstmt.setLong(2, vo.getMemberNo());
		pstmt.setLong(3, vo.getBookNo());
		int count = pstmt.executeUpdate();
		
		
		result= count ==1;
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

public void check(CartVo vo) {
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs=null;
	
	try {
		conn=getConnection();
		String sql = "select count(amount) from cart where member_no=? and book_no=?";
		long count =0;
		pstmt = conn.prepareStatement(sql);
		
		pstmt.setLong(1, vo.getMemberNo());
		pstmt.setLong(2, vo.getBookNo());
		
		rs = pstmt.executeQuery();
		
		while(rs.next()){
			count = rs.getLong(1);
		}
		
		if(count==0)
			insert(vo);
		else
			update(vo);
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
}

public List<CartVo> findUserCart(long memberNo){
	List<CartVo> result =new ArrayList<CartVo>();
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs=null;
	
	try {
		conn=getConnection();
		String sql= "select b.name,a.amount,b.price from cart a, book b where a.book_no=b.no and a.member_no=?";
		pstmt = conn.prepareStatement(sql);
		
		pstmt.setLong(1,memberNo);

		rs = pstmt.executeQuery();
		//5 결과 가져오기 
		while(rs.next()) {
			
			
			String name = rs.getString(1);
			long amount = rs.getLong(2);
			long price = rs.getLong(3);
			
			CartVo vo=new CartVo();
			
			vo.setName(name);
			vo.setAmount(amount);
			vo.setPrice(price);
			
			
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


public List<CartVo> findAll(){
	List<CartVo> result =new ArrayList<CartVo>();
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs=null;
	
	try {
		conn=getConnection();
		String sql= "select b.name,a.amount,b.price from cart a, book b where a.book_no=b.no ";
		pstmt = conn.prepareStatement(sql);

		rs = pstmt.executeQuery();
		//5 결과 가져오기 
		while(rs.next()) {
			
			
			String name = rs.getString(1);
			long amount = rs.getLong(2);
			long price = rs.getLong(3);
			
			CartVo vo=new CartVo();
			
			vo.setName(name);
			vo.setAmount(amount);
			vo.setPrice(price);
			
			
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
