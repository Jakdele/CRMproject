package pl.coderslab.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.coderslab.entity.Priority;
import pl.coderslab.repository.PriorityRepository;
import pl.coderslab.service.PriorityService;

import java.util.List;

@Service
public class PriorityServiceImpl implements PriorityService {
    private PriorityRepository priorityRepository;

    @Autowired
    public PriorityServiceImpl(PriorityRepository priorityRepository) {
        this.priorityRepository = priorityRepository;
    }

    @Override
    public List<Priority> findAll() {
        return priorityRepository.findAll();
    }

    @Override
    public List<Priority> findAllActive() {
        return priorityRepository.findAllByActiveTrue();
    }

    @Override
    public Priority save(Priority priority) {
        return priorityRepository.save(priority);
    }

    @Override
    public void changeActive(int priorityId) {
        Priority priority = priorityRepository.findOne(priorityId);
        priority.setActive(!priority.isActive());
        priorityRepository.save(priority);
    }
}
