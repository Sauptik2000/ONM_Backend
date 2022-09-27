package com.onm;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
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
import com.onm.repositories.CoupanRepository;
import com.onm.repositories.CustomerRepository;
import com.onm.services.CoupanServices;


@ContextConfiguration(classes = {CoupanServices.class})
@ExtendWith(SpringExtension.class)
public class OnmCoupanTests {
	    @MockBean
	    private CoupanRepository coupanRepository;

	    @Autowired
	    private CoupanServices coupanServices;

	    @MockBean
	    private CustomerRepository customerRepository;

	    /**
	     * Method under test: {@link CoupanServices#addCoupan(Coupan, int)}
	     */
	    @Test
	    void testAddCoupan() {
	        Coupan coupan = new Coupan();
	        coupan.setCoupanDescription("Coupan Description");
	        coupan.setCoupanId(123);
	        coupan.setCoupanName("Coupan Name");
	        coupan.setCoupanType("Coupan Type");
	        coupan.setCouponValue(10.0d);
	        when(coupanRepository.save((Coupan) any())).thenReturn(coupan);

	        Customer customer = new Customer();
	        customer.setCustomerEmail("jane.doe@example.org");
	        customer.setCustomerId(123);
	        customer.setCustomerName("Customer Name");
	        customer.setCustomerType("Customer Type");
	        customer.setLoginStatus(true);
	        customer.setPassword("123");
	        Optional<Customer> ofResult = Optional.of(customer);
	        when(customerRepository.findById((Integer) any())).thenReturn(ofResult);

	        Coupan coupan1 = new Coupan();
	        coupan1.setCoupanDescription("Coupan Description");
	        coupan1.setCoupanId(123);
	        coupan1.setCoupanName("Coupan Name");
	        coupan1.setCoupanType("Coupan Type");
	        coupan1.setCouponValue(10.0d);
	        assertTrue(coupanServices.addCoupan(coupan1, 123));
	        verify(coupanRepository).save((Coupan) any());
	        verify(customerRepository).findById((Integer) any());
	    }

	    /**
	     * Method under test: {@link CoupanServices#viewCoupan()}
	     */
	    @Test
	    void testViewCoupan() {
	        ArrayList<Coupan> coupanList = new ArrayList<>();
	        when(coupanRepository.findAll()).thenReturn(coupanList);
	        List actualViewCoupanResult = coupanServices.viewCoupan();
	        assertSame(coupanList, actualViewCoupanResult);
	        assertTrue(actualViewCoupanResult.isEmpty());
	        verify(coupanRepository).findAll();
	    }

	    /**
	     * Method under test: {@link CoupanServices#deleteCoupan(int)}
	     */
	    @Test
	    void testDeleteCoupan() {
	        Coupan coupan = new Coupan();
	        coupan.setCoupanDescription("Coupan Description");
	        coupan.setCoupanId(123);
	        coupan.setCoupanName("Coupan Name");
	        coupan.setCoupanType("Coupan Type");
	        coupan.setCouponValue(10.0d);
	        Optional<Coupan> ofResult = Optional.of(coupan);
	        doNothing().when(coupanRepository).deleteById((Integer) any());
	        when(coupanRepository.findById((Integer) any())).thenReturn(ofResult);
	        coupanServices.deleteCoupan(123);
	        verify(coupanRepository).findById((Integer) any());
	        verify(coupanRepository).deleteById((Integer) any());
	    }
}


