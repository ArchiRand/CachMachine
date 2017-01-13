package com.javarush.test.level26.lesson15.big01.command;

import com.javarush.test.level26.lesson15.big01.CashMachine;
import com.javarush.test.level26.lesson15.big01.ConsoleHelper;
import com.javarush.test.level26.lesson15.big01.CurrencyManipulator;
import com.javarush.test.level26.lesson15.big01.CurrencyManipulatorFactory;

import java.util.ResourceBundle;

class InfoCommand implements Command {
    private ResourceBundle res = ResourceBundle.getBundle(CashMachine.RESOURCE_PATH + "info_en");

    @Override
    public void execute() {
        ConsoleHelper.writeMessage(res.getString("before"));
        boolean s = false;
        for(CurrencyManipulator x : CurrencyManipulatorFactory.getAllCurrencyManipulators()) {
            if(x.hasMoney()) {
                if (x.getTotalAmount() > 0) {
                    s = true;
                    ConsoleHelper.writeMessage(x.getCurrencyCode() + " - " + x.getTotalAmount());
                }
            }
        }
        if(!s)
            ConsoleHelper.writeMessage(res.getString("no.money"));
    }
}
