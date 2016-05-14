package hillbillies.model.expressions;

import hillbillies.model.expressions.*;
import hillbillies.model.Faction;
import hillbillies.model.Unit;

public class Enemy extends Expression<Unit> {
	
	@Override
	public Unit evaluate() {
		return
				this
				.getTask()
				.getUnit()
				.getWorld()
				.getFactions()
				.stream()
				.filter(
						(Faction f)
						-> f != this.getTask().getUnit().getFaction()
						)
				.findAny()
				.get()
				.getRandomUnit();
	}
	
	@Override
	public boolean isWellTyped () {
		return true;
	}
	
	@Override
	public Class<?> getReturningClass () {
		return Unit.class;
	}

}
