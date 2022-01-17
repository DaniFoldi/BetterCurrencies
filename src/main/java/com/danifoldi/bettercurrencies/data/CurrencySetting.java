package com.danifoldi.bettercurrencies.data;

public record CurrencySetting(String name, String displayName, double initial, String balanceCommand, String baltopCommand, String payCommand, boolean invert) {

}
