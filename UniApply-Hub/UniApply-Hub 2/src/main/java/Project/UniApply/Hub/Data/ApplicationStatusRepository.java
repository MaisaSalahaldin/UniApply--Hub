package Project.UniApply.Hub.Data;

import Project.UniApply.Hub.Models.ApplicationStatus;
import Project.UniApply.Hub.Models.StudentForm;
import Project.UniApply.Hub.Models.Universities;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ApplicationStatusRepository extends CrudRepository<ApplicationStatus, Integer> {

    @Query(value = "SELECT * FROM applicationstatus\n" +
            "join universities on universities.id=applicationstatus.universities_id\n" +
            "join studentform on studentform.id=applicationstatus.studentForm_id\n" +
            "where studentform.student_id=?", nativeQuery = true)
    List<ApplicationStatus> findByUniversity(int id);

@Query(value = "SELECT * FROM applicationstatus where applicationstatus.studentForm_id= ?1 and applicationstatus.universities_id= ?2",nativeQuery = true)
    Optional<ApplicationStatus> findByStudentFormAndUniversities(StudentForm studentForm, Universities universities);



    @Query(value = "SELECT * FROM applicationstatus\n" +
            "            join universities on universities.id=applicationstatus.universities_id\n" +
            "            join studentform on studentform.id=applicationstatus.studentForm_id\n" +
            "            where applicationstatus.universities_id=?1",nativeQuery = true)
    List<ApplicationStatus> findFormsByUniversityId(int id);
}
