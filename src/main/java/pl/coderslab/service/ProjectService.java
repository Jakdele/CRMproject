package pl.coderslab.service;

import pl.coderslab.entity.Project;

import java.util.List;

public interface ProjectService {

    List<Project> lastFive();

    List<Project> findAll();

    Project save(Project project);

    String createIdentifier(String text);

    Project findOne(int id);
}
