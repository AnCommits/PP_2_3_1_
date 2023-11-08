package ru.andrey.init;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;
import ru.andrey.service.UserService;

@Component
public class Init implements InitializingBean {
    private final UserService userService;

    public Init(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        userService.initTable();
    }
}
