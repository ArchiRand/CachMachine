package com.javarush.test.level26.lesson15.big01.command;

import com.javarush.test.level26.lesson15.big01.CashMachine;
import com.javarush.test.level26.lesson15.big01.ConsoleHelper;
import com.javarush.test.level26.lesson15.big01.CurrencyManipulator;
import com.javarush.test.level26.lesson15.big01.CurrencyManipulatorFactory;
import com.javarush.test.level26.lesson15.big01.exception.InterruptOperationException;
import com.javarush.test.level26.lesson15.big01.exception.NotEnoughMoneyException;

import java.util.Map;
import java.util.ResourceBundle;

class WithdrawCommand implements Command
{
    private ResourceBundle res = ResourceBundle.getBundle(CashMachine.RESOURCE_PATH + "withdraw_en");

    @Override
    public void execute() throws InterruptOperationException
    {

        String currencyCode = ConsoleHelper.askCurrencyCode();
        CurrencyManipulator currencyManipulator = CurrencyManipulatorFactory.getManipulatorByCurrencyCode(currencyCode);

        int sum;
        while(true) {
            ConsoleHelper.writeMessage(res.getString("before"));
            try {
                sum = Integer.parseInt(ConsoleHelper.readString());
            }

            catch(NumberFormatException e) {
                ConsoleHelper.writeMessage(res.getString("specify.amount"));
                continue;
            }

            if(sum <= 0) {
                ConsoleHelper.writeMessage(res.getString("specify.not.empty.amount"));
                continue;
            }

            if(!currencyManipulator.isAmountAvailable(sum)) {
                ConsoleHelper.writeMessage(res.getString("not.enough.money"));
                continue;
            }

            try {
                for(Map.Entry<Integer, Integer> pair : currencyManipulator.withdrawAmount(sum).entrySet())
                    ConsoleHelper.writeMessage("\t" + pair.getKey() + " - " + pair.getValue());
                ConsoleHelper.writeMessage(String.format(res.getString("success.format"), sum, currencyCode));
                break;
            }
            catch(NotEnoughMoneyException e) {
                ConsoleHelper.writeMessage(res.getString("exact.amount.not.available"));
                continue;
            }
        }
    }
}

