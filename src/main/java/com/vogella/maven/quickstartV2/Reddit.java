package com.vogella.maven.quickstartV2;

import java.io.IOException;
import java.net.MalformedURLException;

import org.eclipse.jetty.io.ssl.ALPNProcessor.Client;

import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.DomElement;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlInput;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

import java.util.Scanner;

/**
 * 
 * @author Sam Imose
 * to-do list:
 * - look up how to upload to github from code
 * - look into getter and setters from java class
 * - try and implement the wait and sleep methods
 * - make it usable for a dynamic website
 * - obtain a second page after submitting a button or making a request
 * - By scraping this tech news site, we could possibly make this into a job finder
 * - Need to add captcha 
 * - Scrape the job forum of the HackerNews site and store all of the listings in an array, then use either linear or binary search to find the search item
 * - We have two routes:
 * - Logging in to a created account
 * - Creating an account
 * - maybe create two methods one for the username and password and the other for 
 * 
 * In this program I would like to compare the amount of trump and biden posts 
 * on reddit in a given time frame. By scraping the website while using the headless browser, HTMLUnit, I can obtain the data
 *
 *
 *
 */

public class Reddit {

	//why are we using private static final?	
	private static final String baseUrlTrump = "https://www.reddit.com/search/?q=trump"; 
	private static final String baseUrlBiden = "https://www.reddit.com/search/?q=biden"; 
	private static final String baseUrlNews = "https://news.ycombinator.com/login?goto=news"; 
	
	//why are we using static final?
	//static final String username = "johncena";
	//static final String password = "sam123";


	public static void main(String[] args) throws FailingHttpStatusCodeException, MalformedURLException, IOException {
		
		String username = "";
		String password = "";
		WebClient client = new WebClient(); // to perform the initial request
		client.getOptions().setJavaScriptEnabled(true);
		client.getOptions().setCssEnabled(true);
		client.getOptions().setUseInsecureSSL(true); // Secure Sockets Layer, for keeping an Internet connection secure 
			// and safeguarding any sensitive data being sent between two systems, preventing criminals from reading and modifying info
		
		HtmlPage page = client.getPage(baseUrlNews);
		// by using a predicate "[x]" in the Xpath we should be able to access the second form on the page
		
		Scanner input = new Scanner(System.in);
		
		// maybe put this in the try catch and use command line arguments
		System.out.println("Do you have account on Hacker News (Y/N): ");
		String answer = input.next();
		
		
		//input.close();
		boolean failed = false;
				
		try {

			//DomElement createAccountForm = page.getFirstByXPath("//input[@value='create account']"); // this xpath does not change on the second page
			
			//HtmlPage secondPage = createAccountForm.click(); // after it clicks on the "create account" button it will return a new page
			client.waitForBackgroundJavaScript(7000); // don't really know whats going on here and if its even working
			//System.out.println(client.getCurrentWindow());
			
			do {
				failed = false;
				System.out.print("Enter Username: ");
				username = input.next();
				
				System.out.print("Enter Password: ");
				password = input.next();
				
				if (answer.equalsIgnoreCase("y")) {
					HtmlInput inputEmail = page.getFirstByXPath("//form[1]//input[@name='acct']");
					inputEmail.setValueAttribute(username);
					
					HtmlInput inputPassword = page.getFirstByXPath("//form[1]//input[@name='pw']");
					inputPassword.setValueAttribute(password);
				} else {
					HtmlInput inputEmail = page.getFirstByXPath("//form[2]//input[@name='acct']");
					inputEmail.setValueAttribute(username);
			
					HtmlInput inputPassword = page.getFirstByXPath("//form[2]//input[@name='pw']");
					inputPassword.setValueAttribute(password);
					}	
				DomElement createAccountForm = page.getFirstByXPath("//input[@value='create account']"); // this xpath does not change on the second page
				
				DomElement loginForm = page.getFirstByXPath("//input[@value='login']");
				
				HtmlPage createAccountPage = createAccountForm.click(); // after it clicks on the "create account" button it will return a new page
				
				HtmlPage loginPage = loginForm.click();
				
				if (createAccountPage.asText().contains("That username is taken") || createAccountPage.asText().contains("Password 8 and 72")) {
						//System.out.print(secondPage.asText());
						failed = true;
						System.err.println("Error: Authentication failed"); // error output
				}
			} while (failed = true);
			System.out.println("Success! You have logged in."); // standard output
				
			
			//HtmlForm loginForm = inputPassword.getEnclosingForm() ;
	
			

			if (secondPage.asText().contains("That username is taken") || secondPage.asText().contains("Password 8 and 72")) {
						System.err.println("Error: Authentication failed"); // error output
						System.out.print(secondPage.asText());
			}
			else {
				System.out.println("Success! You have logged in."); // standard output
			}
			*/
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Failing Reason: "+ e.toString());

		}
	
	}
	
	
	// this method logins the user by getting the password, username, and the page to be accessed
	public static WebClient login(String question, String username, String password, HtmlPage loginPage) {
		WebClient client = new WebClient();
		client.getOptions().setCssEnabled(false);
		client.getOptions().setJavaScriptEnabled(false);
		
		
		// since we already know the login page, idk hold on
		//HtmlPage page = client.getPage(loginUrl);

		
		if (question.equalsIgnoreCase("y")) {
			HtmlInput inputEmail = loginPage.getFirstByXPath("//form[1]//input[@name='acct']");
			inputEmail.setValueAttribute(username);
			
			HtmlInput inputPassword = loginPage.getFirstByXPath("//form[1]//input[@name='pw']");
			inputPassword.setValueAttribute(password);
		}
		if (question.equalsIgnoreCase("n")) {
			HtmlInput inputEmail = loginPage.getFirstByXPath("//form[2]//input[@name='acct']");
			inputEmail.setValueAttribute(username);
			
			HtmlInput inputPassword = loginPage.getFirstByXPath("//form[2]//input[@name='pw']");
			inputPassword.setValueAttribute(password);
		}
		
		do {
			failed = false;
			System.out.print("Enter Username: ");
			username = input.next();
			
			System.out.print("Enter Password: ");
			password = input.next();
			
			if (question.equalsIgnoreCase("y")) {
				HtmlInput inputEmail = page.getFirstByXPath("//form[1]//input[@name='acct']");
				inputEmail.setValueAttribute(username);
				
				HtmlInput inputPassword = page.getFirstByXPath("//form[1]//input[@name='pw']");
				inputPassword.setValueAttribute(password);
			} 
			if (question.equalsIgnoreCase("n")) {
				HtmlInput inputEmail = page.getFirstByXPath("//form[2]//input[@name='acct']");
				inputEmail.setValueAttribute(username);
		
				HtmlInput inputPassword = page.getFirstByXPath("//form[2]//input[@name='pw']");
				inputPassword.setValueAttribute(password);
			}	
			DomElement createAccountForm = page.getFirstByXPath("//input[@value='create account']"); // this xpath does not change on the second page
			
			DomElement loginForm = page.getFirstByXPath("//input[@value='login']");
			
			HtmlPage createAccountPage = createAccountForm.click(); // after it clicks on the "create account" button it will return a new page
			
			HtmlPage loginPage = loginForm.click();
			
			if (createAccountPage.asText().contains("That username is taken") || createAccountPage.asText().contains("Password 8 and 72")) {
					//System.out.print(secondPage.asText());
					failed = true;
					System.err.println("Error: Authentication failed"); // error output
			}
		} while (failed = true);
		System.out.println("Success! You have logged in."); // standard output
	}
	
	public static WebClient login(String question, String username, String password, HtmlPage loginPage) {

}
