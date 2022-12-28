package jpabook.jpashop.api;

import jpabook.jpashop.domain.Order;
import jpabook.jpashop.repository.OrderRepository;
import jpabook.jpashop.repository.OrderSearch;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * To One 최적화
 */
@RestController
@RequiredArgsConstructor
public class OrderSimpleApiController {
    private final OrderRepository orderRepository;

    @GetMapping("/api/v1/simple-orders")
    public List<Order> ordersV1() {
        List<Order> all = orderRepository.findAllByString(new OrderSearch());
        // 이대로면 무한루프 발생 -> 한쪽에 JSON ignore 해야함
        // 지연로딩 == 프록시 객체 들어가있음 -> Hibernate5Module 빈 등록하면됨
        // implementation 'com.fasterxml.jackson.datatype:jackson-datatype-hibernate5-jakarta'
//        for (Order order : all) {
//            order.getMember().getName(); //Lazy 강제 초기화
//            order.getDelivery().getAddress(); //Lazy 강제 초기화
//        } 
        return all;
    }
    // Hibernate5Module 보단 DTO 사용, 이런 경우 항상 지연로딩 + fetch 사용
}
