package com.danifoldi.bettercurrencies.util;

import java.util.function.Function;

public class Permission {

    public static final Function<String, String> BALANCE_COMMAND = c -> "bettercurrencies.command.balance." + c;
    public static final Function<String, String> BALANCE_COMMAND_OTHER = c -> "bettercurrencies.command.balance_other." + c;
    public static final Function<String, String> BALANCE_TOP_COMMAND = c -> "bettercurrencies.command.balancetop." + c;
    public static final Function<String, String> PAY_COMMAND = c -> "bettercurrencies.command.pay." + c;
    public static final Function<String, String> GIVE_COMMAND = c -> "bettercurrencies.command.give." + c;
    public static final Function<String, String> TAKE_COMMAND = c -> "bettercurrencies.command.take." + c;
    public static final Function<String, String> SET_COMMAND = c -> "bettercurrencies.command.set." + c;
    public static final Function<String, String> RESET_COMMAND = c -> "bettercurrencies.command.reset." + c;
}
