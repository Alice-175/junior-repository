package thebook2.utils;

import java.sql.Connection;
import java.sql.DriverManager;

public class JdbcUtil {
    private String dbUrl="jdbc:mysql://localhost:3306/bookstore?serverTimezone=UTC";
    private String dbUserName="root";
    private String dbPassword="";
    private String jdbcName="com.mysql.cj.jdbc.Driver";
    //数据库连接
    public Connection getCon()throws Exception{
        Class.forName(jdbcName);
        Connection con=DriverManager.getConnection(dbUrl,dbUserName,dbPassword);
        return con;
    }
    //数据库关闭
    public void closeCon(Connection con)throws Exception{
        if(con!=null) {
            con.close();
        }
    }
    public static void main(String[] args) {
        JdbcUtil dbUtil=new JdbcUtil();
        try {
            dbUtil.getCon();
            System.out.println("数据库连接成功");
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            System.out.println("数据库连接失败");
        }
    }
}
