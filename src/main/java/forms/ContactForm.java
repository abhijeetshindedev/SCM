package forms;

import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ContactForm {
    @NotBlank(message = "Contact name cannot be empty")
    private String name;

    @NotBlank(message = "Email cannot be empty")
    @Email(message = "Invalid email address")
    private String email;
    
    private Date birthDay;

    @Size(min = 10, max = 12, message = "Phone number must be at least 10 characters long")
    @NotBlank(message = "Phone number cannot be empty")
    private String phoneNumber;

    private String about;

    private String address;

    private Boolean fevorite;

    private String websiteLink;

    private String linkedInLink;

    private MultipartFile contactPicture;

    private String profilePictureURL;

    @Override
    public String toString() {
        return "ContactForm [name=" + name + ", email=" + email + ", birthDay=" + birthDay + ", phoneNumber="
                + phoneNumber + ", about=" + about + ", address=" + address + ", fevorite=" + fevorite
                + ", websiteLink=" + websiteLink + ", linkedInLink=" + linkedInLink + ", contactPicture="
                + contactPicture + "]";
    }

}
