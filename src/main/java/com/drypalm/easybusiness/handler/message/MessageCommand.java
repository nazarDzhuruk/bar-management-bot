package com.drypalm.easybusiness.handler.message;

public enum MessageCommand {

    START("/start", "sends main menu;"),
    HELP("/help", "sends all commands"),
    REGISTER("/r", "register new user (only admin)"),
    LOGIN("/li", "login as employee: example: /li ****"),
    LOGOUT("/lo", "logout user"),
    EXIT("/exit", "stops application (only admin)"),
    STOP("/stop", "command to stop accepting goods"),

    ADD("emptyStringWithoutCommand", "");

    private final String command;
    private final String description;

    MessageCommand(String command, String description) {
        this.command = command;
        this.description = description;
    }

    public String getCommand() {
        return command;
    }

    public String getDescription() {
        return description;
    }
}
