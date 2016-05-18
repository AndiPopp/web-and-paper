package eu.sffi.webandpaper.client.ruleset.dsa5;

import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

import eu.sffi.webandpaper.client.ui.ConvenientListBox;
import eu.sffi.webandpaper.shared.ruleset.CharacterCreationException;
import eu.sffi.webandpaper.shared.ruleset.dsa5.CharacterAttributes;
import eu.sffi.webandpaper.shared.ruleset.dsa5.CharacterCulture;
import eu.sffi.webandpaper.shared.ruleset.dsa5.CharacterExperienceLevel;
import eu.sffi.webandpaper.shared.ruleset.dsa5.CharacterSpecies;
import eu.sffi.webandpaper.shared.ruleset.dsa5.Ruleset;

/**
 * A panel that contains widget to choose the basic character data: experience level, species and culture
 * @author Andi Popp
 *
 */
public class CharacterCreationBasicsPanel extends VerticalPanel implements ChangeHandler,ClickHandler {

	/**
	 * The parent panel
	 */
	CharacterCreationMainPanel mainPanel; 
	
	/**
	 * The textbox to enter the name
	 */
	TextBox nameTextBox;
	
	/**
	 * The Box to choose the exp level
	 */
	CharacterCreationExpLevelListBox chooseExpLevelBox;
	
	/**
	 * The {@link ConvenientListBox} to choose the species
	 */
	ConvenientListBox speciesListBox;
	
	/**
	 * A checkbox to choose if only recommended cultures should be available
	 */
	CheckBox recommendCultureCheckBox;
	
	/**
	 * The {@link ConvenientListBox} to choose the culture
	 */
	ConvenientListBox cultureListBox;
	
	/**
	 * The panel to choose the character attribute modification
	 */
	CharacterCreationModificationChoicePanel modificationChoicePanel;
	
	/**
	 * The list boxes to choose the attributes
	 */
	ConvenientListBox[] attrListBoxes;
	
