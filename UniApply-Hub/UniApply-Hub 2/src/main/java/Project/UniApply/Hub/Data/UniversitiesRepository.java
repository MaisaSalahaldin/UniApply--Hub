package Project.UniApply.Hub.Data;

import Project.UniApply.Hub.Models.Students;
import Project.UniApply.Hub.Models.Universities;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UniversitiesRepository extends CrudRepository<Universities, Integer> {
    Universities findByEmail(String email);
    @Query(value = "select * from user where UniversityName= ?1", nativeQuery = true)
    Universities findByUniversityName(String UniversityName);
}
