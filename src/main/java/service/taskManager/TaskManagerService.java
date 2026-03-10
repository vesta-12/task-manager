package service.taskManager;

public interface TaskManagerService {
    void addTask();
    void listTasks();
    void markTaskCompleted();
    void deleteTask();
    void exit();
}
