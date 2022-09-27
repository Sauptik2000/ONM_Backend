package com.onm;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.atLeast;
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

import com.onm.entity.Customer;
import com.onm.expections.ResourceAlreadyExistException;
import com.onm.repositories.CustomerRepository;
import com.onm.services.LoginServices;

@ContextConfiguration(classes = {LoginServices.class})
@ExtendWith(SpringExtension.class)
public class OnmCustomerTests {
	@MockBean
    private CustomerRepository customerRepository;

    @Autowired
    private LoginServices loginServices;

    /**
     * Method under test: {@link LoginServices#register(Customer)}
     */
    @Test
    void testRegister() {
        Customer customer = new Customer();
        customer.setCustomerEmail("jane.doe@example.org");
        customer.setCustomerId(123);
        customer.setCustomerName("Customer Name");
        customer.setCustomerType("Customer Type");
        customer.setLoginStatus(true);
        customer.setPassword("123");

        Customer customer1 = new Customer();
        customer1.setCustomerEmail("jane.doe@example.org");
        customer1.setCustomerId(123);
        customer1.setCustomerName("Customer Name");
        customer1.setCustomerType("Customer Type");
        customer1.setLoginStatus(true);
        customer1.setPassword("123");
        when(customerRepository.findByCustomerEmail((String) any())).thenReturn(customer);
        when(customerRepository.save((Customer) any())).thenReturn(customer1);

        Customer customer2 = new Customer();
        customer2.setCustomerEmail("jane.doe@example.org");
        customer2.setCustomerId(123);
        customer2.setCustomerName("Customer Name");
        customer2.setCustomerType("Customer Type");
        customer2.setLoginStatus(true);
        customer2.setPassword("123");
        assertThrows(ResourceAlreadyExistException.class, () -> loginServices.register(customer2));
        verify(customerRepository).findByCustomerEmail((String) any());
    }

    
    /**
     * Method under test: {@link LoginServices#login(Customer)}
     */
    @Test
    void testLogin() {
        Customer customer = new Customer();
        customer.setCustomerEmail("jane.doe@example.org");
        customer.setCustomerId(123);
        customer.setCustomerName("Customer Name");
        customer.setCustomerType("Customer Type");
        customer.setLoginStatus(true);
        customer.setPassword("123");

        Customer customer1 = new Customer();
        customer1.setCustomerEmail("jane.doe@example.org");
        customer1.setCustomerId(123);
        customer1.setCustomerName("Customer Name");
        customer1.setCustomerType("Customer Type");
        customer1.setLoginStatus(true);
        customer1.setPassword("123");
        when(customerRepository.save((Customer) any())).thenReturn(customer1);
        when(customerRepository.findByCustomerEmail((String) any())).thenReturn(customer);

        Customer customer2 = new Customer();
        customer2.setCustomerEmail("jane.doe@example.org");
        customer2.setCustomerId(123);
        customer2.setCustomerName("Customer Name");
        customer2.setCustomerType("Customer Type");
        customer2.setLoginStatus(true);
        customer2.setPassword("123");
        Customer actualLoginResult = loginServices.login(customer2);
        assertSame(customer, actualLoginResult);
        assertTrue(actualLoginResult.isLoginStatus());
        verify(customerRepository).findByCustomerEmail((String) any());
        verify(customerRepository).save((Customer) any());
    }

    

    /**
     * Method under test: {@link LoginServices#updateCustomer(Customer)}
     */
    @Test
    void testUpdateCustomer() {
        Customer customer = new Customer();
        customer.setCustomerEmail("jane.doe@example.org");
        customer.setCustomerId(123);
        customer.setCustomerName("Customer Name");
        customer.setCustomerType("Customer Type");
        customer.setLoginStatus(true);
        customer.setPassword("123");
        Optional<Customer> ofResult = Optional.of(customer);

        Customer customer1 = new Customer();
        customer1.setCustomerEmail("jane.doe@example.org");
        customer1.setCustomerId(123);
        customer1.setCustomerName("Customer Name");
        customer1.setCustomerType("Customer Type");
        customer1.setLoginStatus(true);
        customer1.setPassword("123");
        when(customerRepository.save((Customer) any())).thenReturn(customer1);
        when(customerRepository.findById((Integer) any())).thenReturn(ofResult);

        Customer customer2 = new Customer();
        customer2.setCustomerEmail("jane.doe@example.org");
        customer2.setCustomerId(123);
        customer2.setCustomerName("Customer Name");
        customer2.setCustomerType("Customer Type");
        customer2.setLoginStatus(true);
        customer2.setPassword("123");
        loginServices.updateCustomer(customer2);
        verify(customerRepository).save((Customer) any());
        verify(customerRepository, atLeast(1)).findById((Integer) any());
    }

    

    /**
     * Method under test: {@link LoginServices#viewCustomerList()}
     */
    @Test
    void testViewCustomerList() {
        ArrayList<Customer> customerList = new ArrayList<>();
        when(customerRepository.findAll()).thenReturn(customerList);
        List actualViewCustomerListResult = loginServices.viewCustomerList();
        assertSame(customerList, actualViewCustomerListResult);
        assertTrue(actualViewCustomerListResult.isEmpty());
        verify(customerRepository).findAll();
    }

}
 