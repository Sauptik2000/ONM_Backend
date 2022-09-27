package com.onm;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.onm.entity.Coupan;
import com.onm.entity.Customer;
import com.onm.entity.Order;
import com.onm.entity.Plant;
import com.onm.repositories.CoupanRepository;
import com.onm.repositories.CustomerRepository;
import com.onm.repositories.OrderRepository;
import com.onm.repositories.PlantRepository;
import com.onm.services.OrderServices;

@ContextConfiguration(classes = {OrderServices.class})
@ExtendWith(SpringExtension.class)
public class OnmOrderTests {
	@MockBean
    private CoupanRepository coupanRepository;

    @MockBean
    private CustomerRepository customerRepository;

    @MockBean
    private OrderRepository orderRepository;

    @Autowired
    private OrderServices orderServices;

    @MockBean
    private PlantRepository plantRepository;

    /**
     * Method under test: {@link OrderServices#bookOrder(Order, int, int)}
     */
    @Test
    void testBookOrder() {
        Coupan coupan = new Coupan();
        coupan.setCoupanDescription("Coupan Description");
        coupan.setCoupanId(123);
        coupan.setCoupanName("Coupan Name");
        coupan.setCoupanType("Coupan Type");
        coupan.setCouponValue(10.0d);
        Optional<Coupan> ofResult = Optional.of(coupan);
        when(coupanRepository.findById((Integer) any())).thenReturn(ofResult);

        Customer customer = new Customer();
        customer.setCustomerEmail("jane.doe@example.org");
        customer.setCustomerId(123);
        customer.setCustomerName("Customer Name");
        customer.setCustomerType("Customer Type");
        customer.setLoginStatus(true);
        customer.setPassword("123");
        Optional<Customer> ofResult1 = Optional.of(customer);
        when(customerRepository.findById((Integer) any())).thenReturn(ofResult1);

        Order order = new Order();
        order.setCoupanId(123);
        order.setCustomerId(123);
        order.setOrderAmount(10.0d);
        order.setOrderId(123);
        order.setOrderQuantity(1);
        order.setPlantName("Plant Name");
        when(orderRepository.save((Order) any())).thenReturn(order);

        Plant plant = new Plant();
        plant.setPlantCost(10.0d);
        plant.setPlantDesccription("Plant Desccription");
        plant.setPlantId(123);
        plant.setPlantName("Plant Name");
        Optional<Plant> ofResult2 = Optional.of(plant);
        when(plantRepository.findById((Integer) any())).thenReturn(ofResult2);

        Order order1 = new Order();
        order1.setCoupanId(123);
        order1.setCustomerId(123);
        order1.setOrderAmount(10.0d);
        order1.setOrderId(123);
        order1.setOrderQuantity(1);
        order1.setPlantName("Plant Name");
        assertTrue(orderServices.bookOrder(order1, 123, 123));
        verify(coupanRepository).findById((Integer) any());
        verify(customerRepository).findById((Integer) any());
        verify(orderRepository).save((Order) any());
        verify(plantRepository, atLeast(1)).findById((Integer) any());
        assertEquals("Plant Name", order1.getPlantName());
        assertEquals(10.0d, order1.getOrderAmount());
        assertEquals(123, order1.getCustomerId());
    }


    /**
     * Method under test: {@link OrderServices#viewOrder(int)}
     */
    @Test
    void testViewOrder() {
        ArrayList<Order> orderList = new ArrayList<>();
        when(orderRepository.findByCustomerId(anyInt())).thenReturn(orderList);
        List actualViewOrderResult = orderServices.viewOrder(123);
        assertSame(orderList, actualViewOrderResult);
        assertTrue(actualViewOrderResult.isEmpty());
        verify(orderRepository).findByCustomerId(anyInt());
    }

    /**
     * Method under test: {@link OrderServices#viewAllOrder(int)}
     */
    @Test
    void testViewAllOrder() {
        Customer customer = new Customer();
        customer.setCustomerEmail("jane.doe@example.org");
        customer.setCustomerId(123);
        customer.setCustomerName("Customer Name");
        customer.setCustomerType("Customer Type");
        customer.setLoginStatus(true);
        customer.setPassword("123");
        Optional<Customer> ofResult = Optional.of(customer);
        when(customerRepository.findById((Integer) any())).thenReturn(ofResult);
        ArrayList<Order> orderList = new ArrayList<>();
        when(orderRepository.findAll()).thenReturn(orderList);
        List actualViewAllOrderResult = orderServices.viewAllOrder(123);
        assertSame(orderList, actualViewAllOrderResult);
        assertTrue(actualViewAllOrderResult.isEmpty());
        verify(customerRepository).findById((Integer) any());
        verify(orderRepository).findAll();
    }


