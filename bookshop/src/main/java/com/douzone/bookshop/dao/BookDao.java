package com.douzone.bookshop.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.douzone.bookshop.vo.BookVo;
	public class BookDao {
	public boolean insert(BookVo vo) {
			
			boolean result = false;
			Connection conn = null;
			PreparedStatement pstmt = null;
			
			try {
				conn = getConnection();
				
				String sql = "insert into book values(null,?,?,?)";
				pstmt = conn.prepareStatement(sql);
				
				pstmt.setLong(1, vo.getPrice());
				pstmt.setLong(2, vo.getCategoryNo());
				pstmt.setString(3, vo.getName());
			
				
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
	public boolean update(BookVo vo) {
		
		boolean result = false;
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = getConnection();
			
			String sql = "update book set name = ? where no = ?";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, vo.getName());
			pstmt.setLong(2, vo.getNo());
			
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

	public boolean delete(long no) {
		boolean result = false;
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = getConnection();
			
			String sql = "delete from book where no= ?";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setLong(1, no);
			
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

	
	public List<BookVo> findAll(){
		List<BookVo> result =new ArrayList<BookVo>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs=null;
		
		try {
			conn=getConnection();
			String sql= "select name,price,b.type from book a, category b where a.category_no=b.no";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			//5 결과 가져오기 
			while(rs.next()) {
				String name=rs.getString(1);
				long price = rs.getLong(2);
				String categoryName = rs.getString(3);
				BookVo vo=new BookVo();
				
				
				vo.setName(name);
				vo.setPrice(price);
				vo.setCategoryName(categoryName);
				
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
