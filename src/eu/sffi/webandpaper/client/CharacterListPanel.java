/**
 * 
 */
package eu.sffi.webandpaper.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.InlineLabel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.VerticalPanel;

import eu.sffi.webandpaper.client.ui.ConvenientListBox;
import eu.sffi.webandpaper.shared.ruleset.AbstractCharacter;
import eu.sffi.webandpaper.shared.ruleset.AbstractRuleset;

/**
 * @author Andi Popp
 *
 */
public class CharacterListPanel extends VerticalPanel implements ClickHandler {

	/**
	 * The character service object
	 */
	private CharacterServiceAsync charSvc;
	
	/**
	 * Reference to the entry point
	 */
	private WebAndPaper entryPoint;
	
	/**
	 * The side nav associated with this panel
	 */
	private Panel sideNav;
	
	/**
	 * The list of characters listed on this panel
	 */
	CharacterShortInfo[] charList;
	
	/**
	 * a list of all ruleset to choose for character creation
	 */
	ConvenientListBox rulesetList = new ConvenientListBox();
	
	/**
	 * The button to start creation of a new character
	 */
	Button newCharButton = new Button("Charakter erstellen");
	
	/**
	 * Construct a new Panel with the given entry point and side nav
	 * @param entryPoint
	 * @param sideNav
	 */
	public CharacterListPanel(WebAndPaper entryPoint, Panel sideNav) {
		super();
		this.entryPoint = entryPoint;
		this.sideNav = sideNav;
		this.add(new HTML("<h1>Meine Charaktere</h1>"));
		loadCharList();
	}

	/**
	 * Loads the char list from the server and updates the panel on success
	 */
	private void loadCharList(){
		
		//build the callback
		AsyncCallback<CharacterShortInfo[]> callback = new AsyncCallback<CharacterShortInfo[]>() {

			@Override
			public void onFailure(Throwable caught) {
				entryPoint.messageBox.showMessage("Fehler beim Laden", "Die Charakterliste konnte nicht geladen werden. Grund: "+caught.getMessage());	
				fillPanel();
			}

			@Override
			public void onSuccess(CharacterShortInfo[] result) {
				charList = result;
				fillPanel();
			}
		};
		
		//query the server
		if (charSvc == null) charSvc = GWT.create(CharacterService.class);
		charSvc.listCharacters(callback);
	}

	private void fillPanel() {
		this.clear();
		this.add(new HTML("<h1>Meine Charaktere</h1>"));
		if (charList != null) {
			for (AbstractRuleset ruleset : AbstractRuleset.getRulesets()){
				this.add(new HTML("<h2>"+ruleset.getName()+"</h2>"));
				for (CharacterShortInfo info : charList){
					if (info.getRulesetName().equals(ruleset.getName())){
						CharacterListAnchor charListAnchor = new CharacterListAnchor(info);
						this.add(charListAnchor);
						charListAnchor.addClickHandler(this);
					}
				}
			}
		}
		else{
			this.add(new Label("Charakterliste ist null!"));
		}
		
		//Build side nav
		sideNav.clear();
		sideNav.add(new HTML("<h1>Neuer Charakter</h1>"));
		FlowPanel rulesetPanel = new FlowPanel();
		Label rulesetLabel = new InlineLabel("System");
		rulesetLabel.setStyleName("widgetLabel");
		rulesetPanel.add(rulesetLabel);
		rulesetPanel.setStyleName("sidenavEntry");
		rulesetList = new ConvenientListBox();
		fillWithRulesetList(rulesetList);
		rulesetList.setWidth("120px");
		rulesetPanel.add(rulesetList);
		sideNav.add(rulesetPanel);
		newCharButton.setWidth("100%");
		newCharButton.addClickHandler(this);
		sideNav.add(newCharButton);
	}

	@Override
	public void onClick(ClickEvent event) {
		
		if (event.getSource().equals(newCharButton)){
			if (rulesetList.getSelectText().equals("DSA 5")) {
				this.add(new Label("Klick"));
				this.entryPoint.getContent().clear();
				this.entryPoint.getContent().setWidget(new eu.sffi.webandpaper.client.ruleset.dsa5.CharacterCreationMainPanel(entryPoint.sideNav));
			}
		}
		if (event.getSource() instanceof CharacterListAnchor){
			CharacterListAnchor anchor = (CharacterListAnchor) event.getSource();
			loadAndDisplayCharacter(anchor.getCharInfo().getId(), anchor.getCharInfo().getRulesetName());
		}
	}
	
	/**
	 * Switches the current list panel with a specific character display panel
	 * @param charId the displayed character's id
	 */
	private void loadAndDisplayCharacter(Long charId, String rulesetName){
		AsyncCallback<AbstractCharacter> callback = new AsyncCallback<AbstractCharacter>() {

			@Override
			public void onFailure(Throwable caught) {
				entryPoint.messageBox.showMessage("Error while loading character", caught.getMessage());
			}

			@Override
			public void onSuccess(AbstractCharacter result) {
				switchToCharDisplayPanel(result);
			}
			
		};
		
		//query the server
		if (charSvc == null) charSvc = GWT.create(CharacterService.class);
		charSvc.getCharacter(charId, rulesetName, callback);
	}
	
	/**
	 * Replaces the content with a character display panel
	 * @param character the character to be displayed
	 */
	private void switchToCharDisplayPanel(AbstractCharacter character){
		entryPoint.messageBox.showMessage("Got character!", character.getName());
		//TODO actually switch stuff
	}
	
	/**
	 * Clears the list box and fills it with all current rule set names
	 * @param listBox
	 */
	private void fillWithRulesetList(ListBox listBox){
		listBox.clear();
		for (AbstractRuleset ruleset : AbstractRuleset.getRulesets()){
			listBox.addItem(ruleset.getName());
		}
	}
}
