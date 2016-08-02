package com.example.f4f;

import java.util.HashMap;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class Contacts_Recipe_Fragment extends Fragment {
	
	TextView nameTV;
	TextView idTV;
	Button addRecipeBtn;
	
	
	
	public DataStore selectedcontact = DataStore.getInstance();
	
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    	final View view = inflater.inflate(R.layout.contacts_recipe_list, container, false);
    	
    	nameTV = (TextView)view.findViewById(R.id.textView1);
    	idTV = (TextView)view.findViewById(R.id.textView2);
    	
    	addRecipeBtn = (Button) view.findViewById(R.id.button1);
    	addRecipeBtn.setOnClickListener(addrecipelistener);
    	
    	String selectedname = selectedcontact.getContactName();
    	String selectedid = selectedcontact.getContactID();
    	nameTV.setText(selectedname);
    	idTV.setText(selectedid);
    	
    	//display selected contacts recipe based on contactID
        //fetch from contactID table
    	
    	
    	
    	return view;
	}
	
	
	public OnClickListener addrecipelistener = new OnClickListener()
	{

		@Override
		public void onClick(View v) {
			
		    changeFragment();	
		    //save contat's id here --> Singleton --> already done in ContactsFragment.java
		    
		    
		    
		    //create a table for this id with fields recipe --> new database table created specifically for this id
		    //pass this id to cuisines page --> Singleton
		    //pass this id to recipe listview page --> Singleton
		    //store selected recipe into table of this id 
		    
		    //NEW DATABASE
		    //1. create db handler
		    //2. db handler --> add recipe, delete recipe, get all recipes methods
		    //3. When contact gets deleted, table for that contact also gets deleted
		    
			
			
		}
	}; //end button click event
	
    private void changeFragment() {
		
		Fragment newFragment = new CuisinesFragment();
		FragmentTransaction transaction = getFragmentManager().beginTransaction();
		transaction.replace(R.id.main_fragment, newFragment);
		transaction.addToBackStack(null);
		transaction.commit();
		
		
	}

}
