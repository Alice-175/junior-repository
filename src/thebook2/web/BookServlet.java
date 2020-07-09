package thebook2.web;

import thebook2.dao.BookService;
import thebook2.pojo.Book;
import thebook2.pojo.Page;
import thebook2.utils.JdbcUtil;
import thebook2.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.util.List;

public class BookServlet extends BaseServlet {
    protected void list(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BookService bookService = new BookService();
        JdbcUtil dbutil = new JdbcUtil();
        Connection con;
        try {
            con=dbutil.getCon();
            List<Book> books=bookService.queryBooks(con);
            req.setAttribute("books",books);
            req.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(req,resp);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    protected void add(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BookService bookService = new BookService();
        Book book= WebUtils.copyParamToBean(req.getParameterMap(),new Book());
        int pageNo=WebUtils.parseInt(req.getParameter("pageNo"),0);
        JdbcUtil dbutil = new JdbcUtil();
        Connection con;
        try {
            con=dbutil.getCon();
            bookService.addBook(con,book);
            resp.sendRedirect(req.getContextPath()+"/manager/bookServlet?action=page&pageNo="+pageNo);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    protected void delect(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BookService bookService = new BookService();
        String id=req.getParameter("id");
        Book book= WebUtils.copyParamToBean(req.getParameterMap(),new Book());
        JdbcUtil dbutil = new JdbcUtil();
        Connection con;
        try {
            con=dbutil.getCon();
                bookService.deleteBookById(con,Integer.parseInt(id));
            resp.sendRedirect(req.getContextPath()+"/manager/bookServlet?action=page&pageNo="+req.getParameter("pageNo"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    protected void getBook(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BookService bookService = new BookService();
        JdbcUtil dbutil = new JdbcUtil();
        String id=req.getParameter("id");
        Connection con;
        try {
            con=dbutil.getCon();
            Book book=bookService.queryBook(con,Integer.parseInt(id));
            req.setAttribute("book",book);
            req.getRequestDispatcher("/pages/manager/book_edit.jsp").forward(req,resp);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    protected void update(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BookService bookService = new BookService();
        JdbcUtil dbutil = new JdbcUtil();
        Book book= WebUtils.copyParamToBean(req.getParameterMap(),new Book());
        Connection con;
        try {
            con=dbutil.getCon();
            bookService.updateBook(con,book);
            resp.sendRedirect(req.getContextPath()+"/manager/bookServlet?action=page&pageNo="+req.getParameter("pageNo"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
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
            thepage.setUrl("manager/bookServlet?action=page");
            List<Book> items=bookService.queryForPageItems(begin,pageSize,con);
            thepage.setItems(items);
            req.setAttribute("page",thepage);
            req.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(req,resp);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
