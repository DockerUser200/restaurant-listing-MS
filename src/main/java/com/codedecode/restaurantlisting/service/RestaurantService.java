package com.codedecode.restaurantlisting.service;

import com.codedecode.restaurantlisting.dto.RestaurantDTO;
import com.codedecode.restaurantlisting.entity.Restaurant;
import com.codedecode.restaurantlisting.mapper.RestaurantMapper;
import com.codedecode.restaurantlisting.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.Optional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RestaurantService {

    @Autowired
    RestaurantRepository restaurantRepository;

    public List<RestaurantDTO>  findAllRestaurants() {
        List<Restaurant> restaurants = restaurantRepository.findAll();
        //map lt to list of DTOs

        List<RestaurantDTO> restaurantDTOList =
        restaurants.stream().map(RestaurantMapper.INSTANCE::restaurantToRestaurantDTO).toList(); ;
        return restaurantDTOList;
    }

    public RestaurantDTO addRestaurantInDB(RestaurantDTO restaurantDTO) {
       Restaurant restaurant =  RestaurantMapper.INSTANCE.retaurantDTOToRestaurant(restaurantDTO);
        Restaurant savedRestaurant =  restaurantRepository.save(restaurant);
        return RestaurantMapper.INSTANCE.restaurantToRestaurantDTO(savedRestaurant);
    }


    public ResponseEntity<RestaurantDTO> findRestaurantById(Integer id) {
        Optional<Restaurant> restaurant =  restaurantRepository.findById(id);

//        if (restaurant.isPresent()) {
//            return new ResponseEntity<>(RestaurantMapper.INSTANCE.restaurantToRestaurantDTO(restaurant.get()), HttpStatus.OK);
//        }
//        return new ResponseEntity(null, HttpStatus.NOT_FOUND);
        return restaurant.map(value -> new ResponseEntity<>(RestaurantMapper.INSTANCE.restaurantToRestaurantDTO(value), HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(null, HttpStatus.NOT_FOUND));
    }

}


