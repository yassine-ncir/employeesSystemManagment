package apps.employee.services;

import apps.employee.model.Employe;
import java.util.List;

public interface EmployeeService {
    void addEmployee(Employe employee);
    void removeEmployee(Employe employee);
    void updateEmployee(Employe oldEmployee, Employe newEmployee);
    List<Employe> getAllEmployees();
    Employe getEmployeeById(Long id);
}
