package thebook2.dao;

        import thebook2.pojo.Book;
        import thebook2.utils.JdbcUtil;

        import java.math.BigDecimal;
        import java.sql.Connection;
        import java.sql.PreparedStatement;
        import java.sql.ResultSet;
        import java.sql.SQLException;
        import java.util.ArrayList;
        import java.util.List;

public class BookService {
    public Book queryBook(Connection con,int id) throws SQLException {
        Book resultBook=null;
        String sql="select * from t_book where id=?";
        PreparedStatement pstmt=con.prepareStatement(sql);
        pstmt.setInt(1, id);
        ResultSet rs=pstmt.executeQuery();
            if (rs.next()){
                resultBook=new Book();
                resultBook.setId(rs.getInt("id"));
                resultBook.setName(rs.getString("name"));
                resultBook.setauthor(rs.getString("author"));
                resultBook.setPrice(rs.getBigDecimal("price"));
                resultBook.setStock(rs.getInt("stock"));
                resultBook.setImg_path(rs.getString("img_path"));
            }
        return resultBook;
    }
    public Book queryBook(Connection con,String name) throws SQLException {
        Book resultBook=null;
        String sql="select * from t_book where name=?";
        PreparedStatement pstmt=con.prepareStatement(sql);
        pstmt.setString(1, name);
        ResultSet rs=pstmt.executeQuery();
        if (rs.next()){
            resultBook=new Book();
            resultBook.setId(rs.getInt("id"));
            resultBook.setName(rs.getString("name"));
            resultBook.setauthor(rs.getString("author"));
            resultBook.setPrice(rs.getBigDecimal("price"));
            resultBook.setStock(rs.getInt("stock"));
            resultBook.setImg_path(rs.getString("img_path"));
        }
        return resultBook;
    }
    public List<Book> queryBooks(Connection con) throws SQLException {
        List<Book> resultBooklist =new ArrayList<Book>();
        String sql="select * from t_book";
        PreparedStatement pstmt=con.prepareStatement(sql);
        ResultSet rs=pstmt.executeQuery();
        while (rs.next()) {
            Book resultBook = new Book();
            resultBook.setId(rs.getInt("id"));
            resultBook.setName(rs.getString("name"));
            resultBook.setauthor(rs.getString("author"));
            resultBook.setPrice(rs.getBigDecimal("price"));
            resultBook.setStock(rs.getInt("stock"));
            resultBook.setImg_path(rs.getString("img_path"));
            resultBooklist.add(resultBook);
        }
        return resultBooklist;
    }
    public int addBook(Connection con,Book book) throws SQLException {
        String sql="insert into t_book values(null,?,?,?,?,?)";
        PreparedStatement pstmt=con.prepareStatement(sql);
        pstmt.setString(1, book.getName());
        pstmt.setBigDecimal(3, book.getPrice());
        pstmt.setString(2, book.getauthor());
        pstmt.setInt(4, book.getStock());
        pstmt.setString(5, book.getImg_path());
        return pstmt.executeUpdate();
    }
    public int deleteBookById(Connection con,int id) throws SQLException {
        String sql="delete from t_book where id=?";
        PreparedStatement pstmt=con.prepareStatement(sql);
        pstmt.setInt(1, id);
        return pstmt.executeUpdate();
    }
    public int updateBook(Connection con,Book book) throws SQLException {
       // String sql="update t_book set 'name'=?,'price'=?,'author'=?,'sales'=?,'stock'=?,'img_path'=? where id=?";//是`非'
        String sql = "update t_book set `name`=?,`author`=?,`price`=?,`stock`=?,`img_path`=? where id = ?";
        PreparedStatement pstmt=con.prepareStatement(sql);
        pstmt.setString(1, book.getName());
        pstmt.setBigDecimal(3, book.getPrice());
        pstmt.setString(2, book.getauthor());
        pstmt.setInt(4, book.getStock());
        pstmt.setString(5, book.getImg_path());
        pstmt.setInt(6, book.getId());
        return pstmt.executeUpdate();
    }
    public List<Book> queryForPageItems(int begin, int pagesize,Connection con) throws SQLException {
        List<Book> pages=new ArrayList<Book>();
        String sql="select * from t_book limit ?,?";
        PreparedStatement pstmt=con.prepareStatement(sql);
        pstmt.setInt(1,begin);
        pstmt.setInt(2,pagesize);
        ResultSet rs=pstmt.executeQuery();
        while (rs.next()) {
            Book resultBook = new Book();
            resultBook.setId(rs.getInt("id"));
            resultBook.setName(rs.getString("name"));
            resultBook.setauthor(rs.getString("author"));
            resultBook.setPrice(rs.getBigDecimal("price"));
            resultBook.setStock(rs.getInt("stock"));
            resultBook.setImg_path(rs.getString("img_path"));
            pages.add(resultBook);
        }
        return pages;
    }

    public int queryForPageTotalCount(Connection con) throws SQLException {
        String sql="select count(*) from t_book";
        PreparedStatement pstmt=con.prepareStatement(sql);
        ResultSet rs=pstmt.executeQuery();
        int num=0;
        if(rs.next()){
            num=rs.getInt(1);
        }
        return num;
    }


    public static void main(String[] args) throws Exception {
        JdbcUtil db=new JdbcUtil();
        Connection con=db.getCon();
        BookService ss=new BookService();
        Book book=new Book();
        book.setId(1);
        book.setName("数据结构");
        BigDecimal sum=new BigDecimal(32.1);
        book.setPrice(sum);
        book.setStock(2);
        book.setImg_path("default");
        book.setauthor("严敏君");
        ss.updateBook(con,book);
    }

    public int queryForPageTotalCountByname(Connection con, String findname) throws SQLException {
        String sql="select count(*) from t_book where name=?";
        PreparedStatement pstmt=con.prepareStatement(sql);
        pstmt.setString(1,findname);
        ResultSet rs=pstmt.executeQuery();
        int num=0;
        if(rs.next()){
            num=rs.getInt(1);
        }
        return num;
    }


    public List<Book> queryForPageItemsByname(Connection con, String findbookname) throws SQLException {
        List<Book> pages=new ArrayList<Book>();
        String sql="select * from t_book where name=?";
        PreparedStatement pstmt=con.prepareStatement(sql);
        pstmt.setString(1,findbookname);
        ResultSet rs=pstmt.executeQuery();
        while (rs.next()) {
            Book resultBook = new Book();
            resultBook.setId(rs.getInt("id"));
            resultBook.setName(rs.getString("name"));
            resultBook.setauthor(rs.getString("author"));
            resultBook.setPrice(rs.getBigDecimal("price"));
            resultBook.setStock(rs.getInt("stock"));
            resultBook.setImg_path(rs.getString("img_path"));
            pages.add(resultBook);
        }
        return pages;
    }
}
