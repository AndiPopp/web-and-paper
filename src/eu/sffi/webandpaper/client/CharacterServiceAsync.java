package eu.sffi.webandpaper.client;

import com.google.gwt.user.client.rpc.AsyncCallback;

import eu.sffi.webandpaper.shared.ruleset.AbstractCharacter;

/**
 * The Async version of {@link CharacterService}. Refer there for documentation.
 * @author Andi Popp
 *
 */
public interface CharacterServiceAsync {

	void saveCharacter(AbstractCharacter character,
			AsyncCallback<CharacterServiceResult> callback);

	void listCharacters(AsyncCallback<CharacterShortInfo[]> callback);

	void getCharacter(Long charId, String rulesetName,
			AsyncCallback<AbstractCharacter> callback);

}
