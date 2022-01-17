package com.danifoldi.bettercurrencies.data;

import com.danifoldi.dataverse.DataVerse;
import com.danifoldi.dataverse.translation.TranslationEngine;

import java.math.BigDecimal;
import java.util.UUID;

public class Balance {

    String name;
    UUID uuid;
    BigDecimal balance;
    String currency;

    public Balance(UUID uuid, String name, Double initial, String currency) {
        this.uuid = uuid;
        this.name = name;
        this.balance = new BigDecimal(initial);
        this.currency = currency;
    }

    public static Balance empty() {
        return new Balance(UUID.randomUUID(), "", 0d, "");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public static void linkDataverse() {
        assert DataVerse.getDataVerse() != null;
        TranslationEngine engine = DataVerse.getDataVerse().getTranslationEngine();
        engine.addJavaTypeToMysqlColumn(Balance.class.getName(), "VARCHAR(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci");
        engine.addJavaTypeToMysqlQuery(Balance.class.getName(), (statement, col, field, obj) -> {
            // todo

        });
        engine.addMysqlResultToJavaType(Balance.class.getName(), (results, colName, spec, obj) -> {
            // todo

        });
    }
}
