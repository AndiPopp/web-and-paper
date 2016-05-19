package eu.sffi.webandpaper.client.ruleset.dsa5;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Panel;

import eu.sffi.webandpaper.client.WebAndPaper;
import eu.sffi.webandpaper.client.ui.ListItemWidget;
import eu.sffi.webandpaper.client.ui.UnorderedListWidget;
import eu.sffi.webandpaper.shared.ruleset.CharacterCreationException;

public class CharacterCreationSideNav implements ClickHandler {
	
	/**
	 * Associated main panel
	 */
	CharacterCreationMainPanel mainPanel;
	
	/**
	 * The current AP label
	 */
	public Label currentAP;
	
	/**
	 * The current attribute sum label
	 */
	public Label currentAttrSum;
	
	/**
	 * A label telling about the last time the character was rebuilt
	 */
	Label lastBuild;
	
	/**
	 * 
	 */
	UnorderedListWidget stepList;
	
	/**
	 * Link to jump to step 1
	 */
	Anchor step1;
	
	/**
	 * Link to jump to step 2
	 */
	Anchor step2;
	
	Button saveButton = new Button("Charakter speichern");
	
	/**
	 * Creates a new side nav in the target panel
	 * @param targetPanel the panel where the sidenav should be created
	 */
	public CharacterCreationSideNav(Panel targetPanel, CharacterCreationMainPanel mainPanel){
		//set parent
		this.mainPanel = mainPanel;
		
		targetPanel.clear();
		targetPanel.add(new HTML("<h1>Erstellungsstatus</h1>"));
		currentAP = new Label("AP übrig:");;
		currentAP.setStyleName("sidenavEntry");
		targetPanel.add(currentAP);
		currentAttrSum = new Label("Eigenschaften:");
		currentAttrSum.setStyleName("sidenavEntry");
		targetPanel.add(currentAttrSum);
		lastBuild = new Label("Stand:");
		lastBuild.setStyleName("sidenavEntry");
		targetPanel.add(lastBuild);
		saveButton.setEnabled(true);
		saveButton.addClickHandler(this);
		saveButton.setWidth("100%");
		targetPanel.add(saveButton);
		targetPanel.add(new HTML("<h1>Fortschritt</h1>"));
		stepList = new UnorderedListWidget();
		targetPanel.add(stepList);
		updateStepList();
		
	}
	
	public void updateStepList(){
		stepList.clear();
		step1 = new Anchor("1. Grundeinstellungen");
		step1.addClickHandler(mainPanel);
		stepList.add(new ListItemWidget(step1));
		step2 = new Anchor("2. Profession & Talente");
		step2.addClickHandler(mainPanel);
		stepList.add(new ListItemWidget(step2));
	}

	//Events
	
	@Override
	public void onClick(ClickEvent event) {
		this.currentAP.setText("Click");
		try{
			mainPanel.saveCharacter();
		}
		catch(CharacterCreationException ex){
			WebAndPaper.entryPoint.messageBox.showMessage("Fehler", ex.getMessage());
		}
	}

}
