package forms;

import lombok.Data;

@Data
public class UserForm {

    private String name;
    private String email;
    private String password;
    private String phoneNumber;
    private String about;
    
    //toString
    @Override
    public String toString() {
        return "UserForm [name=" + name + ", email=" + email + ", password=" + password + ", phoneNumber=" + phoneNumber  + ", about=" + about + "]";
    }

}
