package eu.sffi.webandpaper.client.ruleset.dsa5;

import java.util.Map;
import java.util.TreeMap;

import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.VerticalPanel;

import eu.sffi.webandpaper.shared.ruleset.dsa5.SkillValue;
import eu.sffi.webandpaper.shared.ruleset.dsa5.Skill;

public class CharacterCreationSkillPanel extends VerticalPanel implements ChangeHandler,ClickHandler {

	/**
	 * The parent panel
	 */
	CharacterCreationMainPanel mainPanel;

	/**
	 * A skill map for this panel
	 */
	private Map<String, Skill> skillMap = Skill.getSkillMap();
	
	/**
	 * The characters skill values
	 */
	Map<String, SkillValue> skillValues;
	
	/**
	 * Create a new skill panel with the given main panel as parent
	 * @param mainPanel the parent main panel
	 */
	public CharacterCreationSkillPanel(CharacterCreationMainPanel mainPanel) {
		super();
		//set parent
		this.mainPanel = mainPanel;
		//set style
		this.setStyleName("inline1");
		
		//profession part
		this.add(new HTML("<h2>Profession</h2>"));

		//skill part
		this.add(new HTML("<h2>Talente</h2>"));
		resetSkillValues();
	}

	protected void resetSkillValues(){
		skillValues = new TreeMap<String, SkillValue>();
		for(Skill skill : skillMap.values()){
			skillValues.put(skill.name, new SkillValue(skill.name, (byte)5));
		}
	}
	
	@Override
	public void onClick(ClickEvent event) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onChange(ChangeEvent event) {
		// TODO Auto-generated method stub
		
	}

}
