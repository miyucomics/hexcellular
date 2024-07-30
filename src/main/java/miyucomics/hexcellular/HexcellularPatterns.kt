package miyucomics.hexcellular

import at.petrak.hexcasting.api.PatternRegistry
import at.petrak.hexcasting.api.spell.Action
import at.petrak.hexcasting.api.spell.math.HexDir
import at.petrak.hexcasting.api.spell.math.HexPattern
import miyucomics.hexcellular.patterns.OpCreateProperty
import miyucomics.hexcellular.patterns.OpObserveProperty
import miyucomics.hexcellular.patterns.OpSetProperty

object HexcellularPatterns {
	@JvmStatic
	fun init() {
		register("create_property", "aawe", HexDir.SOUTH_WEST, OpCreateProperty())
		register("observe_property", "aawd", HexDir.SOUTH_WEST, OpObserveProperty())
		register("set_property", "aawq", HexDir.SOUTH_WEST, OpSetProperty())
	}

	private fun register(name: String, signature: String, startDir: HexDir, action: Action) {
		PatternRegistry.mapPattern(HexPattern.fromAngles(signature, startDir), HexcellularMain.id(name), action)
	}
}