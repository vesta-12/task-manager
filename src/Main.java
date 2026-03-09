import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    static List<Task> tasks = new ArrayList<>();
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean running = true;

        while (running) {
            showMenu();
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    addTask();
                    break;
                case "2":
                    listTasks();
                    break;
                case "3":
                    markTaskCompleted();
                    break;
                case "0":
                    System.out.println("program closed");
                    running = false;
                    break;
                default:
                    System.out.println("invalid choice. try again");
            }
        }
    }

    public static void showMenu() {
        System.out.println("\nmy task manager");
        System.out.println("1) add task");
        System.out.println("2) list tasks");
        System.out.println("3) mark task as completed");
        System.out.println("0) exit");
        System.out.print("choose: ");
    }

    public static void addTask() {
        System.out.print("title: ");
        String title = scanner.nextLine();

        System.out.print("description: ");
        String description = scanner.nextLine();

        int id = tasks.size() + 1;
        Task task = new Task(id, title, description, "pending");
        tasks.add(task);

        System.out.println("task added");
    }

    public static void listTasks() {
        if (tasks.isEmpty()) {
            System.out.println("no tasks found");
            return;
        }

        System.out.println("\ntask list");
        for (Task task : tasks) {
            System.out.println(task);
        }
    }
    public static void markTaskCompleted() {
        if (tasks.isEmpty()) {
            System.out.println("no tasks available");
            return;
        }

        System.out.print("task id: ");
        int id = Integer.parseInt(scanner.nextLine());

        for (Task task : tasks) {
            if (task.getId() == id) {
                task.setStatus("completed!");
                System.out.println("task marked as completed");
                return;
            }
        }
        System.out.println("can't find task with this id");
    }
}