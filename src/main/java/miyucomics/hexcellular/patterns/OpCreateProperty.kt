package miyucomics.hexcellular.patterns

import at.petrak.hexcasting.api.misc.MediaConstants
import at.petrak.hexcasting.api.spell.ConstMediaAction
import at.petrak.hexcasting.api.spell.casting.CastingContext
import at.petrak.hexcasting.api.spell.iota.Iota
import at.petrak.hexcasting.api.spell.iota.NullIota
import miyucomics.hexcellular.PropertyIota
import miyucomics.hexcellular.PropertyNamer
import miyucomics.hexcellular.StateStorage

class OpCreateProperty : ConstMediaAction {
	override val argc = 0
	override val mediaCost = MediaConstants.CRYSTAL_UNIT

	override fun execute(args: List<Iota>, ctx: CastingContext): List<Iota> {
		val state = StateStorage.getServerState(ctx.world.server)
		var property = PropertyNamer.generatePropertyName()
		while (state.properties.containsKey(property))
			property = PropertyNamer.generatePropertyName()
		StateStorage.setProperty(ctx.world, property, NullIota())
		return listOf(PropertyIota(property))
	}
}