    /**
     * Method under test: {@link OrderServices#cancelOrder(int, int)}
     */
    @Test
    void testCancelOrder() {
        Customer customer = new Customer();
        customer.setCustomerEmail("jane.doe@example.org");
        customer.setCustomerId(123);
        customer.setCustomerName("Customer Name");
        customer.setCustomerType("Customer Type");
        customer.setLoginStatus(true);
        customer.setPassword("123");
        Optional<Customer> ofResult = Optional.of(customer);
        when(customerRepository.findById((Integer) any())).thenReturn(ofResult);

        Order order = new Order();
        order.setCoupanId(123);
        order.setCustomerId(123);
        order.setOrderAmount(10.0d);
        order.setOrderId(123);
        order.setOrderQuantity(1);
        order.setPlantName("Plant Name");
        Optional<Order> ofResult1 = Optional.of(order);
        doNothing().when(orderRepository).deleteById((Integer) any());
        when(orderRepository.findById((Integer) any())).thenReturn(ofResult1);
        orderServices.cancelOrder(123, 123);
        verify(customerRepository).findById((Integer) any());
        verify(orderRepository).findById((Integer) any());
        verify(orderRepository).deleteById((Integer) any());
    }

    /**
     * Method under test: {@link OrderServices#updateOrder(Order, int)}
     */
    @Test
    void testUpdateOrder() {
        Coupan coupan = new Coupan();
        coupan.setCoupanDescription("Coupan Description");
        coupan.setCoupanId(123);
        coupan.setCoupanName("Coupan Name");
        coupan.setCoupanType("Coupan Type");
        coupan.setCouponValue(10.0d);
        Optional<Coupan> ofResult = Optional.of(coupan);
        when(coupanRepository.findById((Integer) any())).thenReturn(ofResult);

        Customer customer = new Customer();
        customer.setCustomerEmail("jane.doe@example.org");
        customer.setCustomerId(123);
        customer.setCustomerName("Customer Name");
        customer.setCustomerType("Customer Type");
        customer.setLoginStatus(true);
        customer.setPassword("123");
        Optional<Customer> ofResult1 = Optional.of(customer);
        when(customerRepository.findById((Integer) any())).thenReturn(ofResult1);

        Order order = new Order();
        order.setCoupanId(123);
        order.setCustomerId(123);
        order.setOrderAmount(10.0d);
        order.setOrderId(123);
        order.setOrderQuantity(1);
        order.setPlantName("Plant Name");
        Optional<Order> ofResult2 = Optional.of(order);

        Order order1 = new Order();
        order1.setCoupanId(123);
        order1.setCustomerId(123);
        order1.setOrderAmount(10.0d);
        order1.setOrderId(123);
        order1.setOrderQuantity(1);
        order1.setPlantName("Plant Name");
        when(orderRepository.save((Order) any())).thenReturn(order1);
        when(orderRepository.findById((Integer) any())).thenReturn(ofResult2);

        Plant plant = new Plant();
        plant.setPlantCost(10.0d);
        plant.setPlantDesccription("Plant Desccription");
        plant.setPlantId(123);
        plant.setPlantName("Plant Name");
        when(plantRepository.findByplantName((String) any())).thenReturn(plant);

        Order order2 = new Order();
        order2.setCoupanId(123);
        order2.setCustomerId(123);
        order2.setOrderAmount(10.0d);
        order2.setOrderId(123);
        order2.setOrderQuantity(1);
        order2.setPlantName("Plant Name");
        Order actualUpdateOrderResult = orderServices.updateOrder(order2, 123);
        assertSame(order, actualUpdateOrderResult);
        assertEquals(1, actualUpdateOrderResult.getOrderQuantity());
        assertEquals(10.0d, actualUpdateOrderResult.getOrderAmount());
        verify(coupanRepository).findById((Integer) any());
        verify(customerRepository).findById((Integer) any());
        verify(orderRepository).save((Order) any());
        verify(orderRepository).findById((Integer) any());
        verify(plantRepository).findByplantName((String) any());
    }

}


