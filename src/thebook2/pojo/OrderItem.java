package thebook2.pojo;

import java.math.BigDecimal;
import java.sql.Date;

public class OrderItem {
    private Integer id;
    private String bookname;
    private Integer count;
    private BigDecimal price;
    private String borrowname;
    private Date borrowdate;
    private Date returndate;
    private Integer status;

    public OrderItem() {
    }

    public OrderItem(Integer id, String bookname, Integer count, BigDecimal price, String borrowname, Date borrowdate, Date returndate, Integer status) {
        this.id = id;
        this.bookname = bookname;
        this.count = count;
        this.price = price;
        this.borrowname = borrowname;
        this.borrowdate = borrowdate;
        this.returndate = returndate;
        this.status = status;
    }

    public OrderItem(Integer id, String bookname, Integer count, BigDecimal price, String borrowname) {
        this.id = id;
        this.bookname = bookname;
        this.count = count;
        this.price = price;
        this.borrowname = borrowname;
    }


    @Override
    public String toString() {
        return "OrderItem{" +
                "id=" + id +
                ", bookname='" + bookname + '\'' +
                ", count=" + count +
                ", price=" + price +
                ", borrowname='" + borrowname + '\'' +
                ", borrowdate=" + borrowdate +
                ", returndate=" + returndate +
                ", status=" + status +
                '}';
    }

    public Date getBorrowdate() {
        return borrowdate;
    }

    public void setBorrowdate(Date borrowdate) {
        this.borrowdate = borrowdate;
    }

    public Date getReturndate() {
        return returndate;
    }

    public void setReturndate(Date returndate) {
        this.returndate = returndate;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBookname() {
        return bookname;
    }

    public void setBookname(String bookname) {
        this.bookname = bookname;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getBorrowname() {
        return borrowname;
    }

    public void setBorrowname(String borrowname) {
        this.borrowname = borrowname;
    }
}
