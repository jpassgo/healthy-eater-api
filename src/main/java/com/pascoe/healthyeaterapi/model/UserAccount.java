package com.pascoe.healthyeaterapi.model;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class UserAccount {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer id;

  private String firstName;
  private String lastName;
  @Embedded private UserCredentials userCredentials;
  //  Todo: Create Custom emailAddress class for email address validation
  private String emailAddress;

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
