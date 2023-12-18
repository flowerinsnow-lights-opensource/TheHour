package online.flowerinsnow.theour.config;


import net.minecraft.client.MinecraftClient;
import net.minecraft.util.crash.CrashReport;
import org.bspfsystems.yamlconfiguration.file.YamlConfiguration;

import java.io.IOException;
import java.nio.file.Path;

public class Config {
    private final Path path;

    public boolean enable;
    public String pre4;
    public String pre3;
    public String pre2;
    public String pre1;
    public String onHour;

    private YamlConfiguration config;

    public Config(Path path) {
        this.path = path;
    }

    public void reload() {
        config = YamlConfiguration.loadConfiguration(path.toFile());

        enable = config.getBoolean("enable");
        pre4 = config.getString("pre4");
        pre3 = config.getString("pre3");
        pre2 = config.getString("pre2");
        pre1 = config.getString("pre1");
        onHour = config.getString("on-hour");
    }

    public void save() {
        config.set("enable", enable);
        try {
            config.save(path.toFile());
        } catch (IOException e) {
            MinecraftClient.printCrashReport(CrashReport.create(e, "The Hour保存配置文件失败"));
        }
    }
}
