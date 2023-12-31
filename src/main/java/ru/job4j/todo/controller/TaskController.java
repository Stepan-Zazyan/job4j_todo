package ru.job4j.todo.controller;

import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.job4j.todo.model.Task;
import ru.job4j.todo.model.User;
import ru.job4j.todo.service.PriorityService;
import ru.job4j.todo.service.TaskService;

import javax.servlet.http.HttpSession;
import java.util.Optional;

@ThreadSafe
@Controller
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService taskService;
    private final PriorityService priorityService;

    public TaskController(TaskService taskService, PriorityService priorityService) {
        this.taskService = taskService;
        this.priorityService = priorityService;
    }

    @GetMapping
    public String getAll(Model model) {
        model.addAttribute("taskList", taskService.findAll());
        return "tasks/list";
    }

    @GetMapping("/create")
    public String getCreationPage(Model model) {
        model.addAttribute("priorities", priorityService.findAll());
/*        model.addAttribute("categoryList", categoryService.findAll());*/
        return "tasks/create";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute Task task, Model model) {
        try {
            User user = UserController.getLoggedInUser();
            task.setUser(user);
            taskService.add(task);
            return "redirect:/tasks";
        } catch (Exception exception) {
            model.addAttribute("message", exception.getMessage());
            return "errors/404";
        }
    }

    @GetMapping("/update/{id}")
    public String getUpdatePage(Model model, @PathVariable int id) {
        Optional<Task> task = taskService.findById(id);
        if (task.isEmpty()) {
            model.addAttribute("message", "Задача с указанным идентификатором не найдена");
            return "errors/404";
        }
        model.addAttribute("task", task.get());
        return "tasks/update";
    }



    @GetMapping("/update")
    public String getUpdatePage() {
        return "tasks/update";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute Task task, Model model) {
        try {
            var isUpdated = taskService.update(task);
            if (!isUpdated) {
                model.addAttribute("message", "Задача с указанным идентификатором не найдена");
                return "errors/404";
            }
            return "redirect:/tasks";
        } catch (Exception exception) {
            model.addAttribute("message", exception.getMessage());
            return "errors/404";
        }
    }
    @GetMapping("/done/{id}")
    public String updateDone(@ModelAttribute Task task, Model model) {
        try {
            var isUpdated = taskService.updateDone(task);
            if (!isUpdated) {
                model.addAttribute("message", "Задача с указанным идентификатором не найдена");
                return "errors/404";
            }
            return "redirect:/tasks";
        } catch (Exception exception) {
            model.addAttribute("message", exception.getMessage());
            return "errors/404";
        }
    }

    @GetMapping("/delete/{id}")
    public String delete(Model model, @PathVariable int id) {
        boolean isDeleted = taskService.delete(id);
        if (!isDeleted) {
            model.addAttribute("message", "Задача с указанным идентификатором не найдена");
            return "errors/404";
        }
        return "redirect:/tasks";
    }

    @GetMapping("/{id}")
    public String getById(Model model, @PathVariable int id) {
        var taskOptional = taskService.findById(id);
        if (taskOptional.isEmpty()) {
            model.addAttribute("message", "Задача с указанным идентификатором не найдена");
            return "errors/404";
        }
        model.addAttribute("task", taskOptional.get());
        return "tasks/one";
    }



    @GetMapping("/list")
    public String selectAllTasks(@ModelAttribute Task task, Model model) {
        model.addAttribute("taskList", taskService.findAll());
        return "tasks/list";
    }

    @GetMapping("/listDone")
    public String selectDoneTasks(@ModelAttribute Task task, Model model) {
        model.addAttribute("taskList", taskService.findAll());
        return "tasks/listDone";
    }

    @GetMapping("/listNew")
    public String selectNewTasks(@ModelAttribute Task task, Model model) {
        model.addAttribute("taskList", taskService.findAll());
        return "tasks/listNew";
    }
}
