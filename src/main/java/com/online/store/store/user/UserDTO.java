package com.online.store.store.user;

import jakarta.persistence.Table;
import lombok.*;

@Getter @Setter
@Data @NoArgsConstructor
@AllArgsConstructor
@Table(name="users")
public class UserDTO {
    private String username;
    private String email;
    private String password;
}
