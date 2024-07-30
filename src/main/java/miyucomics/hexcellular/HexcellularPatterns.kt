package miyucomics.hexcellular

import at.petrak.hexcasting.api.PatternRegistry
import at.petrak.hexcasting.api.spell.Action
import at.petrak.hexcasting.api.spell.math.HexDir
import at.petrak.hexcasting.api.spell.math.HexPattern
import net.minecraft.util.Identifier

object HexcellularPatterns {
	private val PATTERNS: MutableList<Triple<HexPattern, Identifier, Action>> = ArrayList()
	private val PER_WORLD_PATTERNS: MutableList<Triple<HexPattern, Identifier, Action>> = ArrayList()

	@JvmStatic
	fun init() {
		for ((first, second, third) in PATTERNS)
			PatternRegistry.mapPattern(first, second, third)
		for ((first, second, third) in PER_WORLD_PATTERNS)
			PatternRegistry.mapPattern(first, second, third, true)
	}

	private fun register(name: String, signature: String, startDir: HexDir, action: Action) = PATTERNS.add(Triple(HexPattern.fromAngles(signature, startDir), HexcellularMain.id(name), action))
	private fun registerPerWorld(name: String, signature: String, startDir: HexDir, action: Action) = PER_WORLD_PATTERNS.add(Triple(HexPattern.fromAngles(signature, startDir), HexcellularMain.id(name), action))
}