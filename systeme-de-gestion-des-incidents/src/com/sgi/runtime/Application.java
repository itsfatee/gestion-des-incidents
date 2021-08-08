package com.sgi.runtime;

import com.sgi.controllers.AuthentificationController;
import com.sgi.ui.UIAuthentification;

public class Application {

	public static void main(String[] args) {
		UIAuthentification uiAuthentification = new UIAuthentification();
		AuthentificationController authentificationController = new AuthentificationController(uiAuthentification);
		
		authentificationController.run ();
	}

}
