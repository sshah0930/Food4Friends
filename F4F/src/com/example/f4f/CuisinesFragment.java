package com.example.f4f;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

public class CuisinesFragment extends Fragment{
	
	ImageView mexican;
	ImageView italian;
	ImageView american;
	ImageView chinese;
	ImageView asian;
	ImageView french;
	ImageView indian;
	ImageView thai;
	ImageView mediterranean;

	
	public DataStore data = DataStore.getInstance();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    	final View view = inflater.inflate(R.layout.cuisines, container, false);
    	mexican = (ImageView)view.findViewById(R.id.iv1);
		mexican.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				data.setSelected("mexican");
				changeFragment();
			}
		});
		
		italian = (ImageView)view.findViewById(R.id.iv2);
		italian.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				data.setSelected("italian");
				changeFragment();
			}
		});
		
		american = (ImageView)view.findViewById(R.id.iv3);
		american.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				data.setSelected("american");
				changeFragment();
			}
		});
		
		chinese = (ImageView)view.findViewById(R.id.iv4);
		chinese.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				data.setSelected("chinese");
				changeFragment();
			}
		});
		
		asian = (ImageView)view.findViewById(R.id.iv5);
		asian.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				data.setSelected("asian");
				changeFragment();
			}
		});
		
		french = (ImageView)view.findViewById(R.id.iv6);
		french.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				data.setSelected("french");
				changeFragment();
			}
		});
		
		indian = (ImageView)view.findViewById(R.id.iv7);
		indian.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				data.setSelected("indian");
				changeFragment();
			}
		});
		
		thai = (ImageView)view.findViewById(R.id.iv8);
		thai.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				data.setSelected("thai");
				changeFragment();
			}
		});
		
		mediterranean = (ImageView)view.findViewById(R.id.iv9);
		mediterranean.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				data.setSelected("mediterranean");
				changeFragment();
			}
		});
        return  view;
    }
	
	private void changeFragment() {
	
		Fragment newFragment = new MainFragment();
		FragmentTransaction transaction = getFragmentManager().beginTransaction();
		transaction.replace(R.id.main_fragment, newFragment);
		transaction.addToBackStack(null);
		transaction.commit();
		
		
	}	
	
	
}
