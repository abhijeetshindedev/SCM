package forms;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserForm {

    @NotBlank(message = "User name cannot be empty")
    private String name;

    @NotBlank(message = "Email cannot be empty")
    @Email(message = "Invalid email address")
    private String email;

    @NotBlank(message = "Password cannot be empty")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$", 
            message = "Password must be at least 8 characters long and contain at least one uppercase letter, one lowercase letter, one number and one special character")
    private String password;

    @Size(min = 10, max = 12, message = "Phone number must be at least 10 characters long")
    private String phoneNumber;
    private String about;
    





    //toString
    @Override
    public String toString() {
        return "UserForm [name=" + name + ", email=" + email + ", password=" + password + ", phoneNumber=" + phoneNumber  + ", about=" + about + "]";
    }

}
