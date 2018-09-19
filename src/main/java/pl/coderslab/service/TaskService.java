package pl.coderslab.service;

import pl.coderslab.entity.Task;

import java.util.List;

public interface TaskService {
    Task save(Task task);

    List<Task> findAllByProjectId(int id);

    Task findOne(int id);
}
