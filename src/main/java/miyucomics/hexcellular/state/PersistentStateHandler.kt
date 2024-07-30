package miyucomics.hexcellular.state

import miyucomics.hexcellular.HexcellularMain
import net.minecraft.nbt.NbtCompound
import net.minecraft.server.MinecraftServer
import net.minecraft.world.PersistentState
import net.minecraft.world.World

class PersistentStateHandler : PersistentState() {
	override fun writeNbt(nbt: NbtCompound): NbtCompound {
		return nbt
	}

	companion object {
		private fun createFromNbt(tag: NbtCompound): PersistentStateHandler {
			val state = PersistentStateHandler()
			return state
		}

		private fun getServerState(server: MinecraftServer): PersistentStateHandler {
			val persistentStateManager = server.getWorld(World.OVERWORLD)!!.persistentStateManager
			val state = persistentStateManager.getOrCreate(PersistentStateHandler::createFromNbt, ::PersistentStateHandler, HexcellularMain.MOD_ID)
			state.markDirty()
			return state
		}
	}
}