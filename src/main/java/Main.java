import controller.TaskManagerController;
import controller.implementation.TaskManagerControllerImpl;

import java.util.Scanner;
public class Main {

    static final TaskManagerController taskManagerController = TaskManagerControllerImpl.getInstance();
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            taskManagerController.showMenu();
            int choice = scanner.nextInt();

            if (choice == 0) {
                taskManagerController.exit();
                break;
            }

            Runnable method = taskManagerController
                    .runFuncById(choice)
                    .orElse(null);

            if (method != null) {
                method.run();
            } else {
                System.out.println("invalid choice. try again");
            }
        }
    }
}