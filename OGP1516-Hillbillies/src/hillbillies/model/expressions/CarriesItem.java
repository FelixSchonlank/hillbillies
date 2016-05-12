package hillbillies.model.expressions;

import hillbillies.model.expressions.*;
import hillbillies.model.Unit;

public class CarriesItem extends Expression<Boolean> {
	
	public CarriesItem(Expression<Unit> unit){
		this.setUnit(unit);
	}
	
	public Boolean evaluate(){
		return this.getUnit().evaluate().hasItem();
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
