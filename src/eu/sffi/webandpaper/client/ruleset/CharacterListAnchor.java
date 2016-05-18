/**
 * 
 */
package eu.sffi.webandpaper.client.ruleset;

import com.google.gwt.user.client.ui.Anchor;

/**
 * An Anchor associated with an entry in the character list
 * @author Andi Popp
 *
 */
public class CharacterListAnchor extends Anchor {
	
	/**
	 * The {@link CharacterShortInfo} object associated with this link
	 */
	private CharacterShortInfo charInfo;

	/**
	 * Create new {@link CharacterListAnchor} associated with a given {@link CharacterShortInfo}
	 * @param charInfo
	 */
	public CharacterListAnchor(CharacterShortInfo charInfo) {
		super();
		this.charInfo = charInfo;
		this.setText(""+charInfo.getName()+" ("+Long.toHexString(charInfo.getId())+")");
		//set style
		this.setStyleName("listEntry");
	}

	/**
	 * Getter for {@link CharacterShortInfo#charInfo}
	 * @return
	 */
	public CharacterShortInfo getCharInfo() {
		return charInfo;
	}
	
	
	
	

}
