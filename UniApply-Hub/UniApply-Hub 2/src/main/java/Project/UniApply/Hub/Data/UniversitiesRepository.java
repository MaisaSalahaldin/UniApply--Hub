package Project.UniApply.Hub.Data;

import Project.UniApply.Hub.Models.Students;
import Project.UniApply.Hub.Models.Universities;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UniversitiesRepository extends CrudRepository<Universities, Integer> {
    List<Universities> findAll();
    Universities findByEmail(String email);
    @Query(value = "select * from universities where UniversityName= ?1", nativeQuery = true)
    Universities findByUniversityName(String UniversityName);

//    @Query("SELECT * FROM universitiesWHERE id NOT IN (SELECT university_id FROM stu_uni  WHERE student_id =?1)")
//    Universities findUniversitiesNotApplied(int studentId);

    @Query(value = " SELECT * FROM Universities  WHERE Universities.id NOT IN (SELECT stuform_uni.university_id FROM stuform_uni  left join studentform on  studentform.id=stuform_uni .studentForm_id WHERE studentform.student_id =?1)", nativeQuery = true)
    List<Universities> findUniversitiesNotApplied(int studentId);
}
