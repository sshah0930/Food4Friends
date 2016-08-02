package com.example.f4f;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

@SuppressLint("SetJavaScriptEnabled")
public class SelectedRecipeWebViewFragment extends Fragment {
	
	private WebView mWebView;
	public DataStore recipeurl = DataStore.getInstance();
	
	  @Override
	    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
	    	final View view = inflater.inflate(R.layout.selectedrecipewv, container, false);
			
	    	String url = recipeurl.getRecipeURL();
			
			
			mWebView = (WebView) view.findViewById(R.id.webview);
			mWebView.setWebViewClient(new WebViewClient());
			mWebView.getSettings().setJavaScriptEnabled(true);
			mWebView.setWebViewClient(new WebViewClient() {
				   @Override
				   public boolean shouldOverrideUrlLoading(WebView view, String url) {
				      view.loadUrl(url);
				      return true;
				   }
				});
			mWebView.loadUrl(url);
			return view;
			
	}

}
