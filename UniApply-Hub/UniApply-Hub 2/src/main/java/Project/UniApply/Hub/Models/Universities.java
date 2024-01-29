package Project.UniApply.Hub.Models;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;
@Entity
public class Universities extends AbstractEntity{
    @ManyToMany
    private final List<StudentForm> studentForms=new ArrayList<>();
    @ManyToMany(mappedBy = "universities")
    private final List<Students> student=new ArrayList<>();
    @NotBlank(message = "University Name is required")
    @Size(min = 3, max = 45, message = "University Name must be between 3 and 45 characters")
    private String UniversityName;
    @Size(min = 3, max = 45, message = "Address must be between 3 and 45 characters")
    @NotBlank(message = "Address is required")
    private String address;
    @Size(min = 3, max = 45, message = "description must be between 3 and 45 characters")
    @NotBlank(message = "description is required")
    private String description;
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
        this.city = city;
        this.zip = zip;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
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
    public List<Students> getStudent() {
        return student;
    }
    public String getUniversityName() {
        return UniversityName;
    }
    public void setUniversityName(String universityName) {
        UniversityName = universityName;
    }
    public void addStudents(Students students){
        this.student.add(students);
    }
    public List<StudentForm> getStudentForms() {
        return studentForms;
    }
    public void addStudentForm(StudentForm studentForm){
        this.studentForms.add(studentForm);
    }
}