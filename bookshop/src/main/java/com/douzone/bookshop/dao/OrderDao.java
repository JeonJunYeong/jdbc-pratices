package com.douzone.bookshop.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.douzone.bookshop.vo.OrderVo;

public class OrderDao {

	public boolean insert(OrderVo vo) {
		
		boolean result = false;
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = getConnection();
			
			String sql = "insert into orders values(null,?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setLong(1, vo.getPrice());
			pstmt.setString(2, vo.getAddr());
			pstmt.setString(3, getOrderNum());
			pstmt.setLong(4, vo.getMemberNo());
			
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
	
	public List<OrderVo> findAll(){
		List<OrderVo> result =new ArrayList<OrderVo>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs=null;
		
		try {
			conn=getConnection();
			String sql= "select b.name,b.mail,a.price,a.addr,a.order_num from orders a,member b where a.member_no = b.no";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			//5 결과 가져오기 
			while(rs.next()) {
				String name = rs.getString(1);
				String mail = rs.getString(2);
				long price = rs.getLong(3);
				String addr = rs.getString(4);
				String orderNo = rs.getString(5);
				OrderVo vo=new OrderVo();
				
				vo.setName(name+"/"+mail);
				vo.setPrice(price);
				vo.setAddr(addr);
				vo.setOrderNo(orderNo);
				
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
	
	public String getOrderNum() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs=null;
		Date today=new Date();
		SimpleDateFormat format1=new SimpleDateFormat("yyyyMMdd");
		 String orderNum="";
		try {
			conn=getConnection();
			String sql= "select order_num from orders where order_num like ? order by order_num desc";
			pstmt = conn.prepareStatement(sql);
			String date ="";
			pstmt.setString(1, format1.format(today)+"%");
			rs = pstmt.executeQuery();
			
			//5 결과 가져오기 
			while(rs.next()) {
				
				date= rs.getString(1);
			}
			
			if("".equals(date)) {
			
				
				orderNum=format1.format(today)+"-"+1;
				
			}else {

				String token[]=date.split("-");
				
				
				int now = Integer.parseInt(token[1])+1;
				
				orderNum=format1.format(today)+"-"+Integer.toString(now);
			
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
		return orderNum;
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
