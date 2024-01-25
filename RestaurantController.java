package com.example.helloworld;

import com.example.helloworld.request.RestaurantModel;
import com.example.helloworld.request.RestaurantQueryModel;
import com.example.helloworld.request.UserModel;
import com.example.helloworld.request.UserQueryModel;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/restaurants")
public class RestaurantController {

    @Autowired
    private RestaurantRepository restaurantRepository;
    private ModelMapper modelMapper = new ModelMapper();

//    @GetMapping
//    public List<Restaurant> getRestaurant(RestaurantQueryModel restaurantQueryModel) {
//        Restaurant user = modelMapper.map(restaurantQueryModel, Restaurant.class);
//        Example userExample = Example.of(user);
//        List<Restaurant> restaurants = restaurantRepository.findAll(userExample);
//        return restaurants;
//    }

    @GetMapping
    public List<Restaurant> getRestaurant() {
        List<Restaurant> restaurants = restaurantRepository.findAll();
        return restaurants;
    }

    @PostMapping()
    public RestaurantModel registerRestaurant(@RequestBody RestaurantModel restaurantModel) {
        Restaurant restaurantRequest = modelMapper.map(restaurantModel, Restaurant.class);
        Restaurant restaurant = restaurantRepository.save(restaurantRequest);
        return modelMapper.map(restaurant, RestaurantModel.class);
    }

    @PatchMapping(value = "/{id}")
    public RestaurantModel patchRestaurant(@PathVariable(value = "id") Long id, @RequestBody String jsonPatch) throws ApplicationException {
        Restaurant restaurant = returnRestaurantIfExists(id);
        restaurant = ApiUtils.patch(restaurant, jsonPatch, Restaurant.class);
        Restaurant updatedRestaurant = restaurantRepository.save(restaurant);
        return modelMapper.map(updatedRestaurant, RestaurantModel.class);
    }

    private Restaurant returnRestaurantIfExists(Long id) {
        Restaurant restaurant = restaurantRepository.findById(id).orElse(null);
        if (ObjectUtils.isEmpty(restaurant)) {
            throw new NotFoundException("No restaurant exists with given search criteria.", "USER_NOT_FOUND_EXCEPTION", null);
        }
        return restaurant;
    }

}
