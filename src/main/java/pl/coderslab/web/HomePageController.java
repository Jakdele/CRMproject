package pl.coderslab.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.entity.Activity;
import pl.coderslab.entity.Project;
import pl.coderslab.service.ActivityService;
import pl.coderslab.service.ProjectService;

import java.util.List;

@Controller
public class HomePageController {

    @Autowired
    private ProjectService projectService;
    @Autowired
    private ActivityService activityService;


    @GetMapping("/home")
    public String homePage(Model model){
        List<Project> projects = projectService.lastFive();
        List<Activity> activities = activityService.lastTwentyFive();
        model.addAttribute("projects", projects);
        model.addAttribute("activities", activities);
        return "homepage";
    }

    @RequestMapping("/logoutpage")
    public String logOut(){
        return "logout";
    }

}
