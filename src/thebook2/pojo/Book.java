package thebook2.pojo;

import java.math.BigDecimal;

public class Book {
    private Integer id;
    private String name;
    private BigDecimal price;
    private String author;
    private Integer stock;
    private String img_path="static/img/index.jpg";

    public Book() {
    }

    public Book(Integer id, String name, BigDecimal price, String author, Integer stock, String img_path) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.author = author;
        this.stock = stock;
        this.img_path = img_path;
    }

    public Book(String name, BigDecimal price, String author, Integer stock, String img_path) {
        this.name = name;
        this.price = price;
        this.author = author;
        this.stock = stock;
        this.img_path = img_path;
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

    public String getauthor() {
        return author;
    }

    public void setauthor(String author) {
        this.author = author;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public String getImg_path() {
        return img_path;
    }

    public void setImg_path(String img_path) {
        this.img_path = img_path;
    }

    public String getAuthor() {
        return author;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", author='" + author + '\'' +
                ", stock=" + stock +
                ", img_path='" + img_path + '\'' +
                '}';
    }
}
