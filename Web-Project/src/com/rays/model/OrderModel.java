package com.rays.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.rays.bean.OrderBean;
import com.rays.exception.DuplicateRecordException;
import com.rays.util.JDBCDataSource;

public class OrderModel {
//	Generate primary key
	public int nextPk() throws Exception {
		int pk = 0;
		Connection comm = null;
		comm = JDBCDataSource.getConnection();
		PreparedStatement pstmt = comm.prepareStatement("select max(id) from orders");
		ResultSet rs = pstmt.executeQuery();
		while (rs.next()) {
			pk = rs.getInt(1);
		}
		comm.close();
		return pk + 1;
	}

//	insert record
	public void add(OrderBean bean) throws Exception {
		Connection comm = null;
		try {
			comm = JDBCDataSource.getConnection();
			comm.setAutoCommit(false);
			PreparedStatement pstmt = comm.prepareStatement("insert into orders values (?,?,?,?,?)");
			pstmt.setInt(1, nextPk());
			pstmt.setString(2, bean.getShopeName());
			pstmt.setString(3, bean.getProdectName());
			pstmt.setDouble(4, bean.getPrice());
			pstmt.setDate(5, new java.sql.Date(bean.getDop().getTime()));
			int i = pstmt.executeUpdate();
			comm.commit();
			System.out.println("Data inserted successfully: " + i);
		} catch (Exception e) {
			System.out.println("Transaction is rolled back");
			comm.rollback();
		} finally {
			comm.close();
		}
	}

//	delete record
	public void Delete(int id) throws Exception {
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn.prepareStatement("delete from orders where id = ?");
			pstmt.setInt(1, id);
			pstmt.executeUpdate();
			conn.commit();
			System.out.println("Data deleted successfully.");
		} catch (Exception e) {
			System.out.println("Transaction is rolled back");
			conn.rollback();
		} finally {
			conn.close();
		}
	}

//	update record
	public void Update(OrderBean bean) throws Exception {
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn.prepareStatement(
					"update orders set  shopeName= ?, productName = ?, price = ?, dop = ? where id =?");
			pstmt.setString(1, bean.getShopeName());
			pstmt.setString(2, bean.getProdectName());
			pstmt.setDouble(3, bean.getPrice());
			pstmt.setDate(4, new java.sql.Date(bean.getDop().getTime()));
			pstmt.setInt(5, bean.getId());
			pstmt.executeUpdate();
			conn.commit();
			System.out.println("Data Upadated successfully.");
		} catch (Exception e) {
			System.out.println("Transaction is rolled back");
			conn.rollback();
		} finally {
			conn.close();
		}
	}

//	find by id
	public OrderBean findById(int id) throws Exception {
		Connection conn = null;
		conn = JDBCDataSource.getConnection();
		PreparedStatement pstmt = conn.prepareStatement("select * from orders where id = ?");
		pstmt.setInt(1, id);
		ResultSet rs = pstmt.executeQuery();
		OrderBean bean = null;
		while (rs.next()) {
			bean = new OrderBean();
			bean.setId(rs.getInt(1));
			bean.setShopeName(rs.getString(2));
			bean.setProdectName(rs.getString(3));
			bean.setPrice(rs.getDouble(4));
			bean.setDop(rs.getDate(5));
		}
		conn.close();
		return bean;
	}

//	Search() will search multiple record.
//	if SQL injunction is true then we can append as many as quarry in one quarry

	public List search(OrderBean bean, int pageNo, int pageSize) throws Exception {
		ArrayList list = new ArrayList();
		StringBuffer sql = new StringBuffer("select * from orders where 1 = 1");
		if (bean != null) {
			if (bean.getShopeName() != null && bean.getShopeName().length() > 0) {
				sql.append(" and shopeName like '%" + bean.getShopeName() + "%'");
			}
			if (bean.getId() > 0 && bean.getId() < nextPk()) {
				sql.append(" and id like '%" + bean.getId() + "%'");
			}
			if (bean.getProdectName() != null && bean.getProdectName().length() > 0) {
				sql.append(" and productName like '%" + bean.getProdectName() + "%'");
			}
			if (bean.getPrice() != 0 && bean.getPrice() > 0) {
				sql.append(" and price like '%" + bean.getPrice() + "%'");
			}
			
			if (bean.getDop() != null && bean.getDop().getTime() > 0) {
				sql.append(" and dop = '" + new java.sql.Date(bean.getDop().getTime()) + "'");
			}
		}
		if (pageSize > 0) {
			pageNo = (pageNo - 1) * pageSize;
			sql.append(" limit " + pageNo + ", " + pageSize);
		}

		Connection conn = null;
		conn = JDBCDataSource.getConnection();
		PreparedStatement pstmt = conn.prepareStatement(sql.toString());
		ResultSet rs = pstmt.executeQuery();

		while (rs.next()) {
			bean = new OrderBean();
			bean.setId(rs.getInt(1));
			bean.setShopeName(rs.getString(2));
			bean.setProdectName(rs.getString(3));
			bean.setPrice(rs.getDouble(4));
			bean.setDop(rs.getDate(5));
			list.add(bean);

		}
		conn.close();
		return list;
	}
}
