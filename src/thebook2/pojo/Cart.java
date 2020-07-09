package thebook2.pojo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Cart {
    //private Integer totalcount;
    //private Integer totalPrice;
    private Map<Integer,Cartitem> items=new LinkedHashMap<Integer,Cartitem>();

    public  void addItem(Cartitem cartitem){
        Cartitem item=items.get(cartitem.getId());
        if(item==null){
            items.put(cartitem.getId(),cartitem);
        }else{
            item.setCount(item.getCount()+1);
            item.setTotalPrice(item.getPrice().multiply(new BigDecimal(item.getCount())));
        }
    }
    public  void deleteItem(Integer id){
        items.remove(id);
    }
    public  void Clear(){
        items.clear();
    }
    public void UpdateCount(Integer id,Integer count){
        Cartitem item=items.get(id);
        if(item!=null){
            item.setCount(count);
            item.setTotalPrice(item.getPrice().multiply(new BigDecimal(item.getCount())));
        }else{

        }
    }

    public Integer getTotalcount() {
        int totalcount=0;
        for(Map.Entry<Integer,Cartitem>entry:items.entrySet()){
            totalcount+=entry.getValue().getCount();
        }
        return totalcount;
    }


    public BigDecimal getTotalPrice() {
        BigDecimal totalPrice=new BigDecimal(0);
        for(Map.Entry<Integer,Cartitem>entry:items.entrySet()){
            totalPrice=totalPrice.add(entry.getValue().getTotalPrice());
        }
        return totalPrice;
    }


    public Map<Integer, Cartitem> getItems() {
        return items;
    }

    public void setItems(Map<Integer, Cartitem> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "totalcount=" + getTotalcount() +
                ", totalPrice=" + getTotalPrice() +
                ", items=" + items +
                '}';
    }
}
