/**
 * 
 */
package eu.sffi.webandpaper.client.ruleset.dsa5;

import java.util.Map;

import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.user.client.ui.DockPanel;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

import eu.sffi.webandpaper.client.ui.ConvenientListBox;
import eu.sffi.webandpaper.shared.ruleset.dsa5.CharacterExperienceLevel;
import eu.sffi.webandpaper.shared.ruleset.dsa5.IncreaseCostTable;
import eu.sffi.webandpaper.shared.ruleset.dsa5.Skill;
import eu.sffi.webandpaper.shared.ruleset.dsa5.SkillValue;

/**
 * A horizontal Panel containing a select box for skill values and annotations.
 * @author Andi Popp
 *
 */
public class SkillLevelSelector extends ConvenientListBox {

	/**
	 * The skill associated with the skill value
	 */
	Skill skill;
	
	/**
	 * The skill value to be manipulated by this selector
	 */
	SkillValue skillValue;
	
	/**
	 * The label to output the skill name
	 */
	public Label skillNameLabel;
	
	/**
	 * The label to output the AP cost for the next point
	 */
	public Label apCostLabel;
	
	/**
	 * Constructs a selector with a given skillValue getting the associated skill from
	 * a name indexed skill map.
	 * @param skillValue
	 * @param skillMap
	 */
	public SkillLevelSelector(SkillValue skillValue, Map<String, Skill> skillMap){
		super();
		this.skillValue = skillValue;
		this.skill = skillMap.get(skillValue.skillName);
		this.skillNameLabel = new Label(skillValue.skillName+" ("+IncreaseCostTable.costFactorString(skill.increaseCostFactor)+")");
		this.skillNameLabel.addStyleName("widgetLabel");
		this.apCostLabel = new Label();
		this.initialize();
		this.calcululateAPCost();
	}
	
	/**
	 * Returns a widget containing the selector and its labels.
	 * @return a widget containing the selector and its labels.
	 */
	public Widget getWidget(){
		DockPanel panel = new DockPanel();
		panel.add(skillNameLabel, DockPanel.WEST);
		
		Grid grid = new Grid(1, 2);
		grid.setWidget(0, 0, this);
		grid.setWidget(0, 1, apCostLabel);
		
		panel.add(grid, DockPanel.EAST);
		return panel;
	}
	
	/**
	 * Updates the skillLevel to the current selected value
	 */
	public void updateSkillLevel(){
		this.skillValue.level = (byte)this.getSelectedIndex();
	}
	
	/**
	 * Calculates the AP costs for the next level and writes it into the {@link SkillLevelSelector#apCostLabel}
	 * @return the AP costs for the next level
	 */
	int calcululateAPCost(){
		int apCost = IncreaseCostTable.getCost(skillValue.level+1, skill.increaseCostFactor);
		apCostLabel.setText("("+apCost+")");
		return apCost;
	}
	
	/**
	 * Fills the values up to the current skill level an selects it
	 */
	private void initialize(){
		//fill up
		while (this.getItemCount() < this.skillValue.level+1){
			this.addItem(""+this.getItemCount());
		}
		this.setSelectedIndex(this.skillValue.level);
	}

	/**
	 * Fills in the values for a given character experience level up to the max value
	 * @param maxValue the max value
	 */
	void setMaxValue(byte maxValue){
		//fill up
		while (this.getItemCount() < maxValue+1){
			this.addItem(""+this.getItemCount());
		}

		//turn down to selected index if need be
		if (this.getSelectedIndex() > maxValue){
			this.setSelectedIndex(maxValue);
			updateSkillLevel();
		}
		
		//cut off
		while (this.getItemCount() > maxValue+1){
			this.removeItem(maxValue+1);
		}
	
	}

}
