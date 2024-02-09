package Project.UniApply.Hub.Data;

import Project.UniApply.Hub.Models.ApplicationStatus;
import Project.UniApply.Hub.Models.StudentForm;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentFormRepository  extends CrudRepository<StudentForm, Integer> {
    StudentForm findByEmail(String email);

@Query(value = "SELECT * FROM studentform where studentform.student_id=?1", nativeQuery = true)
    StudentForm findStudentById(int id);
//    @Query(value =" SELECT studentform.firstName as firstName,studentform.lastName as lastName, " +
//            " studentform.email as email,studentform.usCitizen as usCitizen" +
//            " ,studentform.gpa as gpa ,studentform.reference as reference " +
//            ",studentform.educationLevel as educationLevel ,studentform.coverLetter as coverLetter " +
//            ",applicationstatus.applicationStatusType  FROM studentform\n" +
//            " join applicationstatus on studentform.id =applicationstatus.studentForm_id\n" +
//            " JOIN stuform_uni ON applicationstatus.studentForm_id = stuform_uni.studentform_id\n" +
//            " JOIN universities ON stuform_uni.university_id = universities.id\n" +
//            " WHERE universities.id = ?1", nativeQuery = true)
//            @Query(value ="SELECT * FROM studentform\n" +
//            "JOIN stuform_uni ON studentform.id = stuform_uni.studentform_id\n" +
//            "join applicationstatus on studentform.id =applicationstatus.studentForm_id\n" +
//            "JOIN universities ON stuform_uni.university_id = universities.id\n" +
//            "WHERE universities.id = ?1", nativeQuery = true)

}
