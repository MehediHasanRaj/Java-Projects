package com.raj.rating.services;

import com.raj.rating.entities.Rating;

import java.util.List;

public interface RatingService {


    //create
    Rating create(Rating rating);


    //get all rating
    List<Rating> getRatings();

    // get all by one id's rating
    List<Rating> getRatingByUserId(String userId);

    // get all by hotel
    List<Rating> getRatingByHotelId(String hotelId);
}
