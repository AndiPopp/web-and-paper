/**
 * 
 */
package eu.sffi.webandpaper.client.ruleset.dsa5;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.ibm.icu.text.DateFormatSymbols;

import eu.sffi.webandpaper.client.CharacterService;
import eu.sffi.webandpaper.client.CharacterServiceAsync;
import eu.sffi.webandpaper.client.CharacterServiceResult;
import eu.sffi.webandpaper.client.MessageBox;
import eu.sffi.webandpaper.shared.ruleset.CharacterCreationException;
import eu.sffi.webandpaper.shared.ruleset.dsa5.Character;
import eu.sffi.webandpaper.shared.ruleset.dsa5.SkillValue;

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
	 * The basic character data panel
	 */
	CharacterCreationBasicsPanel characterCreationBasicsPanel;
	
	/**
	 * The profession and skills panel 
	 */
	CharacterCreationSkillPanel characterCreationSkillPanel;
	
	/**
	 * The panel for the sidenav
	 */
	private CharacterCreationSideNav characterCreationSideNav;
	
	MessageBox messageBox = new MessageBox();
	
	private DateTimeFormat dateFormat = DateTimeFormat.getFormat("hh:mm:ss");
	
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
		
		//create the other panels
		characterCreationSkillPanel = new CharacterCreationSkillPanel(this);
		
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
			//set skill values
			this.character.setSkillValues(new ArrayList<SkillValue>(this.characterCreationSkillPanel.skillValues.values()));
			//output left AP to panel
			int leftAP = this.character.getLeftAP(this.characterCreationSkillPanel.skillMap);
			if (leftAP <= 10 && leftAP >= 0) characterCreationSideNav.currentAP.setText("AP übrig: " + leftAP +" \u2713");
			else characterCreationSideNav.currentAP.setText("AP übrig: " + leftAP +" \u2717");
			//output attribute sum to panel
			int attrSum = this.character.getAttributeSum();
			if (attrSum <= character.getStartingExperienceLevel().maxAttributeSum) characterCreationSideNav.currentAttrSum.setText("Eigenschaften: "+attrSum+"/"+character.getStartingExperienceLevel().maxAttributeSum+" \u2713");
			else characterCreationSideNav.currentAttrSum.setText("Eigenschaften: "+attrSum+"/"+character.getStartingExperienceLevel().maxAttributeSum+" \u2717");
			//ouput last build
			characterCreationSideNav.lastBuild.setText("Stand: "+dateFormat.format(new Date()));
			
		} catch (CharacterCreationException e) {
			characterCreationSideNav.currentAP.setText("Fehler: " + e.getMessage());
		}
	}
	
	public void saveCharacter() throws CharacterCreationException{
		rebuildCharacter();
		character.verify();
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
		//remove the current step widget
		this.remove(currentStepWidget);
		
		//set a new current step widget
		if (event.getSource().equals(characterCreationSideNav.step1)) this.currentStepWidget = characterCreationBasicsPanel;
		if (event.getSource().equals(characterCreationSideNav.step2)) this.currentStepWidget = characterCreationSkillPanel;
	
		//add the new current step widget
		this.add(currentStepWidget);
	}
}
