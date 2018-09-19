package pl.coderslab.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.coderslab.entity.Activity;
import pl.coderslab.repository.ActivityRepository;
import pl.coderslab.service.ActivityService;

import java.util.List;

@Service
public class ActivityServiceImpl implements ActivityService {
    private ActivityRepository activityRepository;

    @Autowired
    public ActivityServiceImpl(ActivityRepository activityRepository) {
        this.activityRepository = activityRepository;
    }

    @Override
    public List<Activity> lastTwentyFive() {
        return activityRepository.findFirst25ByOrderByCreatedDesc();
    }

    @Override
    public Activity save(Activity activity) {
        return activityRepository.save(activity);
    }
}
