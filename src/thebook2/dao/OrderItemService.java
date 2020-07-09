package thebook2.dao;

import thebook2.pojo.Book;
import thebook2.pojo.OrderItem;
import thebook2.pojo.User;
import thebook2.utils.JdbcUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderItemService {

    public int addOrderItem(Connection con, OrderItem orderitem) throws SQLException {
        String sql="insert into t_order_item(`bookname`,`count`,`price`,`borrowname`,`borrowdate`,`status`) values(?,?,?,?,?,?)";
        PreparedStatement pstmt=con.prepareStatement(sql);

        pstmt.setString(1,orderitem.getBookname());
        pstmt.setInt(2,1);
        pstmt.setBigDecimal(3, orderitem.getPrice());
        pstmt.setString(4, orderitem.getBorrowname());
        pstmt.setDate(5,new java.sql.Date(System.currentTimeMillis()));
        pstmt.setInt(6,1);
        return pstmt.executeUpdate();
    }

    public int queryForPageTotalCount(Connection con,String borrowname) throws SQLException {
        String sql="SELECT count(*) FROM t_order_item WHERE borrowname = ?";
        PreparedStatement pstmt=con.prepareStatement(sql);
        pstmt.setString(1,borrowname);
        ResultSet rs=pstmt.executeQuery();
        int num=0;
        if(rs.next()){
            num=rs.getInt(1);
        }
        return num;
    }

    public List<OrderItem> queryForPageItems(int begin, int pageSize, Connection con,String borrowname) throws SQLException {
        List<OrderItem> pages=new ArrayList<OrderItem>();
        String sql="select * from t_order_item WHERE borrowname = ? limit ?,?";
        PreparedStatement pstmt=con.prepareStatement(sql);
        pstmt.setString(1,borrowname);
        pstmt.setInt(2,begin);
        pstmt.setInt(3,pageSize);
        ResultSet rs=pstmt.executeQuery();
        while (rs.next()) {
            OrderItem result = new OrderItem();
            result.setId(rs.getInt("id"));
            result.setBookname(rs.getString("bookname"));
            result.setPrice(rs.getBigDecimal("price"));
            result.setCount(rs.getInt("count"));
            result.setBorrowname(rs.getString("borrowname"));
            result.setBorrowdate(rs.getDate("borrowdate"));
            result.setReturndate(rs.getDate("returndate"));
            result.setStatus(rs.getInt("status"));
            pages.add(result);
        }
        return pages;
    }
    //借阅记录
    public int updateborrowBook(Connection con,String bookname) throws SQLException {
        String sql = "update t_order_item set `returndate`=?,`status`=? where bookname = ?";
        PreparedStatement pstmt=con.prepareStatement(sql);
        pstmt.setDate(1, new java.sql.Date(System.currentTimeMillis()));
        pstmt.setInt(2, 0);
        pstmt.setString(3, bookname);
        return pstmt.executeUpdate();
    }

    public static void main(String[] args) throws Exception {
        OrderItemService orderItemService=new OrderItemService();
        JdbcUtil db=new JdbcUtil();
        Connection con=db.getCon();
        int a=orderItemService.queryForPageTotalCount(con,"admin");
        System.out.println(a);
    }
}
