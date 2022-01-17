package com.danifoldi.bettercurrencies.util;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;

public class Util {

    public static @NotNull Path copyIfNotExists(final @NotNull String name, final @NotNull Path parent) throws IOException {
        if (Files.notExists(parent)) {
            Files.createDirectories(parent);
        }

        final Path child = parent.resolve(name);
        if (Files.exists(child)) {
            return child;
        }

        try (final InputStream stream = Util.class.getResourceAsStream("/" + name)) {
            if (stream == null) {
                throw new IOException(String.format("Could not find resource named '%s'", name));
            }

            Files.copy(stream, child);
        }

        return child;
    }

    private Util() {
        throw new UnsupportedOperationException();
    }
}
