package com.danifoldi.bettercurrencies.command;

import com.danifoldi.bettercurrencies.api.BetterCurrenciesAPI;
import com.danifoldi.bettercurrencies.config.Config;
import com.danifoldi.bettercurrencies.util.Permission;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.plugin.Command;
import net.md_5.bungee.api.plugin.TabExecutor;
import org.jetbrains.annotations.NotNull;

public class BalanceTopCommand extends Command implements TabExecutor {

    private final @NotNull String name;
    private final @NotNull Config config;
    private final @NotNull BetterCurrenciesAPI api;

    public BalanceTopCommand(String name, Config config, BetterCurrenciesAPI api) {
        super(config.getCurrency(name).baltopCommand(), Permission.BALANCE_TOP_COMMAND.apply(name));
        this.name = name;
        this.config = config;
        this.api = api;
    }

    @Override
    public void execute(CommandSender sender, String[] args) {

        api.getBaltop(name).thenAccept(c -> {
            // todo messages
            c.forEach(b -> sender.sendMessage());
        });
    }

    @Override
    public Iterable<String> onTabComplete(CommandSender sender, String[] args) {
        return null;
    }
}
