package controller;

import javafx.event.ActionEvent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import model.Student;
import util.CrudUtil;

import java.sql.SQLException;

public class StudentDashBoardFormController {
    public TextField txtStudentId;
    public TextField txtStudentAddress;
    public TextField txtStudentContact;
    public TextField txtStudentEmail;
    public TextField txtStudentName;
    public TextField txtStudentNic;
    public TableView tblStudent;
    public TableColumn colStudentId;
    public TableColumn colEmail;
    public TableColumn colSId;
    public TableColumn colContact;
    public TableColumn colAddress;
    public TableColumn colNic;
    public TableColumn colOption;

    public void saveOnAction(ActionEvent actionEvent) {
    }

    public void UpdateOnAction(ActionEvent actionEvent) {
    }

    public void DeleteOnAction(ActionEvent actionEvent) {
    }

    public void SearchStudentOnACtion(ActionEvent actionEvent) {
    }
}
