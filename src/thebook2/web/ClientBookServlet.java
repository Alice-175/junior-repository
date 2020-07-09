package thebook2.web;

import thebook2.dao.BookService;
import thebook2.dao.OrderItemService;
import thebook2.pojo.Book;
import thebook2.pojo.OrderItem;
import thebook2.pojo.Page;
import thebook2.pojo.User;
import thebook2.utils.JdbcUtil;
import thebook2.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.util.List;

public class ClientBookServlet extends BaseServlet{
    protected void page(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BookService bookService = new BookService();
        JdbcUtil dbutil = new JdbcUtil();
        int pageNo= WebUtils.parseInt(req.getParameter("pageNo"),1);
        if(pageNo<1){
            pageNo=1;
        }
        int pageSize= WebUtils.parseInt(req.getParameter("pageSize"), Page.PAGE_SIZE);
        Page<Book> thepage=new Page<Book>();
        Connection con;
        try {
            con=dbutil.getCon();
            int pageTotalCount=bookService.queryForPageTotalCount(con);
            int pageTotal=pageTotalCount/pageSize;
            if(pageTotalCount%pageSize>0){
                pageTotal+=1;
            }
            if(pageNo>pageTotal){
                pageNo=pageTotal;
            }
            int begin=(pageNo-1)*pageSize;
            thepage.setPageSize(pageSize);
            thepage.setPageNo(pageNo);
            thepage.setPageTotal(pageTotal);
            thepage.setPageTotalcount(pageTotalCount);
            thepage.setUrl("client/ClientBookServlet?action=page");
            List<Book> items=bookService.queryForPageItems(begin,pageSize,con);
            thepage.setItems(items);
            req.setAttribute("page",thepage);
            req.getRequestDispatcher("/pages/client/index.jsp").forward(req,resp);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    protected void borrow(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        OrderItemService orderItemService = new OrderItemService();
        JdbcUtil dbutil = new JdbcUtil();
        User user= (User) req.getSession().getAttribute("user");
        String username=user.getName();
        //System.out.println(username);
        int pageNo= WebUtils.parseInt(req.getParameter("pageNo"),1);
        if(pageNo<1){
            pageNo=1;
        }
        int pageSize= WebUtils.parseInt(req.getParameter("pageSize"), Page.PAGE_SIZE);
        Page<OrderItem> thepage=new Page<OrderItem>();
        Connection con;
        try {
            con=dbutil.getCon();
            int pageTotalCount=orderItemService.queryForPageTotalCount(con,username);
            int pageTotal=pageTotalCount/pageSize;
            if(pageTotalCount%pageSize>0){
                pageTotal+=1;
            }
            if(pageNo>pageTotal){
                pageNo=pageTotal;
            }
            int begin=(pageNo-1)*pageSize;
            thepage.setPageSize(pageSize);
            thepage.setPageNo(pageNo);
            thepage.setPageTotal(pageTotal);
            thepage.setPageTotalcount(pageTotalCount);
            thepage.setUrl("client/ClientBookServlet?action=borrow");
            List<OrderItem> items=orderItemService.queryForPageItems(begin,pageSize,con,username);
            thepage.setItems(items);
            req.setAttribute("brrowpage",thepage);
            req.getRequestDispatcher("/pages/borrow/order.jsp").forward(req,resp);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    protected void pageByname(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BookService bookService = new BookService();
        JdbcUtil dbutil = new JdbcUtil();
        int pageNo= WebUtils.parseInt(req.getParameter("pageNo"),1);
        if(pageNo<1){
            pageNo=1;
        }
        int pageSize= WebUtils.parseInt(req.getParameter("pageSize"), Page.PAGE_SIZE);
        String findbookname=req.getParameter("findbookname");
        Page<Book> thepage=new Page<Book>();
        Connection con;
        try {
            con=dbutil.getCon();
            int pageTotalCount=bookService.queryForPageTotalCountByname(con,findbookname);
            int pageTotal=pageTotalCount/pageSize;
            if(pageTotalCount%pageSize>0){
                pageTotal+=1;
            }
            if(pageNo>pageTotal){
                pageNo=pageTotal;
            }
            int begin=(pageNo-1)*pageSize;
            thepage.setPageSize(pageSize);
            thepage.setPageNo(pageNo);
            thepage.setPageTotal(pageTotal);
            thepage.setPageTotalcount(pageTotalCount);
            StringBuilder sb=new StringBuilder("client/ClientBookServlet?action=pageByname");
            if(req.getParameter("min")!=null){
                sb.append("&min=").append(req.getParameter("min"));
            }
            if(req.getParameter("max")!=null){
                sb.append("&max=").append(req.getParameter("max"));
            }
            thepage.setUrl(sb.toString());
            List<Book> items=bookService.queryForPageItemsByname(con,findbookname);
            thepage.setItems(items);
            req.setAttribute("page",thepage);
            req.getRequestDispatcher("/pages/client/index.jsp").forward(req,resp);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
