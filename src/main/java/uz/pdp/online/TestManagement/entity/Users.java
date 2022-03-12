package uz.pdp.online.TestManagement.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.pdp.online.TestManagement.utils.UserRole;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Users {
    private Integer id;
    private String name;
    private String userName;
    private String password;
    private UserRole userRole=UserRole.USER;
    private String role;
    private String phone;
    private boolean active;
    private boolean isDeleted;

    public Users(String userName, String password, UserRole userRole) {
        this.userName = userName;
        this.password = password;
        this.userRole = userRole;
    }

    public Users(String name, String phone, UserRole role, boolean active, boolean isDeleted, String password, String userName) {
    }

    public Users(String userName, String password, UserRole role, String phone, String name) {
    }
}
