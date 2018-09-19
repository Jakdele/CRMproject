package pl.coderslab.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pl.coderslab.entity.Project;
import pl.coderslab.entity.User;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findByLogin(String login);

    List<User> getAllByProjects(Project project);

    @Query("SELECT u FROM User u JOIN u.projects p WHERE p.id=:id")
    List<User> findAllByProjectId(@Param("id") int id);
}
