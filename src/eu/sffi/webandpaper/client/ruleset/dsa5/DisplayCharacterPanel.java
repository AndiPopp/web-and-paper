/**
 * 
 */
package eu.sffi.webandpaper.client.ruleset.dsa5;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Panel;

import eu.sffi.webandpaper.client.AbstractDisplayCharacterPanel;
import eu.sffi.webandpaper.client.CharacterServiceException;
import eu.sffi.webandpaper.client.ruleset.CharacterDeletionDialog;
import eu.sffi.webandpaper.shared.ruleset.dsa5.Character;
import eu.sffi.webandpaper.shared.ruleset.dsa5.SkillValue;

/**
 * A panel to display a raw DSA 5 character
 * @author Andi Popp
 *
 */
public class DisplayCharacterPanel extends AbstractDisplayCharacterPanel implements ClickHandler{

	/**
	 * The sidenav panel
	 */
	private final Panel sidenav;
	
	/**
	 * The Button to delete the character
	 */
	private final Button deleteButton;
	
	/**
	 * Creates a new display panel for the given character
	 * @param character the character
	 * @param sidenav the panel to put the sidenav in
	 * @throws CharacterServiceException if the character is not a valid {@link Character}
	 */
	public DisplayCharacterPanel(Character character, Panel sidenav) throws CharacterServiceException{
		//Build main
		super(character);
		if (!(character instanceof Character)) throw new CharacterServiceException("The character with the id "+character.getId()+" is no DSA 5 character.");
		addCharacterInfo();
		
		//Build the side nav
		this.sidenav = sidenav;
		this.sidenav.clear();
		this.deleteButton = new Button("Charakter löschen");
		this.deleteButton.setWidth("100%");
		this.deleteButton.addClickHandler(this);
		this.sidenav.add(this.deleteButton);
		
	}

	/**
	 * Adds the actual character info to this panel
	 */
	private void addCharacterInfo(){
		Character character = this.getCharacter();
		this.add(new HTML("<h1>"+character.getName()+"</h1>"
				+ "<p><strong>Erfahrungsgrad:</strong> "+character.getStartingExperienceLevel()+"<br/>"
				+ "<strong>Spezies:</strong> " +character.getSpecies()+"<br/>"
				+ "<strong>Kultur:</strong> "+character.getCulture()+"</p>"));
		this.add(new HTML("<h2>Attribute</h2>"));
		this.add(new HTML("<h2>Talente</h2>"));
		for (SkillValue skillvalue : character.getSkillValues()){
			this.add(new HTML(skillvalue.skillName+": "+skillvalue.value+"<br/>"));
		}
	}
	
	@Override
	public Character getCharacter(){
		return (Character) this.character;
	}

	@Override
	public void onClick(ClickEvent event) {
		if (event.getSource().equals(deleteButton)){
			CharacterDeletionDialog deletionDialog = new CharacterDeletionDialog(getCharacter());
			deletionDialog.center();
		}
	}
}
