package online.flowerinsnow.theour.entrypoint;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import online.flowerinsnow.theour.TheHour;
import online.flowerinsnow.theour.screen.ConfigScreen;

public class TheHourModMenuAPIImpl implements ModMenuApi {
    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        return superScreen -> new ConfigScreen(superScreen, TheHour.getConfig());
    }
}
