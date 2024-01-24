package Project.UniApply.Hub.Data;

import Project.UniApply.Hub.Models.Students;
import Project.UniApply.Hub.Models.Universities;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UniversitiesRepository extends CrudRepository<Universities, Integer> {
    List<Universities> findAll();
    Universities findByEmail(String email);
    @Query(value = "select * from universities where UniversityName= ?1", nativeQuery = true)
    Universities findByUniversityName(String UniversityName);

    List<Universities> findAllById(List<Universities> universities);
}
