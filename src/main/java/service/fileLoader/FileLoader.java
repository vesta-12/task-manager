package service.fileLoader;

import models.Task;

import java.util.Collection;
import java.util.HashMap;

public interface FileLoader {
    void saveTasks(Collection<Task> taskList);
    HashMap<String, Task> loadTasks();
}