	/**
	 * Creates a new panel with the given panel as parent
	 * @param mainPanel the parent panel
	 * @throws CharacterCreationException 
	 */
	public CharacterCreationBasicsPanel(CharacterCreationMainPanel mainPanel) throws CharacterCreationException{
		//set parent
		this.mainPanel = mainPanel;
		
		this.setStyleName("inline1");
		
		//head line
		this.add(new HTML("<h2>Grundeinstellungen</h2>"));
		
		Grid grid = new Grid(4, 2);
		this.add(grid);
		
		//name entry setup
		nameTextBox = new TextBox();
		nameTextBox.setWidth("100%");
		nameTextBox.setHeight("3ex");
		nameTextBox.setMaxLength(64);
		nameTextBox.addChangeHandler(this);
		Label nameLabel = new Label("Name");
		nameLabel.setStyleName("widgetLabel");
		grid.setWidget(0, 0, nameLabel);
		grid.setWidget(0, 1, nameTextBox);
		
		//Experience Level setup
		Label expLevelLabel = new Label("Erfahrungsgrad");
		expLevelLabel.setStyleName("widgetLabel");
		grid.setWidget(1, 0, expLevelLabel);
		chooseExpLevelBox = new CharacterCreationExpLevelListBox();
		grid.setWidget(1, 1, chooseExpLevelBox);
		chooseExpLevelBox.addChangeHandler(this);
		
		//Species setup
		Label speciesLabel = new Label("Spezies");
		speciesLabel.setStyleName("widgetLabel");
		speciesListBox = new ConvenientListBox();
		speciesListBox.addItems(Ruleset.ruleset.species.keySet(), false);
		speciesListBox.setSelectedIndex(2);
		speciesListBox.addChangeHandler(this);
		recommendCultureCheckBox = new CheckBox("Nur übliche Kulturen");
		recommendCultureCheckBox.setValue(true);
		recommendCultureCheckBox.addClickHandler(this);
		recommendCultureCheckBox.setStyleName("inline1");
		HorizontalPanel speciesPanel = new HorizontalPanel();
		speciesPanel.setVerticalAlignment(ALIGN_MIDDLE);
		speciesPanel.add(speciesListBox);
		speciesPanel.add(recommendCultureCheckBox);
		grid.setWidget(2, 0, speciesLabel);
		grid.setWidget(2, 1, speciesPanel);
		
		//Cultures setup
		Label cultureLabel = new Label("Kultur");
		cultureLabel.setStyleName("widgetLabel");
		cultureListBox = new ConvenientListBox();
		updateCultureListBox();
		grid.setWidget(3, 0, cultureLabel);
		grid.setWidget(3, 1, cultureListBox);
	
		//attribute headline
		this.add(new HTML("<h2>Eigenschaftswerte</h2>"));
		this.add(new HTML("<h3>Speziesmodifikationen für Maximalwerte</h3>"));
		
		//character attribute modification panel
		modificationChoicePanel = new CharacterCreationModificationChoicePanel(this);
		modificationChoicePanel.configure(Ruleset.ruleset.species.get(this.speciesListBox.getSelectText()).attributesModifications);		
		this.add(modificationChoicePanel);
		
		this.add(new HTML("<h3>Eigenschaftswerte wählen</h3>"));
		
		//choose character attributes
		attrListBoxes = new ConvenientListBox[8];
		Grid attributeGrid = new Grid(2, 8);
		for (byte i = 0; i < attrListBoxes.length; i++){
			attrListBoxes[i] = new ConvenientListBox();
			attrListBoxes[i].fillWithRange(8, 15);
			attrListBoxes[i].addChangeHandler(this);
			Label attrLabel = new Label(CharacterAttributes.attributeName(i));
			attrLabel.setTitle(CharacterAttributes.attributeTitle(i));
			attrLabel.setStyleName("widgetLabel");
			attrLabel.setStyleName("inline1", true);
			if (i < 4) {
				attributeGrid.setWidget(0, i*2, attrLabel);
				attributeGrid.setWidget(0, i*2+1, attrListBoxes[i]);
			}
			else {
				attributeGrid.setWidget(1, (i-4)*2, attrLabel);
				attributeGrid.setWidget(1, (i-4)*2+1, attrListBoxes[i]);
			}
		}
		updateAttributeListBoxes();
		this.add(attributeGrid);
		
	}
	
	/**
	 * Return the selected index of the {@link CharacterCreationBasicsPanel#chooseExpLevelBox}
	 * @return the selected index of the {@link CharacterCreationBasicsPanel#chooseExpLevelBox}
	 */
	public byte getExpLevelByteValue(){
		return (byte)chooseExpLevelBox.getSelectedIndex();
	}
	
	/**
	 * Return the currently selected {@link String} value of the {@link CharacterCreationBasicsPanel#speciesListBox}
	 * @return the currently selected {@link String} value of the {@link CharacterCreationBasicsPanel#speciesListBox}
	 */
	public String getSpeciesName(){
		return speciesListBox.getSelectText();
	}
	
	/**
	 * Returns the currently selected {@link String} value for the {@link CharacterCreationBasicsPanel#cultureListBox}
	 * @return the currently selected {@link String} value for the {@link CharacterCreationBasicsPanel#cultureListBox}
	 */
	public String getCultureName() {
		return cultureListBox.getSelectText();
	}
	
	/**
	 * Updates the entries in the {@link CharacterCreationBasicsPanel#cultureListBox}. If {@link CharacterCreationBasicsPanel#recommendCultureCheckBox} is checked 
	 * only recommended cultures are added.
	 */
	private void updateCultureListBox(){
		CharacterSpecies chosenSpecies = getChosenSpecies();
		cultureListBox.clear();
		for (CharacterCulture culture : Ruleset.ruleset.cultures.values()){
			if (!recommendCultureCheckBox.getValue()) {
				cultureListBox.addItem(culture.name);
			}
			else {
				for (CharacterSpecies species : culture.species) {
					if (species.equals(chosenSpecies)) cultureListBox.addItem(culture.name);
				}
			}
		}
	}
	
