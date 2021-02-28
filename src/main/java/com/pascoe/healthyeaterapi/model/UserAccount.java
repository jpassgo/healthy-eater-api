package com.pascoe.healthyeaterapi.model;

import javax.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
public class UserAccount {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer id;

  private String firstName;
  private String lastName;
  //  Todo: Create Custom emailAddress class for email address validation
  private String emailAddress;
  private LoginDetails loginDetails;


  @Override
  public String toString() {
    return String.format("firstName: %s \n" +
            "lastName: %s \n" +
            "emailAddress: %s \n" +
            "loginDetails: %s ",
            this.firstName,
            this.lastName,
            this.emailAddress,
            "Hidden");
  }
}
