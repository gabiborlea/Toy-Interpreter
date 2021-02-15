package model.adt;

import javafx.util.Pair;
import model.statement.StatementInterface;

public interface ProcTableInterface extends DictionaryInterface<String, Pair<java.util.List<String>, StatementInterface>> {
    void add(String name, Pair<java.util.List<String>, StatementInterface> frame);
}
