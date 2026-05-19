package dev.boxadactle.everyslab.datagen;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import dev.boxadactle.everyslab.Constants;
import net.minecraft.locale.Language;
import net.minecraft.network.chat.FormattedText;
import net.minecraft.util.FormattedCharSequence;
import net.minecraft.util.GsonHelper;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

/**
 * Allows for the loading of languages that are not on the classpath
 * Minecraft bundles its language resources separately, so
 * they are not accessible in the datagen classpath
 */
public class LangUtil {

    static Language previous;

    public static void injectLanguage(String locale, boolean rightToLeft) {
        Map<String, String> map = new HashMap<>();

        previous = Language.getInstance();

        loadLangIntoMap(locale, map);

        // Inject into Minecraft's Language singleton
        Language.inject(new Language() {
            @Override
            public String getOrDefault(String key, String defaultValue) {
                return map.getOrDefault(key, defaultValue);
            }

            @Override
            public boolean has(String key) {
                return map.containsKey(key);
            }

            @Override
            public boolean isDefaultRightToLeft() {
                return rightToLeft; // set true for Arabic/Hebrew etc.
            }

            @Override
            public FormattedCharSequence getVisualOrder(FormattedText formattedText) {
                return null;
            }
        });
    }

    private static void loadLangIntoMap(String locale, Map<String, String> map) {
        // this is so unconventional
        String url = String.format(
                "https://raw.githubusercontent.com/InventivetalentDev/minecraft-assets/refs/heads/%s/assets/minecraft/lang/%s.json",
                Constants.MC_VERSION, locale
        );

        try {
            HttpURLConnection connection = (HttpURLConnection) new URI(url).toURL().openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);

            int status = connection.getResponseCode();
            if (status != 200) {
                Constants.LOG.error("Failed to fetch lang file from GitHub: HTTP {}", status);
            }

            try (InputStream is = connection.getInputStream()) {
                JsonObject json = GsonHelper.parse(new InputStreamReader(is, StandardCharsets.UTF_8));
                for (Map.Entry<String, JsonElement> entry : json.entrySet()) {
                    map.put(entry.getKey(), entry.getValue().getAsString());
                }
                Constants.LOG.info("Successfully loaded {}/{} from GitHub assets", Constants.MC_VERSION, locale);
            }

            connection.disconnect();
        } catch (IOException e) {
            Constants.LOG.error("Failed to fetch lang file from GitHub for version {} locale {}", Constants.MC_VERSION, locale, e);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    public static void restore() {
        Language.inject(previous);
        previous = null;
    }

}
