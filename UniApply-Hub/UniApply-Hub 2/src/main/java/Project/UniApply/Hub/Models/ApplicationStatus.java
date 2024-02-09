package Project.UniApply.Hub.Models;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class ApplicationStatus {


    @Id
    @GeneratedValue
    private int id;

    public int getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof StudentForm)) return false;
        StudentForm that = (StudentForm) o;
        return id == this.id;
    }
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }



    @OneToOne
private StudentForm studentForm;

    public Universities getUniversities() {
        return universities;
    }

    public void setUniversities(Universities universities) {
        this.universities = universities;
    }

    @OneToOne
    private Universities universities;
    public enum ApplicationStatusType {
        PENDING,
        APPROVED,
        DENIED
    }
private ApplicationStatusType applicationStatusType;

    public ApplicationStatusType getApplicationStatusType() {
        return applicationStatusType;
    }

    public void setApplicationStatusType(ApplicationStatusType applicationStatusType) {
        this.applicationStatusType = applicationStatusType;
    }

    public ApplicationStatus() {

    }

    public StudentForm getStudentForm() {
        return studentForm;
    }

    public void setStudentForm(StudentForm studentForm) {
        this.studentForm = studentForm;
    }
}
