package controller;


import java.util.Optional;

public interface TaskManagerController {
    Optional<Runnable> runFuncById(Integer id);
    void showMenu();
    void exit();
}
