package Project.UniApply.Hub.Models;

import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
public class Students extends AbstractEntity {
    public Students() {
    }


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
        this.Country = country;
    }

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


    public Students(String email, String pwHash, String phone, String firstName, String lastName, String country) {
        super(email, pwHash, phone);
        this.firstName = firstName;
        this.lastName = lastName;
        this.Country = country;
    }



}
