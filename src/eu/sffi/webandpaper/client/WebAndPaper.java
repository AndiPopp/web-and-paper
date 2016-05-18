package eu.sffi.webandpaper.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.ScrollPanel;

import eu.sffi.webandpaper.client.ruleset.dsa5.CharacterCreationMainPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class WebAndPaper implements EntryPoint {

	/**
	 * Saves the logininfo returned fromt the {@link LoginService}
	 */
	private LoginInfo loginInfo;
	
	/**
	 * The main navigation panel
	 */
	private Panel mainNav;
	
	/**
	 * Anchor for opening news panel
	 */
	Anchor navNews;
	
	/**
	 * Anchor for opening about panel
	 */
	Anchor navAbout;
	
	/**
	 * Anchor for opening groups panel
	 */
	public Anchor navGroups;
	
	/**
	 * Anchor for opening character panel
	 */
	public Anchor navCharacters;
	
	/**
	 * Anchor for opening profile panel
	 */
	public Anchor navProfile;
	
	/**
	 * Anchor for google login/logout
	 */
	public Anchor navLogin;
	
	/**
	 * Event handler to manage navigation events
	 */
	public WebAndPaperEventHandler eventHandler = new WebAndPaperEventHandler(this);
	
	/**
	 * A scroll panel to take in the main content
	 */
	private ScrollPanel content;
	
	/**
	 * The side navigation panel
	 */
	public Panel sideNav;
	
	/**
	 * A message box to display error message etc.
	 */
	public MessageBox messageBox = new MessageBox();
	
	/**
	 * Static reference to the entry point
	 */
	public static WebAndPaper entryPoint;
	
	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {
		//Hide login specific navigation until status is confirmend
//		RootPanel.get("myGroups").setVisible(false);
//		RootPanel.get("myCharacters").setVisible(false);
//		RootPanel.get("myProfile").setVisible(false);
		
		//check login status
//		checkLoginStatus();
		
//		ScrollPanel scrollPanel = new ScrollPanel(new CharacterCreationMainPanel());
//		scrollPanel.setHeight("500px");
//		RootPanel.get("indexMainContent").add(scrollPanel);
	    
		//set entry point to this
		entryPoint = this;
		
		//build main navigation
		mainNav = RootPanel.get("mainNav");
		checkLoginStatus();
		
		//build side navigation
		sideNav = RootPanel.get("sideNav");
		
		//build main content panel
		content = new ScrollPanel();
		content.setWidget(new CharacterCreationMainPanel(this.sideNav));
		content.setHeight("500px");
		RootPanel.get("mainContent").add(content);
		
		
	}
	
	/**
	 * Constructs the navigation 
	 */
	private void buildNavigation(){
		mainNav.clear();
		navNews = new Anchor("News");
		navNews.addClickHandler(eventHandler);
		mainNav.add(navNews);
		navAbout = new Anchor("Über");
		navAbout.addClickHandler(eventHandler);
		mainNav.add(navAbout);
		if (loginInfo != null && loginInfo.isLoggedIn()){
			navGroups = new Anchor("Gruppen");
			navGroups.addClickHandler(eventHandler);
			mainNav.add(navGroups);
			navCharacters = new Anchor("Charaktere");
			navCharacters.addClickHandler(eventHandler);
			mainNav.add(navCharacters);
			navProfile = new Anchor("Profil");
			navProfile.addClickHandler(eventHandler);
			mainNav.add(navProfile);
			navLogin = new Anchor("Logout (Google)");
			navLogin.setHref(loginInfo.getLogoutUrl());
    		mainNav.add(navLogin);
		}
		else{
			navLogin = new Anchor("Login (Google)");
			navLogin.setHref(loginInfo.getLoginUrl());
        	mainNav.add(navLogin);
		}
	}
	
	/**
	 * Checks the login status and updates the field and the navigation accordingly
	 */
	private void checkLoginStatus() {
		// Check login status using login service.
	    LoginServiceAsync loginService = GWT.create(LoginService.class);
	    loginService.login(GWT.getHostPageBaseURL(), new AsyncCallback<LoginInfo>() {
	      public void onFailure(Throwable error) {}

	      public void onSuccess(LoginInfo result) {
	    	  loginInfo = result;
	    	  buildNavigation();
	      }
	    });
	}

	/**
	 * Getter for {@link WebAndPaper#content}
	 * @return
	 */
	public ScrollPanel getContent() {
		return content;
	}

	

}
