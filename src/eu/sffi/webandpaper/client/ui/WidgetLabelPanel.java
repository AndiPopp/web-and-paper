package eu.sffi.webandpaper.client.ui;

import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

/**
 * A simple horizontal panel with a label with the "widgetLabel" style and a widget
 * @author AndiPopp
 *
 */
public class WidgetLabelPanel extends HorizontalPanel {

	
	/**
	 * Creates a widget Label Panel
	 */
	public WidgetLabelPanel(String labelText, Widget widget){
		super();
		this.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
		Label myLabel = new Label(labelText);
		myLabel.addStyleName("widgetLabel");
		this.add(myLabel);
		this.add(widget);
	}
}
