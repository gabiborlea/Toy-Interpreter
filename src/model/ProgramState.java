package model;

import model.adt.*;
import model.statement.StatementInterface;
import model.value.ValueInterface;

public class ProgramState {
    StackInterface<StatementInterface> executionStack;
    DictionaryInterface<String, ValueInterface> symbolTable;
    ListInterface<ValueInterface> output;

    public ProgramState(StackInterface<StatementInterface> executionStack,
                        DictionaryInterface<String,ValueInterface> symbolTable,
                        ListInterface<ValueInterface> output,
                        StatementInterface program) {
        this.executionStack = executionStack;
        this.symbolTable = symbolTable;
        this.output = output;
        executionStack.push(program);
    }


    public StackInterface<StatementInterface> getExecutionStack() {
        return executionStack;
    }

    public DictionaryInterface<String, ValueInterface> getSymbolTable() {
        return symbolTable;
    }

    public ListInterface<ValueInterface> getOutput() {
        return output;
    }

    public void setExecutionStack(StackInterface<StatementInterface> executionStack) {
        this.executionStack = executionStack;
    }

    public void setSymbolTable(DictionaryInterface<String, ValueInterface> symbolTable) {
        this.symbolTable = symbolTable;
    }

    public void setOutput(ListInterface<ValueInterface> output) {
        this.output = output;
    }

    @Override
    public String toString() {
        return "Execution Stack: " + executionStack + "\n" +
                "System Table: " + symbolTable + "\n" +
                "Output: " + output + "\n";
    }
}
