package miyucomics.hexcellular.patterns;

import java.util.List;

import at.petrak.hexcasting.api.spell.ConstMediaAction;
import at.petrak.hexcasting.api.spell.OperationResult;
import at.petrak.hexcasting.api.spell.casting.CastingContext;
import at.petrak.hexcasting.api.spell.casting.eval.SpellContinuation;
import at.petrak.hexcasting.api.spell.iota.BooleanIota;
import at.petrak.hexcasting.api.spell.iota.Iota;
import net.minecraft.text.Text;
import org.jetbrains.annotations.NotNull;

public class OpTest implements ConstMediaAction{
	public OpTest () {}

	@Override
	public int getArgc(){ return 1;}

	@Override
	public int getMediaCost(){
		return 0;
	}

	@Override
	public boolean isGreat(){ return false;}

	@Override
	public boolean getCausesBlindDiversion(){ return false;}

	@Override
	public boolean getAlwaysProcessGreatSpell(){ return false;}

	@Override
	public @NotNull Text getDisplayName(){
		return DefaultImpls.getDisplayName(this);
	}

	@Override
	public @NotNull List<Iota> execute(@NotNull List<? extends Iota> args, @NotNull CastingContext context){
		return List.of(new BooleanIota(false));
	}

	@Override
	public @NotNull OperationResult operate(@NotNull SpellContinuation continuation, @NotNull List<Iota> stack, Iota ravenmind, @NotNull CastingContext castingContext){
		return ConstMediaAction.DefaultImpls.operate(this, continuation, stack, ravenmind, castingContext);
	}
}