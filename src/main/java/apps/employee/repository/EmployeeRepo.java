package apps.employee.repository;

import apps.employee.model.Employe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepo extends JpaRepository<Employe,Long> {
    @Override
    List<Employe> findAll();
    Employe findEmployeeById(Long id);
}
