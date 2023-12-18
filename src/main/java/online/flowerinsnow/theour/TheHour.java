package online.flowerinsnow.theour;

import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;

import java.util.Calendar;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class TheHour implements ClientModInitializer {
	private final ScheduledThreadPoolExecutor scheduler = new ScheduledThreadPoolExecutor(32);
	@Override
	public void onInitializeClient() {
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
					case 56 -> player.networkHandler.sendChatMessage("应援团提醒您，整点对时");
					case 57 -> player.networkHandler.sendChatMessage("3");
					case 58 -> player.networkHandler.sendChatMessage("2");
					case 59 -> player.networkHandler.sendChatMessage("1");
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
				player.networkHandler.sendChatMessage("现在时刻：" + moment + hour + "点");
			}
		}, 0L, 1000L, TimeUnit.MILLISECONDS);
	}
}