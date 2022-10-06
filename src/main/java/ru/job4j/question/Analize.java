package ru.job4j.question;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class Analize {

    public static Info diff(Set<User> previous, Set<User> current) {

        int added = 0;
        int changed = 0;
        int deleted = 0;
        Map<Integer, User> previousMap = previous.stream().collect(Collectors.toMap(User::getId, e -> e));
        Map<Integer, User> currentMap = current.stream().collect(Collectors.toMap(User::getId, e -> e));
        for (Integer key : previousMap.keySet()) {
            if (!currentMap.containsKey(key)) {
                deleted++;
            } else {
                if (currentMap.get(key) != previousMap.get(key)) {
                    changed++;
                }
            }
        }
        for (Integer key : currentMap.keySet()) {
            if (!(previousMap.containsKey(key))) {
                added++;
            }
        }

        return new Info(added, changed, deleted);
    }
}