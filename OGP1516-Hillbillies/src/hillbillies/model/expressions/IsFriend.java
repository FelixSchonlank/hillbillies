package hillbillies.model.expressions;

import hillbillies.model.Expression;
import hillbillies.model.Unit;

public class IsFriend extends Expression<Boolean> {
	
	public IsFriend(Expression<Unit> unit){
		this.setUnit(unit);
	}
	
	public Boolean evaluate(){
		return this.getTask().getScheduler().getFaction().hasAsUnit(getUnit().evaluate());
	}
	
	public static boolean isValidUnit(Expression<Unit> unit){
		return true;
	} 
	
	public void setUnit(Expression<Unit> unit){
		if(!isValidUnit(unit)){
			throw new IllegalArgumentException();
		}
		this.unit = unit;
	}
	
	public Expression<Unit> getUnit(){
		return this.unit;
	}
	
	private Expression<Unit> unit;

}
