package com.act.model;

import java.util.*;
import java.sql.*;

public class ActJDBCDAO implements ActDAO_interface {
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/activity?serverTimezone=Asia/Taipei";
	String userid = "root";
	String passwd = "523399";

	private static final String INSERT_STMT = 
		"INSERT INTO act_picture (act_no,act_pic_name,act_pic) VALUES (?, ?, ?)";
	private static final String GET_ALL_STMT = 
		"SELECT act_pic_no,act_no,act_pic_name,act_pic FROM act_picture order by act_pic_no";
	private static final String GET_ONE_STMT = 
		"SELECT act_pic_no,act_no,act_pic_name,act_pic FROM act_picture where act_pic_no = ?";
	private static final String DELETE = 
		"DELETE FROM act_picture where act_pic_no = ?";
	private static final String UPDATE = 
		"UPDATE act_picture set act_pic_name=?, act_pic=? where act_pic_no = ?";

	@Override
	public void insert(ActVO actVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, actVO.getActNo());
			pstmt.setString(2, actVO.getActPicName());
			pstmt.setBytes(3, actVO.getActPic());
			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}

	}

	@Override
	public void update(ActVO actVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);
			
			pstmt.setString(1, actVO.getActPicName());
			pstmt.setBytes(2, actVO.getActPic());
			pstmt.setInt(3, actVO.getActPicNo());

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}

	}

	@Override
	public void delete(Integer actPicNo) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, actPicNo);

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}

	}

	@Override
	public ActVO findByPrimaryKey(Integer actPicNo) {

		ActVO actVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, actPicNo);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				
				actVO = new ActVO();
				actVO.setActPicNo(rs.getInt("act_pic_No"));
				actVO.setActNo(rs.getInt("act_no"));
				actVO.setActPicName(rs.getString("act_pic_name"));
				actVO.setActPic(rs.getBytes("act_pic"));
				
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return actVO;
	}

	@Override
	public List<ActVO> getAll() {
		List<ActVO> list = new ArrayList<ActVO>();
		ActVO actVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO 也稱為 Domain objects
				actVO = new ActVO();
				
				actVO.setActPicNo(rs.getInt("act_pic_no"));
				actVO.setActNo(rs.getInt("act_no"));
				actVO.setActPicName(rs.getString("act_pic_name"));
				actVO.setActPic(rs.getBytes("act_pic"));
				
				list.add(actVO); // Store the row in the list
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return list;
	}

	public static void main(String[] args) {

		ActJDBCDAO dao = new ActJDBCDAO();

		// 新增
//		ActVO actVO1 = new ActVO();
//		actVO1.setActNo(106);
//		actVO1.setActPicName("地點6");		
//		dao.insert(actVO1);

		// 修改
//		ActVO actVO2 = new ActVO();
//		actVO2.setActPicNo(6);
//		actVO2.setActNo(106);
//		actVO2.setActPicName("茶壺山");		
//		dao.update(actVO2);

		// 刪除
//		dao.delete(9);

		// 查詢
//		ActVO actVO3 = dao.findByPrimaryKey(1);
//		System.out.print(actVO3.getActPicNo() + ",");
//		System.out.print(actVO3.getActNo() + ",");
//		System.out.print(actVO3.getActPicName());		
//		System.out.println("---------------------");

		// 查詢
		List<ActVO> list = dao.getAll();
		for (ActVO aAct : list) {
			System.out.print(aAct.getActPicNo() + ",");
			System.out.print(aAct.getActNo() + ",");
			System.out.print(aAct.getActPicName());			
			System.out.println();
		}
	}
}