package hillbillies.model.expressions;

import hillbillies.model.WrongTypeException;

public class Convert<T> extends Expression<T> {

	public final Expression<?> expression;
	private final Class<T> clazz;
	
	/**
	 * Constructs a new Convert object, which will convert its expression,
	 * which can be parameterized to any type, to the desired type upon
	 * evaluation. It will throw an error if the type cast appears to be
	 * impossible.
	 * @param expression
	 * 		The expression that can be of any type
	 * @param clazz
	 * 		The class of the expression. For example: if you want a CarriesItem
	 * 		expression to be converted, you should call
	 * 		Convert(theCarriesItemExpression, Boolean.class)
	 * 		btw, class was a reserved keyword so we had to use clazz.
	 */
	public Convert (Expression<?> expression, Class<T> clazz) {
		this.expression = expression;
		this.clazz = clazz;
	}

	@Override
	public T evaluate () throws WrongTypeException {
	    try {
	        return this.clazz.cast(expression);
	    } catch(ClassCastException e) {
	    	throw new WrongTypeException(e);
	    }
	}
	
	@Override
	public boolean isWellTyped () {
		// TODO I'm not even sure if this works
		return this.expression.isWellTyped()
				&& (this.expression instanceof ReadVariable
						|| this.expression.getReturningClass().equals(this.clazz)
						);
	}
	
	@Override
	public Class<?> getReturningClass () {
		return this.clazz;
	}

}
