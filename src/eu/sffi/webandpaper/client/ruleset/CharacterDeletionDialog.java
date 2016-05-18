package eu.sffi.webandpaper.client.ruleset;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.DockPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.VerticalPanel;

import eu.sffi.webandpaper.client.CharacterService;
import eu.sffi.webandpaper.client.CharacterServiceAsync;
import eu.sffi.webandpaper.client.CharacterServiceResult;
import eu.sffi.webandpaper.client.WebAndPaper;
import eu.sffi.webandpaper.shared.ruleset.AbstractCharacter;

public class CharacterDeletionDialog extends DialogBox implements ClickHandler{
	
	/**
	 * The character service object
	 */
	private CharacterServiceAsync charSvc;
	
	/**
	 * The cancel button
	 */
	Button cancelButton;
	
	/**
	 * The OK button
	 */
	Button okButton;
	
	/**
	 * The Done button
	 */
	Button doneButton;
	
	/**
	 * Checkbox to be marked to execute deletion
	 */
	CheckBox confirmCheckBox;
	
	/**
	 * the character to be deleted
	 */
	AbstractCharacter character;
	
	/**
	 * Creates a new dialog to delete this character
	 * @param character
	 */
	public CharacterDeletionDialog(AbstractCharacter character){
		this.character = character;
		this.setText("Character wirklich löschen?");
		Panel contentPanel = new VerticalPanel();
		contentPanel.setWidth("250px");
		contentPanel.add(new HTML("<p style=\"padding:2px;font-size:small\">Soll <strong>"+character+"</strong> wirklich gelöscht werden? Einmal gelöschte Chractere können nicht wiederhergestellt werden.</p>"));
		contentPanel.add(confirmCheckBox = new CheckBox("Ja, Charakter löschen."));
		DockPanel buttonPanel = new DockPanel();
		buttonPanel.add(okButton = new Button("Löschen"), DockPanel.EAST);
		buttonPanel.add(cancelButton = new Button("Abbrechen"), DockPanel.WEST);
		doneButton = new Button("Fertig"); //add later when dialog is done
		buttonPanel.setWidth("100%");
		contentPanel.add(buttonPanel);
		this.setWidget(contentPanel);
		//add handlers
		okButton.addClickHandler(this);
		cancelButton.addClickHandler(this);
		doneButton.addClickHandler(this);
		//configurations
		this.setAnimationEnabled(true);
	}

	@Override
	public void onClick(ClickEvent event) {
		if (event.getSource().equals(cancelButton)){
			this.hide();
		}
		else if (event.getSource().equals(okButton)){
			if (this.confirmCheckBox.getValue()){
				this.setWidget(new Label("Deleting..."));
				deleteChar();
			}
		}
		else if (event.getSource().equals(doneButton)){
			this.hide();
			WebAndPaper.entryPoint.getContent().setWidget(new CharacterListPanel(WebAndPaper.entryPoint, WebAndPaper.entryPoint.sideNav));
		}
	}
	
	/**
	 * Deletes the char via Character service
	 */
	private void deleteChar(){
		
		final CharacterDeletionDialog me = this;
		
		//build the callback
		AsyncCallback<CharacterServiceResult> callback = new AsyncCallback<CharacterServiceResult>() {

			@Override
			public void onFailure(Throwable caught) {
				Panel contentPanel = new VerticalPanel();
				contentPanel.add(new HTML("Error: " + caught.getMessage()));
				contentPanel.add(cancelButton);
				me.setWidget(contentPanel);
			}

			@Override
			public void onSuccess(CharacterServiceResult result) {
				Panel contentPanel = new VerticalPanel();
				contentPanel.add(new HTML(result.getMessage()));
				contentPanel.add(doneButton);
				me.setWidget(contentPanel);
			}
		};
		
		//query the server
		if (charSvc == null) charSvc = GWT.create(CharacterService.class);
		charSvc.deleteCharacter(character.getId(), character.rulesetName(), callback);
	}
}
