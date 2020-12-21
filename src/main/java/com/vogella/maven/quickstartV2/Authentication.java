package com.vogella.maven.quickstartV2;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.logging.Level;

import org.apache.xerces.util.URI.MalformedURIException;

import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.DomElement;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlInput;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlSelect;
import com.gargoylesoftware.htmlunit.html.HtmlOption;
import com.gargoylesoftware.htmlunit.html.*;

import java.util.Scanner;




public class Authentication {
	// let the user insert the baseUrl
	Scanner input = new Scanner(System.in);
	
	//System.out.println("Please enter the site's base url (i.e. https://www.instagram.com/): ");
	
	
	static final String baseUrl = "https://www.instagram.com/"; // In our case the home page
	static final String loginUrl = "/account/login"; // where the login form is located (the directory)
	// creating two different logins to differentiate between email and username (used for user login attribute)
	static final String email = "samuel.u.imose@gmail.com";
	static final String username = "asap_sui";
	static final String idk = "https://news.ycombinator.com/login?goto=news/";

	static final String password = "Ryanbroyles85";

	public static void main(String[] args) throws FailingHttpStatusCodeException
		,MalformedURLException, IOException, InterruptedException {
		
		WebClient client = new WebClient();
		//client.getOptions().setJavaScriptEnabled(true);
		client.getOptions().setCssEnabled(false);
		client.getOptions().setUseInsecureSSL(true); // ????
		
		java.util.logging.Logger.getLogger("com.gargoylesoftware").setLevel(Level.OFF); // ???
		
		// Get the login page
		//HtmlPage page = client.getPage(String.format("%s%s", baseUrl, loginUrl)); // ??? 
		HtmlPage page = client.getPage("https://www.instagram.com/login");
		
		autoLogin(idk, "johncena", "sam123");


		/*
		// Select the email input
		
		HtmlInput inputEmail = page.getFirstByXPath(
				"//form//input[@name='username']");
		inputEmail.setValueAttribute(username);

		
		HtmlInput inputPassword = page.getFirstByXPath("//form//input[@name='password']");
		inputPassword.setValueAttribute(password);
		
		// Select the form
		HtmlForm loginForm = inputPassword.getEnclosingForm() ;
		
		// Generate the POST request with the form
		page = client.getPage(loginForm.getWebRequest(null));
		if (!page.asText().contains("You are now logged in")) {
			System.err.println("Error: Authentication failed");
		}
		else {
			System.out.println("Success! Logged in");
		}
	*/
		
	}
	
	public static WebClient autoLogin(String loginUrl, String login, String password)
			throws FailingHttpStatusCodeException, MalformedURLException, IOException{
			WebClient client = new WebClient();
			client.getOptions().setCssEnabled(false);
			client.getOptions().setJavaScriptEnabled(false);
			HtmlPage page = client.getPage(loginUrl);
			HtmlInput inputPassword = page.getFirstByXPath("//input[@type='password']");
			//The first preceding input that is not hidden
			HtmlInput inputLogin = inputPassword.getFirstByXPath(".//preceding::input[not(@type='hidden')]"); // selects the input node that are children of the current node 
			inputLogin.setValueAttribute(login);
			inputPassword.setValueAttribute(password);
			//get the enclosing form
			HtmlForm loginForm = inputPassword.getEnclosingForm() ;
			//submit the form
			page = client.getPage(loginForm.getWebRequest(null));
			//returns the cookie filled client 
			return client;
	}
}