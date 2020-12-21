package com.vogella.maven.quickstartV2;

import java.net.MalformedURLException;
import java.net.URL;

import com.gargoylesoftware.htmlunit.*;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import java.util.Scanner;

public class WalmartScrapper {
	public static void main(String[] args) {
		WebClient client = new WebClient();
		client.getOptions().setCssEnabled(false);
		client.getOptions().setJavaScriptEnabled(false);

		String baseUrl = ("https://www.ericemanuel.com/");
		
		try {
			HtmlPage page = client.getPage(baseUrl);
			System.out.println(page.asText());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		//System.out.println(client.getCookies(baseUrl));
		/*
		try {
			HtmlPage page = client.getPage(baseUrl);
			System.out.println(client.getCookieManager());
		} catch (Exception e){
			e.printStackTrace();
		}
		*/
		
		
	}
}