	/**
	 * Convenience function to return the chosen species
	 * @return the chosen species
	 */
	private CharacterSpecies getChosenSpecies(){
		return Ruleset.ruleset.species.get(speciesListBox.getSelectText());
	}
		
	/**
	 * Updates the attribute list boxes
	 */
	private void updateAttributeListBoxes(){
		//save current values
		String[] currentValues = new String[attrListBoxes.length];
		for (int i = 0; i < attrListBoxes.length; i++){
			currentValues[i] = attrListBoxes[i].getSelectText();
		}
		
		//Create a exp level object
		CharacterExperienceLevel experienceLevel = CharacterExperienceLevel.getLevel(getExpLevelByteValue());
		
		//check for species modification and prerequisites for selected skills
		byte[] attrModifications = getAttributeModifications();
		for (byte attr = 0; attr < attrListBoxes.length; attr++){
			//start with the max level given by the experience level
			int maxValue = experienceLevel.maxAttribute + attrModifications[attr];
			
			//TODO consider minima with chosen special skills
			
			//put in the max value
			attrListBoxes[attr].fillWithRange(8, maxValue+1);
		}
		
		//set to old values
		for (int i = 0; i < attrListBoxes.length; i++){
			if (!attrListBoxes[i].selectString(currentValues[i])) attrListBoxes[i].setSelectedIndex(attrListBoxes[i].getItemCount()-1);
		}
	}
	
	/**
	 * Returns the attribute array calculated from the current state of the {@link CharacterCreationBasicsPanel#attrListBoxes}
	 * @return the attribute array calculated from the current state of the {@link CharacterCreationBasicsPanel#attrListBoxes}
	 */
	public byte[] getAttributes(){
		byte[] attributes = new byte[attrListBoxes.length];
		for (byte i = 0; i < attrListBoxes.length; i++){
			attributes[i] = Byte.parseByte(attrListBoxes[i].getSelectText());
		}
		return attributes;
	}
	
	/**
	 * Returns the ready to use byte array of species attribute modifications
	 * @return the ready to use byte array of species attribute modifications
	 */
	public byte[] getAttributeModifications(){
		byte[] attrModifications = new byte[attrListBoxes.length];
		for (byte attr = 0; attr < attrListBoxes.length; attr++){
			attrModifications[attr] = 0;
			for (CharacterCreationModificationChoiceListBox choiceListBox : this.modificationChoicePanel.choiceListBoxes){
				if (CharacterAttributes.attributeValue(choiceListBox.getSelectText()) == attr){
					attrModifications[attr] = choiceListBox.attributesModification.value;
				}
				
			}
		}
		return attrModifications;
	}
	
	//Event Handlers
	
	@Override
	public void onChange(ChangeEvent event) {
		//events without rebuilding
		if (event.getSource() instanceof CharacterCreationModificationChoiceListBox){
			updateAttributeListBoxes();
		}
		else if (event.getSource().equals(nameTextBox)){
			if (nameTextBox.getValue().length() > 0 && nameTextBox.getValue().length() < 4){
				nameTextBox.setValue("");
				mainPanel.messageBox.showMessage("Name zu kurz", "Der Name muss mindestens 4 Zeichen lang sein");
				mainPanel.messageBox.closeButton.setFocus(true);
			}
		}
		//events with rebuilding
		else{
			if (event.getSource().equals(chooseExpLevelBox)){
				updateAttributeListBoxes();
			}
			else if (event.getSource().equals(speciesListBox)){
				updateCultureListBox();
				modificationChoicePanel.configure(Ruleset.ruleset.species.get(this.speciesListBox.getSelectText()).attributesModifications);	
				updateAttributeListBoxes();
			}
			mainPanel.rebuildCharacter();
		}
	}
	
	@Override
	public void onClick(ClickEvent event){
		if (event.getSource().equals(recommendCultureCheckBox)){
			updateCultureListBox();
		}
		mainPanel.rebuildCharacter();
	}

	
}
