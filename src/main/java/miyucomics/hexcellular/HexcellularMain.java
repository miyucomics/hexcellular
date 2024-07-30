package miyucomics.hexcellular;

import net.fabricmc.api.ModInitializer;
import net.minecraft.util.Identifier;

public class HexcellularMain implements ModInitializer {
	public static final String MOD_ID = "hexcellular";

	@Override
	public void onInitialize() {
		HexcellularPatterns.init();
	}

	public static Identifier id(String string) {
		return new Identifier(MOD_ID, string);
	}
}