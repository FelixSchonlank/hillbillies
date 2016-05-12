package hillbillies.model.expressions;

import hillbillies.model.Unit;

public class IsAlive extends Expression<Boolean> {
	
	public IsAlive(Expression<Unit> unit){
		this.setUnit(unit);
	}
	
	public Boolean evaluate(){
		return !this.getUnit().evaluate().isTerminated();
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
