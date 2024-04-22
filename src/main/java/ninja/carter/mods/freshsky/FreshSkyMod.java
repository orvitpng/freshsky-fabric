package ninja.carter.mods.freshsky;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.world.scores.DisplaySlot;
import net.minecraft.world.scores.Objective;

public class FreshSkyMod implements ClientModInitializer {
	public static boolean isSkyblock;

	@Override
	public void onInitializeClient() {
		// TODO: maybe find a more efficient way to do this? every tick could hurt performance
		ClientTickEvents.START_WORLD_TICK.register(level -> {
			Objective sb = level.getScoreboard().getDisplayObjective(DisplaySlot.SIDEBAR);
			isSkyblock = sb != null && sb.getName().equals("SBScoreboard");
		});
	}
}
