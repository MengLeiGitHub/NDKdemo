package cn.jjmd.ndkdemo;

import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;



public class SingleSelectDialog extends AlertDialog{

 
	 String title;
	 Activity activity;
	 ArrayList<Object> arraylist;
	 SingleOnclick   singleClick;
	 private  Object  SelectObj;
	public Object getSelectObj() {
		return SelectObj;
	}

	public void setSelectObj(Object selectObj) {
		SelectObj = selectObj;
	}

	public SingleSelectDialog(Activity activity, String title, Object obj, SingleOnclick singleClick) {
		super(activity);
		// TODO Auto-generated constructor stub
 	    this.title=title;
	    this.activity=activity;
	    this.singleClick=singleClick;
	    this.arraylist=  (ArrayList<Object>) obj;
	}
   
 
	ListView listview;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.single_select);
		listview=(ListView) this.findViewById(R.id.listview);
		listview.setAdapter(new myAdapter());

 	}
	
	class   myAdapter  extends BaseAdapter{

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return arraylist.size();
		}

		@Override
		public Object getItem(int arg0) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public long getItemId(int arg0) {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public View getView(final int index, View arg1, ViewGroup arg2) {
			// TODO Auto-generated method stub
			
			SingleModel single= (SingleModel) arraylist.get(index);
			
			arg1=LayoutInflater.from(activity).inflate(R.layout.single_item, null);
			TextView text=(TextView) arg1.findViewById(R.id.textview);
			text.setText(single.getSelectItem());
			arg1.setOnClickListener(new View.OnClickListener() {
				
				public void onClick(View arg0) {
					// TODO Auto-generated method stub
					singleClick.singleClick(arraylist.get(index));
					setSelectObj(arraylist.get(index));
					SingleSelectDialog.this.dismiss();
				}
			});
			
			return arg1;
		}
		
	}
	
	public  interface  SingleOnclick{
		public  void  singleClick(Object obj);
	}
	
	public  interface  SingleModel{
		public  String   getSelectItem();
	} 
	
}
