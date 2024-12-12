package apps.employee.services;

import apps.employee.model.Employe;
import apps.employee.repository.EmployeeRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService{

    @Autowired
    private EmployeeRepo employeeRepo;

    @Override
    @Transactional
    public Employe getEmployeeById(Long id) {
        return employeeRepo.findEmployeeById(id);
    }

    @Override
    public List<Employe> getAllEmployees() {
        return employeeRepo.findAll();
    }

    @Override
    @Transactional
     public void addEmployee(Employe employee) {
        if (employeeRepo.findEmployeeById(employee.getId()) == null){
            employeeRepo.save(employee);
        } else {
            throw new RuntimeException("Employee with id " + employee.getId() + " already exists.");
        }
    }

    @Override
    @Transactional
    public void removeEmployee(Employe employee) {
        Employe e = employeeRepo.findEmployeeById(employee.getId());
        if(e != null){
            employeeRepo.delete(employee);
        }else {
            throw new RuntimeException("Employee with id " + employee.getId() + " does not exist.");
        }
    }

    @Override
    @Transactional
    public void updateEmployee(Employe oldEmployee, Employe newEmployee) {
        Employe exist = employeeRepo.findEmployeeById(oldEmployee.getId());
        if (exist != null) {
            exist.setName(newEmployee.getName());
            exist.setEmail(newEmployee.getEmail());
            exist.setAge(newEmployee.getAge());
            exist.setTel(newEmployee.getTel());
            employeeRepo.save(exist);
        }else {
            throw new RuntimeException("Employee "+oldEmployee.getName() + "with id " + oldEmployee.getId() + " does not exist.");
        }
    }


}
