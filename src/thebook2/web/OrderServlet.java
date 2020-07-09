package thebook2.web;

import thebook2.dao.BookService;
import thebook2.dao.OrderItemService;
import thebook2.pojo.*;
import thebook2.utils.JdbcUtil;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.util.Map;

public class OrderServlet extends BaseServlet{
    protected void addOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        OrderItemService orderItemService = new OrderItemService();
        Cart cart=(Cart)req.getSession().getAttribute("cart");
        User user=(User)req.getSession().getAttribute("user");
        if(user==null){
            req.getRequestDispatcher("index.jsp").forward(req,resp);
            return;
        }
        java.util.Date utilDate = new java.util.Date();  //获取当前时间
        Date sqlDate = new Date(utilDate.getTime());  //通过getTime()转换
        JdbcUtil dbutil = new JdbcUtil();
        Connection con;
        BookService bookService=new BookService();
        try {
            con=dbutil.getCon();
            con.setAutoCommit(false);
            for(Map.Entry<Integer, Cartitem>entry:cart.getItems().entrySet()) {
                Cartitem cartitem = entry.getValue();
                //更新图书库存销量等信息
                Book book=bookService.queryBook(con,cartitem.getId());
                book.setStock(book.getStock()-cartitem.getCount());
                bookService.updateBook(con,book);
                //System.out.println(cartitem.toString());
                OrderItem orderItem = new OrderItem(null,cartitem.getName(),cartitem.getCount(),cartitem.getPrice(),user.getName());
                //OrderItem orderItem = new OrderItem(null, cartitem.getName(), cartitem.getCount(), cartitem.getPrice(), cartitem.getTotalPrice(), orderId);
                orderItemService.addOrderItem(con, orderItem);
            }
            con.commit();
            cart.Clear();
            //req.getSession().setAttribute("orderId",orderId);
            //resp.sendRedirect(req.getContextPath()+"/pages/borrow/order.jsp");
            req.getRequestDispatcher("/pages/repage/borrow.jsp").forward(req,resp);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
