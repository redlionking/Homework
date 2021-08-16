package tu.work2.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import tu.work2.pojo.Order;

import java.util.List;
import java.util.Map;

@Repository
@Mapper
public interface OrderMapper {
    void insertOne(Order order);
    void insertMany(List<Order> orders);
    void delete(Long id);
    void update(Order order);
    List<Map<String, Object>> query(Map<String, Object> condition);
}
