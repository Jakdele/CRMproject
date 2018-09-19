package pl.coderslab.service;

import pl.coderslab.entity.Status;

import java.util.List;

public interface StatusService {
    List<Status> findAll();
    List<Status> findAllActive();

    Status save(Status status);

    void changeActive(int statusId);
}
