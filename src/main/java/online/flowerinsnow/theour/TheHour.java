package online.flowerinsnow.theour;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import online.flowerinsnow.theour.config.Config;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Calendar;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class TheHour implements ClientModInitializer {
	public static final Logger LOGGER = LogManager.getLogger("the-hour");

	private static Config config;
	private final ScheduledThreadPoolExecutor scheduler = new ScheduledThreadPoolExecutor(32);
	@Override
	public void onInitializeClient() {
		config = new Config(FabricLoader.getInstance().getConfigDir().resolve("the-hour.yml"));
		config.reload();

		scheduler.scheduleAtFixedRate(() -> {
			ClientPlayerEntity player = MinecraftClient.getInstance().player;
			if (player == null) {
				return;
			}
			Calendar calendar = Calendar.getInstance();
			int hour = calendar.get(Calendar.HOUR_OF_DAY);
			int minute = calendar.get(Calendar.MINUTE);
			int second = calendar.get(Calendar.SECOND);
			if (minute == 59) {
				switch (second) {
					case 56 -> player.networkHandler.sendChatMessage(config.pre4);
					case 57 -> player.networkHandler.sendChatMessage(config.pre3);
					case 58 -> player.networkHandler.sendChatMessage(config.pre2);
					case 59 -> player.networkHandler.sendChatMessage(config.pre1);
				}
			} else if (minute == 0 && second == 0) {
				String moment;
				if (hour <= 5) {
					moment = "凌晨";
				} else if (hour <= 8) {
					moment = "早晨";
				} else if (hour <= 11) {
					moment = "上午";
				} else if (hour <= 17) {
					moment = "下午";
				} else if (hour <= 19) {
					moment = "傍晚";
				} else {
					moment = "晚上";
				}
				player.networkHandler.sendChatMessage(config.onHour
						.replace("%(moment)", moment)
						.replace("%(hour)", Integer.toString(hour)));
			}
		}, 0L, 1000L, TimeUnit.MILLISECONDS);
	}

	public static Config getConfig() {
		return config;
	}
}