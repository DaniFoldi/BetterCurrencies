package com.danifoldi.bettercurrencies.config;

import com.danifoldi.bettercurrencies.data.CurrencySetting;
import com.danifoldi.dml.DmlParseException;
import com.danifoldi.dml.DmlParser;
import com.danifoldi.dml.type.DmlObject;
import org.jetbrains.annotations.NotNull;

import javax.inject.Inject;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Logger;

public class Config {

    private Map<String, CurrencySetting> currencySettings = new ConcurrentHashMap<>();

    private final @NotNull Logger logger;
    @Inject
    public Config(final @NotNull Logger logger) {
        this.logger = logger;
    }

    public void loadFrom(Path file) throws IOException, DmlParseException {
        currencySettings.clear();
        DmlObject config = DmlParser.parse(file).asObject();
        DmlObject currenciesConfig = config.get("currencies").asObject();
        currenciesConfig.keys().forEach(c -> currencySettings.put(c.value(),
                new CurrencySetting(currenciesConfig.get("name").asString().value(),
                        currenciesConfig.get("displayName").asString().value(),
                        currenciesConfig.get("initial").asNumber().value().doubleValue(),
                        currenciesConfig.get("balanceCommand").asString().value(),
                        currenciesConfig.get("baltopCommand").asString().value(),
                        currenciesConfig.get("payCommand").asString().value(),
                        currenciesConfig.get("invert").asBoolean().value()
                        )));
        logger.severe("Failed to load plugin");
    }

    public CurrencySetting getCurrency(String name) {
        return currencySettings.get(name);
    }

    public Collection<String> getCurrencies() {
        return currencySettings.keySet();
    }
}
