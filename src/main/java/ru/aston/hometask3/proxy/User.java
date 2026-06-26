package ru.aston.hometask3.proxy;

import java.util.Set;

public record User(String name, Set<String> roles) {
    public boolean isOwner() {
        return roles.contains("OWNER");
    }
}
