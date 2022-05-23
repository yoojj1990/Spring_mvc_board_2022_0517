package com.yjj.MVCBoard.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.yjj.MVCBoard.dto.BDto;

public class BDao {

	
	DataSource dataSource;
	

	public BDao() {
		super();
		// TODO Auto-generated constructor stub
		
		
		try {
			Context context = new InitialContext();
			dataSource = (DataSource) context.lookup("java:comp/env/jdbc/Oracle11g");
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public ArrayList<BDto> list() {
		
		ArrayList<BDto> dtos = new ArrayList<BDto>();
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			
			conn = dataSource.getConnection();
			String query = "select * from mvc_board order by bgroup desc, bstep asc";
			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				
				int bId = rs.getInt("bid");
				String bName = rs.getString("bname");
				String bTitle = rs.getString("btitle");
				String bContent = rs.getString("bcontent");
				Timestamp bDate = rs.getTimestamp("bdate");
				int bHit = rs.getInt("bhit");
				int bGroup = rs.getInt("bgroup");
				int bStep = rs.getInt("bstep");
				int bIndent = rs.getInt("bindent");
				
				BDto dto = new BDto(bId, bName, bTitle, bContent, bDate, bHit, bGroup, bStep, bIndent);
				dtos.add(dto);

			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs != null) {
					rs.close();
				}
				if(pstmt != null) {
					pstmt.close();
				}
				if(conn != null) {
					conn.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return dtos; 
	}
	
	
	
	public void write(String bname, String btitle, String bcontent) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		
		try {
			
			conn = dataSource.getConnection();
			String query = "INSERT INTO mvc_board(bid, bname, btitle, bcontent, bhit, bgroup, bstep, bindent) VALUES (MVC_BOARD_SEQ.nextval, ?, ?, ?, 0, MVC_BOARD_SEQ.currval, 0, 0)";
			pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, bname);
			pstmt.setString(2, btitle);
			pstmt.setString(3, bcontent);
			
			pstmt.executeUpdate(); // 데이터 삽입에 성공하면 1반환

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				
				if(pstmt != null) {
					pstmt.close();
				}
				if(conn != null) {
					conn.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}
	
	
	
	
	public BDto content_view(String cid) {
		
		uphit(cid);// 조회수 계산 함수
		
		BDto dto = null;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			
			conn = dataSource.getConnection();
			String query = "select * from mvc_board where bid=?";
			pstmt = conn.prepareStatement(query);
			
			//pstmt.setInt(1, Integer.parseInt(cid)); // bid 필드값이 number인경우
			pstmt.setString(1, cid);
			
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				
				int bId = rs.getInt("bid");
				String bName = rs.getString("bname");
				String bTitle = rs.getString("btitle");
				String bContent = rs.getString("bcontent");
				Timestamp bDate = rs.getTimestamp("bdate");
				int bHit = rs.getInt("bhit");
				int bGroup = rs.getInt("bgroup");
				int bStep = rs.getInt("bstep");
				int bIndent = rs.getInt("bindent");
				
				dto = new BDto(bId, bName, bTitle, bContent, bDate, bHit, bGroup, bStep, bIndent);
				

			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs != null) {
					rs.close();
				}
				if(pstmt != null) {
					pstmt.close();
				}
				if(conn != null) {
					conn.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return dto;
	}
	
	
	public void modify(String bid, String bname, String btitle, String bcontent) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		
		try {
			
			conn = dataSource.getConnection();
			String query = "UPDATE mvc_board SET bname=?, btitle=?, bcontent=? WHERE bid=?";
			pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, bname);
			pstmt.setString(2, btitle);
			pstmt.setString(3, bcontent);
			pstmt.setString(4, bid);
			
			
			pstmt.executeUpdate(); // 데이터 삽입에 성공하면 1반환

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				
				if(pstmt != null) {
					pstmt.close();
				}
				if(conn != null) {
					conn.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}
	
	
	public void delete(String bid) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		
		try {
			
			conn = dataSource.getConnection();
			String query = "DELETE FROM  mvc_board WHERE bid=?";
			pstmt = conn.prepareStatement(query);
			
			
			pstmt.setString(1, bid);
			
			
			pstmt.executeUpdate(); // 데이터 삽입에 성공하면 1반환

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				
				if(pstmt != null) {
					pstmt.close();
				}
				if(conn != null) {
					conn.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}
	
	
	
	public void uphit(String bid) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		
		try {
			
			conn = dataSource.getConnection();
			String query = "UPDATE mvc_board SET bhit=bhit+1 WHERE bid=?";
			pstmt = conn.prepareStatement(query);

			pstmt.setString(1, bid);
			
			
			pstmt.executeUpdate(); // 데이터 삽입에 성공하면 1반환

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				
				if(pstmt != null) {
					pstmt.close();
				}
				if(conn != null) {
					conn.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
