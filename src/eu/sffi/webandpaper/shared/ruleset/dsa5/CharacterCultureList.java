package eu.sffi.webandpaper.shared.ruleset.dsa5;

import java.util.Comparator;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * A simple wrapper for easy access to the list of character cultures
 * @author Andi Popp
 *
 */
public class CharacterCultureList extends TreeMap<String, CharacterCulture> {

	public CharacterCultureList() {
	}

	public CharacterCultureList(Comparator<? super String> comparator) {
		super(comparator);
	}

	public CharacterCultureList(
			Map<? extends String, ? extends CharacterCulture> m) {
		super(m);
	}

	public CharacterCultureList(SortedMap<String, ? extends CharacterCulture> m) {
		super(m);
	}
	
	public void add(CharacterCulture characterCulture){
		this.put(characterCulture.name, characterCulture);
	}

}
