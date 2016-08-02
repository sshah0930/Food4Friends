package com.example.f4f;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.http.client.ClientProtocolException;






import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.transition.ChangeBounds;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class ContactsFragment extends Fragment{
	
	ContactsDBHandler dbHandler;
	
	EditText enteredName;
	String enteredNameStr;
	TextView contactID, contactName;
	Button addcontact;
	
	ListView namesLV;
	
	SimpleAdapter lvadapter;
	
	public DataStore selectedcontact = DataStore.getInstance();
	
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    	final View view = inflater.inflate(R.layout.contacts_list, container, false);
    	
    	
    	
    	enteredName = (EditText)view.findViewById(R.id.editText1);
    	
    	addcontact = (Button)view.findViewById(R.id.button1);
    	addcontact.setOnClickListener(addListener);
    	
    	dbHandler = new ContactsDBHandler(getActivity());
    	
    		
    	namesLV = (ListView)view.findViewById(R.id.namesLV);
    		
    	updateLV();
    	
    	
    	
    	return view;
    	
	}
	
	public OnClickListener addListener = new OnClickListener()
	{

		@Override
		public void onClick(View v) {
			
			if(enteredName.getText() != null){
				
				dbHandler = new ContactsDBHandler(getActivity());
				HashMap <String,String> enteredNameMap = new HashMap<String, String>();
				enteredNameStr = enteredName.getText().toString();
				enteredNameMap.put("name", enteredNameStr);
				dbHandler.addcontact(enteredNameMap);
				
				updateLV();
				
				
				
		    	}
		    	
		}
	}; //end button click event
	
	public void updateLV(){
		//get new name list
		
		ArrayList<HashMap<String,String>> newNameList = dbHandler.getAllNames();
    	if(newNameList.size() != 0){
    		
    	    lvadapter = new SimpleAdapter(getActivity(), newNameList, R.layout.contacts_list_item, new String[] {"id","contactname"}, new int[] {R.id.textView1,R.id.textView2});
    	    namesLV.setAdapter(lvadapter);
    	    
    		namesLV.setOnItemClickListener(new OnItemClickListener(){

				@Override
				public void onItemClick(AdapterView<?> parent, View view,
						int position, long id) {
					
					
					String name = ((TextView) view.findViewById(R.id.textView2)).getText().toString();
					String selectedID = ((TextView) view.findViewById(R.id.textView1)).getText().toString();
					
					selectedcontact.setContactName(name);
					selectedcontact.setContactID(selectedID);
					
					
					changeFragment();
					
				}
				
			});	
    		
    		namesLV.setOnItemLongClickListener(new OnItemLongClickListener() {

                public boolean onItemLongClick(AdapterView<?> parent, View view,
                        int pos, long id) {
                    // TODO Auto-generated method stub
                	String selectedID = ((TextView) view.findViewById(R.id.textView1)).getText().toString();
			        
			        dbHandler.deletecontact(selectedID);
			          
			        updateLV();
                   

                    return true;
                }
            }); 
    		
    		
    		
    	}
    	
    	
	}
	
	private void changeFragment() {
		
		Fragment newFragment = new Contacts_Recipe_Fragment();
		FragmentTransaction transaction = getFragmentManager().beginTransaction();
		transaction.replace(R.id.main_fragment, newFragment);
		transaction.addToBackStack(null);
		transaction.commit();
		
		
	}	
	
}

