package pl.coderslab.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.entity.Activity;
import pl.coderslab.entity.Project;
import pl.coderslab.entity.User;
import pl.coderslab.service.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/project")
public class ProjectController {
    @Autowired
    private ProjectService projectService;
    @Autowired
    private UserService userService;
    @Autowired
    private TaskService taskService;
    @Autowired
    private ActivityService activityService;

    @ModelAttribute("users")
    public List<User> users() {
        return userService.findAll();
    }

    @GetMapping("/list")
    public String showAllProjects(Model model) {
        List<Project> projects = projectService.findAll();
        model.addAttribute("projects", projects);
        return "project/project_list";
    }

    @GetMapping("/add")
    public String projectForm(Model model) {
        Project project = new Project();
        model.addAttribute("project", project);
        return "forms/project";
    }

    @PostMapping("/add")
    public String addPost(@AuthenticationPrincipal CurrentUser customUser,@Valid Project project, BindingResult result){
        if(result.hasErrors()){
            return "forms/project";
        }
        project.setIdentifier(projectService.createIdentifier(project.getName()));
        projectService.save(project);
        activityService.save(new Activity("New project created by user: " + customUser.getUser().getLogin(), project.getId()));
        return "redirect:/project/list";
    }

    @GetMapping("/details/{id}")
    public String projectDetails(@PathVariable int id, Model model){
        model.addAttribute("project", projectService.findOne(id));
        return "project/project_details";
    }

    @GetMapping("/tasks/{id}")
    public  String projectTasks(@PathVariable int id, Model model){
        model.addAttribute("tasks", taskService.findAllByProjectId(id));
        return "project/project_tasks";
    }
}
