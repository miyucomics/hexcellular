package miyucomics.hexcellular;

import at.petrak.hexcasting.common.lib.hex.HexIotaTypes;
import net.fabricmc.api.ModInitializer;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class HexcellularMain implements ModInitializer {
	public static final String MOD_ID = "hexcellular";

	@Override
	public void onInitialize() {
		HexcellularPatterns.init();
		Registry.register(HexIotaTypes.REGISTRY, id("property"), PropertyIota.TYPE);
	}

	public static Identifier id(String string) {
		return new Identifier(MOD_ID, string);
	}
}