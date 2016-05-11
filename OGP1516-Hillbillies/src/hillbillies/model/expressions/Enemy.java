package hillbillies.model.expressions;

import hillbillies.model.Expression;
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

}
