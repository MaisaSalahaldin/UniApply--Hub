package Project.UniApply.Hub.Models.DTO;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class RegisterStudentsFormDTO extends LoginFormDTO{

    @NotBlank(message = "First name is required")
    @Size(min = 3, max = 45, message = "First name must be between 3 and 45 characters")
    private String firstName;

    @Size(min = 3, max = 45, message = "Last name must be between 3 and 45 characters")
    @NotBlank(message = "Last name is required")
    private String lastName;


    @NotBlank(message = "v name is required")
    private String Country;

    public String getCountry() {
        return Country;
    }

    public void setCountry(String country) {
        Country = country;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @NotBlank(message = "Phone number is required")
    private String phone;

    @NotBlank(message = " verifyPassword is required")
    private String verifyPassword;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }



    public String getVerifyPassword() {
        return verifyPassword;
    }

    public void setVerifyPassword(String verifyPassword) {
        this.verifyPassword = verifyPassword;
    }

}
