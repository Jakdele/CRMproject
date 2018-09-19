package pl.coderslab.service;

import org.springframework.data.repository.query.Param;
import pl.coderslab.entity.Project;
import pl.coderslab.entity.User;

import java.util.List;

public interface UserService {
    List<User> findAll();

    User findByUserName(String name);

    User findByLogin(String login);

    void saveUser(User user);

    List<User> findByProject(Project project);

    List<User> findByProjectId(int id);

}
