package thebook2.dao;

import thebook2.pojo.User;
import thebook2.utils.JdbcUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserService {
    public User existsUsername(Connection con,String name) throws SQLException {
        User resultUser=null;
        String sql="select * from t_user where username=?";
        PreparedStatement pstmt=con.prepareStatement(sql);
        pstmt.setString(1, name);
        ResultSet rs=pstmt.executeQuery();
        if(rs.next()) {
            resultUser=new User();
            resultUser.setId(rs.getInt("id"));
            resultUser.setName(rs.getString("userName"));
            resultUser.setPassword(rs.getString("password"));
            resultUser.setEmail(rs.getString("email"));
        }
        return resultUser;
    }

    public int registUser(Connection con,User user) throws SQLException {
        String sql="insert into t_user values(null,?,?,?)";
        PreparedStatement pstmt=con.prepareStatement(sql);
        pstmt.setString(1, user.getName());
        pstmt.setString(2, user.getPassword());
        pstmt.setString(3, user.getEmail());
        return pstmt.executeUpdate();
    }

    public User loginUser(Connection con,User user) throws SQLException {
        User resultUser=null;
        String sql="select * from t_user where username=? and password=?";
        PreparedStatement pstmt=con.prepareStatement(sql);
        pstmt.setString(1, user.getName());
        pstmt.setString(2, user.getPassword());
        ResultSet rs=pstmt.executeQuery();
        if(rs.next()) {
            resultUser=new User();
            resultUser.setId(rs.getInt("id"));
            resultUser.setName(rs.getString("userName"));
            resultUser.setPassword(rs.getString("password"));
            resultUser.setEmail(rs.getString("email"));
        }
        return resultUser;
    }

    public static void main(String[] args) throws Exception {
        JdbcUtil db=new JdbcUtil();
        Connection con=db.getCon();
        UserService ss=new UserService();
        User a=ss.existsUsername(con,"wzg168");
        if(a!=null){
            System.out.println("ok");
        }else
        {
            System.out.println("false");
        }
    }
}
