package hillbillies.model;

public class False extends Expression<Boolean>{

	@Override
	public Boolean evaluate() {
		return false;
	}

}
