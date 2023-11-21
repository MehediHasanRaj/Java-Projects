package com.raj.user.service.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "micro_users")
public class User {
    @Id
    @Column(name="ID")
    private String userId;
    @Column(name="NAME",length = 20)
    private String name;
    @Column(name="EMAIL")
    private String email;
    @Column(name="ABOUT")
    private String about;



    //other class
    @Transient // we use this annotation. it does not store in the database
    private List<Rating> ratings = new ArrayList<>(); // we will fetch this rating from rating microservice class
}
