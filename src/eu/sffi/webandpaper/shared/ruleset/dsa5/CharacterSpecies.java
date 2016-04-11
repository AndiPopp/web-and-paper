package eu.sffi.webandpaper.shared.ruleset.dsa5;

import java.util.Map;
import java.util.TreeMap;

import static eu.sffi.webandpaper.shared.ruleset.dsa5.CharacterAttributes.*;


/**
 * A class representing the character species
 * @author Andi Popp
 *
 */
public class CharacterSpecies  {
	
	/**
	 * The species name
	 */
	public final String name;
	
	/**
	 * The species' starting LE
	 */
	public final byte startLE;
	
	/**
	 * The species' starting SK
	 */
	public final byte startSK;
	
	/**
	 * The specis' starting ZK
	 */
	public final byte startZK;
	
	/**
	 * The species' starting GS
	 */
	public final byte startGS;
	
	/**
	 * The species' AP cost
	 */
	public final int apCost;
	
	public final CharacterAttributesModification[] attributesModifications;
	
	/**
	 * Full parameter constructor
	 * @param name
	 * @param startLE
	 * @param startSK
	 * @param startZK
	 * @param startGS
	 * @param apCost
	 * @param recommendedCultures
	 * @param attributesModifications
	 */
	public CharacterSpecies(String name, byte startLE, byte startSK,
			byte startZK, byte startGS, int apCost, CharacterAttributesModification[] attributesModifications) {
		super();
		this.name = name;
		this.startLE = startLE;
		this.startSK = startSK;
		this.startZK = startZK;
		this.startGS = startGS;
		this.apCost = apCost;
		this.attributesModifications = attributesModifications;
	}

	//Methods
	
	@Override
	public String toString(){
		return this.name;
	}
	
	/**
	 * Returns a {@link TreeMap} containing all species
	 * @return a {@link TreeMap} containing all species
	 */
	public static Map<String, CharacterSpecies> getSpeciesMap(){
		Map<String, CharacterSpecies> speciesMap = new TreeMap<String, CharacterSpecies>();
		CharacterAttributesModification[] characterAttributesModifications;
		//Mensch
		characterAttributesModifications = new CharacterAttributesModification[1];
		characterAttributesModifications[0] = new CharacterAttributesModification((byte)1, MU, KL, IN, CH, FF, GE, KO, KK);
		speciesMap.put("Mensch", new CharacterSpecies("Mensch", (byte) 5, (byte) -5, (byte) -5, (byte) 8, 0, characterAttributesModifications));
		//Elf
		characterAttributesModifications = new CharacterAttributesModification[3];
		characterAttributesModifications[0] = new CharacterAttributesModification((byte)1, IN);
		characterAttributesModifications[1] = new CharacterAttributesModification((byte)1, GE);
		characterAttributesModifications[2] = new CharacterAttributesModification((byte)-2, KL, KK);
		speciesMap.put("Elf", new CharacterSpecies("Elf", (byte) 2, (byte) -4, (byte) -6, (byte) 8, 18, characterAttributesModifications));
		//Halbelf
		characterAttributesModifications = new CharacterAttributesModification[1];
		characterAttributesModifications[0] = new CharacterAttributesModification((byte)1, MU, KL, IN, CH, FF, GE, KO, KK);
		speciesMap.put("Halbelf", new CharacterSpecies("Halbelf", (byte) 5, (byte) -4, (byte) -6, (byte) 8, 0, characterAttributesModifications));
		//Zwerg
		characterAttributesModifications = new CharacterAttributesModification[3];
		characterAttributesModifications[0] = new CharacterAttributesModification((byte)1, KO);
		characterAttributesModifications[1] = new CharacterAttributesModification((byte)1, KK);
		characterAttributesModifications[2] = new CharacterAttributesModification((byte)-2, CH, GE);
		speciesMap.put("Zwerg", new CharacterSpecies("Zwerg", (byte) 8, (byte) -4, (byte) -4, (byte) 6, 61, characterAttributesModifications));
		return speciesMap;
	}
}
