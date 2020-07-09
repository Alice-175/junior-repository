package thebook2.pojo;

import java.util.List;

public class Page<T> {
    public static final int PAGE_SIZE =4;
    private Integer pageNo;//当前页码
    private Integer pageTotal;//总页码
    private Integer pageSize=PAGE_SIZE;//每页显示数量
    private Integer pageTotalcount;//总记录数
    private List<T> items;//当前页数据
    private  String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public Integer getPageTotal(int pageTotal) {
        return this.pageTotal;
    }

    public void setPageTotal(Integer pageTotal) {
        this.pageTotal = pageTotal;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getPageTotalcount() {
        return pageTotalcount;
    }

    public void setPageTotalcount(Integer pageTotalcount) {
        this.pageTotalcount = pageTotalcount;
    }

    public List<T> getItems() {
        return items;
    }

    public void setItems(List<T> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "Page{" +
                "pageNo=" + pageNo +
                ", pageTotal=" + pageTotal +
                ", pageSize=" + pageSize +
                ", pageTotalcount=" + pageTotalcount +
                ", items=" + items +
                ", url='" + url + '\'' +
                '}';
    }

    public int getPageTotal() {
        return this.pageTotal;
    }
}
