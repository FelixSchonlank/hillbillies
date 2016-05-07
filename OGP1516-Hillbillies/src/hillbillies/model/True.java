package hillbillies.model;

public class True extends Expression<Boolean>{

	@Override
	public Boolean evaluate() {
		return true;
	}

}
