package com.example.f4f;

import java.util.ArrayList;
import java.util.HashMap;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ContactsDBHandler extends SQLiteOpenHelper {
	
	public ContactsDBHandler(Context context){
		super(context, "MyDataBase.db", null, 1);
		
	}
	
	@Override
	//creates the table in database with id and name fields
	public void onCreate(SQLiteDatabase db) {
		db.execSQL("create table contacts (id integer primary key autoincrement , name varchar)");
		
	}
	
	
	public void addcontact(HashMap <String,String> queryValues){
		
		SQLiteDatabase db = this.getWritableDatabase();
		
		ContentValues cv = new ContentValues();
		
		cv.put("name", queryValues.get("name"));
		
		db.insert("contacts", null, cv);
		
		db.close();
	}
	
	public ArrayList<HashMap<String,String>> getAllNames(){
		
		ArrayList<HashMap<String,String>> namesList = new ArrayList<HashMap<String,String>>();
		
		String selectQuery = "SELECT * FROM contacts";
		
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);
		
		while(cursor.moveToNext()){
			HashMap<String,String> nameMap = new HashMap<String, String>();
			nameMap.put("id", cursor.getString(0));
			nameMap.put("contactname", cursor.getString(1));
		    namesList.add(nameMap);
		}
		
		return namesList;
	}
	
	
	
	public void deletecontact(String id){
		
		SQLiteDatabase database = this.getWritableDatabase();
		String deleteQuery = "DELETE FROM contacts WHERE id='"+id+"'";
		database.execSQL(deleteQuery);
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		
	}

}
