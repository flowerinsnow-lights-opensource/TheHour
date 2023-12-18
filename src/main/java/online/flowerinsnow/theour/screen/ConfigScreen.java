package online.flowerinsnow.theour.screen;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.Text;
import online.flowerinsnow.theour.config.Config;

public class ConfigScreen extends Screen {
    private final Screen superScreen;
    private final Config config;
    public ConfigScreen(Screen superScreen, Config config) {
        super(Text.translatable("the-hour.screen.config.title"));

        this.superScreen = superScreen;
        this.config = config;
    }

    @Override
    protected void init() {
        addDrawable(ButtonWidget.builder(config.enable ? Text.translatable("the-hour.screen.config.enabled") : Text.translatable("the-hour.screen.config.disabled"),
                button -> {
                    config.enable = !config.enable;
                    config.save();
                    button.setMessage(config.enable ? Text.translatable("the-hour.screen.config.enabled") : Text.translatable("the-hour.screen.config.disabled"));
                })
                .position(this.width / 2 - 100, this.height / 2 - 30)
                .size(200, 20)
                .build()
        );
        addDrawable(ButtonWidget.builder(Text.translatable("the-hour.screen.config.reload"),
                button -> config.reload())
                .position(this.width / 2 - 100, this.height / 2 - 10)
                .size(200, 20)
                .build()
        );
        addDrawable(ButtonWidget.builder(Text.translatable("the-hour.screen.config.done"),
                button -> MinecraftClient.getInstance().setScreen(superScreen))
                .position(this.width / 2 - 100, this.height / 2 + 10)
                .size(200, 20)
                .build()
        );
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        renderBackground(context);
        super.render(context, mouseX, mouseY, delta);
    }
}
