package controller;

import view.ConsoleView;

import java.util.Scanner;

public class OperationManager {
    private final ConsoleView view;
    private final InputManager inputManager;

    public OperationManager(ConsoleView view, Scanner scanner) {
        this.view = view;
        this.inputManager = new InputManager(view, scanner);
    }

    public void executeOperation(String choice) {
        Operation operation = null;
        String inputFilePath;
        String outputFilePath;
        int key;

        switch (choice) {
            case "1":
                inputFilePath = inputManager.getInputFilePath("Enter source (full path OR only filename OR Enter for text.txt): ", Constants.INPUT_FILE_PATH);
                outputFilePath = inputManager.getOutputFilePath("Enter destination (full path OR only filename OR Enter for encrypted.txt): ", Constants.ENCRYPTED_FILE_PATH);
                key = inputManager.getKey("Enter key (int number OR Enter for key=1): ", 1);
                operation = new EncryptOperation(new Caesar(), view, inputFilePath, outputFilePath, key);
                break;
            case "2":
                inputFilePath = inputManager.getInputFilePath("Enter source (full path OR only filename OR Enter for encrypted.txt): ", Constants.ENCRYPTED_FILE_PATH);
                outputFilePath = inputManager.getOutputFilePath("Enter destination (full path OR only filename OR Enter for decrypted.txt): ", Constants.DECRYPTED_FILE_PATH);
                key = inputManager.getKey("Enter key (int number OR Enter for key=1): ", 1);
                operation = new DecryptOperation(new Caesar(), view, inputFilePath, outputFilePath, key);
                break;
            case "3":
                inputFilePath = inputManager.getInputFilePath("Enter source (full path OR only filename OR Enter for encrypted.txt): ", Constants.ENCRYPTED_FILE_PATH);
                operation = new BruteForceOperation(new Caesar(), view, inputFilePath, Constants.BRUTE_FORCE_FILE_PATH);
                break;
            case "4":
                operation = new ExitOperation(view);
                break;
            default:
                view.displayMessage("Invalid option. Please enter a number between 1 and 4.");
                return;
        }

        operation.execute();
        view.displayMessage("Operation complete\nResult: " + operation.getOutputFileName());
    }
}
