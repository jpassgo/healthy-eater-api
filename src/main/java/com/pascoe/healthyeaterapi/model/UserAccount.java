package com.pascoe.healthyeaterapi.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity
@AllArgsConstructor
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
    return String.format(
        "id: %s \n"
            + "firstName: %s \n"
            + "lastName: %s \n"
            + "emailAddress: %s \n"
            + "loginDetails: %s ",
        this.id, this.firstName, this.lastName, this.emailAddress, "Hidden");
  }
}
