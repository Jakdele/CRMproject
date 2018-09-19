package pl.coderslab.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.entity.Project;
import pl.coderslab.entity.Status;
import pl.coderslab.service.StatusService;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/status")
public class StatusController {
    @Autowired
    private StatusService statusService;


    @GetMapping("/list")
    public String showAllProjects(Model model) {
        List<Status> statuses = statusService.findAll();
        model.addAttribute("statuses", statuses);
        return "status/status_list";
    }

    @GetMapping("/add")
    public String projectForm(Model model) {
        Status status = new Status();
        model.addAttribute("status", status);
        return "forms/status";
    }

    @PostMapping("/add")
    public String addPost(@Valid Status status, BindingResult result) {
        if (result.hasErrors()) {
            return "forms/status";
        }
        statusService.save(status);
        return "redirect:/status/list";
    }


    @GetMapping(path = "/change-status/{id}")
    public String activate(@PathVariable int id) {
        statusService.changeActive(id);
        return "redirect:/status/list";
    }

}
