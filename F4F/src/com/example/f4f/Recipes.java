package com.example.f4f;


public class Recipes {
	
	private String recipeName;
	private String rating;
	private String id;
	private String smallImageUrls;
	
	public Recipes(String recipeName, String rating, String id, String smallImageUrls){
		super();
		this.rating = rating;
		this.recipeName = recipeName;
		this.id = id;
		this.smallImageUrls = smallImageUrls;
		
	}
	
	public String getRating(){
		return rating;
	}
	public void setRating(String rating){
		this.rating = rating;
	}
	public String getrecipeName(){
		return recipeName;
	}
	public void setrecipeName(String recipeName){
		this.recipeName= recipeName;
	}
	public String getID(){
		return id;
	}
	public void setID(String id){
		this.id = id;
	}
	public String getImage(){
		return smallImageUrls;
	}
	public void setImage(String smallImageUrls){
		this.smallImageUrls = smallImageUrls;
	}
	
	

}
