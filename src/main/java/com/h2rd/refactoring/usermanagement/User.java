package com.h2rd.refactoring.usermanagement;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// Added name of the root element. In this I have taken "user" as root element
@XmlRootElement(name = "user")
@Data // Use Lombok to generate getters and setters and constructor automatically
@AllArgsConstructor
@NoArgsConstructor
public class User {
    //Added private access modifier since I do not want to expose the instance variable to outside world.
    private String name;
    private String email;
    private List<String> roles;
}
