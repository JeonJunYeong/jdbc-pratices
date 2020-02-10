package com.douzone.bookmall.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.douzone.bookmall.vo.BookVO;

public class BookDAO {
	
		
	public BookVO find(Long no) {
		return null;
	}
	public void update(long no,String text) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs=null;
		
		try {
			conn=getConnection();
			String sql= "update book set state='대여중' where no=?";
			pstmt.setLong(1,no);
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
		
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
	public List<BookVO> findAll(){
		List<BookVO> result =new ArrayList<BookVO>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs=null;
		
		try {
			conn=getConnection();
			String sql= "select b.no, b.author_no,a.name, b.title , b.state from author a, book b where a.no=b.author_no";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			//5 결과 가져오기 
			while(rs.next()) {
				long no=rs.getLong(1);
				long author_no=rs.getLong(2);
				String name= rs.getString(3);
				String title = rs.getString(4);
				String state = rs.getString(5);
				
				BookVO vo=new BookVO();
				vo.setNo(no);
				vo.setAuthorNO(no);
				vo.setAuthorName(name);
				vo.setTitle(title);
				vo.setState(state);
				
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
	
		public boolean insert(BookVO bookVO) {
			
			boolean result = false;
			Connection conn = null;
			PreparedStatement pstmt = null;

			try {
				
				conn=getConnection();
				
				String sql= "insert into book values(null,?,'대여가능',?)";
				pstmt = conn.prepareStatement(sql);
				
				pstmt.setString(1,bookVO.getTitle());
				pstmt.setLong(2, bookVO.getAuthorNO());
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
		
		public Long findByName(String name) {
			
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs=null;
			long no = 0;
			try {
				conn=getConnection();
				String sql= "select no from author where name LIKE ?";
				pstmt = conn.prepareStatement(sql);
				
				pstmt.setString(1,name);
				
				rs = pstmt.executeQuery();
				
				//5 결과 가져오기 
				while(rs.next()) {
					 no= rs.getLong(1);
					System.out.println(no);
					return no;
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
			return no;

		}

}
