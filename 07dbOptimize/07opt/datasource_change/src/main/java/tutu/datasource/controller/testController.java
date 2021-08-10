package tutu.datasource.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tutu.datasource.service.OrderService;
import tutu.datasource.pojo.Order;

import java.util.List;

@RestController
public class testController {

    @Autowired
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
