package com.codedecode.restaurantlisting.mapper;

import com.codedecode.restaurantlisting.dto.RestaurantDTO;
import com.codedecode.restaurantlisting.entity.Restaurant;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface RestaurantMapper {

    RestaurantMapper INSTANCE = Mappers.getMapper(RestaurantMapper.class);

    Restaurant retaurantDTOToRestaurant(RestaurantDTO restaurantDTO);
    RestaurantDTO  restaurantToRestaurantDTO(Restaurant restaurant);

}
