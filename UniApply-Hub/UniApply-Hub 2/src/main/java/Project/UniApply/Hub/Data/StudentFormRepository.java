package Project.UniApply.Hub.Data;


import Project.UniApply.Hub.Models.StudentForm;
import org.springframework.data.jpa.repository.Query;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentFormRepository  extends CrudRepository<StudentForm, Integer> {
    StudentForm findByEmail(String email);

    Optional<StudentForm> findByStudentId(Integer studentId);
    @Query(value = "SELECT * FROM studentform where studentform.student_id=?1", nativeQuery = true)
    StudentForm findStudentById(int id);
}
