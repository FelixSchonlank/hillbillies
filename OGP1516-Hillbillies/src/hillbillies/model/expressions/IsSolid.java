package hillbillies.model.expressions;

import hillbillies.model.expressions.*;
import hillbillies.model.Position;

public class IsSolid extends Expression<Boolean> {
	
	IsSolid(Expression<Position> position){
		this.setPosition(position);
	}
	
	public Boolean evaluate(){
		return !this.getTask().getScheduler().getFaction().getRandomUnit().getWorld().isPassableCube(((Position) position.evaluate()).toCoordinate());
	}
	
	public void setPosition(Expression<Position> position){
		if (! this.isValidPosition(position)){
			throw new IllegalArgumentException();
		}
		this.position = position;
	}
	
	private boolean isValidPosition(Expression<Position> position) {
		return true;
	}

	public Expression<Position> getPosition(){
		return this.position;
	}
	
	private Expression<Position> position;
}
