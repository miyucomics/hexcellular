package miyucomics.hexcellular;

import at.petrak.hexcasting.api.PatternRegistry;
import at.petrak.hexcasting.api.spell.Action;
import at.petrak.hexcasting.api.spell.math.HexDir;
import at.petrak.hexcasting.api.spell.math.HexPattern;
import miyucomics.hexcellular.patterns.OpTest;

public class HexcellularPatterns {
	public static void init() {
		try {
			register("test", "ewe", HexDir.EAST, new OpTest());
		} catch (Exception ignored) {

		}
	}

	private static void register(String name, String signature, HexDir startDir, Action action) throws PatternRegistry.RegisterPatternException {
		PatternRegistry.mapPattern(HexPattern.fromAngles(signature, startDir), HexcellularMain.id(name), action);
	}

	private static void registerPerWorld(String name, String signature, HexDir startDir, Action action) throws PatternRegistry.RegisterPatternException {
		PatternRegistry.mapPattern(HexPattern.fromAngles(signature, startDir), HexcellularMain.id(name), action, true);
	}
}