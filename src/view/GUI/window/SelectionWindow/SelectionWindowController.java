package view.GUI.window.SelectionWindow;

import javafx.beans.property.ReadOnlyBooleanProperty;
import javafx.beans.property.ReadOnlyBooleanWrapper;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.SelectionMode;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.adt.Dictionary;
import model.exceptions.MyException;
import model.statement.StatementInterface;

import javafx.scene.control.ListView;
import utils.Programs;

import java.net.URL;

public class SelectionWindowController {
    @FXML
    private ListView<StatementInterface> programList;
    @FXML
    private Button chooseButton;
    @FXML
    private Button cancelButton;
    private final ReadOnlyObjectWrapper<StatementInterface> selectedProgram = new ReadOnlyObjectWrapper<>();
    private final ReadOnlyBooleanWrapper chosen = new ReadOnlyBooleanWrapper(false);

    public ReadOnlyObjectProperty<StatementInterface> getSelectedProgram(){
        return selectedProgram.getReadOnlyProperty();
    }

    public ReadOnlyBooleanProperty getChosen(){
        return chosen.getReadOnlyProperty();
    }

    @FXML
    public void initialize() {
        programList.setItems(FXCollections.observableList(Programs.getPrograms()));
        programList.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        selectedProgram.bind(programList.getSelectionModel().selectedItemProperty());
        programList.getSelectionModel().selectFirst();
    }

    public static URL getFXMLPath() {
        return SelectionWindowController.class.getResource("SelectionWindow.fxml");
    }

    public void cancelButtonOnClick(ActionEvent actionEvent) {
        chosen.set(false);
        ((Stage)cancelButton.getScene().getWindow()).close();
    }

    public void chooseButtonOnClick(ActionEvent actionEvent) {
        try {
            selectedProgram.get().typeCheck(new Dictionary<>());
        } catch (MyException exception)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.setTitle("Error");
            alert.setHeaderText("Error while typechecking");
            alert.setContentText(exception.getMessage());
            alert.showAndWait();
        }
        chosen.set(true);
        ((Stage)chooseButton.getScene().getWindow()).close();
    }
}
