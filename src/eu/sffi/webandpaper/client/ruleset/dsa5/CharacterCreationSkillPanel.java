package eu.sffi.webandpaper.client.ruleset.dsa5;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.StackPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

import eu.sffi.webandpaper.shared.ruleset.dsa5.Skill;
import eu.sffi.webandpaper.shared.ruleset.dsa5.SkillValue;

public class CharacterCreationSkillPanel extends VerticalPanel implements ChangeHandler,ClickHandler {

	/**
	 * The parent panel
	 */
	CharacterCreationMainPanel mainPanel;

	/**
	 * A skill map for this panel
	 */
	Map<String, Skill> skillMap = Skill.getSkillMap();
	
	/**
	 * The characters skill values
	 */
	Map<String, SkillValue> skillValues;
	
	/**
	 * A list containing all the skill value selectors
	 */
	private List<SkillLevelSelector> skillValueSelectors;
	
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
		skillValues = new TreeMap<String, SkillValue>();
		for(Skill skill : skillMap.values()){
			skillValues.put(skill.name, new SkillValue(skill.name, (byte)0));
		}
		
		//construct the list for the skill value selectors
		skillValueSelectors = new LinkedList<SkillLevelSelector>();
		
		StackPanel stackPanel = new StackPanel();
		stackPanel.add(buildSkillGroupPanel(Skill.SKILL_GROUP_KOERPER), "Körpertalente");
		stackPanel.add(buildSkillGroupPanel(Skill.SKILL_GROUP_GESELLSCHAFT), "Gesellschaftstalente");
		stackPanel.add(buildSkillGroupPanel(Skill.SKILL_GROUP_NATUR), "Naturtalente");
		stackPanel.add(buildSkillGroupPanel(Skill.SKILL_GROUP_WISSEN), "Wissenstalente");
		stackPanel.add(buildSkillGroupPanel(Skill.SKILL_GROUP_HANDWERK), "Handwerkstalente");
		this.add(stackPanel);
	}
	
	private Panel buildSkillGroupPanel(byte skillGroup){
		//get the current max value
		byte maxSkill = mainPanel.characterCreationBasicsPanel.chooseExpLevelBox.getExperienceLevel().maxSkill;
		
		//build the panel
		Grid grid = new Grid(1, 7);
		
		//build the spacer
		Label spacer = new Label("");
		spacer.setStyleName("spaceBoth");
		
		//row and col iterators
		int row = 0;
		int col = 0;
		
		//build the skill level selectors
		for (SkillValue skillValue : skillValues.values()){
			Skill skill = skillMap.get(skillValue.skillName);
			if (skill.skillGroup == skillGroup){
				//build the new skill value selector
				SkillLevelSelector skillValueSelector = new SkillLevelSelector(skillValue, skillMap);
				//set the max skill to the current value
				skillValueSelector.setMaxValue(maxSkill);
				//add it to the list
				skillValueSelectors.add(skillValueSelector);
				//add this panel as change handler
				skillValueSelector.addChangeHandler(this);
				
				//iterate the row and col
				if (col >= grid.getColumnCount()){
					row++;
					if (row >= grid.getRowCount()) grid.insertRow(grid.getRowCount());
					col = 0;
				}
				if (col > 0){
					//put in some spacing
					grid.setWidget(row, col, spacer);
					col++;
				}
				//add the panel with the selector to the grid
				grid.setWidget(row, col, skillValueSelector.skillNameLabel);col++;
				grid.setWidget(row, col, skillValueSelector);col++;
				grid.setWidget(row, col, skillValueSelector.apCostLabel);col++;
			}
		}
		
		return grid;
	}
	
	/**
	 * Updates the max values for all elements in {@link CharacterCreationSkillPanel#skillValueSelectors}
	 * according to the associated {@link CharacterCreationBasicsPanel#chooseExpLevelBox}.
	 */
	public void updateMaxValues(){
		byte maxSkill = mainPanel.characterCreationBasicsPanel.chooseExpLevelBox.getExperienceLevel().maxSkill;
		for (SkillLevelSelector skillLevelSelector : skillValueSelectors){
			skillLevelSelector.setMaxValue(maxSkill);
		}
	}
	
	@Override
	public void onClick(ClickEvent event) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onChange(ChangeEvent event) {
		if (event.getSource() instanceof SkillLevelSelector){
			SkillLevelSelector levelSelector = (SkillLevelSelector) event.getSource();
			levelSelector.updateSkillLevel();
			levelSelector.calcululateAPCost();
			mainPanel.rebuildCharacter();
		}
		
	}

}
