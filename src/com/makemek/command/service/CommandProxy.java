package com.makemek.command.service;

import com.makemek.command.UndoableCommand;

import java.io.FileInputStream;

/**
 * Created by Apipol on 21/02/15.
 */
public class CommandProxy implements CommandService {

    private CommandService cmdService;
    private String commandFile;

    public CommandProxy(String commandFile, CommandService cmdService) {
        this.cmdService = cmdService;
        this.commandFile = commandFile;
        cmdService._import(commandFile);
    }

    public CommandProxy(CommandService cmdService) {
        this.cmdService = cmdService;
    }

    @Override
    public void storeAndExecute(UndoableCommand cmd) {
        cmdService.storeAndExecute(cmd);
        cmdService._export(commandFile);
    }

    @Override
    public void undo() {
        cmdService.undo();
    }

    @Override
    public void redo() {
        cmdService.redo();
    }

    @Override
    public void _import(String filename) {
        cmdService._import(filename);
    }

    @Override
    public void _export(String filename) {
        cmdService._export(filename);
    }
}
