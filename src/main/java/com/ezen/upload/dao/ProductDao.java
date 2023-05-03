package com.ezen.upload.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.ezen.upload.dto.ProductVO;
import com.ezen.upload.util.DBMan;


public class ProductDao {


	private ProductDao() {
	}

	private static ProductDao itc = new ProductDao();

	public static ProductDao getInstance() {return itc;}

	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;

	public ArrayList<ProductVO> selectAll() {
		ArrayList<ProductVO> list = new ArrayList<>();
		String sql = "select * from bookproduct order by code desc";
		con = DBMan.getConnection();

		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				ProductVO pvo = new ProductVO();
				pvo.setCode(rs.getInt("code"));
				pvo.setName(rs.getString("name"));
				pvo.setPrice(rs.getInt("price"));
				pvo.setPictureurl(rs.getString("pictureurl"));
				pvo.setDescription(rs.getString("description"));
				list.add(pvo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBMan.close(con, pstmt, rs);
		}

		return list;
	}

	public ProductVO getProduct(int code) {

		ProductVO pvo=null;
		String sql = "select*from bookproduct where code=?";
		con = DBMan.getConnection();
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, code);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				pvo = new ProductVO();
				pvo.setCode(rs.getInt("code"));
				pvo.setName(rs.getString("name"));
				pvo.setPrice(rs.getInt("price"));
				pvo.setPictureurl(rs.getString("pictureurl"));
				pvo.setDescription(rs.getString("description"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBMan.close(con, pstmt, rs);
		}

		return pvo;
	}

	public void insertProduct(ProductVO pvo) {
		con = DBMan.getConnection();
		String sql = "insert into bookproduct(code,name,price,pictureurl,description) "
				+ "values(bookproduct_seq.nextVal,?,?,?,?)";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, pvo.getName());
			pstmt.setInt(2, pvo.getPrice());
			pstmt.setString(3, pvo.getPictureurl());
			pstmt.setString(4, pvo.getDescription());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBMan.close(con, pstmt, rs);
		}

	}

	public void updateProduct(ProductVO pvo) {
		con = DBMan.getConnection();
		String sql = "update bookproduct set name=?,price=?,pictureurl=?,description=? "
				+ " where code=?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, pvo.getName());
			pstmt.setInt(2, pvo.getPrice());
			pstmt.setString(3, pvo.getPictureurl());
			pstmt.setString(4, pvo.getDescription());
			pstmt.setInt(5, pvo.getCode());

			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBMan.close(con, pstmt, rs);
		}
	}

	public void deleteProduct(int code) {

		con = DBMan.getConnection();
		String sql = "delete from bookproduct where code=?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, code);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBMan.close(con, pstmt, rs);
		}

	}







}
