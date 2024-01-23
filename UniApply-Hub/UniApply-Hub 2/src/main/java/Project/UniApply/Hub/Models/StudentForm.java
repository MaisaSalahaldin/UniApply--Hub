
package Project.UniApply.Hub.Models;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.List;
import java.util.Objects;

@Entity
public class StudentForm {
   @Id
   @GeneratedValue
   private int id;

   @Override
   public boolean equals(Object o) {
       if (this == o) return true;
       if (!(o instanceof StudentForm)) return false;
       StudentForm that = (StudentForm) o;
       return id == that.id;
   }

   @Override
   public int hashCode() {
       return Objects.hash(id);
   }






    @NotBlank(message = "First name is required")
    @Size(min = 3, max = 45, message = "First name must be between 3 and 45 characters")
    private String firstName;

    @NotBlank(message = "Last name is required")
    @Size(min = 3, max = 45, message = "Last name must be between 3 and 45 characters")
    private String lastName;

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email. Try again.")
    private String email;

    @NotNull(message = "GPA is required")
    @DecimalMin(value = "0.0", message = "GPA must be equal to or greater than 0.0")
    @DecimalMax(value = "4.0", message = "GPA must be equal to or less than 4.0")
    private double gpa;

    // @NotBlank(message = "Education level is required")
    // @Enumerated(EnumType.STRING)
    private EducationLevel educationLevel;

    private String reference;

    private String coverLetter;


    private boolean usCitizen;
    @ManyToOne(cascade = CascadeType.PERSIST)
    private Students students;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public StudentForm() {
    }

    public StudentForm(String firstName, String lastName, String email, double gpa,
                       String reference, String coverLetter, boolean usCitizen) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.gpa = gpa;
        this.reference = reference;
        this.coverLetter = coverLetter;
        this.usCitizen = usCitizen;

    }
    public StudentForm(String firstName, String lastName, String email, double gpa,
                       String reference, String coverLetter, boolean usCitizen,Students students) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.gpa = gpa;
        this.reference = reference;
        this.coverLetter = coverLetter;
        this.usCitizen = usCitizen;
        this.students=students;

    }
    public enum EducationLevel {
        HIGH_SCHOOL,
        BACHELOR,
        MASTER,
        DOCTORATE,

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public double getGpa() {
        return gpa;
    }

    public void setGpa(double gpa) {
        this.gpa = gpa;
    }

    public EducationLevel getEducationLevel() {
        return educationLevel;
    }

    public void setEducationLevel(EducationLevel educationLevel) {
        this.educationLevel = educationLevel;
    }


    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getCoverLetter() {
        return coverLetter;
    }

    public void setCoverLetter(String coverLetter) {
        this.coverLetter = coverLetter;
    }

    public boolean isUsCitizen() {
        return usCitizen;
    }

    public void setUsCitizen(boolean usCitizen) {
        this.usCitizen = usCitizen;
    }
}


