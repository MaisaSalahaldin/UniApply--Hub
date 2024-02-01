package Project.UniApply.Hub.Data;

import Project.UniApply.Hub.Models.StudentForm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentFormRepository  extends CrudRepository<StudentForm, Integer> {
    StudentForm findByEmail(String email);

@Query(value = "SELECT * FROM studentform where studentform.student_id=?1", nativeQuery = true)
    StudentForm findStudentById(int id);
}
