package pl.coderslab.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.entity.Priority;
import pl.coderslab.entity.Status;
import pl.coderslab.service.PriorityService;
import pl.coderslab.service.StatusService;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/priority")
public class PriorityController {
    @Autowired
    private PriorityService priorityService;


    @GetMapping("/list")
    public String showAllProjects(Model model) {
        List<Priority> priorities = priorityService.findAll();
        model.addAttribute("priorities", priorities);
        return "priority/priority_list";
    }

    @GetMapping("/add")
    public String projectForm(Model model) {
        Priority priority = new Priority();
        model.addAttribute("priority", priority);
        return "forms/priority";
    }

    @PostMapping("/add")
    public String addPost(@Valid Priority priority, BindingResult result) {
        if (result.hasErrors()) {
            return "forms/priority";
        }
        priorityService.save(priority);
        return "redirect:/priority/list";
    }

    @GetMapping(path = "/change-status/{id}")
    public String activate(@PathVariable int id) {
        priorityService.changeActive(id);
        return "redirect:/priority/list";
    }

}
