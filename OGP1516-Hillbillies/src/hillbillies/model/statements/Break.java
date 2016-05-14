package hillbillies.model.statements;

import hillbillies.model.BadFSMStateException;

public class Break extends Statement {
	
	public Break () {
		
	}

	@Override
	public void execute() throws BadFSMStateException {
		
	}
	
	@Override
	public boolean isWellTyped () {
		return true;
	}
	
}
