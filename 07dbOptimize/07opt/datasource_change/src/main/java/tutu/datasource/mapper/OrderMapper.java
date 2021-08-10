package tutu.datasource.mapper;


import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import tutu.datasource.pojo.Order;

import java.util.List;

@Mapper
@Repository
public interface OrderMapper {
	@Select("SELECT * FROM online_shop.order;")
	public List<Order> select();

	@Insert("insert into online_shop.order values (#{good_id}, #{user_id}, #{count})")
	public int insert(@Param("good_id") String goodid, @Param("user_id")String userid, @Param("count")int count);
}
