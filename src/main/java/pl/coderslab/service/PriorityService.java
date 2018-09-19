package pl.coderslab.service;

import pl.coderslab.entity.Priority;

import java.util.List;

public interface PriorityService {
    List<Priority> findAll();
    List<Priority> findAllActive();

    Priority save(Priority priority);

    void changeActive(int priorityId);
}


