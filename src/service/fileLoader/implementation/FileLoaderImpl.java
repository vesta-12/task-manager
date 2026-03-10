package service.fileLoader.implementation;

import config.ApplicationConfig;
import enums.TaskStatus;
import models.Task;
import service.fileLoader.FileLoader;

import java.io.*;
import java.util.Collection;
import java.util.HashMap;

public class FileLoaderImpl implements FileLoader {
    static final String FILE_PATH = ApplicationConfig.getConfig().getProperty("filePath");

    @Override
    public void saveTasks(Collection<Task> taskList) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (Task task : taskList) {
                writer.write(task.toFileString());
                writer.newLine();
            }
            System.out.println("tasks saved to file");
        } catch (IOException e) {
            System.out.println("error");
        }
    }

    @Override
    public HashMap<String, Task> loadTasks() {

        File file = new File(FILE_PATH);
        if (!file.exists()) {
            return new HashMap<>();
        }

        HashMap<String, Task> taskMap = new HashMap<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";", 4);

                if (parts.length == 4) {
                    String id = parts[0];
                    String title = parts[1];
                    String description = parts[2];
                    TaskStatus status = TaskStatus.valueOf(parts[3]);

                    taskMap.put(id, new Task(id, title, description, status));
                }
            }
        } catch (IOException e) {
            System.out.println("error");
        }

        return taskMap;
    }

    private static final class FileLoaderHolder {
        private static final FileLoaderImpl INSTANCE = new FileLoaderImpl();
    }

    public static FileLoaderImpl getInstance() {
        return FileLoaderHolder.INSTANCE;
    }
}
