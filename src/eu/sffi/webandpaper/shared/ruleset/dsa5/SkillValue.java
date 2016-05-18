/**
 * 
 */
package eu.sffi.webandpaper.shared.ruleset.dsa5;

import java.io.Serializable;

import javax.jdo.annotations.Extension;
import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Key;

/**
 * A skill value of a character. Optimized for persistance
 * @author Andi Popp
 *
 */
@PersistenceCapable(identityType = IdentityType.APPLICATION)
public class SkillValue implements Serializable {

	/**
	 * The character skill's id
	 */
    @PrimaryKey
    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
    @Extension(vendorName="datanucleus", key="gae.encoded-pk", value="true")
    private String encodedKey;
	
	/**
	 * The skill
	 */
    @Persistent(defaultFetchGroup = "true")
	public String skillName;

	/**
	 * The skill value
	 */
	@Persistent(defaultFetchGroup = "true")
	public byte value;
	
	/**
	 * Empty constructor
	 */
	public SkillValue(){};
	
	/**
	 * Creates a new object with only a name and value
	 * @param skillName
	 * @param value
	 */
	public SkillValue(String skillName, byte value) {
		super();
		this.skillName = skillName;
		this.value = value;
	}

	@Override
	public String toString(){
		return this.skillName + ": " + this.value;
	}
	
	public String getKey() {
		return encodedKey;
	}
}
