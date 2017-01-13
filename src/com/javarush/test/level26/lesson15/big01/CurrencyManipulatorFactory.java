package com.javarush.test.level26.lesson15.big01;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public final class CurrencyManipulatorFactory {

    static Map<String, CurrencyManipulator> map = new HashMap<>();

    private CurrencyManipulatorFactory() {

    }

    public static CurrencyManipulator getManipulatorByCurrencyCode(String currencyCode) {

        if(map.containsKey(currencyCode))
            return map.get(currencyCode);
        else {
            map.put(currencyCode, new CurrencyManipulator(currencyCode));
            return map.get(currencyCode);
        }
    }

    public static Collection<CurrencyManipulator> getAllCurrencyManipulators() {
        return map.values();
    }
}