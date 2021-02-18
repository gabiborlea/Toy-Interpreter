package view.GUI.window.MainWindow;

import controller.Controller;
import javafx.beans.binding.Bindings;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Pair;
import model.ProgramState;
import model.adt.*;
import model.exceptions.RunTimeException;
import model.statement.StatementInterface;
import model.value.StringValue;
import model.value.ValueInterface;
import repository.Repository;
import repository.RepositoryInterface;
import view.GUI.window.SelectionWindow.SelectionWindowController;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.stream.Collector;

public class MainWindowController {
    @FXML
    private TableView<Pair<String, ValueInterface>> symbolTableView;
    @FXML
    private TableColumn<Pair<String, ValueInterface>, String> symbolTableVarNameColumn;
    @FXML
    private TableColumn<Pair<String, ValueInterface>, String> symbolTableValueColumn;
    @FXML
    private TableView<Pair<Integer, ValueInterface>> heapTableView;
    @FXML
    private TableColumn<Pair<Integer, ValueInterface>, Integer> heapAddressColumn;
    @FXML
    private TableColumn<Pair<Integer, ValueInterface>, ValueInterface> heapValueColumn;

    @FXML
    private TableView<Pair<Integer, Integer>> latchTableView;
    @FXML
    private TableColumn<Pair<Integer, Integer>, Integer> latchAddressColumn;
    @FXML
    private TableColumn<Pair<Integer, Integer>, Integer> latchValueColumn;

    @FXML
    private ListView<StringValue> fileTableListView;
    @FXML
    private ListView<ValueInterface> outputListView;
    @FXML
    private Text currentProgramID;
    @FXML
    private ListView<StatementInterface> executionStackListView;
    @FXML
    private ListView<ProgramState> programStatesListView;
    @FXML
    private Text currentNumberOfPrograms;

    private Controller controller;
    private RepositoryInterface repository;
    private ProgramState programState;
    private ProgramState selectedProgram;


    public static URL getFXMLPath() {
        return MainWindowController.class.getResource("MainWindow.fxml");
    }

    private StatementInterface selectProgram() {
        var selectedProgram = new SimpleObjectProperty<>();
        try {
            FXMLLoader loader = new FXMLLoader(SelectionWindowController.getFXMLPath());
            Stage selectionWindow = loader.load();
            SelectionWindowController selectionWindowController = loader.getController();

            selectedProgram.bind(selectionWindowController.getSelectedProgram());

            BooleanProperty chosen = new SimpleBooleanProperty();
            chosen.bind(selectionWindowController.getChosen());

            selectionWindow.initModality(Modality.APPLICATION_MODAL);
            selectionWindow.centerOnScreen();
            selectionWindow.showAndWait();

            if (chosen.get())
                return (StatementInterface) selectedProgram.get();
            return null;
        } catch (IOException exception) {
            return null;
        }
    }

    public void setExecutionStackListView(ProgramState programState) {
        if (programState == null)
            return;
        executionStackListView.getItems().clear();
        executionStackListView.getItems().addAll(programState.getExecutionStack().getContent());
    }

    public void setOutputListView() {
        outputListView.getItems().clear();
        outputListView.getItems().addAll(programState.getOutput().getContent());
    }

    public void setSymbolTableView(ProgramState programState) {
        if (programState == null)
            return;
        symbolTableView.setItems(programState.getSymbolTable().getContent().entrySet().stream()
                .map(entry -> new Pair<>(entry.getKey(), entry.getValue()))
                .collect(Collector.of(FXCollections::observableArrayList, ObservableList::add, (elem1, elem2) -> {
                    elem1.addAll(elem2);
                    return elem1;
                })));
    }

    public void setFileTableListView() {
        fileTableListView.getItems().clear();
        fileTableListView.getItems().addAll(programState.getFileTable().getContent().keySet());
    }

    public void setHeapTableView() {
        heapTableView.setItems(programState.getMemoryHeap().getContent().entrySet().stream()
                .map(entry -> new Pair<>(entry.getKey(), entry.getValue()))
                .collect(Collector.of(FXCollections::observableArrayList, ObservableList::add, (elem1, elem2) -> {
                    elem1.addAll(elem2);
                    return elem1;
                })));
    }

    public void setLatchTableView() {
        latchTableView.setItems(programState.getLatchTable().getContent().entrySet().stream()
                .map(entry -> new Pair<>(entry.getKey(), entry.getValue()))
                .collect(Collector.of(FXCollections::observableArrayList, ObservableList::add, (elem1, elem2) -> {
                    elem1.addAll(elem2);
                    return elem1;
                })));
    }

    private void setProgramProperties(ProgramState programState) {
        setExecutionStackListView(programState);
        setOutputListView();
        setSymbolTableView(programState);
        setFileTableListView();
        setHeapTableView();
        setLatchTableView();

    }
    private void setProgramStatesListView() {
        programStatesListView.itemsProperty().bind(getListProperty());

        currentNumberOfPrograms.textProperty().bind(Bindings.size(this.programStatesListView.getItems()).asString());
    }

    public void initialize() {
        programState = new ProgramState(new Stack<>(), new Dictionary<>(), new List<>(), new Dictionary<>(), new Heap<>(), new LatchTable(), selectProgram());
        repository = new Repository(programState, "logs\\logGUI.txt");
        controller = new Controller(repository);
        setProgramStatesListView();


        programStatesListView.getSelectionModel().selectedItemProperty()
                .addListener(((observableValue, programState, program) -> {
                    if (program == null)
                        return;
                    setProgramProperties(program);
                    selectedProgram = program;
                    currentProgramID.textProperty().setValue(Integer.toString(this.selectedProgram.getId()));
                }));


        this.symbolTableVarNameColumn.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(cellData.getValue().getKey()));
        this.symbolTableValueColumn.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(cellData.getValue().getValue().toString()));

        this.heapAddressColumn.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(cellData.getValue().getKey()));
        this.heapValueColumn.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(cellData.getValue().getValue()));

        this.latchAddressColumn.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(cellData.getValue().getKey()));
        this.latchValueColumn.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(cellData.getValue().getValue()));
    }

    @FXML
    public void nextButtonOnPress() {
        try {
            setProgramStatesListView();
            controller.oneStepExecution();
            this.setProgramProperties(selectedProgram);

        } catch (RunTimeException exception) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.setTitle("Warning");
            alert.setHeaderText("Execution stopped");
            alert.setContentText(exception.getMessage());
            alert.showAndWait();
        }

    }

    public ReadOnlyListProperty<ProgramState> getListProperty() {
        var programs = new ReadOnlyListWrapper<>(FXCollections.observableList(new ArrayList<>(controller.getProgramStatesList())));
        return programs.getReadOnlyProperty();
    }
}

