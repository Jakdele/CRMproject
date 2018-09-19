package pl.coderslab.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.coderslab.entity.Status;
import pl.coderslab.repository.StatusRepository;
import pl.coderslab.service.StatusService;

import java.util.List;

@Service
public class StatusServiceImpl implements StatusService {
    private StatusRepository statusRepository;

    @Autowired
    public StatusServiceImpl(StatusRepository statusRepository) {
        this.statusRepository = statusRepository;
    }

    @Override
    public List<Status> findAll() {
        return statusRepository.findAll();
    }

    @Override
    public List<Status> findAllActive() {
        return statusRepository.findAllByActiveTrue();
    }

    @Override
    public Status save(Status status) {
        return statusRepository.save(status);
    }

    @Override
    public void changeActive(int statusId) {
        Status status = statusRepository.findOne(statusId);
        status.setActive(!status.getActive());
        statusRepository.save(status);
    }
}
