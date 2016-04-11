package eu.sffi.webandpaper.client;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * A simple dialog box with a title, a vertical panel as content widget and a close button
 * @author Andi Popp
 *
 */
public class MessageBox extends DialogBox implements ClickHandler{

	public Button closeButton;
	
	public VerticalPanel content;
	
	public MessageBox() {
		super();
		this.setText("Titel");
		content = new VerticalPanel();
		VerticalPanel metaPanel = new VerticalPanel();
		metaPanel.setHorizontalAlignment(VerticalPanel.ALIGN_CENTER);
		metaPanel.add(content);
		metaPanel.setWidth("250px");
		
		closeButton = new Button("OK");
		closeButton.addClickHandler(this);
		metaPanel.add(closeButton);
		
		this.setWidget(metaPanel);
		
		//configurations
		this.setAnimationEnabled(true);
	}

	/**
	 * Configures the message box with the given title and message and shows the message box in center.
	 * @param title
	 * @param message
	 */
	public void showMessage(String title, String message){
		this.content.clear();
		this.setText(title);
		this.content.add(new Label(message));
		this.center();
	}
	
	@Override
	public void onClick(ClickEvent event) {
		this.hide();		
	}

	
	
}
