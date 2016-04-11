package eu.sffi.webandpaper.client.ui;

import java.util.Set;

import com.google.gwt.dom.client.Element;
import com.google.gwt.user.client.ui.ListBox;

/**
 * A convient list box that implements some basic methods, including methods that are not implemented in the current used version of GWT.
 * @author Andi Popp
 *
 */
public class ConvenientListBox extends ListBox {
	
	
	
	public ConvenientListBox() {
		super();
	}

	public ConvenientListBox(boolean isMultipleSelect) {
		super(isMultipleSelect);
	}

	public ConvenientListBox(Element element) {
		super(element);
	}

	/**
	 * Adds an array of Strings as items for the list box
	 * @param items the items to be added
	 * @param append if true, the new items will be appended, it false the current items will be cleared
	 */
	public void addItems(String[] items, boolean append){
		//if not appended clear first
		if (!append) this.clear();
		
		//add the items
		for(String string : items){
			this.addItem(string);
		}
	}
	
	/**
	 * Adds a set of Strings, typically a key set of a map
	 * @param keySet items the items to be added
	 * @param append if true, the new items will be appended, it false the current items will be cleared
	 */
	public void addItems(Set<String> stringSet, boolean append){
		//if not appended clear first
		if (!append) this.clear();
		
		//add the items
		for(String string : stringSet){
			this.addItem(string);
		}
	}
	
	/**
	 * Fills the list box with integer values from first to last
	 * @param first the first value (including)
	 * @param last the last value (excluding)
	 */
	public void fillWithRange(int first, int last){
		this.clear();
		for (int i = first; i < last; i++){
			this.addItem(""+i);
		}
	}
	
	/**
	 * Returns the text of the selected entry
	 * @return the text of the selected entry
	 */
	public String getSelectText(){
		return getItemText(getSelectedIndex());
	}
	
	/**
	 * Tries to find the string in the list box and select it. Does nothing if String is not present.
	 * @param value the String to be selected
	 * @returns true if an item has been selected; false otherwise
	 */
	public boolean selectString(String value){
		for (int i = 0; i < this.getItemCount(); i++){
			if (this.getItemText(i).equals(value)){
				this.setSelectedIndex(i);
				return true;
			}
		}
		return false;
	}
}
