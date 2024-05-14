package main;

import controller.OperationManager;
import exception.AppException;
import view.ConsoleView;

import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        ConsoleView view = new ConsoleView();

        try (Scanner scanner = new Scanner(System.in)) {
            OperationManager operationManager = new OperationManager(view, scanner);
            while (true) {
                view.displayMessage("--------------------");
                view.displayMessage("Please select mode:");
                view.displayMessage("1. Encrypt");
                view.displayMessage("2. Decrypt");
                view.displayMessage("3. Brute force");
                view.displayMessage("4. Exit");
                view.displayMessage("--------------------");
                view.displayMessage("Enter your choice: ");

                String choice = scanner.nextLine();
                operationManager.executeOperation(choice);
            }
        } catch (AppException e) {
            view.displayMessage(e.getMessage());

        } finally {
            view.close();
        }
    }
}
