package com.example.f4f;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Locale;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;





import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.webkit.WebChromeClient.CustomViewCallback;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;



public class MainFragment extends Fragment{
	
	private LayoutInflater layoutInflator;
	private Context context;
	private ArrayList<Recipes> recipes;
	private ArrayList<Recipes> recipeclonelist = new ArrayList<Recipes>();
	private String URL;
	EditText searchRecipe;
	ListView myList;
	public DataStore data = DataStore.getInstance();
	RecipesAdapter adapter;



	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    	final View view = inflater.inflate(R.layout.activity_main, container, false);
		String cuisinename = data.getSelected();
		if(cuisinename!=null && cuisinename !=""){
			URL = "http://api.yummly.com/v1/api/recipes?_app_id=2ba071bd&_app_key=2f7647c2f0855c80256b2a31bfca4a8c&q="+cuisinename+"&maxResult=100&requirePictures=true";
			this.layoutInflator = LayoutInflater.from(getActivity());
			MyAsyncTask task = new MyAsyncTask(MainFragment.this);
			task.execute();
		}
		return view;
	}
	

	public void setRecipes(ArrayList<Recipes> recipeData){
		recipes = recipeData;
		 myList = (ListView) getActivity().findViewById(R.id.listview1);
		ProgressBar progress = (ProgressBar) getActivity().findViewById(R.id.progress);
		progress.setVisibility(View.GONE);
		
		searchRecipe = (EditText)getActivity().findViewById(R.id.editText1);
		
		
		
		adapter = new RecipesAdapter(getActivity(), this.layoutInflator, this.recipes);
		myList.setAdapter(adapter);
		myList.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				String selectedURL = "http://www.yummly.com/recipe/external/"+recipes.get(position).getID();
				
				data.setRecipeURL(selectedURL);
				
				Fragment newFragment = new SelectedRecipeWebViewFragment();
				FragmentTransaction transaction = getFragmentManager().beginTransaction();
				transaction.replace(R.id.main_fragment, newFragment);
				transaction.addToBackStack(null);
				transaction.commit();
			}
		});
		
		myList.setOnItemLongClickListener(new OnItemLongClickListener() {

			public boolean onItemLongClick(AdapterView<?> parent, View view,
                    int pos, long id) {
                // TODO Auto-generated method stub
            	
				
				
				//get data from selected item (recipename, recipeid, url, image, rating)
				
				//add selected item to id of contact
		
				//change to contact screen of selected id
				
				Toast.makeText(getActivity(), "Item Added to Contact.",
						   Toast.LENGTH_LONG).show();

                return true;
            }
        }); 
		
		searchRecipe.addTextChangedListener(new TextWatcher() {
			 
			@Override
			public void afterTextChanged(Editable arg0) {
				// TODO Auto-generated method stub
				
				
				
				String text = searchRecipe.getText().toString().toLowerCase(Locale.getDefault());
				
				recipeclonelist = filter(text);
				
				adapter = new RecipesAdapter(getActivity(), layoutInflator, recipeclonelist);
				myList.setAdapter(adapter);
				adapter.notifyDataSetChanged();
				myList.setOnItemClickListener(new OnItemClickListener() {

					@Override
					public void onItemClick(AdapterView<?> parent, View view,
							int position, long id) {
						String selectedURL = "http://www.yummly.com/recipe/external/"+recipeclonelist.get(position).getID();
						
						data.setRecipeURL(selectedURL);
						
						Fragment newFragment = new SelectedRecipeWebViewFragment();
						FragmentTransaction transaction = getFragmentManager().beginTransaction();
						transaction.replace(R.id.main_fragment, newFragment);
						transaction.addToBackStack(null);
						transaction.commit();
					}
				});
				
			}
 
			@Override
			public void beforeTextChanged(CharSequence arg0, int arg1,
					int arg2, int arg3) {
				// TODO Auto-generated method stub
			}
 
			@Override
			public void onTextChanged(CharSequence arg0, int arg1, int arg2,
					int arg3) {
				// TODO Auto-generated method stub
				
				
				
			}
			
			public ArrayList<Recipes> filter(String charText){
				
				ArrayList<Recipes> recipesclone = new ArrayList<Recipes>();
				
				charText = charText.toLowerCase(Locale.getDefault());
				recipesclone.clear();
				if (charText.length() == 0) {
					recipesclone.addAll(recipes);
				} 
				else{
					for (Recipes recipe : recipes) 
					{
						if (recipe.getrecipeName().toLowerCase(Locale.getDefault()).contains(charText)) 
						{
							recipesclone.add(recipe);
							
						}
					}
				}
				
				return recipesclone;
				
			}
		});

	}
	

	public class MyAsyncTask extends AsyncTask<String, Integer, String>{


		private MainFragment myactivity;
		private Context context;



		private byte[] buff = new byte[1024];

		public MyAsyncTask(MainFragment myactivity){
			super();
			this.myactivity = myactivity;
			this.context = getActivity().getApplicationContext();
		}

		@Override
		protected String doInBackground(String... params) {
			String result = null;
			try {
				result = getRecipes();
			} catch (ClientProtocolException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			return result;
		}

		private String getRecipes() throws ClientProtocolException, IOException {
			HttpClient client = new DefaultHttpClient();
			HttpGet request = new HttpGet(URL);

			HttpResponse response = client.execute(request);
			HttpEntity entity = response.getEntity();
			InputStream ist = entity.getContent();
			ByteArrayOutputStream content = new ByteArrayOutputStream();

			int readCount = 0;

			while((readCount = ist.read(buff))!=-1){
				content.write(buff, 0, readCount);
			}
			String data;
			data = new String(content.toByteArray());

			return data;

		}

		@Override
		protected void onPostExecute(String result) {
			super.onPostExecute(result);
			ArrayList<Recipes> data = new ArrayList<Recipes>();

			try {
				String urlAtIndex = null;
				JSONObject resObj = new JSONObject(result);
				JSONArray foundrecipes = resObj.getJSONArray("matches");
				for(int i = 0;i<foundrecipes.length(); i++){
					JSONObject recipe = foundrecipes.getJSONObject(i);
					String recipeName = recipe.getString("recipeName");
					String rating = recipe.getString("rating");
					String id = recipe.getString("id");
					JSONArray smallImageUrls = recipe.getJSONArray("smallImageUrls");
					for (int j = 0; j < smallImageUrls.length(); j++) {
			              urlAtIndex = smallImageUrls.optString(0);
			         }
					data.add(new Recipes(recipeName, rating, id, urlAtIndex));
				}

			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			this.myactivity.setRecipes(data);

		}

	}


}
