package com.example.f4f;


import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;






import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

public class RecipesAdapter extends BaseAdapter {
	
	private Context context;
	private MainFragment activity;
	private LayoutInflater layoutInflater;
	private ArrayList<Recipes> recipes;
	
	public DataStore recipeurl = DataStore.getInstance();
	
	
	
	
	public RecipesAdapter (Context context, LayoutInflater l, ArrayList<Recipes> data)
	{
		this.context = context;
		//this.activity = a;
		this.layoutInflater = l;
		this.recipes = data;
	}
	
//	public RecipesAdapter(Context context, List<Recipes> recipelist) {
//		this.context = context;
//		this.recipelist = recipelist;
//		layoutInflater = LayoutInflater.from(this.context);
//		this.recipes = new ArrayList<Recipes>();
//		this.recipes.addAll(recipelist);
//	}
	
	@Override
	public int getCount(){
		return this.recipes.size();
	}
	
	@Override
	public boolean areAllItemsEnabled ()
	{
		return true;
	}
	
	@Override
	public Object getItem(int arg0){
		return null;
	}
	
	@Override
	public long getItemId(int pos) {
		return pos;
	}

	@Override
	public View getView(int pos, View convertView, ViewGroup parent) {
		MyViewHolder holder;
		//RatingBar ratingBar = null;
		if (convertView == null) {
			
			//ratingBar = (RatingBar)convertView.findViewById(R.id.ratingBar1);
			convertView = layoutInflater.inflate (R.layout.list_items, parent, false);
			
			View myView = (View) convertView.findViewById(R.id.listitem);
			
			if(pos % 2 == 0){
				myView.setBackgroundColor(Color.parseColor("#000000"));
			}
			holder = new MyViewHolder();
			holder.recipeName = (TextView) convertView.findViewById(R.id.textView1);
			holder.rating = (TextView) convertView.findViewById(R.id.textView2);
			holder.imageview = (ImageView) convertView.findViewById(R.id.imageView1);
			
			//holder.storyDesp = (TextView) convertView.findViewById(R.id.textView3);
			convertView.setTag(holder);
		}
		else {
			holder = (MyViewHolder) convertView.getTag();
		}

		Recipes recipe = recipes.get(pos);
		holder.recipe = recipe;
		holder.imageview.setImageResource(R.drawable.ic_launcher);
		new DownloadImageTask(holder.imageview).execute(recipe.getImage());
		holder.recipeName.setText(recipe.getrecipeName());
		holder.rating.setText(recipe.getRating());
	
		String r = (String)(recipe.getRating());
		RatingBar test = (RatingBar) convertView.findViewById(R.id.ratingBar1);
		float rating = Float.parseFloat(r);
		test.setRating(rating);
		holder.rating.setText(null);

		return convertView;
	}

	
	public static class MyViewHolder {
		
		public TextView recipeName, rating;
		public Recipes recipe;
		public ImageView imageview;
	}
	
	private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
		ImageView bmImage;

		public DownloadImageTask(ImageView bmImage) {
			this.bmImage = bmImage;
		}

		protected Bitmap doInBackground(String... urls) {
			String urldisplay = urls[0];
			Bitmap mIcon11 = null;
			try {
				InputStream in = new java.net.URL(urldisplay).openStream();
				mIcon11 = BitmapFactory.decodeStream(in);
			} catch (Exception e) {
				Log.e("Error", e.getMessage());
				e.printStackTrace();
			}
			return mIcon11;
		}

		protected void onPostExecute(Bitmap result) {
			bmImage.setImageBitmap(result);
		}

	}


	


		
	}
	
	

