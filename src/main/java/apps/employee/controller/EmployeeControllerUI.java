package apps.employee.controller;

import apps.employee.model.Employe;
import apps.employee.services.EmployeeService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class EmployeeControllerUI {

    @Autowired
    private EmployeeService employeeService;

    // Label
    @FXML private Label labDisplay;
    // TextField
    @FXML private TextField fieldName;
    @FXML private TextField fieldAge;
    @FXML private TextField fieldEmail;
    @FXML private TextField fieldTel;
    // Buttons
    @FXML private Button btnAdd;
    @FXML private Button btnUpdate;
    @FXML private Button btnRemove;
    // TableView
    @FXML private TableView<Employe> tabListEmploye;
    // TableColumn
    @FXML private TableColumn<Employe, String> clmName;
    @FXML private TableColumn<Employe, Integer> clmAge;
    @FXML private TableColumn<Employe, String> clmEmail;
    @FXML private TableColumn<Employe, Double> clmTel;
    @FXML private TableColumn<Employe, Long> clmId;

    // ObservableList for TableView
    @FXML
    private ObservableList<Employe> employeeList = FXCollections.observableArrayList();

    // Initialize method to set up the TableView
    @FXML
    public void initialize() {
        clmName.setCellValueFactory(new PropertyValueFactory<>("name"));
        clmAge.setCellValueFactory(new PropertyValueFactory<>("age"));
        clmEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        clmTel.setCellValueFactory(new PropertyValueFactory<>("tel"));
        clmId.setCellValueFactory(new PropertyValueFactory<>("id"));

        employeeList.setAll(employeeService.getAllEmployees());
        tabListEmploye.setItems(employeeList);
    }

    // Method to clear input fields
    private void clearFields() {
        fieldName.clear();
        fieldAge.clear();
        fieldEmail.clear();
        fieldTel.clear();
    }

    // Method to update the display message
    private void updateMessage(String message) {
        labDisplay.setText(message);
    }

    // Method to handle the removal of an employee
    @FXML
    private void handleRemove(ActionEvent event) {
        Employe selectedEmployee = tabListEmploye.getSelectionModel().getSelectedItem();
        if (selectedEmployee == null) {
            updateMessage("Please select an employee to remove.");
            return;
        }

        employeeService.removeEmployee(selectedEmployee);
        employeeList.remove(selectedEmployee);
        updateMessage("Employee removed successfully.");
    }

    // Method to handle the addition of a new employee
    @FXML
    public void handleAdd(ActionEvent actionEvent) {
        try {
            String name = fieldName.getText();
            int age = Integer.parseInt(fieldAge.getText());
            String email = fieldEmail.getText();
            double tel = Double.parseDouble(fieldTel.getText());

            Employe newEmployee = new Employe(name, age, email, tel);
            employeeService.addEmployee(newEmployee);
            employeeList.add(newEmployee);

            clearFields();
            updateMessage("Employee added successfully.");
        } catch (NumberFormatException e) {
            updateMessage("Please enter valid inputs.");
        }
    }
}
