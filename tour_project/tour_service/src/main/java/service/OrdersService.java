package service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import dao.OrdersDao;
import domain.Orders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class OrdersService {

    @Autowired
    OrdersDao ordersDao;

    public List<Orders> findAll(){
        PageHelper.startPage(1,4);
        return ordersDao.findAll();
    }

    public List<Orders> findByPage(Integer page,Integer pageSize){
        return ordersDao.findAll();
    }

    public Orders findById(String orderId){
        return ordersDao.findById(orderId);
    }
}
