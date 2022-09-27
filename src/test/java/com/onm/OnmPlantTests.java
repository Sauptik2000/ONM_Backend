package com.onm;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
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

import com.onm.entity.Customer;
import com.onm.entity.Plant;
import com.onm.repositories.CustomerRepository;
import com.onm.repositories.PlantRepository;
import com.onm.services.PlantServices;

@ContextConfiguration(classes = {PlantServices.class})
@ExtendWith(SpringExtension.class)
public class OnmPlantTests {
	@MockBean
    private CustomerRepository customerRepository;

    @MockBean
    private PlantRepository plantRepository;

    @Autowired
    private PlantServices plantServices;

    /**
     * Method under test: {@link PlantServices#addPlant(Plant, int)}
     */
    @Test
    void testAddPlant() {
        Customer customer = new Customer();
        customer.setCustomerEmail("jane.doe@example.org");
        customer.setCustomerId(123);
        customer.setCustomerName("Customer Name");
        customer.setCustomerType("Customer Type");
        customer.setLoginStatus(true);
        customer.setPassword("123");
        Optional<Customer> ofResult = Optional.of(customer);
        when(customerRepository.findById((Integer) any())).thenReturn(ofResult);

        Plant plant = new Plant();
        plant.setPlantCost(10.0d);
        plant.setPlantDesccription("Plant Desccription");
        plant.setPlantId(123);
        plant.setPlantName("Plant Name");

        Plant plant1 = new Plant();
        plant1.setPlantCost(10.0d);
        plant1.setPlantDesccription("Plant Desccription");
        plant1.setPlantId(123);
        plant1.setPlantName("Plant Name");
        when(plantRepository.findByplantName((String) any())).thenReturn(plant);
        when(plantRepository.save((Plant) any())).thenReturn(plant1);

        Plant plant2 = new Plant();
        plant2.setPlantCost(10.0d);
        plant2.setPlantDesccription("Plant Desccription");
        plant2.setPlantId(123);
        plant2.setPlantName("Plant Name");
        assertFalse(plantServices.addPlant(plant2, 123));
        verify(customerRepository).findById((Integer) any());
        verify(plantRepository).findByplantName((String) any());
    }

   

    /**
     * Method under test: {@link PlantServices#deletePlant(int, int)}
     */
    @Test
    void testDeletePlant() {
        Customer customer = new Customer();
        customer.setCustomerEmail("jane.doe@example.org");
        customer.setCustomerId(123);
        customer.setCustomerName("Customer Name");
        customer.setCustomerType("Customer Type");
        customer.setLoginStatus(true);
        customer.setPassword("123");
        Optional<Customer> ofResult = Optional.of(customer);
        when(customerRepository.findById((Integer) any())).thenReturn(ofResult);

        Plant plant = new Plant();
        plant.setPlantCost(10.0d);
        plant.setPlantDesccription("Plant Desccription");
        plant.setPlantId(123);
        plant.setPlantName("Plant Name");
        Optional<Plant> ofResult1 = Optional.of(plant);
        doNothing().when(plantRepository).deleteById((Integer) any());
        when(plantRepository.findById((Integer) any())).thenReturn(ofResult1);
        plantServices.deletePlant(123, 123);
        verify(customerRepository).findById((Integer) any());
        verify(plantRepository).findById((Integer) any());
        verify(plantRepository).deleteById((Integer) any());
        assertEquals("User not found", plantServices.msgUNF);
        assertEquals("Access Denied", plantServices.msgAD);
    }

    
    /**
     * Method under test: {@link PlantServices#updatePlant(Plant, int)}
     */
    @Test
    void testUpdatePlant() {
        Customer customer = new Customer();
        customer.setCustomerEmail("jane.doe@example.org");
        customer.setCustomerId(123);
        customer.setCustomerName("Customer Name");
        customer.setCustomerType("Customer Type");
        customer.setLoginStatus(true);
        customer.setPassword("123");
        Optional<Customer> ofResult = Optional.of(customer);
        when(customerRepository.findById((Integer) any())).thenReturn(ofResult);

        Plant plant = new Plant();
        plant.setPlantCost(10.0d);
        plant.setPlantDesccription("Plant Desccription");
        plant.setPlantId(123);
        plant.setPlantName("Plant Name");
        Optional<Plant> ofResult1 = Optional.of(plant);

        Plant plant1 = new Plant();
        plant1.setPlantCost(10.0d);
        plant1.setPlantDesccription("Plant Desccription");
        plant1.setPlantId(123);
        plant1.setPlantName("Plant Name");
        when(plantRepository.save((Plant) any())).thenReturn(plant1);
        when(plantRepository.findById((Integer) any())).thenReturn(ofResult1);

        Plant plant2 = new Plant();
        plant2.setPlantCost(10.0d);
        plant2.setPlantDesccription("Plant Desccription");
        plant2.setPlantId(123);
        plant2.setPlantName("Plant Name");
        Plant actualUpdatePlantResult = plantServices.updatePlant(plant2, 123);
        assertSame(plant, actualUpdatePlantResult);
        assertEquals(10.0d, actualUpdatePlantResult.getPlantCost());
        assertEquals("Plant Name", actualUpdatePlantResult.getPlantName());
        assertEquals("Plant Desccription", actualUpdatePlantResult.getPlantDesccription());
        verify(customerRepository).findById((Integer) any());
        verify(plantRepository).save((Plant) any());
        verify(plantRepository).findById((Integer) any());
    }

    
    /**
     * Method under test: {@link PlantServices#viewPlant()}
     */
    @Test
    void testViewPlant() {
        ArrayList<Plant> plantList = new ArrayList<>();
        when(plantRepository.findAll()).thenReturn(plantList);
        List actualViewPlantResult = plantServices.viewPlant();
        assertSame(plantList, actualViewPlantResult);
        assertTrue(actualViewPlantResult.isEmpty());
        verify(plantRepository).findAll();
    }

}

