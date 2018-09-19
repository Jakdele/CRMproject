package pl.coderslab.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.coderslab.entity.Task;
import pl.coderslab.repository.TaskRepository;
import pl.coderslab.service.TaskService;

import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {
    private TaskRepository taskRepository;

    @Autowired
    public TaskServiceImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public Task save(Task task) {
        return taskRepository.save(task);
    }

    @Override
    public List<Task> findAllByProjectId(int id) {
        return taskRepository.findAllByProjectId(id);
    }

    @Override
    public Task findOne(int id) {
        return taskRepository.findOne(id);
    }
}
