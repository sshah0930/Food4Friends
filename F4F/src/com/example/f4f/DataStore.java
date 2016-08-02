package com.example.f4f;

public class DataStore {

	public String selected = null;
	public String recipeurl = null;
	public String contactname = null;
	public String contactid = null;
	
	public DataStore(){
		
	}

	private static DataStore instance = null;

	public static DataStore getInstance() {
		 if(instance == null) {
	         instance = new DataStore();
	      }
	      return instance;
	}
	
	public String getSelected() {
		return selected;
	}

	public void setSelected(String selected) {
		this.selected = selected;
	}
	
	public String getRecipeURL(){
		return recipeurl;
	}
	
	public void setRecipeURL(String recipeurl){
		this.recipeurl = recipeurl;
	}
	
	public String getContactName(){
		return contactname;
	}
	
	public void setContactName(String contactname){
		this.contactname = contactname;
	}
	
	public String getContactID(){
		return contactid;
	}
	
	public void setContactID(String contactid){
		this.contactid = contactid;
	}
	

}
