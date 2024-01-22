package Project.UniApply.Hub.Data;

import Project.UniApply.Hub.Models.StudentForm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentFormRepository  extends JpaRepository<StudentForm, Integer> {
    StudentForm findByEmail(String email);
}
