/**
 * 
 */
package eu.sffi.webandpaper.client;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;

/**
 * This class handles events on the upper hierarchy level, esp. the main navigation.
 * @author Andi Popp
 *
 */
public class WebAndPaperEventHandler implements ClickHandler {

	/**
	 * The entry point module associated to this event handler
	 */
	private final WebAndPaper entryPoint;
	
	/**
	 * Full parameter constructor
	 * @param entryPoint
	 */
	public WebAndPaperEventHandler(WebAndPaper entryPoint) {
		super();
		this.entryPoint = entryPoint;
	}



	/* (non-Javadoc)
	 * @see com.google.gwt.event.dom.client.ClickHandler#onClick(com.google.gwt.event.dom.client.ClickEvent)
	 */
	@Override
	public void onClick(ClickEvent event) {
		if (event.getSource().equals(entryPoint.navCharacters)){
			entryPoint.getContent().setWidget(new CharacterListPanel(entryPoint, entryPoint.sideNav));
		}

	}

}
