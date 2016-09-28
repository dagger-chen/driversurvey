package com.phicomm.android.driversurvey;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;


public class InputNameAndIdActivity extends Activity {


	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_name_id);
        
        getActionBar().setDisplayShowHomeEnabled(false);
        
        Fragment fragment = new InputNameAndIdFragment();
        
        FragmentManager fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(android.R.id.content, fragment);
        ft.commit();
        
    }
    
    
    @Override
    public void onBackPressed() {
    	super.onBackPressed();
			this.finish(); 
            Intent startMain = new Intent(Intent.ACTION_MAIN);  
            startMain.addCategory(Intent.CATEGORY_HOME);  
            startMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);  
            startActivity(startMain); 
    }

}
