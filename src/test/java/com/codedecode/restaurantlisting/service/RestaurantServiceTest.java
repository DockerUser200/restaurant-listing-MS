package com.codedecode.restaurantlisting.service;

import com.codedecode.restaurantlisting.dto.RestaurantDTO;
import com.codedecode.restaurantlisting.entity.Restaurant;
import com.codedecode.restaurantlisting.mapper.RestaurantMapper;
import com.codedecode.restaurantlisting.repository.RestaurantRepository;
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
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class RestaurantServiceTest {

//    public void setup(){
//        MockitoAnnotations.openMocks(this);
//    }

    @InjectMocks
    RestaurantService restaurantService;

    @Mock
    RestaurantRepository restaurantRepository;

    @Test
    public void testFindAllRestaurants() {

        //Arrange Data
        List<Restaurant> mockRestaurantsList = Arrays.asList(new Restaurant(1, "Restaurant1", "Address1", "City1", "RestaurantDescription1"),
                new Restaurant(2, "Restaurant2", "Address2", "City2", "RestaurantDescription2"));

        when(restaurantRepository.findAll()).thenReturn(mockRestaurantsList);

        //Act
        List<RestaurantDTO> restaurantDTOList = restaurantService.findAllRestaurants();

        //Assertion
        assertEquals(mockRestaurantsList.size(), restaurantDTOList.size());
        for (int i=0; i < mockRestaurantsList.size(); i++) {
            RestaurantDTO restaurantDTO = RestaurantMapper.INSTANCE.restaurantToRestaurantDTO(mockRestaurantsList.get(i));
            assertEquals(restaurantDTO, restaurantDTOList.get(i));
        }

        verify(restaurantRepository, times(1)).findAll();
    }

    @Test
    public void testAddRestaurantInDB() {
        //Arrange Data
        Restaurant mockedRestaurant = new Restaurant(1, "Restaurant1", "Address1", "City1", "RestaurantDescription1");
        RestaurantDTO mockedRestaurantDTO = new RestaurantDTO(1, "Restaurant1", "Address1", "City1", "RestaurantDescription1");

        when(restaurantRepository.save(mockedRestaurant)).thenReturn(mockedRestaurant);

        //Act
        RestaurantDTO expactedRestaurantDTO=  restaurantService.addRestaurantInDB(RestaurantMapper.INSTANCE.restaurantToRestaurantDTO(mockedRestaurant));

        //Assertion
        assertEquals(mockedRestaurantDTO, expactedRestaurantDTO);

        verify(restaurantRepository, times(1)).save(mockedRestaurant);
    }

    @Test
    public void testFindRestaurantById_AvailableId() {

        // Arrange Data
        Integer mockedRestaurantId = 1;
        Restaurant mockedRestaurant = new Restaurant(1, "Restaurant1", "Address1", "City1", "RestaurantDescription1");
        when(restaurantRepository.findById(mockedRestaurantId)).thenReturn(Optional.of(mockedRestaurant));

       // Act
        ResponseEntity<RestaurantDTO> expactedReastaurantDTO = restaurantService.findRestaurantById(mockedRestaurantId);

        // Assertion
        assertEquals(HttpStatus.OK, expactedReastaurantDTO.getStatusCode());
        assertEquals(mockedRestaurantId, expactedReastaurantDTO.getBody().getId());

        verify(restaurantRepository, times(1)).findById(mockedRestaurantId);
    }

    @Test
    public void testFindRestaurantById_NotAvailableId() {
        // Arrange data
        Integer mockRestaurantId = 1;
        when(restaurantRepository.findById(mockRestaurantId)).thenReturn(Optional.empty());

        //Act
        ResponseEntity<RestaurantDTO> expactedRestaurantDTO = restaurantService.findRestaurantById(mockRestaurantId);

        // Assertion
        assertEquals(HttpStatus.NOT_FOUND, expactedRestaurantDTO.getStatusCode());

        verify(restaurantRepository, times(1)).findById(mockRestaurantId);
    }
}
