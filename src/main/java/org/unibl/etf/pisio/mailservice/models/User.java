package org.unibl.etf.pisio.mailservice.models;

import lombok.Data;
import org.unibl.etf.pisio.mailservice.models.enums.Role;
import org.unibl.etf.pisio.mailservice.models.enums.UserStatus;

import java.io.Serializable;

@Data
public class User {
    private Integer id;
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private Role role;
    private UserStatus status;
}
