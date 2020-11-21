package model;

import model.adt.*;
import model.statement.StatementInterface;
import model.value.StringValue;
import model.value.ValueInterface;

import java.io.BufferedReader;

public class ProgramState {
    StackInterface<StatementInterface> executionStack;
    DictionaryInterface<String, ValueInterface> symbolTable;
    ListInterface<ValueInterface> output;
    DictionaryInterface<StringValue, BufferedReader> fileTable;
    HeapInterface<ValueInterface> memoryHeap;

    public ProgramState(StackInterface<StatementInterface> executionStack,
                        DictionaryInterface<String, ValueInterface> symbolTable,
                        ListInterface<ValueInterface> output,
                        DictionaryInterface<StringValue, BufferedReader> fileTable,
                        HeapInterface<ValueInterface> memoryHeap,
                        StatementInterface program) {
        this.executionStack = executionStack;
        this.symbolTable = symbolTable;
        this.output = output;
        this.fileTable = fileTable;
        this.memoryHeap = memoryHeap;
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

    public DictionaryInterface<StringValue, BufferedReader> getFileTable() {
        return fileTable;
    }

    public HeapInterface<ValueInterface> getMemoryHeap() {
        return memoryHeap;
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
        StringBuilder executionStack = new StringBuilder();
        StringBuilder symbolTable = new StringBuilder();
        StringBuilder output = new StringBuilder();
        StringBuilder fileTable = new StringBuilder();
        StringBuilder memoryHeap = new StringBuilder();

        for (var element : this.executionStack.getElementsStrings())
            executionStack.append(element).append("\n");

        for (var element : this.symbolTable.getElementsStrings())
            symbolTable.append(element.get(0)).append("-->").append(element.get(1)).append("\n");

        for (var element : this.output.getElementsStrings())
            output.append(element).append("\n");

        for (var element : this.fileTable.getElementsStrings())
            fileTable.append(element.get(0)).append("\n");

        for (var element : this.memoryHeap.getElementsStrings())
            memoryHeap.append(element.get(0)).append("-->").append(element.get(1)).append("\n");


        return "Execution Stack:\n" + executionStack + "\n" +
                "Symbol Table:\n" + symbolTable + "\n" +
                "Output:\n" + output + "\n" +
                "File Table:\n" + fileTable + "\n" +
                "Memory Heap:\n" + memoryHeap + "--------------------------\n\n";
    }
}
