package miyucomics.hexcellular

import at.petrak.hexcasting.api.spell.iota.Iota
import at.petrak.hexcasting.api.spell.iota.IotaType
import at.petrak.hexcasting.api.spell.mishaps.MishapInvalidIota
import at.petrak.hexcasting.api.spell.mishaps.MishapNotEnoughArgs
import net.minecraft.nbt.NbtCompound
import net.minecraft.nbt.NbtElement
import net.minecraft.server.world.ServerWorld
import net.minecraft.text.Text
import net.minecraft.util.Formatting

class PropertyIota(property: String) : Iota(TYPE, property) {
	override fun isTruthy() = true
	override fun toleratesOther(that: Iota) = typesMatch(this, that) && this.name == (that as PropertyIota).name
	val name = payload as String

	override fun serialize(): NbtElement {
		val compound = NbtCompound()
		compound.putString("name", this.name)
		return compound
	}

	companion object {
		@JvmField
		val TYPE: IotaType<PropertyIota> = object : IotaType<PropertyIota>() {
			override fun deserialize(nbt: NbtElement, world: ServerWorld) = PropertyIota((nbt as NbtCompound).getString("name"))
			override fun display(nbt: NbtElement) = Text.literal((nbt as NbtCompound).getString("name")).formatted(Formatting.GREEN)
			override fun color() = -0x591c5f
		}
	}
}

fun List<Iota>.getProperty(idx: Int, argc: Int): String {
	val x = this.getOrElse(idx) { throw MishapNotEnoughArgs(idx + 1, this.size) }
	if (x is PropertyIota)
		return x.name
	throw MishapInvalidIota.ofType(x, if (argc == 0) idx else argc - (idx + 1), "property")
}