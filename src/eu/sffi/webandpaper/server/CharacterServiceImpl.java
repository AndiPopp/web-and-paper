/**
 * 
 */
package eu.sffi.webandpaper.server;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Query;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import eu.sffi.webandpaper.client.CharacterService;
import eu.sffi.webandpaper.client.CharacterServiceException;
import eu.sffi.webandpaper.client.CharacterServiceResult;
import eu.sffi.webandpaper.client.NotLoggedInException;
import eu.sffi.webandpaper.client.ruleset.CharacterShortInfo;
import eu.sffi.webandpaper.shared.ruleset.AbstractCharacter;
import eu.sffi.webandpaper.shared.ruleset.CharacterCreationException;
import eu.sffi.webandpaper.shared.ruleset.dsa5.SkillValue;

/**
 * The implementation of the remote character service.
 * @author Andi Popp
 *
 */
public class CharacterServiceImpl extends RemoteServiceServlet implements
		CharacterService {

	private static final PersistenceManagerFactory PMF =
		      JDOHelper.getPersistenceManagerFactory("transactions-optional");
	
	/* (non-Javadoc)
	 * @see eu.sffi.webandpaper.client.CharacterService#saveCharacter(eu.sffi.webandpaper.shared.ruleset.AbstractCharacter)
	 */
	@Override
	public CharacterServiceResult saveCharacter(AbstractCharacter character)
			throws NotLoggedInException, CharacterServiceException, CharacterCreationException {
		//Get the user or throw a NotLoggedInException if the user is not logged in
		User user = getUser();
		
		//clean and verify the character
		character.verify();
		
		//Save the character to datastore
		PersistenceManager pm = PMF.getPersistenceManager();
		try{
			character.setOwner(user.getUserId());
			AbstractCharacter savedCharacter = pm.makePersistent(character);
			System.out.println("Charakter gespeichert unter: "+savedCharacter.getId());
		}
		finally{
			pm.close();
		}
		
		CharacterServiceResult result = new CharacterServiceResult();
		result.setReturnCode(CharacterServiceResult.OK);
		result.setMessage("Dein Charakter wurde gespeichert.");
		return result;
		
	}

	
	private User getUser() throws NotLoggedInException {
		UserService userService = UserServiceFactory.getUserService();
		User user = userService.getCurrentUser();
		if (user == null) {
			throw new NotLoggedInException("Not logged in.");
	    }
		else return user;
	}

	@Override
	public CharacterShortInfo[] listCharacters() throws NotLoggedInException {
		
		//Get the user or throw a NotLoggedInException if the user is not logged in
		User user = getUser();
		
		//char collector
		List<AbstractCharacter> charCollection = new ArrayList<AbstractCharacter>();
		
		PersistenceManager pm = PMF.getPersistenceManager();
		
		try{
			//get DSA5 characters
			Query q = pm.newQuery(eu.sffi.webandpaper.shared.ruleset.dsa5.Character.class, "ownerId == o");
			q.declareParameters("String o");
			List<AbstractCharacter> characterResults = (List<AbstractCharacter>) q.execute(user.getUserId());
			charCollection.addAll(characterResults);
		}
		finally {
			pm.close();
		}
		CharacterShortInfo[] list = new CharacterShortInfo[charCollection.size()];
		Iterator<AbstractCharacter> it = charCollection.iterator();
		for (int i = 0; i < list.length; i++){
			AbstractCharacter character = it.next();
			list[i] = new CharacterShortInfo(character);
		}
		return list;
	}


	@Override
	public AbstractCharacter getCharacter(Long charId, String rulesetName)
			throws NotLoggedInException, CharacterServiceException {
		//Get the user or throw a NotLoggedInException if the user is not logged in
		User user = getUser();
		
		//return object
		AbstractCharacter character = null;
		
		//get the object from the persistence manager
		PersistenceManager pm = PMF.getPersistenceManager();
		pm.getFetchPlan().setMaxFetchDepth(-1);
		
		try{
			Query q = null;
			//map the correct persistence class
			if (rulesetName.equals("DSA 5")){
				q = pm.newQuery(eu.sffi.webandpaper.shared.ruleset.dsa5.Character.class, "id == i");
			}
			else{
				throw new CharacterServiceException("Could not load character with id "+charId+". Unknown rulesetName.");
			}
			
			q.declareParameters("Long i");
			List<AbstractCharacter> characterResults = (List<AbstractCharacter>) q.execute(charId);
			if (characterResults.isEmpty()) throw new CharacterServiceException("Could not load character with id "+charId);
			else character = pm.detachCopy(characterResults.iterator().next());
		}
		finally {
			pm.close();
		}
		
		//Check if user has right to see character
		//user is owner
		if (character.getOwnerId().equals(user.getUserId())){
			return character;
		}
		else{
			throw new CharacterServiceException("Could not load character with id "+charId);
		}
		
	}


	@Override
	public CharacterServiceResult deleteCharacter(Long charId, String rulesetName) throws NotLoggedInException
	{
			
		//Get the user or throw a NotLoggedInException if the user is not logged in
		User user = getUser();
		
		//return object
		AbstractCharacter character = null;
		
		//get the object from the persistence manager
		PersistenceManager pm = PMF.getPersistenceManager();

		//build return object
		CharacterServiceResult result = new CharacterServiceResult();
		
		try{
			Query q = null;
			//map the correct persistence class
			if (rulesetName.equals("DSA 5")){
				q = pm.newQuery(eu.sffi.webandpaper.shared.ruleset.dsa5.Character.class, "id == i");
			}
			else{
				throw new CharacterServiceException("Could not load character with id "+charId+". Unknown rulesetName.");
			}
			q.declareParameters("Long i");
			List<AbstractCharacter> characterResults = (List<AbstractCharacter>) q.execute(charId);
			if (characterResults.isEmpty()) throw new CharacterServiceException("Could not load character with id "+charId);
			else character = characterResults.iterator().next();
			
			//check if user is owner
			if (!character.getOwnerId().equals(user.getUserId())) throw new CharacterServiceException("Could not delete character with id "+charId+". You are not the owner.");
			
			cleanUpBeforeDeletion(character, pm);
			pm.deletePersistent(character);
			
			Thread.sleep(50);
			
			//set result
			result.setReturnCode(CharacterServiceResult.OK);
			result.setMessage("Operation succesful. Character with id "+charId+" will be deleted shortly.");
		}
		catch (Exception ex){
			result.setReturnCode(CharacterServiceResult.GENERIC_ERROR);
			result.setMessage("Character with id "+charId+" was not deleted: "+ex.getMessage());
		}
		finally {
			pm.close();
		}
		
		//return result
		return result;
	}

	/**
	 * Cleans up a character to be deleted
	 * @param character the character
	 * @param pm the persistence manager this character is tied to
	 */
	private void cleanUpBeforeDeletion(AbstractCharacter character, PersistenceManager pm){
		if (character.rulesetName().equals("DSA 5")){
			eu.sffi.webandpaper.shared.ruleset.dsa5.Character charac = (eu.sffi.webandpaper.shared.ruleset.dsa5.Character) character;
			pm.deletePersistentAll(charac.getSkillValues());
		}
		
		//sleep a bit
		try{
			Thread.sleep(100);
		}
		catch(InterruptedException ex){}
	}
}
