package eu.sffi.webandpaper.client.ui;

import java.util.Iterator;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * A simple vertical panel with a headline that can be collapsed
 * @author Andi Popp
 *
 */
public class CollapsableVerticalPanel extends VerticalPanel implements ClickHandler {

	private String headline;
	
	private Anchor headlineWidget;
	
	private byte headLineLevel;
	
	public boolean collapsed;
	
	public CollapsableVerticalPanel(String headline, byte headLineLevel){
		this.headline = headline;
		this.headLineLevel = headLineLevel;
		headlineWidget = new Anchor("\u25BC "+headline);
		headlineWidget.setStyleName("collapseHeadline");
		headlineWidget.addClickHandler(this);
		this.add(headlineWidget);
		this.collapsed = false;
	}
	
	public void collapse(){
		for(Iterator<Widget> it = this.iterator(); it.hasNext(); ){
			Widget child = it.next();
			if (!child.equals(headlineWidget)) child.setVisible(false);
		}
		this.collapsed = true;
		this.headlineWidget.setText("\u25B6 "+headline);
	}
	
	public void uncollapse(){
		for(Iterator<Widget> it = this.iterator(); it.hasNext(); ){
			it.next().setVisible(true);		
		}
		this.collapsed = false;
		this.headlineWidget.setText("\u25BC "+headline);
	}

	@Override
	public void onClick(ClickEvent event) {
		if (collapsed) uncollapse();
		else collapse();
	}
	
}
