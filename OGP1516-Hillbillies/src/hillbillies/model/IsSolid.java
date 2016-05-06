package hillbillies.model;

public class IsSolid extends BooleanExpression {
	
	IsSolid(PositionExpression position){
		this.setPosition(position);
	}
	
	public boolean evalateExpression(){
		return this.getTask().getScheduler().getFaction().getRandomUnit().getWorld().isPassableCube(((Position) position.evaluate()).toCoordinate());
	}
	
	public void setPosition(PositionExpression position){
		if (! this.isValidPosition(position)){
			throw new IllegalArgumentException();
		}
		this.position = position;
	}
	
	private boolean isValidPosition(PositionExpression position) {
		return true;
	}

	public PositionExpression getPosition(){
		return this.position;
	}
	
	private PositionExpression position;
}
