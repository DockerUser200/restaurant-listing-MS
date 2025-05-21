package com.codedecode.restaurantlisting.controller;

import com.codedecode.restaurantlisting.dto.RestaurantDTO;
import com.codedecode.restaurantlisting.service.RestaurantService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class RestaurantControllerTest {

    @InjectMocks
    private RestaurantController restaurantController;

    @Mock
    private RestaurantService restaurantService;

//    @BeforeEach
//    public void setup() {
//        MockitoAnnotations.openMocks(this);
//    }

    @Test
    public void testFetchAllRestaurants () {

        //Arranging Data
        List<RestaurantDTO> mockResponseRestaurants = Arrays.asList(
                new RestaurantDTO(1, "Resturant1", "Address1", "City1", "RestaurantDescription1"),
                new RestaurantDTO(2, "Resturant2", "Address2", "City2", "RestaurantDescription2")
        );

        when(restaurantService.findAllRestaurants()).thenReturn(mockResponseRestaurants);

       //Act
        ResponseEntity<List<RestaurantDTO>> restaurants = restaurantController.fetchAllRestaurants();

        //Assert
        assertEquals(HttpStatus.OK, restaurants.getStatusCode());
        assertEquals(mockResponseRestaurants, restaurants.getBody());

        verify(restaurantService, times(1)).findAllRestaurants();
    }

    @Test
    public void testSaveRestaurant() {

        //Arrange Data
        RestaurantDTO mockRestaurantDTO = new RestaurantDTO(1, "Restaurant1", "Address1", "City1", "RestaurantDescription1");
        when(restaurantService.addRestaurantInDB(mockRestaurantDTO)).thenReturn(mockRestaurantDTO);

        //Act
        ResponseEntity<RestaurantDTO> restaurantDTO = restaurantController.saveRestaurant(mockRestaurantDTO);

        //Assert
        assertEquals(HttpStatus.CREATED, restaurantDTO.getStatusCode());
        assertEquals(mockRestaurantDTO, restaurantDTO.getBody());

        verify(restaurantService, times(1)).addRestaurantInDB(mockRestaurantDTO);

    }

    @Test
    public void testFetchRestaurantById() {

        //Arrange Data
        Integer mockRestaurantId = 1;
        RestaurantDTO mockResponse = new RestaurantDTO(1, "Restaurant1", "Address1", "City1", "RestaurantDescription1");
        when(restaurantService.findRestaurantById(mockRestaurantId)).thenReturn(new ResponseEntity<>(mockResponse, HttpStatus.OK));

        //Act
        ResponseEntity<RestaurantDTO> restaurantDTO = restaurantController.fetchRestaurantById(mockRestaurantId);

        //Assert
        assertEquals(HttpStatus.OK, restaurantDTO.getStatusCode());
        assertEquals(mockResponse, restaurantDTO.getBody());

        verify(restaurantService, times(1)).findRestaurantById(mockRestaurantId);
    }



}
