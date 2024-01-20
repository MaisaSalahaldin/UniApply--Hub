package Project.UniApply.Hub.Data;

import Project.UniApply.Hub.Models.Students;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentsRepository  extends CrudRepository<Students, Integer> {
    Students findByEmail(String email);
}
