package eu.sffi.webandpaper.shared.ruleset.dsa5;

import java.io.Serializable;

import eu.sffi.webandpaper.shared.ruleset.CharacterCreationException;

public class CharacterAttributes {

	/**
	 * Constant for the MU attribute
	 */
	public static final byte MU = 0;
	
	/**
	 * Constant for the KL attribute
	 */
	public static final byte KL = 1;
	
	/**
	 * Constant for the IN attribute
	 */
	public static final byte IN = 2;
	
	/**
	 * Constant for the CH attribute
	 */
	public static final byte CH = 3;
	
	/**
	 * Constant for the FF attribute
	 */
	public static final byte FF = 4;
	
	/**
	 * Constant for the GE attribute
	 */
	public static final byte GE = 5;
	
	/**
	 * Constant for the KO attribute
	 */
	public static final byte KO = 6;
	
	/**
	 * Constant for the KK attribute
	 */
	public static final byte KK = 7;
	
//	/**
//	 * An array of attribute values for a character
//	 */
//	@Persistent
//	private final byte[] values;
//	
//	/**
//	 * An array to save any max value modifications as they are done by e.g. species
//	 */
//	@Persistent
//	private final byte[] maxLevelModifications;
	
//	/**
//	 * Creates a starting set of character attributes with all values at 8
//	 */
//	public CharacterAttributes() {
//		this.values = new byte[8];
//		for(int i = 0; i < this.values.length; i++){
//			this.values[i] = 8;
//		}
//		this.maxLevelModifications = new byte[8];
//	}
	
	/**
	 * Returns a {@link String} representation of the attribute
	 * @param attribute the attribute byte value
	 * @return a {@link String} representation of the attribute; empty String if the byte value is not associated with any attribute
	 */
	public static String attributeName(byte attribute) throws CharacterCreationException {
		switch (attribute) {
		case MU: return "MU";
		case KL: return "KL";
		case IN: return "IN";
		case CH: return "CH";
		case FF: return "FF";
		case GE: return "GE";
		case KO: return "KO";
		case KK: return "KK";
		default: throw new CharacterCreationException("The byte value "+attribute+" is not a valid attribute identifier.");
		}
	}
	
	/**
	 * Returns the attribute identifier associated with a given name String
	 * @param name the two letter name String of the attribute
	 * @return the corresponding attribute byte identifier
	 * @throws IllegalArgumentException if the name is not a valid attribute name
	 */
	public static byte attributeValue(String name) throws IllegalArgumentException{
		if (name.equals("MU")) return MU;
		else if (name.equals("KL")) return KL;
		else if (name.equals("IN")) return IN;
		else if (name.equals("CH")) return CH;
		else if (name.equals("FF")) return FF;
		else if (name.equals("GE")) return GE;
		else if (name.equals("KO")) return KO;
		else if (name.equals("KK")) return KK;
		else throw new IllegalArgumentException("The String "+name+" is not a valid attribute name.");
	}
	
	/**
	 * Returns a full title of the attribute
	 * @param attribute
	 * @return a full title of the attribute; empty if attr is not a valid attribute byte identifier
	 */
	public static String attributeTitle(byte attribute) {
		switch (attribute) {
		case MU: return "Mut";
		case KL: return "Klugheit";
		case IN: return "Intuition";
		case CH: return "Charisma";
		case FF: return "Fingerfertigkeit";
		case GE: return "Gewandheit";
		case KO: return "Konstitution";
		case KK: return "Körperkraft";
		default: return "";
		}
	}
}
