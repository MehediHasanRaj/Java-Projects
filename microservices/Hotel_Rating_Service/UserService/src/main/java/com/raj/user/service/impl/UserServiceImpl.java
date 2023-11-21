package com.raj.user.service.impl;

import com.raj.user.service.entities.Hotel;
import com.raj.user.service.entities.Rating;
import com.raj.user.service.entities.User;
import com.raj.user.service.exceptions.ResourceNotFoundException;
import com.raj.user.service.repositories.UserRepository;
import com.raj.user.service.services.UserService;
import lombok.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository; // for saving the data into database

    @Autowired
    private RestTemplate restTemplate; // for autowiring we need a bean, and we should make it

    private Logger logger = LoggerFactory.getLogger(UserService.class);
    @Override
    public User saveUser(User user) {
        String randomUserId = UUID.randomUUID().toString(); // generate unique user id
        user.setUserId(randomUserId);
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUser() {
        //can implement the Rating service calling using rest template
        return userRepository.findAll();
    }

    // get single user
    @Override
    public User getUser(String userId) {
        // get user from database with the help of user repository
        User user =  userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User with given id is not found!! :"+userId));

        //fetch rating of the user from the RATING SERVICE
        Rating[] ratingOfUser = restTemplate.getForObject("http://RATING-SERVICE/ratings/users/"+user.getUserId(), Rating[].class);
        logger.info("{}",ratingOfUser);

        List<Rating> ratings = Arrays.stream(ratingOfUser).toList();

        // getting the rating of all the hotels
        List<Rating> ratingList = ratings.stream().map(rating -> {
            //api call to hotel service to get the hotel
            ResponseEntity<Hotel> forEntity = restTemplate.getForEntity("http://HOTEL-SERVICE/hotels/"+rating.getHotelId(), Hotel.class);
            Hotel hotel = forEntity.getBody();

            // set the hotel to rating
            rating.setHotel(hotel);

            // return the rating
            return rating;

        }).collect(Collectors.toList());


        user.setRatings(ratingList);
        return user;
    }
}
