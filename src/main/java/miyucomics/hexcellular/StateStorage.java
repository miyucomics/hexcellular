package miyucomics.hexcellular;

import at.petrak.hexcasting.api.spell.iota.Iota;
import at.petrak.hexcasting.api.spell.iota.NullIota;
import at.petrak.hexcasting.common.lib.hex.HexIotaTypes;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.PersistentState;
import net.minecraft.world.PersistentStateManager;
import net.minecraft.world.World;

import java.util.HashMap;

public class StateStorage extends PersistentState {
	public StateStorage() { super(); }

	public final HashMap<String, NbtCompound> properties = new HashMap<>();

	@Override
	public NbtCompound writeNbt(NbtCompound nbt) {
		properties.forEach(nbt::put);
		return nbt;
	}

	public static StateStorage createFromNbt(NbtCompound nbt) {
		StateStorage state = new StateStorage();
		nbt.getKeys().forEach(key -> state.properties.put(key, nbt.getCompound(key)));
		return state;
	}

	public static StateStorage getServerState(MinecraftServer server) {
		PersistentStateManager persistentStateManager = server.getWorld(World.OVERWORLD).getPersistentStateManager();
		return persistentStateManager.getOrCreate(StateStorage::createFromNbt, StateStorage::new, HexcellularMain.MOD_ID);
	}

	public static void setProperty(ServerWorld world, String name, Iota iota) {
		StateStorage state = getServerState(world.getServer());
		state.properties.put(name, HexIotaTypes.serialize(iota));
		state.markDirty();
	}

	public static Iota getProperty(ServerWorld world, String name) {
		StateStorage state = getServerState(world.getServer());
		if (!state.properties.containsKey(name))
			return new NullIota();
		return HexIotaTypes.deserialize(state.properties.get(name), world);
	}
}