package com.rays.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.rays.bean.UserBean;
import com.rays.util.JDBCDataSource;

public class UserModel {
//	Generate primary key
	public int nextPk() throws Exception {
		int pk = 0;
		Connection comm = null;
		comm = JDBCDataSource.getConnection();
		PreparedStatement pstmt = comm.prepareStatement("select max(id) from st_user");
		ResultSet rs = pstmt.executeQuery();
		while (rs.next()) {
			pk = rs.getInt(1);
		}
		comm.close();
		return pk + 1;
	}

//	insert record
	public void add(UserBean bean) throws Exception {
		Connection comm = null;
		try {
			UserBean existBean = findByLogin(bean.getLogin());

			if (existBean != null) {
				throw new RuntimeException("User alrady Exist");
			}
			comm = JDBCDataSource.getConnection();
			Statement stmt = comm.createStatement();
			comm.setAutoCommit(false);
			PreparedStatement pstmt = comm.prepareStatement("insert into st_user values (?,?,?,?,?,?)");
			pstmt.setInt(1, nextPk());
			pstmt.setString(2, bean.getFirstName());
			pstmt.setString(3, bean.getLastName());
			pstmt.setString(4, bean.getLogin());
			pstmt.setString(5, bean.getPassword());
			pstmt.setDate(6, new java.sql.Date(bean.getDob().getTime()));
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
	public void Delete(UserBean bean) throws Exception {
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			Statement stmt = conn.createStatement();
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn.prepareStatement("delete from st_user where id = ?");
			pstmt.setInt(1, bean.getId());
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
	public void Update(UserBean bean) throws Exception {
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			Statement stmt = conn.createStatement();
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn.prepareStatement(
					"update st_user set firstName = ?, lastName = ?, login = ?, password = ?, dob = ? where id =?");
			pstmt.setString(1, bean.getFirstName());
			pstmt.setString(2, bean.getLastName());
			pstmt.setString(3, bean.getLogin());
			pstmt.setString(4, bean.getPassword());
			pstmt.setDate(5, new java.sql.Date(bean.getDob().getTime()));
			pstmt.setInt(6, bean.getId());
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

//	find by login id
	public UserBean findByLogin(String login) throws Exception {
		Connection conn = null;
		conn = JDBCDataSource.getConnection();
		Statement stmt = conn.createStatement();
		PreparedStatement pstmt = conn.prepareStatement("select * from st_user where login = ?");
		pstmt.setString(1, login);
		ResultSet rs = pstmt.executeQuery();
		UserBean bean = null;
		while (rs.next()) {
			bean = new UserBean();
			bean.setId(rs.getInt(1));
			bean.setFirstName(rs.getString(2));
			bean.setLastName(rs.getString(3));
			bean.setLogin(rs.getString(4));
			bean.setPassword(rs.getString(5));
			bean.setDob(rs.getDate(6));
		}
		conn.close();
		return bean;
	}

//	User Authentication
	public UserBean authenticator(String login, String password) throws Exception {
		Connection conn = null;
		conn = JDBCDataSource.getConnection();
		Statement stmt = conn.createStatement();
		PreparedStatement pstmt = conn.prepareStatement("select * from st_user where login = ? and password = ?");
		pstmt.setString(1, login);
		pstmt.setString(2, password);
		ResultSet rs = pstmt.executeQuery();
		UserBean bean = null;
		while (rs.next()) {
			bean = new UserBean();
			bean.setId(rs.getInt(1));
			bean.setFirstName(rs.getString(2));
			bean.setLastName(rs.getString(3));
			bean.setLogin(rs.getString(4));
			bean.setPassword(rs.getString(5));
			bean.setDob(rs.getDate(6));
		}
		conn.close();
		return bean;
	}

//	Change password
	public void changePassword(String login, String password, String newPassword) throws Exception {
		Connection conn = null;
		try {
			if (password != newPassword) {
				UserBean bean = authenticator(login, password);

				if (bean != null) {
					conn = JDBCDataSource.getConnection();
					Statement stmt = conn.createStatement();
					conn.setAutoCommit(false);
					PreparedStatement pstmt = conn.prepareStatement("update st_user set password = ? where id =?");
					pstmt.setString(1, newPassword);
					pstmt.setInt(2, bean.getId());
					pstmt.executeUpdate();
					conn.commit();
					System.out.println("Password changed successfully");
				} else {
					throw new RuntimeException("Wrong Username or Password");
				}
			} else {
				throw new RuntimeException("Both the oldPassword and newPasswoed both are same.");
			}
		} catch (Exception e) {
			System.out.println("Transaction is rolled back");
			conn.rollback();
		} finally {
			conn.close();
		}

	}

//	Forget Password
	public void forgetPassword(String login) throws Exception {
		UserBean bean = findByLogin(login);
		if (bean != null) {
			System.out.println("Password: " + bean.getPassword());
		} else {
			throw new RuntimeException("User does not exist.");
		}
	}

//	find by id
	public UserBean findById(int id) throws Exception {
		Connection conn = null;
		conn = JDBCDataSource.getConnection();
		Statement stmt = conn.createStatement();
		PreparedStatement pstmt = conn.prepareStatement("select * from st_user where id = ?");
		pstmt.setInt(1, id);
		ResultSet rs = pstmt.executeQuery();
		UserBean bean = null;
		while (rs.next()) {
			bean = new UserBean();
			bean.setId(rs.getInt(1));
			bean.setFirstName(rs.getString(2));
			bean.setLastName(rs.getString(3));
			bean.setLogin(rs.getString(4));
			bean.setPassword(rs.getString(5));
			bean.setDob(rs.getDate(6));
		}
		conn.close();
		return bean;
	}

//	Search() will search multiple record.
//	if SQL injunction is true then we can append as many as quarry in one quarry

	public List search(UserBean bean) throws Exception {
		ArrayList list = new ArrayList();
		StringBuffer sql = new StringBuffer("select * from st_user where 1 = 1");
		if (bean != null) {
			if (bean.getFirstName() != null && bean.getFirstName().length() > 0) {
				sql.append(" and firstName like '%" + bean.getFirstName() + "%'");
			}
			if (bean.getId() > 0 && bean.getId() < nextPk()) {
				sql.append(" and id like '%" + bean.getId() + "%'");
			}
			if (bean.getLastName() != null && bean.getLastName().length() > 0) {
				sql.append(" and lastName like '%" + bean.getLastName() + "%'");
			}
			if (bean.getLogin() != null && bean.getLogin().length() > 0) {
				sql.append(" and login like '%" + bean.getLogin() + "%'");
			}
			if (bean.getPassword() != null && bean.getPassword().length() > 0) {
				sql.append(" and password like '%" + bean.getPassword() + "%'");
			}
			if (bean.getDob() != null && bean.getDob().getTime() > 0) {
				sql.append(" and dob like '" + new java.sql.Date(bean.getDob().getTime()) + "'");
			}
		}

		Connection conn = null;
		conn = JDBCDataSource.getConnection();
		Statement stmt = conn.createStatement();
		PreparedStatement pstmt = conn.prepareStatement(sql.toString());
		ResultSet rs = pstmt.executeQuery();

		while (rs.next()) {
			bean = new UserBean();
			bean.setId(rs.getInt(1));
			bean.setFirstName(rs.getString(2));
			bean.setLastName(rs.getString(3));
			bean.setLogin(rs.getString(4));
			bean.setPassword(rs.getString(5));
			bean.setDob(rs.getDate(6));
			list.add(bean);

		}
		conn.close();
		return list;
	}
}
