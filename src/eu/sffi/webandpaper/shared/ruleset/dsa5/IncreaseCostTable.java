package eu.sffi.webandpaper.shared.ruleset.dsa5;

/**
 * A class to calculate the increasing costs for skill, attributes and combat skills
 * @author Andi Popp
 *
 */
public class IncreaseCostTable {

	/**
	 * cost factor A
	 */
	public static final byte A = 1;
	
	/**
	 * cost factor B
	 */
	public static final byte B = 2;

	/**
	 * cost factor C
	 */
	public static final byte C = 3;

	/**
	 * cost factor D
	 */
	public static final byte D = 4;

	/**
	 * cost factor E
	 */
	public static final byte E = 15;
	
	/**
	 * Returns the cost to increase the value by 1 to targetLevel
	 * @param targetLevel the level of the value after the increase
	 * @return the AP costs to increase the value
	 * @throws IllegalArgumentException if the cost factor is not a valid cost factor byte or the target level is negative
	 */
	public static int getCost(int targetLevel, byte costFactor) throws IllegalArgumentException{
		//Check input
		if (costFactor < A || costFactor > E || (costFactor < E && costFactor > D)) throw new IllegalArgumentException("Cost factor no valid: "+costFactor);
		if (targetLevel < 0) throw new IllegalArgumentException("Target level not valid: "+targetLevel);
		
		//calculate cost
		if (costFactor < E){
			//cost factor A-D
			if (targetLevel < 13) return costFactor;
			return costFactor*(targetLevel-11);
		}
		else{
			//cost factor E
			if (targetLevel < 15) return costFactor;
			return costFactor*(targetLevel-13);
		}
	}
	
	/**
	 * A batch calculation to increase a value from the startLevel to a given level
	 * @param startLevel the current level of the value
	 * @param targetLevel the aspired target level
	 * @param costFactor the cost factor as byte value
	 * @return the total AP cost to increase the value from start level to target level
	 * @throws IllegalArgumentException if targetLevel is lower than start level or in the same cases as {@link IncreaseCostTable#getCost(int, byte)}
	 */
	public static int getCost(int startLevel, int targetLevel, byte costFactor) throws IllegalArgumentException{
		if (targetLevel < startLevel) throw new IllegalArgumentException("Target level (" + targetLevel +") cannot be lower than start level (" + startLevel +  ").");
		int cost = 0;
		for (int calcLevel = startLevel ; calcLevel < targetLevel; calcLevel++){
			cost += getCost(calcLevel+1, costFactor);
		}
		return cost;
	}
	
	/**
	 * Returns a char representation of the cost factor
	 * @param costFactor cost factor as byte
	 * @returns a char representation of the cost factor, space sign if byte does not match any factor
	 */
	public static char costFactorString(byte costFactor){
		switch (costFactor) {
		case A: return 'A';
		case B: return 'B';
		case C: return 'C';
		case D: return 'D';
		case E: return 'E';
		default: return ' ';
		}
	}
	
}
