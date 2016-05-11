package hillbillies.model.expressions;

import java.util.HashSet;
import java.util.Set;

import hillbillies.model.Expression;
import hillbillies.model.Faction;
import hillbillies.model.Unit;
import hillbillies.model.Utils;

public class Any extends Expression<Unit> {
		
		@Override
		public Unit evaluate() {
			Set<Unit> units = new HashSet<Unit>();
			for(Faction element : this.getTask().getUnit().getWorld().getFactions() ){
					units.add(element.getRandomUnit());
			}
			return (Unit) Utils.getRandomElement(units);
		}	
}
