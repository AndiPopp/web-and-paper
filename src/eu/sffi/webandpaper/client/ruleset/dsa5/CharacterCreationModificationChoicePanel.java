package eu.sffi.webandpaper.client.ruleset.dsa5;

import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;

import eu.sffi.webandpaper.shared.ruleset.CharacterCreationException;
import eu.sffi.webandpaper.shared.ruleset.dsa5.CharacterAttributesModification;

/**
 * A panel containing list box objects and labels to choose the character attribute modifications
 * @author Andi Popp
 *
 */
public class CharacterCreationModificationChoicePanel extends HorizontalPanel {
	
	/**
	 * The attribute modifications
	 */
	public CharacterCreationModificationChoiceListBox[] choiceListBoxes;
	
	private final CharacterCreationBasicsPanel basicsPanel;
	
	/**
	 * Empty constructor
	 */
	public CharacterCreationModificationChoicePanel(CharacterCreationBasicsPanel basicsPanel){
		this.basicsPanel = basicsPanel;
		this.setVerticalAlignment(ALIGN_MIDDLE);
	}
	
	/**
	 * Constructs a list box that is already configured with a set of attribute modifications
	 * @param attributesModifications
	 * @throws CharacterCreationException 
	 */
	public CharacterCreationModificationChoicePanel(CharacterAttributesModification[] attributesModifications, CharacterCreationBasicsPanel basicsPanel) throws CharacterCreationException{
		this(basicsPanel);
		configure(attributesModifications);
	}
	
	/**
	 * Configures this panel with a given array of attribute modifications
	 * @param attributesModifications
	 * @throws CharacterCreationException 
	 */
	public void configure(CharacterAttributesModification[] attributesModifications) {
		choiceListBoxes = new CharacterCreationModificationChoiceListBox[attributesModifications.length];
		for (int i = 0; i < attributesModifications.length; i++){
			choiceListBoxes[i] = new CharacterCreationModificationChoiceListBox(attributesModifications[i]);
			choiceListBoxes[i].addChangeHandler(basicsPanel);
		}
		this.build();
	}
	
	/**
	 * Clears the panel and rebuilds it with the current configuration
	 */
	private void build(){
		this.clear();
		for (CharacterCreationModificationChoiceListBox choiceListBox : this.choiceListBoxes){
			this.add(choiceListBox);
			String sign = "";
			if (choiceListBox.attributesModification.value > 0) sign = "+";
			Label modLabel = new Label(sign+choiceListBox.attributesModification.value);
			modLabel.setStyleName("widgetLabel");
			this.add(modLabel);
		}
	}
	
}
