package domain;

import utils.DateUtil;

import java.util.Date;
import java.util.List;

public class Orders {
    private String id;
    private String orderNum;
    private Date orderTime;
    private String orderTimeStr;
    private Integer orderStatus;
    //订单状态(0 未支付 1 已支付)
    private String orderStatusStr;
    private Integer peopleCount;
    private Product product;
    private List<Traveller> travellers;
    private Member member;
    private Integer payType;
    //支付方式(0 支付宝 1 微信 2其它)
    private String payTypeStr;
    private String orderDesc;

    public String getId() {
        return id;
    }

    public String getOrderNum() {
        return orderNum;
    }

    public Date getOrderTime() {
        return orderTime;
    }

    public String getOrderTimeStr() {
        if(orderTime != null){
            orderTimeStr = DateUtil.date2string(orderTime,"yyyy-MM-dd HH:mm");
        }
        return orderTimeStr;
    }

    public Integer getOrderStatus() {
        return orderStatus;
    }

    public String getOrderStatusStr() {
        if(orderStatus != null){
            orderStatusStr = orderStatus.equals(0)?"未支付":"已支付";
        }
        return orderStatusStr;
    }

    public Integer getPeopleCount() {
        return peopleCount;
    }

    public Product getProduct() {
        return product;
    }

    public List<Traveller> getTravellers() {
        return travellers;
    }

    public Member getMember() {
        return member;
    }

    public Integer getPayType() {
        return payType;
    }

    //支付方式(0 支付宝 1 微信 2其它)
    public String getPayTypeStr() {
        if(payType.equals(0)){
            payTypeStr = "支付宝";
        }else if(payType.equals(1)){
            payTypeStr = "微信";
        }else{
            payTypeStr = "其它";
        }
        return payTypeStr;
    }

    public String getOrderDesc() {
        return orderDesc;
    }

    @Override
    public String toString() {
        return "Orders{" +
                "id='" + id + '\'' +
                ", orderNum='" + orderNum + '\'' +
                ", orderTime=" + orderTime +
                ", orderTimeStr='" + orderTimeStr + '\'' +
                ", orderStatus=" + orderStatus +
                ", peopleCount=" + peopleCount +
                ", product=" + product +
                ", travellers=" + travellers +
                ", member=" + member +
                ", payType=" + payType +
                ", payTypeStr='" + payTypeStr + '\'' +
                ", orderDesc='" + orderDesc + '\'' +
                '}';
    }
}
