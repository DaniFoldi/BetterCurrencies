package com.danifoldi.bettercurrencies.command;

import com.danifoldi.bettercurrencies.api.BetterCurrenciesAPI;
import com.danifoldi.bettercurrencies.config.Config;
import com.danifoldi.bettercurrencies.util.Permission;
import com.destroystokyo.paper.profile.PlayerProfile;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;
import net.md_5.bungee.api.plugin.TabExecutor;
import org.jetbrains.annotations.NotNull;

import java.util.Collections;

public class BalanceCommand extends Command implements TabExecutor {

    private final @NotNull String name;
    private final @NotNull Config config;
    private final @NotNull BetterCurrenciesAPI api;

    public BalanceCommand(String name, Config config, BetterCurrenciesAPI api) {
        super(config.getCurrency(name).balanceCommand(), Permission.BALANCE_COMMAND.apply(name));
        this.name = name;
        this.config = config;
        this.api = api;
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if (!(sender instanceof ProxiedPlayer)) {
            // todo message
            sender.sendMessage();
            return;
        }
        ProxiedPlayer player = (ProxiedPlayer)sender;

        api.getBalance(name, player.getUniqueId(), player.getName()).thenAccept(bal -> {
            // todo message
            sender.sendMessage();
        });
    }

    @Override
    public Iterable<String> onTabComplete(CommandSender sender, String[] args) {
        return args.length <= 1 ? ProxyServer.getInstance().getPlayers().stream().map(CommandSender::getName).filter(n -> n.startsWith(args.length == 0 ? "" : args[0])).toList() : Collections.emptyList();
    }
}
