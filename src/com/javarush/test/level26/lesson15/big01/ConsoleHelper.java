package com.javarush.test.level26.lesson15.big01;

import com.javarush.test.level26.lesson15.big01.exception.InterruptOperationException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ResourceBundle;

public class ConsoleHelper {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    private static ResourceBundle res = ResourceBundle.getBundle(CashMachine.RESOURCE_PATH + "common_en");

    public static void writeMessage(String message) {
        System.out.println(message);
    }

    public static String readString() throws InterruptOperationException {

        String line = "";
        try {
            line = br.readLine();
            if(line.equalsIgnoreCase("exit"))
                throw new InterruptOperationException();

        } catch (IOException ioe) {}

        return line;
    }

    public static String askCurrencyCode() throws InterruptOperationException {

        writeMessage(res.getString("choose.currency.code"));
        String test;
        while (true)
        {
            test = readString();
            if (test.length() == 3)
                break;
            else
                writeMessage(res.getString("invalid.data"));
        }
        return test.toUpperCase();
    }

    public static String[] getValidTwoDigits(String currencyCode) throws InterruptOperationException {

        writeMessage(String.format(res.getString("choose.denomination.and.count.format"), currencyCode));
        String[] array;
        while (true)
        {
            String s = readString();
            array = s.split(" ");
            int k;
            int l;
            try
            {
                k = Integer.parseInt(array[0]);
                l = Integer.parseInt(array[1]);
            }
            catch (Exception e)
            {
                writeMessage(res.getString("invalid.data"));
                continue;
            }
            if (k <= 0 || l <= 0 || array.length > 2)
            {
                writeMessage(res.getString("invalid.data"));
                continue;
            }
            break;
        }
        return array;
    }

    public static Operation askOperation() throws InterruptOperationException {
        while(true) {
            writeMessage(res.getString("choose.operation"));
            writeMessage(res.getString("operation.INFO") + ": 1;\n" +
                    res.getString("operation.DEPOSIT") + ": 2;\n" +
                    res.getString("operation.WITHDRAW") + ": 3;\n" +
                    res.getString("operation.EXIT") + ": 4");
            try {
                return  Operation.getAllowableOperationByOrdinal(Integer.parseInt(readString()));
            } catch(IllegalArgumentException e) {
                writeMessage(res.getString("invalid.data"));
            }
        }
    }

    public static void printExitMessage() {
        writeMessage(res.getString("the.end"));
    }
}
