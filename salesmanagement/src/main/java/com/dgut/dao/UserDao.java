package com.dgut.dao;

import com.dgut.entity.Stock;
import com.dgut.entity.User;
import com.dgut.util.MyUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDao {
    public User getUserByUsername(String username) {
        Connection conn = null;
        User user = null;
        PreparedStatement ps =null;
        try {
            conn = MyUtil.getConnection();
            String sql = "SELECT * FROM users WHERE name = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                user = new User();
                user.setId(rs.getInt("id"));
                user.setName(rs.getString("name"));
                user.setPassword(rs.getString("password"));
                user.setType(rs.getInt("type"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            MyUtil.close(conn,ps);
        }
        return user;
    }
    public boolean selectName(String uname){
        return (getUserByUsername(uname)!=null);
    }
    public boolean insertUser(User user) {
        Connection con = MyUtil.getConnection();
        PreparedStatement ps =null;
        String sql = "INSERT INTO users(name,password,type) VALUES(?,?,?)";
        boolean flag = false;
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1,user.getName());
            ps.setString(2,user.getPassword());
            ps.setString(3, "0");
            flag = (ps.executeUpdate()==1);

        }catch (SQLException e) {
            if(!e.getMessage().contains("PRIMARY")){
                e.printStackTrace();
            }else {
                System.out.println("该用户名已存在");
                return false;
            }
        }finally {
            MyUtil.close(con,ps);
        }
        return flag;

    }
    public List<User> findAllSalesperson() {
        String sql = "SELECT * FROM users WHERE type =1 ";
        return getUsers(sql);
    }
    public List<User> findAllUser() {
        String sql = "SELECT * FROM users ";
        return getUsers(sql);
    }
    static List<User> getUsers(String sql)  {
        List<User> users = new ArrayList<>();
        try (Connection conn = MyUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                users.add(new User(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getInt("type")
                ));
            }
        }
        catch (SQLException e) {
                e.printStackTrace();
        }
        return users;
    }
    public boolean deleteUser(int id) {
        String sql = "DELETE FROM users WHERE id=?";
        try {
            Connection conn = MyUtil.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);

            pstmt.executeUpdate();
            MyUtil.close(conn, pstmt);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateUser(User customer) {
        String sql = "UPDATE users SET name=?, email=?, phone=?, address=? WHERE id=?";
        try {
            Connection conn = MyUtil.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, customer.getName());
            pstmt.setString(2, customer.getEmail());
            pstmt.setString(3, customer.getPhone());
            pstmt.setString(4, customer.getAddress());
            pstmt.setInt(5, customer.getId());
            pstmt.executeUpdate();
            MyUtil.close(conn, pstmt);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}