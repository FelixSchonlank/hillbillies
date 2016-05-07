package hillbillies.model;

import java.util.HashSet;
import java.util.Set;

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
