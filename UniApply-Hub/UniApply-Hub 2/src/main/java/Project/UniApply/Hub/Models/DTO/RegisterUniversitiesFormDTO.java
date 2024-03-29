package Project.UniApply.Hub.Models.DTO;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class RegisterUniversitiesFormDTO extends LoginFormDTO{
    @NotBlank(message = "University Name is required")
    @Size(min = 3, max = 45, message = "University Name must be between 3 and 45 characters")
    private String UniversityName;

    @Size(min = 3, max = 45, message = "Address must be between 3 and 45 characters")
    @NotBlank(message = "Address is required")
    private String address;
    @Size(min = 3, max = 45, message = "description must be between 3 and 45 characters")
    @NotBlank(message = "description is required")
    private String description;
//    @Size(min = 3, max = 10, message = "rank must be rank 3 and 10 characters")
//    @NotBlank(message = "Rank is required")
//    private String rank;
    @NotBlank(message = "City name is required")
    private String city;

    @NotBlank(message = "Zip code  is required")
    private String zip ;

    @NotBlank(message = "Phone number is required")
    private String phone;

    @NotBlank(message = " verifyPassword is required")
    private String verifyPassword;


    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }


    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getVerifyPassword() {
        return verifyPassword;
    }

    public void setVerifyPassword(String verifyPassword) {
        this.verifyPassword = verifyPassword;
    }
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

//    public String getRank() {
//        return rank;
//    }
//
//    public void setRank(String rank) {
//        this.rank = rank;
//    }
    public String getUniversityName() {
        return UniversityName;
    }

    public void setUniversityName(String universityName) {
        this.UniversityName = universityName;
    }
}
