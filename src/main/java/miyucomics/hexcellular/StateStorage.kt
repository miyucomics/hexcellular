package miyucomics.hexcellular

import at.petrak.hexcasting.api.spell.iota.Iota
import at.petrak.hexcasting.api.spell.iota.NullIota
import at.petrak.hexcasting.common.lib.hex.HexIotaTypes
import net.minecraft.nbt.NbtCompound
import net.minecraft.server.MinecraftServer
import net.minecraft.server.world.ServerWorld
import net.minecraft.world.PersistentState
import net.minecraft.world.World
import java.util.function.Consumer

class StateStorage : PersistentState() {
	val properties: HashMap<String, NbtCompound> = HashMap()

	override fun writeNbt(nbt: NbtCompound): NbtCompound {
		properties.forEach { (key: String?, element: NbtCompound?) -> nbt.put(key, element) }
		return nbt
	}

	companion object {
		private fun createFromNbt(nbt: NbtCompound): StateStorage {
			val state = StateStorage()
			nbt.keys.forEach(Consumer { key: String -> state.properties[key] = nbt.getCompound(key) })
			return state
		}

		fun getServerState(server: MinecraftServer): StateStorage {
			val persistentStateManager = server.getWorld(World.OVERWORLD)!!.persistentStateManager
			return persistentStateManager.getOrCreate(::createFromNbt, ::StateStorage, HexcellularMain.MOD_ID)
		}

		fun setProperty(world: ServerWorld, name: String, iota: Iota) {
			val state = getServerState(world.server)
			state.properties[name] = HexIotaTypes.serialize(iota)
			state.markDirty()
		}

		fun getProperty(world: ServerWorld, name: String): Iota {
			val state = getServerState(world.server)
			if (!state.properties.containsKey(name)) return NullIota()
			return HexIotaTypes.deserialize(state.properties[name]!!, world)
		}
	}
}