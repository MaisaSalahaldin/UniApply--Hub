package Project.UniApply.Hub.Models;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
public class Universities extends AbstractEntity{



        @ManyToMany
        private List<StudentForm> studentForms;

        @ManyToMany
        private List<Students> students;




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

public Universities(){

}
    public Universities(String email, String pwHash, String phone, String universityName, String address, String description,  String city, String zip) {
        super(email, pwHash, phone);
        this.UniversityName = universityName;
        this.address = address;
        this.description = description;
        //this.rank = rank;
        this.city = city;
        this.zip = zip;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }


    public List<StudentForm> getStudentForms() {
        return studentForms;
    }

    public void setStudentForms(List<StudentForm> studentForms) {
        this.studentForms = studentForms;
    }

    public List<Students> getStudents() {
        return students;
    }

    public void setStudents(List<Students> students) {
        this.students = students;
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
