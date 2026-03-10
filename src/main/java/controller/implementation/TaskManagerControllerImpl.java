package controller.implementation;

import controller.TaskManagerController;
import service.taskManager.TaskManagerService;
import service.taskManager.implementation.TaskManagerServiceImpl;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Optional;

public class TaskManagerControllerImpl implements TaskManagerController {
    private final TaskManagerService taskManagerService = TaskManagerServiceImpl.getInstance();
    private HashMap<Integer, Method> methodMap = new HashMap<>();

    private static final class TaskManagerControllerHolder {
        private static final TaskManagerController taskManagerController = new TaskManagerControllerImpl();
    }

    public static TaskManagerController getInstance() {
        return TaskManagerControllerHolder.taskManagerController;
    }

    @Override
    public Optional<Runnable> runFuncById(Integer id) {
        if (methodMap.isEmpty()) {
            this.setMethods();
        }

        return Optional
                .ofNullable(methodMap.get(id))
                .map(method -> () -> {
                    try {
                        method.invoke(taskManagerService);
                    } catch (Exception ignored) {}
                });
    }

    @Override
    public void showMenu() {
        System.out.println("\nmy task manager");

        if (methodMap.isEmpty()) {
            this.setMethods();
        }

        for (int i : methodMap.keySet()) {
            System.out.println(i + ") " + methodMap.get(i).getName());
        }

        System.out.println("0) exit");
        System.out.print("choose: ");
    }

    @Override
    public void exit() {
        taskManagerService.exit();
    }

    private void setMethods() {
        Method[] methods = TaskManagerService.class.getDeclaredMethods();

        Arrays.sort(methods, Comparator.comparing(Method::getName));

        HashMap<Integer, Method> methodMap = new HashMap<>();
        for (int j = 0; j < methods.length; j++) {
            methodMap.put(j + 1, methods[j]);
        }

        this.methodMap = methodMap;
    }
}
