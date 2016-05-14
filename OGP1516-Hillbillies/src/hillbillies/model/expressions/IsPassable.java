package hillbillies.model.expressions;

import hillbillies.model.expressions.*;
import hillbillies.model.Coordinate;
import hillbillies.model.Position;
import hillbillies.model.WrongTypeException;

public class IsPassable extends Expression<Boolean> {
	
	IsPassable(Expression<Coordinate> position){
		this.setPosition(position);
	}
	
	public Boolean evaluate() throws IllegalArgumentException, WrongTypeException{
		return this.getTask().getScheduler().getFaction().getRandomUnit().getWorld().isPassableCube((Coordinate) position.evaluate());
	}
	
	public void setPosition(Expression<Coordinate> position){
		if (! this.isValidPosition(position)){
			throw new IllegalArgumentException();
		}
		this.position = position;
	}
	
	private boolean isValidPosition(Expression<Coordinate> position) {
		return true;
	}

	public Expression<Coordinate> getPosition(){
		return this.position;
	}
	
	private Expression<Coordinate> position;
	
	@Override
	public boolean isWellTyped () {
		return this.getPosition().isWellTyped();
	}
	
	@Override
	public Class<?> getReturningClass () {
		return Boolean.class;
	}

}
