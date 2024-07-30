package miyucomics.hexcellular.patterns

import at.petrak.hexcasting.api.misc.MediaConstants
import at.petrak.hexcasting.api.spell.ConstMediaAction
import at.petrak.hexcasting.api.spell.casting.CastingContext
import at.petrak.hexcasting.api.spell.iota.Iota
import at.petrak.hexcasting.api.spell.mishaps.MishapOthersName
import miyucomics.hexcellular.StateStorage
import miyucomics.hexcellular.getProperty

class OpSetProperty : ConstMediaAction {
	override val argc = 2
	override val mediaCost = MediaConstants.DUST_UNIT / 10
	override fun execute(args: List<Iota>, ctx: CastingContext): List<Iota> {
		val name = args.getProperty(0, 2)
		val iota = args[1]
		val trueName = MishapOthersName.getTrueNameFromDatum(iota, ctx.caster)
		if (trueName != null)
			throw MishapOthersName(trueName)
		StateStorage.setProperty(ctx.world, name, iota)
		return listOf()
	}
}