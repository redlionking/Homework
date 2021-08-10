package tutu.sharing.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import tutu.sharing.mapper.OrderMapper;
import tutu.sharing.pojo.Order;

import java.util.List;

@Service("sorderService")
public class OrderService {

    @Autowired
    @Qualifier("sorderMapper")
    private OrderMapper mapper;

    public List<Order> selectAll(){
        return mapper.select();
    }

    public int insert(Order order){
        return mapper.insert(order.getGoodId(),order.getUserId(),order.getCount());
    }
}
