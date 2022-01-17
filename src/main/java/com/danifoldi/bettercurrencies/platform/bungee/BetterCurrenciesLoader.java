package com.danifoldi.bettercurrencies.platform.bungee;

import com.danifoldi.bettercurrencies.api.BetterCurrenciesAPI;
import com.danifoldi.bettercurrencies.command.BalanceCommand;
import com.danifoldi.bettercurrencies.command.BalanceTopCommand;
import com.danifoldi.bettercurrencies.command.PayCommand;
import com.danifoldi.bettercurrencies.config.Config;
import com.danifoldi.bettercurrencies.data.CurrencySetting;
import com.danifoldi.bettercurrencies.util.Util;
import com.danifoldi.dml.DmlParseException;
import net.md_5.bungee.api.ProxyServer;
import org.jetbrains.annotations.NotNull;

import javax.inject.Inject;
import java.io.IOException;
import java.nio.file.Path;
import java.util.logging.Logger;

public class BetterCurrenciesLoader {

    private final @NotNull BetterCurrenciesPlugin plugin;
    private final @NotNull BetterCurrenciesAPI api;
    private final @NotNull Config config;
    private final @NotNull Path datafolder;
    private final @NotNull Logger logger;

    @Inject
    public BetterCurrenciesLoader(final @NotNull BetterCurrenciesPlugin plugin,
                                  final @NotNull BetterCurrenciesAPI api,
                                  final @NotNull Config config,
                                  final @NotNull Path datafolder,
                                  final @NotNull Logger logger) {
        this.plugin = plugin;
        this.api = api;
        this.config = config;
        this.datafolder = datafolder;
        this.logger = logger;
    }

    public void load() {
        try {
            Util.copyIfNotExists("config.dml", datafolder);
            config.loadFrom(datafolder.resolve("config.dml"));
            config.getCurrencies().forEach(c -> {
                CurrencySetting currency = config.getCurrency(c);
                if (!currency.balanceCommand().isEmpty()) {
                    ProxyServer.getInstance().getPluginManager().registerCommand(plugin, new BalanceCommand(c, config, api));
                }
                if (!currency.baltopCommand().isEmpty()) {
                    ProxyServer.getInstance().getPluginManager().registerCommand(plugin, new BalanceTopCommand(c, config, api));
                }
                if (!currency.payCommand().isEmpty()) {
                    ProxyServer.getInstance().getPluginManager().registerCommand(plugin, new PayCommand(c, config, api));
                }
            });
        } catch (IOException | DmlParseException e) {
            logger.severe("Failed to load config");
        }
    }

    public void unload() {
        ProxyServer.getInstance().getPluginManager().unregisterCommands(plugin);
    }
}
