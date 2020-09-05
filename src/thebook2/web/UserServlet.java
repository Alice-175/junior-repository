package thebook2.web;

import com.google.gson.Gson;
import thebook2.dao.UserService;
import thebook2.pojo.User;
import thebook2.utils.JdbcUtil;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

public class UserServlet extends BaseServlet {
    protected void existsUsername(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username=req.getParameter("username");
        UserService userService=new UserService();
        JdbcUtil dbutil=new JdbcUtil();
        Connection con;
        try {
            con=dbutil.getCon();
            User u=userService.existsUsername(con, username);
            Map<String,String> map=new HashMap<>();
            if(u!= null){
                map.put("existsUsername","t");
            }else{
                map.put("existsUsername","f");
            }
            Gson gson=new Gson();
            //转换成json
            String json=gson.toJson(map);
            resp.getWriter().write(json);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    protected void logout(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getSession().invalidate();
        resp.sendRedirect(req.getContextPath());
    }
    protected void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserService userService=new UserService();
        JdbcUtil dbutil=new JdbcUtil();
        Connection con;

        String username=req.getParameter("username");
        String password=req.getParameter("password");

        try {
            con=dbutil.getCon();
            User u=userService.existsUsername(con, username);
            if(u!= null){
                User loginU=userService.loginUser(con,new User(username,password));
                if(loginU!=null){
                    req.getSession().setAttribute("user",loginU);
                    req.getRequestDispatcher("/pages/repage/re.jsp").forward(req,resp);
                }else{
                    System.out.println("密码错误");
                    req.getRequestDispatcher("index.jsp").forward(req,resp);
                }

            }else{
                System.out.println("用户不存在");
                req.getRequestDispatcher("index.jsp").forward(req,resp);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
