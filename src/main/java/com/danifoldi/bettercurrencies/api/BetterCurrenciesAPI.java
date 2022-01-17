package com.danifoldi.bettercurrencies.api;

import com.danifoldi.bettercurrencies.config.Config;
import com.danifoldi.bettercurrencies.data.Balance;
import com.danifoldi.dataverse.DataVerse;
import com.danifoldi.dataverse.data.NamespacedDataVerse;
import org.jetbrains.annotations.NotNull;

import javax.inject.Inject;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

public class BetterCurrenciesAPI {

    private final @NotNull Config config;
    private final @NotNull Map<String, NamespacedDataVerse<Balance>> dataverseCache;

    @Inject
    public BetterCurrenciesAPI(final @NotNull Config config) {
        this.config = config;
        dataverseCache = config.getCurrencies().stream().collect(Collectors.toMap(c -> c, c -> DataVerse.getDataVerse().getNamespacedDataVerse(plugin, c, Balance::empty)))
    }

    public CompletableFuture<BigDecimal> getBalance(String currency, UUID uuid, String name) {
        return dataverseCache.get(currency).getOrCreate(uuid, new Balance(uuid, name, config.getCurrency(name).initial(), currency)).thenApply(b -> b.getBalance());
    }

    public CompletableFuture<Boolean> setBalance(Balance balance) {
        return dataverseCache.get(balance.getName()).createOrUpdate(balance.getUuid(), balance);
    }

    public CompletableFuture<Boolean> hasBalance(String currency, UUID uuid) {
        return dataverseCache.get(currency).exists(uuid);
    }

    public CompletableFuture<Collection<Balance>> getBaltop(String currency) {
        // todo paginate, sort
        return dataverseCache.get(currency).list();
    }
}
