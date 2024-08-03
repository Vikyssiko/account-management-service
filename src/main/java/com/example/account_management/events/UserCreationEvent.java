package com.example.account_management.events;

import com.example.account_management.entities.User;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class UserCreationEvent extends ApplicationEvent {
    private final User user;

    public UserCreationEvent(Object source, User user) {
        super(source);
        this.user = user;
    }
}
