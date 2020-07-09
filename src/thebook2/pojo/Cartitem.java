package thebook2.pojo;

import java.math.BigDecimal;

public class Cartitem {
    private Integer id;
    private String name;
    private String author;
    private BigDecimal price;
    private Integer count;
    private BigDecimal totalPrice;

    public Cartitem(Integer id, String name, String author,BigDecimal price, Integer count, BigDecimal totalPrice) {
        this.id = id;
        this.name = name;
        this.author=author;
        this.price = price;
        this.count = count;
        this.totalPrice = totalPrice;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Cartitem() {
    }

    @Override
    public String toString() {
        return "Cartitem{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", count=" + count +
                ", totalPrice=" + totalPrice +
                '}';
    }


    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getTotalprice() {
        return ""+totalPrice;
    }
}
