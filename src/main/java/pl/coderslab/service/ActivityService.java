package pl.coderslab.service;

import pl.coderslab.entity.Activity;

import java.util.List;

public interface ActivityService {
    List<Activity> lastTwentyFive();

    Activity save(Activity activity);
}
