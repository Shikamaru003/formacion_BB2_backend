package formacion.bb2.dtos;

import formacion.bb2.models.Role;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserDto {

    private Long id;
    private String username;
    private String password;
    private Role role;

    public UserDto(Long id, String username, String password, Role role) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.role = role;
    }
}