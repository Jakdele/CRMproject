package pl.coderslab.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.coderslab.entity.Project;
import pl.coderslab.repository.ProjectRepository;
import pl.coderslab.service.ProjectService;

import java.util.List;

@Service
public class ProjectServiceImpl implements ProjectService {

    private ProjectRepository projectRepository;


    @Autowired
    public ProjectServiceImpl(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }


    @Override
    public List<Project> lastFive() {
        return projectRepository.findFirst5ByOrderByCreatedDesc();
    }

    @Override
    public List<Project> findAll() {
        return projectRepository.findAll();
    }

    @Override
    public Project save(Project project) {
        return projectRepository.save(project);
    }

    public String createIdentifier(String urlAddress) {
        return StringUtils.stripAccents(urlAddress).replace("\\s", "-");
    }

    @Override
    public Project findOne(int id) {
        return projectRepository.findOne(id);
    }
}
