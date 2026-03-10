import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class Main {

    static List<Task> tasks = new ArrayList<>();
    static Scanner scanner = new Scanner(System.in);
    static final String FILE_NAME = "my_tasks.csv";

    public static void main(String[] args) {
        loadTasks();
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
                case "4":
                    deleteTask();
                    break;
                case "5":
                    saveTasks();
                    break;
                case "6":
                    loadTasks();
                    System.out.println("tasks loaded from file");
                    break;
                case "7":
                    editTask();
                    break;
                case "0":
                    saveTasks();
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
        System.out.println("4) delete task");
        System.out.println("5) save to file");
        System.out.println("6) load from file");
        System.out.println("7) edit");
        System.out.println("0) exit");
        System.out.print("choose: ");
    }

    public static void addTask() {
        System.out.print("title: ");
        String title = scanner.nextLine();

        if (title.isBlank()) {
            System.out.println("not empty, please");
            return;
        }

        System.out.print("description: ");
        String description = scanner.nextLine();

        int id = getNextId();
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

        try {
            int id = Integer.parseInt(scanner.nextLine());

            for (Task task : tasks) {
                if (task.getId() == id) {
                    task.setStatus("completed!");
                    System.out.println("task marked as completed");
                    return;
                }
            }

            System.out.println("can't find task with this id");
        } catch (NumberFormatException e) {
            System.out.println("please enter a number");
        }
    }
    public static void deleteTask() {
        if (tasks.isEmpty()) {
            System.out.println("no tasks available");
            return;
        }

        System.out.print("task id: ");

        try {
            int id = Integer.parseInt(scanner.nextLine());

            for (int i = 0; i < tasks.size(); i++) {
                if (tasks.get(i).getId() == id) {
                    tasks.remove(i);
                    System.out.println("task deleted");
                    return;
                }
            }

            System.out.println("can't find task with this id");
        } catch (NumberFormatException e) {
            System.out.println("please enter a number");
        }
    }
    public static void saveTasks() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (Task task : tasks) {
                writer.write(task.toFileString());
                writer.newLine();
            }
            System.out.println("tasks saved to file");
        } catch (IOException e) {
            System.out.println("error");
        }
    }
    public static void loadTasks() {
        tasks.clear();

        File file = new File(FILE_NAME);
        if (!file.exists()) {
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";", 4);

                if (parts.length == 4) {
                    int id = Integer.parseInt(parts[0]);
                    String title = parts[1];
                    String description = parts[2];
                    String status = parts[3];

                    tasks.add(new Task(id, title, description, status));
                }
            }
        } catch (IOException e) {
            System.out.println("error");
        }
    }
    public static int getNextId() {
        int maxId = 0;

        for (Task task : tasks) {
            if (task.getId() > maxId) {
                maxId = task.getId();
            }
        }

        return maxId + 1;
    }
    public static void editTask() {
        if (tasks.isEmpty()) {
            System.out.println("no tasks available.");
            return;
        }

        System.out.print("task id: ");

        try {
            int id = Integer.parseInt(scanner.nextLine());
            for (Task task : tasks) {
                if (task.getId() == id) {

                    System.out.println(task.getTitle());

                    System.out.print("new title: ");
                    String newTitle = scanner.nextLine();

                    if (!newTitle.isBlank()) {
                        task.setTitle(newTitle);
                    }

                    System.out.println("updated");
                    return;
                }
            }

            System.out.println("can't find task with this id");

        } catch (NumberFormatException e) {
            System.out.println("error");
        }
    }
}