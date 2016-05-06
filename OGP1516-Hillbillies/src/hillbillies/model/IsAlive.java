package hillbillies.model;

public class IsAlive extends BooleanExpression {
	
	public IsAlive(UnitExpression unit){
		this.setUnit(unit);
	}
	
	public Boolean evaluate(){
		return !this.getUnit().evaluate().isTerminated();
	}
	
	public static boolean isValidUnit(UnitExpression unit){
		return true;
	} 
	
	public void setUnit(UnitExpression unit){
		if(!isValidUnit(unit)){
			throw new IllegalArgumentException();
		}
		this.unit = unit;
	}
	
	public UnitExpression getUnit(){
		return this.unit;
	}
	
	private UnitExpression unit;

}
