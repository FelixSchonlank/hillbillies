package hillbillies.model.expressions;

import hillbillies.model.Expression;
import hillbillies.model.Unit;

public class Friend extends Expression<Unit> {

	@Override
	public Unit evaluate() {
		Unit thisUnit = this.getTask().getUnit();
		for( Unit unit : thisUnit.getFaction().getAllUnits()){
			if(unit != this.getTask().getUnit()){
				return unit;
			}
		}
		return null;
	}
	
}
