package apps.employee;

import apps.employee.services.EmployeeService;
import javafx.fxml.FXML;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.text.TableView;

@Component
public class ControllerEmployee {

    @Autowired
    private EmployeeService employeeService;
}
