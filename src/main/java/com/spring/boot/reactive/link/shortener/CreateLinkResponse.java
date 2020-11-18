package com.spring.boot.reactive.link.shortener;

public class CreateLinkResponse 
{
	private String shortenedLink;

	
	  public CreateLinkResponse(String string) { shortenedLink = string; }
	  
	  public String getShortenedLink() { return shortenedLink; }
	  
	  public void setShortenedLink(String shortenedLink) { this.shortenedLink =
	  shortenedLink; }
	 
}
