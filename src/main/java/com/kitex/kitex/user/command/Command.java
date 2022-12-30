package com.kitex.kitex.user.command;

public interface Command {
    int CREATE_USER = 1;
    Object execute();

}
