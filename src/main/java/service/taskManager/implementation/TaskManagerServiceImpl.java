package service.taskManager.implementation;

import enums.TaskStatus;
import models.Task;
import service.fileLoader.FileLoader;
import service.fileLoader.implementation.FileLoaderImpl;
import service.taskManager.TaskManagerService;

import java.util.*;

public class TaskManagerServiceImpl implements TaskManagerService {
    private final FileLoader fileLoader = FileLoaderImpl.getInstance();
    private final Map<String, Task> taskList = fileLoader.loadTasks();
    private final Scanner scanner = new Scanner(System.in);

    @Override
    public void addTask() {
        System.out.print("title: ");
        String title = scanner.nextLine();

        if (title.isBlank()) {
            System.out.println("not empty, please");
            return;
        }

        System.out.print("description: ");
        String description = scanner.nextLine();

        String id = UUID.randomUUID().toString().toLowerCase();
        Task task = new Task(id, title, description, TaskStatus.PENDING);

        taskList.put(id, task);

        System.out.println("task added");
    }

    @Override
    public void listTasks() {
        if (taskList.isEmpty()) {
            System.out.println("no tasks found");
            return;
        }

        System.out.println("\ntask list");
        for (Task task : taskList.values()) {
            System.out.println(task);
        }
    }

    @Override
    public void markTaskCompleted() {
        this.listTasks();

        System.out.print("task id: ");

        try {
            String id = scanner.nextLine();

            this.taskList.get(id.toLowerCase()).setStatus(TaskStatus.COMPLETED);

            System.out.println("success");
        } catch (NumberFormatException e) {
            System.out.println("can't find task with this id");
        }
    }

    @Override
    public void deleteTask() {
        if (taskList.isEmpty()) {
            System.out.println("no tasks available");
            return;
        }

        this.listTasks();

        System.out.print("task id: ");

        try {
            String id = scanner.nextLine();

            this.taskList.remove(id.toLowerCase());

            System.out.println("task deleted");
        } catch (NumberFormatException e) {
            System.out.println("can't find task with this id");
        }
    }

    @Override
    public void exit() {
        fileLoader.saveTasks(taskList.values());
    }

    private static final class TaskManagerServiceHolder {
        private static final TaskManagerService taskManagerService = new TaskManagerServiceImpl();
    }

    public static TaskManagerService getInstance() {
        return TaskManagerServiceHolder.taskManagerService;
    }

}
