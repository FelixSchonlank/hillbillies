package hillbillies.model.statements;

import java.util.List;

public class SequenceStatement extends Statement {
	
	private final List<Statement> body;
	
	public SequenceStatement (List<Statement> body) {
		this.body = body;
		for (int i=0; i<this.body.size()-1; i++) {
			this.body.get(i).setNext(this.body.get(i+1));
		}
		// This leaves the last element unset, and thus setNext should be
		// called.
	}
	
	@Override
	public Statement getNextStatement () {
		if (this.body.size() == 0) {
			return this.getNext();
		} else {
			return this.body.get(0);
		}
	}
	
	@Override
	public void setNext (Statement next) {
		this.next = next;
		this.body.get(this.body.size() - 1).setNext(next);
	}
	
}
