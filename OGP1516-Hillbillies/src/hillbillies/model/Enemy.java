package hillbillies.model;

public class Enemy extends Expression<Unit> {
	
	@Override
	public Unit evaluate() {
		Faction enemyFaction = null;
		for(Faction element : this.getTask().getUnit().getWorld().getFactions() ){
			if(element != this.getTask().getUnit().getFaction()){
				enemyFaction = element;
			}
		}
		return enemyFaction.getRandomUnit();
	}

}
