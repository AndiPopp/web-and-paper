/**
 * 
 */
package eu.sffi.webandpaper.client.ruleset.dsa5;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

import eu.sffi.webandpaper.client.CharacterService;
import eu.sffi.webandpaper.client.CharacterServiceAsync;
import eu.sffi.webandpaper.client.CharacterServiceResult;
import eu.sffi.webandpaper.client.MessageBox;
import eu.sffi.webandpaper.shared.ruleset.CharacterCreationException;
import eu.sffi.webandpaper.shared.ruleset.dsa5.Character;

/**
 * @author Andi Popp
 *
 */
public class CharacterCreationMainPanel extends VerticalPanel implements ClickHandler {
	
	/**
	 * The character service object
	 */
	private CharacterServiceAsync charSvc;
	
	/**
	 * The character created by this panel
	 */
	private Character character;
	
	/**
	 * Marks the current step widget in the character creation process
	 */
	private Widget currentStepWidget;
	
	/**
	 * The panel basic character data panel
	 */
	private CharacterCreationBasicsPanel characterCreationBasicsPanel;
	
	/**
	 * The panel for the sidenav
	 */
	private CharacterCreationSideNav characterCreationSideNav;
	
	MessageBox messageBox = new MessageBox();
	
	public CharacterCreationMainPanel(Panel sideNav) {
		super();
		
		//Side nav
		characterCreationSideNav = new CharacterCreationSideNav(sideNav,this);
		
		//headline
		this.add(new HTML("<h1>Neuen DSA5-Charakter erstellen</h1>"));
		
		//Add the basics panel to the stack layout panel
		characterCreationBasicsPanel = new CharacterCreationBasicsPanel(this);
		this.add(characterCreationBasicsPanel);
		this.currentStepWidget = characterCreationBasicsPanel;
		
		//build character for the first time
		rebuildCharacter();
	}
	
	/**
	 * Rebuilds the panel's character
	 */
	void rebuildCharacter(){
		this.character = new Character(this.characterCreationBasicsPanel.getExpLevelByteValue(), this.characterCreationBasicsPanel.getSpeciesName(), this.characterCreationBasicsPanel.getCultureName());
		try {
			//set the name
			this.character.setName(this.characterCreationBasicsPanel.nameTextBox.getValue());
			//set the attributes
			this.character.setAttributes(this.characterCreationBasicsPanel.getAttributes());
			//output left AP to panel
			int leftAP = this.character.getLeftAP();
			if (leftAP <= 10 && leftAP >= 0) characterCreationSideNav.currentAP.setText("AP übrig: " + leftAP +" \u2713");
			else characterCreationSideNav.currentAP.setText("AP übrig: " + leftAP +" \u2717");
			//output attribute sum to panel
			int attrSum = this.character.getAttributeSum();
			if (attrSum <= character.getStartingExperienceLevel().maxAttributeSum) characterCreationSideNav.currentAttrSum.setText("Eigenschaften: "+attrSum+"/"+character.getStartingExperienceLevel().maxAttributeSum+" \u2713");
			else characterCreationSideNav.currentAttrSum.setText("Eigenschaften: "+attrSum+"/"+character.getStartingExperienceLevel().maxAttributeSum+" \u2717");
		} catch (CharacterCreationException e) {
			characterCreationSideNav.currentAP.setText("Fehler: " + e.getMessage());
		}
	}
	
	public void saveCharacter(){
		rebuildCharacter();
		AsyncCallback<CharacterServiceResult> callback = new AsyncCallback<CharacterServiceResult>() {

			@Override
			public void onFailure(Throwable caught) {
				messageBox.showMessage("Fehler beim Speichern", caught.getMessage());
			}

			@Override
			public void onSuccess(CharacterServiceResult result) {
				messageBox.showMessage("Erfolg", result.getMessage());
				
			}
		};
		if (charSvc == null) charSvc = GWT.create(CharacterService.class);
		charSvc.saveCharacter(character, callback);
	}
	
	//Event Handlers
	


	@Override
	public void onClick(ClickEvent event) {
		this.remove(currentStepWidget);
		if (event.getSource().equals(characterCreationSideNav.step1)) this.add(characterCreationBasicsPanel);
	}
}
