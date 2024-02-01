package Project.UniApply.Hub.Models.DTO;

import Project.UniApply.Hub.Models.Students;
import Project.UniApply.Hub.Models.Universities;
import com.sun.istack.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SelectedUniversityDTO {

    List<Universities> universities=new ArrayList<>();
    @NotNull
    Universities university;
    @NotNull
    Students students;

    public Universities getUniversity() {
        return university;
    }

    public void setUniversity(Universities university) {
        this.university = university;
    }

    public Students getStudents() {
        return students;
    }

    public void setStudents(Students students) {
        this.students = students;
    }

    public SelectedUniversityDTO() {
    }
    public Universities getUniversity(Universities university){
        return this.universities.get(universities.indexOf(university));
    }
    public List<Universities> getUniversities() {
        return universities;
    }

    public void setUniversities(List<Universities> universities) {
        this.universities = universities;
    }
    public void addUniversity(Universities universities){
        this.universities.add(universities);
    }
}
