package pl.coderslab.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.entity.Activity;
import pl.coderslab.entity.Task;
import pl.coderslab.service.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/task")
public class TaskController {
    @Autowired
    private TaskService taskService;
    @Autowired
    private UserService userService;
    @Autowired
    private ProjectService projectService;
    @Autowired
    private PriorityService priorityService;
    @Autowired
    private StatusService statusService;
    @Autowired
    private ActivityService activityService;

    @GetMapping("/add/{id}")
    public String projectForm(Model model, @PathVariable int id) {
        Task task = new Task();
        task.setProject(projectService.findOne(id));
        model.addAttribute("projectUsers", userService.findByProjectId(id));
        model.addAttribute("task", task);
        return "forms/task";
    }

    @PostMapping("/add")
    public String addPost(@AuthenticationPrincipal CurrentUser customUser, @Valid Task task, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("projectUsers", userService.findByProjectId(task.getProject().getId()));
            return "forms/task";
        }
        taskService.save(task);
        activityService.save(new Activity("New task added to project " + task.getProject().getName() + "by user: " + customUser.getUser().getLogin(),task.getProject().getId()));
        return "redirect:/project/tasks/" + task.getProject().getId();
    }

    @GetMapping(path = "/details/{id}")
    public String details(@PathVariable int id, Model model) {
        model.addAttribute("task", taskService.findOne(id));
        return "/task/task_details";
    }

    @GetMapping(path = "/status/{id}")
    public String changeStatusForm(@PathVariable int id, Model model) {
        Task task = taskService.findOne(id);
        model.addAttribute("task", task);
        model.addAttribute("users", userService.findByProjectId(task.getProject().getId()));
        return "/task/task_status";
    }

    @PostMapping(path = "/status")
    public String changeStatus(@AuthenticationPrincipal CurrentUser customUser, @Valid Task task, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("users", userService.findByProjectId(task.getProject().getId()));
            return "/task/task_status";
        }
        taskService.save(task);
        activityService.save(new Activity("Task status changed " + task.getProject().getName() + "by user: " + customUser.getUser().getLogin(),  task.getProject().getId()));
        return "redirect:/task/all/" + task.getProject().getId();
    }
    @GetMapping(path = "/edit/{id}")
    public String editForm(@PathVariable int id, Model model) {
        Task task = taskService.findOne(id);
        model.addAttribute("task", task);
        model.addAttribute("projectUsers", userService.findByProjectId(task.getProject().getId()));
        return "/task/task_edit";
    }

    @PostMapping(path = "/edit/{id}")
    public String edit(@Valid @ModelAttribute("task")Task task, BindingResult result,
                       Model model) {
        if (result.hasErrors()) {
            model.addAttribute("projectUsers", userService.findByProjectId(task.getProject().getId()));
            return "/task/task_edit";
        }
        taskService.save(task);
        return "redirect:/project/tasks/" + task.getProject().getId();
    }


    @ModelAttribute
    public void modelAttributes(Model model) {
        model.addAttribute("statuses", statusService.findAllActive());
        model.addAttribute("priorities", priorityService.findAllActive());
    }
}
