package ru.aston.hometask3.chain;

public interface HandlerInterface {
    HandlerInterface setNext(HandlerInterface next);
    ModerationResult handle(Comment comment);
}
