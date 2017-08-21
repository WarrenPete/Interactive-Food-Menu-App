package com.example.warren.rest;

import android.app.ExpandableListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.ExpandableListContextMenuInfo;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ExpandableListView.OnGroupClickListener;
import android.widget.TextView;
import android.widget.Toast;


public class MenuScreen extends ExpandableListActivity {
	//Expandable list is used to display data
    
	ExpandableListAdapter mAdapter;
	
	//DatabaseHelper menuHelper;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAdapter = new MyExpandableListAdapter();
        setListAdapter(mAdapter);
        registerForContextMenu(getExpandableListView());
        getExpandableListView().setCacheColorHint(5);
        getExpandableListView().setBackgroundResource(R.drawable.btn_style);
        
       // getExpandableListView().set
        
        
        getExpandableListView().setOnGroupClickListener(new OnGroupClickListener() {
			
			@Override
			public boolean onGroupClick(ExpandableListView parent, View v,
					int groupPosition, long id) {
				Toast.makeText(MenuScreen.this, ((TextView)v).getText().toString(), Toast.LENGTH_SHORT).show();
				return false;
			}
		});
        
        getExpandableListView().setOnChildClickListener(new OnChildClickListener() {
			
			@Override
			public boolean onChildClick(ExpandableListView parent, View v,
					int groupPosition, int childPosition, long id) {
				Toast.makeText(MenuScreen.this, ((TextView)v).getText().toString(), Toast.LENGTH_LONG).show();
				String s=((TextView)v).getText().toString();
				Intent i =new Intent (MenuScreen.this, OrderScreen.class);
				i.putExtra("order", s);
				startActivity(i);
				return false;
			}
		});

    }
    
   
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo) {
        menu.setHeaderTitle("Sample menu");
        menu.add(0, 0, 0, R.string.app_name);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        ExpandableListContextMenuInfo info = (ExpandableListContextMenuInfo) item.getMenuInfo();

        String title = ((TextView) info.targetView).getText().toString();

        int type = ExpandableListView.getPackedPositionType(info.packedPosition);
        if (type == ExpandableListView.PACKED_POSITION_TYPE_CHILD) {
            int groupPos = ExpandableListView.getPackedPositionGroup(info.packedPosition); 
            int childPos = ExpandableListView.getPackedPositionChild(info.packedPosition); 
            Toast.makeText(this, title + ": Child " + childPos + " clicked in group " + groupPos,
                    Toast.LENGTH_SHORT).show();
            return true;
        } else if (type == ExpandableListView.PACKED_POSITION_TYPE_GROUP) {
            int groupPos = ExpandableListView.getPackedPositionGroup(info.packedPosition); 
            Toast.makeText(this, title + ": Group " + groupPos + " clicked", Toast.LENGTH_LONG).show();
            return true;
        }

        return false;
    }

    
    public class MyExpandableListAdapter extends BaseExpandableListAdapter {
        // Sample data set.  children[i] contains the children (String[]) for groups[i].
        //items are defined here
    	private String[] groups = { "APPETIZERS", "BITS AND BITES","FRESH SALADS","GENEROUS BIG MEALS", "EXPERTLY CRAFTED PIZZA", "HOT AND COLD BEVERAGES","FRESH FRUIT JUICE","PASTRIES" };
        private String[][] children = {
                { "Grilled Chicken Wings - 20,500 UGX","Tender Beef Sticks - 6000 UGX","Fish Fingers - 17,000 UGX"},
                {"Beef Samosas - 7000 UGX","Vegetable Samosas - 7000 UGX","Sausages - 7000 UGX","Meat Pie - 10,000 UGX ","Chips - 9,000 UGX"},
                {"Caesar Salad - 18,000 UGX","Cobb Salad - 22,000 UGX","Tuna Salad - 23,500 UGX","Chicken Salad - 22,000 UGX","Smoked Tilapia Salad - 23,500 UGX","Chef's Special Mixed Green - 16,500 UGX"},
                {"Chicken Delight - 30,000 UGX","Succulent Beef - 30,500 UGX","Savoury Fish - 30,500 UGX","Curries Par Excellence - 28,500 UGX","Delectable Pasta - 26,000 UGX"},
                {"Pizza Margherita - 27,000 UGX","Vegetarian Pizza - 28,000 UGX","Mexican Chicken - 29,000 UGX","Super Supreme Chicken - 29,000 UGX","Super Supreme Beef - 29,000 UGX","Chicken Hawaii - 29,000 UGX" },
                { "Coffees - 6000~8000UGX","Iced Drinks - 8000 UGX","Shakes - 14,000UGX","Smoothies - 14,000UGX" },
                { "Tropical Fruits - 10,000~16,000 UGX","Barista's Special - 15,000~20,000 UGX"},
                {"Red Velvet - 80,000 UGX","Rainbow Temptation - 120,000 UGX","Chocolate Gateau - 120,000 UGX", "Muffins - 5,000 UGX"}
                
                
        };

        public Object getChild(int groupPosition, int childPosition) {
            return children[groupPosition][childPosition];
        }

        public long getChildId(int groupPosition, int childPosition) {
            return childPosition;
        }

        public int getChildrenCount(int groupPosition) {
            return children[groupPosition].length;
        }

        public TextView getGenericView() {
            // Layout parameters for the ExpandableListView
            AbsListView.LayoutParams lp = new AbsListView.LayoutParams(
                    ViewGroup.LayoutParams.FILL_PARENT, 64);

            TextView textView = new TextView(MenuScreen.this);
            textView.setLayoutParams(lp);
            // Center the text vertically
            textView.setGravity(Gravity.CENTER_VERTICAL | Gravity.LEFT);
            // Set the text starting position
            textView.setPadding(36, 0, 0, 0);
           
            return textView;
        }

        public View getChildView(int groupPosition, int childPosition, boolean isLastChild,
                View convertView, ViewGroup parent) {
            TextView textView = getGenericView();
            textView.setText(getChild(groupPosition, childPosition).toString());
            return textView;
        }

        public Object getGroup(int groupPosition) {
            return groups[groupPosition];
        }

        public int getGroupCount() {
            return groups.length;
        }

        public long getGroupId(int groupPosition) {
            return groupPosition;
        }

        public View getGroupView(int groupPosition, boolean isExpanded, View convertView,
                ViewGroup parent) {
            TextView textView = getGenericView();
            textView.setText(getGroup(groupPosition).toString());
            return textView;
        }

        public boolean isChildSelectable(int groupPosition, int childPosition) {
            return true;
        }

        public boolean hasStableIds() {
            return true;
        }

    }

}