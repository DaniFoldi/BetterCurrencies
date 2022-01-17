package com.danifoldi.bettercurrencies.platform.bungee;

import com.danifoldi.bettercurrencies.data.Balance;
import com.danifoldi.dataverse.data.Namespaced;
import net.md_5.bungee.api.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

public class BetterCurrenciesPlugin extends Plugin implements Namespaced {

    @Override
    public void onEnable() {
        Balance.linkDataverse();
    }

    @Override
    public void onDisable() {

    }

    @Override
    public @NotNull String getNamespace() {
        return getDescription().getName();
    }
}
