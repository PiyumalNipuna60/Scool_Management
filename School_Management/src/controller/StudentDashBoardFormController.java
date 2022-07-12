package controller;

import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import model.Student;
import util.CrudUtil;

import java.sql.ResultSet;
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
        Student s=new Student(
                txtStudentId.getText(),txtStudentName.getText(),txtStudentEmail.getText(),txtStudentContact.getText(),txtStudentAddress.getText(),txtStudentNic.getText()
        );
        try {
            if (CrudUtil.execute("INSERT INTO Student VALUES(?,?,?,?,?,?)", s.getsId(), s.getsName(), s.getsEmail(), s.getsContact(), s.getsAddress(), s.getsNic())) {
                new Alert(Alert.AlertType.CONFIRMATION, "Save Customer!..").show();
            } else {
                new Alert(Alert.AlertType.CONFIRMATION, "Something went Wrong!..").show();
            }
        }catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void UpdateOnAction(ActionEvent actionEvent) {
        Student s=new Student(
                txtStudentId.getText(),txtStudentName.getText(),txtStudentEmail.getText(),txtStudentContact.getText(),txtStudentAddress.getText(),txtStudentNic.getText()
        );
        try {
            if (CrudUtil.execute("UPDATE Student SET student_name=?,email=?,contact=?,address=?,nic=? WHERE student_id=?",s.getsName(),s.getsEmail(),s.getsContact(),s.getsAddress(),s.getsNic(),s.getsId())){
                new Alert(Alert.AlertType.CONFIRMATION, "Updated!..").show();
            } else {
                new Alert(Alert.AlertType.WARNING, "Something Wrong!..").show();
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public void DeleteOnAction(ActionEvent actionEvent) {
        try {
            if (CrudUtil.execute("DELETE FROM Student WHERE student_id=?",txtStudentId.getText())) {
                new Alert(Alert.AlertType.CONFIRMATION, "Deleted!..").show();
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public void SearchStudentOnACtion(ActionEvent actionEvent) {
        try {
            ResultSet resultSet = CrudUtil.execute("SELECT * FROM Student WHERE student_id=?",txtStudentId.getText());
            if (resultSet.next()){
                txtStudentName.setText(resultSet.getString(2));
                txtStudentEmail.setText(resultSet.getString(3));
                txtStudentContact.setText(resultSet.getString(4));
                txtStudentAddress.setText(resultSet.getString(5));
                txtStudentNic.setText(resultSet.getString(6));
            }else {
                new Alert(Alert.AlertType.WARNING,"Empty Result!..").show();
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
}
