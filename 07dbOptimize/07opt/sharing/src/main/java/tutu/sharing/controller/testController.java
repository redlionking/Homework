package tutu.sharing.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;
import tutu.sharing.pojo.Order;
import tutu.sharing.service.OrderService;

import java.util.List;

@RestController("stestController")
public class testController {

    @Autowired
    @Qualifier("sorderService")
    private OrderService orderService;

    @GetMapping("/test")
    public String test(){
        System.out.println("suc");
        return "<h1>hello,world!</h1>";
    }

    @GetMapping("/getAll")
    public List<Order> getAll(){
        orderService.selectAll().forEach(order -> {
            System.out.println(order);
        });
       return orderService.selectAll();
    }

    @PostMapping("/insert")
    public int insert(@RequestBody Order order){
        System.out.println(order);
        return orderService.insert(order);
    }
}
