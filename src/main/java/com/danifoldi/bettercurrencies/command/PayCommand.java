package com.danifoldi.bettercurrencies.command;

import com.danifoldi.bettercurrencies.api.BetterCurrenciesAPI;
import com.danifoldi.bettercurrencies.config.Config;
import com.danifoldi.bettercurrencies.util.Permission;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;
import net.md_5.bungee.api.plugin.TabExecutor;
import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

public class PayCommand extends Command implements TabExecutor {

    private final @NotNull String name;
    private final @NotNull Config config;
    private final @NotNull BetterCurrenciesAPI api;

    public PayCommand(String name, Config config, BetterCurrenciesAPI api) {
        super(config.getCurrency(name).payCommand(), Permission.PAY_COMMAND.apply(name));
        this.name = name;
        this.config = config;
        this.api = api;
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if (!(sender instanceof ProxiedPlayer)) {
            // todo message
            return;
        }
        if (args.length < 2) {
            // todo message
            return;
        }

        ProxiedPlayer player = (ProxiedPlayer)sender;
        BigDecimal amount = new BigDecimal(args[1]);
        api.hasBalance(name, UUID.fromString(args[0])).thenApply(b -> {
            if (!b) {
                // todo message
                return;
            }

            CompletableFuture.allOf(api.getBalance(name, UUID.fromString(args[0])), api.getBalance(name, player.getUniqueId())).thenApply()
        });
    }

    @Override
    public Iterable<String> onTabComplete(CommandSender sender, String[] args) {
        return null;
    }
}
