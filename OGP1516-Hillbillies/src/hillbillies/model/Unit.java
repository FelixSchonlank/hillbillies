package hillbillies.model;

public class Unit {
	
	/* Position */
}
	/**
	 * Get the precise coordinate of the given unit.
	 * 
	 * @param unit
	 *            The unit for which to return the position.
	 * @return The coordinate of the center of the unit, as an array with 3 doubles {x, y, z}.
	 *         |unit.Position
	 * @throws ModelException
	 *             the unit is not a valid unit
	 *             |unit == null
	 */
        public double[] getPosition(Unit unit) throws ModelException;{     
	     if (unit == 0)                                                 
	         throw new ModelException(...);                             
 	     return unit.Position;              

	/**
	 *alternatief voor de vorige
	 */
	public double[] getPosition(Unit unit) throws ModelException;{
	    if (unit == null)
		throw new ModelException(...);       
	    return unit.getPosition();             
	

	/**Voorstel: We introduceren een basic inspector getPosition die inwerkt op het object waartegen hij is uitgevoord ipv het object die meegegeven is als param  
	 *
	 * return the position of the center of the unit
	 *
	 */
	@basic 
	public double[] getPosition(){     
	    return this.position;          
	}

	/**Voorstel: in de constructor zullen we waarschijnlijk de positie moeten initialiseren dus hebben we daar best een setter voor
	 *
	 *sets the Position to a given position
	 *@post The new position of the unit is equal to the given position
	 *      |new.position == position
	 *@throw ModelException
	 *        If the Position is not a valid position
	 *       |if !(isValidPosition(Position)
	 */
	public void setPosition(double[] Position)throws ModelException{
	    if (!isValidPosition(Position)
		throw new ModelException(...);
	    this.Position = Position;      
	}


	/**
	 *Checks whether the geven position is a valid position
	 *
	 *@param: Position (The position to check)
	 *@return: True if and only if 
	 *             Position contaigns exactly 3 eleements
	 *             all tree coordinates are positive and smaller or equal to 50
	 *             |Position.lenth == 3
	 *             |for each i in Position.lenth:
	 *             |    Position[i] >=0 and Position <= 50
	 */
	public boolean isValidPosition(double[] Position){     
	    boolean result = (Position.lenth == 3);             
	    for (double coordinate : Position){              
		if (!(coordinate >= 0 and coordiate <= 50)) 
		    result = false;                         
	    }                                          
	    return result;                             
	    }




	/**
	 * Get the coordinate of the cube occupied by the given unit.
	 * 
	 * @param unit
	 *            The unit for which to return the cube coordinate.
	 * @return The coordinate of the cube in which the center of the unit lies,
	 *         as an array with 3 integers {x, y, z}.
	 * @throws ModelException
	 *             A precondition was violated or an exception was thrown.
	 */
	public int[] getCubeCoordinate(Unit unit) throws ModelException;


}
