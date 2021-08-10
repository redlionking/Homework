package tutu.datasource.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tutu.datasource.mapper.OrderMapper;
import tutu.datasource.pojo.Order;

import java.util.List;

@Service
public class OrderService {
    @Autowired
    private OrderMapper mapper;

    public List<Order> selectAll(){
        return mapper.select();
    }

    public int insert(Order order){
        return mapper.insert(order.getGoodId(),order.getUserId(),order.getCount());
    }
}
