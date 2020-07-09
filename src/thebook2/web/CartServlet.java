package thebook2.web;

import thebook2.dao.BookService;
import thebook2.dao.OrderItemService;
import thebook2.pojo.Book;
import thebook2.pojo.Cart;
import thebook2.pojo.Cartitem;
import thebook2.utils.JdbcUtil;
import thebook2.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;

public class CartServlet extends BaseServlet{
    protected void addCart(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id= WebUtils.parseInt(req.getParameter("id"),0);
        BookService bookService = new BookService();
        JdbcUtil dbutil = new JdbcUtil();
        Connection con;
        try {
            con=dbutil.getCon();
            Book book=bookService.queryBook(con,id);
            Cartitem cartitem=new Cartitem(book.getId(),book.getName(),book.getauthor(),book.getPrice(),1,book.getPrice());
            Cart cart=(Cart)req.getSession().getAttribute("cart");
            if(cart==null){
                cart=new Cart();
                cart.addItem(cartitem);
                req.getSession().setAttribute("cart",cart);
            }else{
                cart.addItem(cartitem);
            }

            req.getSession().setAttribute("lastName",cartitem.getName());
            System.out.println(req.getHeader("Referer"));
            req.getRequestDispatcher("/pages/repage/re.jsp").forward(req,resp);
            //resp.sendRedirect(req.getHeader("Referer"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //归还书籍
    protected void b_return(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String bookname= req.getParameter("bookname");
        BookService bookService = new BookService();
        OrderItemService orderItemService=new OrderItemService();
        JdbcUtil dbutil = new JdbcUtil();
        Connection con;
        try {
            con=dbutil.getCon();
            Book book=bookService.queryBook(con,bookname);
            System.out.println(book.toString());
            Book newbook=new Book(book.getId(),book.getName(),book.getPrice(),book.getauthor(),book.getStock()+1,book.getImg_path());
            System.out.println(newbook.toString());
            bookService.updateBook(con,newbook);
            orderItemService.updateborrowBook(con,book.getName());
            req.getRequestDispatcher("/pages/repage/borrow.jsp").forward(req,resp);
            //resp.sendRedirect(req.getHeader("Referer"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    protected void clean(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cart cart=(Cart)req.getSession().getAttribute("cart");
        if(cart!=null){
            cart.Clear();
            //req.getSession().setAttribute("cart",cart);
            resp.sendRedirect(req.getHeader("Referer"));
        }
    }
    protected void deleteItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id= WebUtils.parseInt(req.getParameter("id"),0);
        Cart cart=(Cart)req.getSession().getAttribute("cart");
        if(cart!=null){
            cart.deleteItem(id);
            //req.getSession().setAttribute("cart",cart);
            resp.sendRedirect(req.getHeader("Referer"));
        }
    }
    protected void updateCount(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id= WebUtils.parseInt(req.getParameter("id"),0);
        int count= WebUtils.parseInt(req.getParameter("count"),0);
        Cart cart=(Cart)req.getSession().getAttribute("cart");
        if(cart!=null){
            cart.UpdateCount(id,count);
            resp.sendRedirect(req.getHeader("Referer"));
        }
    }
}
