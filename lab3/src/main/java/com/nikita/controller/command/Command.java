package com.nikita.controller.command;

public interface Command<T> {
    T execute();
}
