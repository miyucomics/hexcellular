package miyucomics.hexcellular.patterns

import at.petrak.hexcasting.api.spell.ConstMediaAction
import at.petrak.hexcasting.api.spell.casting.CastingContext
import at.petrak.hexcasting.api.spell.iota.Iota
import miyucomics.hexcellular.StateStorage
import miyucomics.hexcellular.getProperty

class OpObserveProperty : ConstMediaAction {
	override val argc = 1
	override fun execute(args: List<Iota>, ctx: CastingContext) = listOf(StateStorage.getProperty(ctx.world, args.getProperty(0, 1)))
}