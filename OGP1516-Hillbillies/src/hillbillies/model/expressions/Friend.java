package hillbillies.model.expressions;

import hillbillies.model.expressions.*;
import hillbillies.model.Unit;

public class Friend extends Expression<Unit> {

	@Override
	public Unit evaluate() {
		return
				this
				.getTask()
				.getUnit()
				.getFaction()
				.getAllUnits()
				.stream()
				.filter(
						(Unit u)
						-> u != this.getTask().getUnit()
						)
				.findAny()
				.get();
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
