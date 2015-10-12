package app.com.io.codephillip.soccerdashboard;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import app.com.io.codephillip.soccerdashboard.services.ApiIntentService;

public class MainActivity extends AppCompatActivity implements ActionBar.TabListener {
    private TabsPagerAdapter pageAdapter;
    private ViewPager pager;
    private ActionBar actionBar;
    private View parentLayout;
    private IntentFilter intentFilter;

    @Override
    protected void onResume() {
        super.onResume();
//        intentFilter = new IntentFilter();
//        intentFilter.addAction("FINISHED SERVICE");
//        registerReceiver(intentReceiver, intentFilter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pager = (ViewPager) findViewById(R.id.pager);
        parentLayout = findViewById(R.id.pager);
        actionBar = getSupportActionBar();
//        intentFilter = new IntentFilter();
//        intentFilter.addAction("FINISHED SERVICE");
//        registerReceiver(intentReceiver, intentFilter);
        startServerConnection();
        pageAdapter = new TabsPagerAdapter(getSupportFragmentManager());
        pager.setAdapter(pageAdapter);
        pager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int position) {
                actionBar.setSelectedNavigationItem(position);
            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onPageScrollStateChanged(int arg0) {
                // TODO Auto-generated method stub

            }
        });

        actionBar.setHomeButtonEnabled(false);
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        String[] tabs = { "Predictions", "Tables",
                "Fixtures" };

        for (String tab_name : tabs) {
            actionBar.addTab(actionBar.newTab().setText(tab_name)
                    .setTabListener(this));
        }

//        ApiIntentService apiIntentService = new ApiIntentService();
//        apiIntentService.startS
//        Intent intent = new Intent(this, ApiIntentService.class);
//        startService(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onTabReselected(ActionBar.Tab arg0, FragmentTransaction arg1) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onTabSelected(ActionBar.Tab arg0, FragmentTransaction arg1) {
        pager.setCurrentItem(arg0.getPosition());
    }

    @Override
    public void onTabUnselected(ActionBar.Tab arg0, FragmentTransaction arg1) {
        // TODO Auto-generated method stub

    }

    private boolean isConnectedToInternet(){
        ConnectivityManager connectivity = (ConnectivityManager) this.getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivity != null)
        {
            NetworkInfo[] info = connectivity.getAllNetworkInfo();
            if (info != null)
                for (int i = 0; i < info.length; i++)
                    if (info[i].getState() == NetworkInfo.State.CONNECTED)
                    {
                        return true;
                    }
        }
        return false;
    }

    public void startServerConnection(){
        boolean connectionCheck = isConnectedToInternet();
        if (connectionCheck){
            Intent intent = new Intent(this, ApiIntentService.class);
            startService(intent);
//            snackBar("Connected");
        }else {
            //Toast.makeText(this, "Check Internet Connection", Toast.LENGTH_SHORT).show();
            snackBar("Check Internet Connection");
        }
    }

    private void snackBar(String message){
        Snackbar snackbar = Snackbar.make(parentLayout, message, Snackbar.LENGTH_SHORT);
        View snackbarView = snackbar.getView();
        snackbarView.setBackgroundColor(getResources().getColor(R.color.red));
        snackbar.show();
    }

//    private BroadcastReceiver intentReceiver = new BroadcastReceiver() {
//        @Override
//        public void onReceive(Context context, Intent intent) {
//            Log.d("BROADCAST###", "BROADCAST RECEIVED");
////            Toast.makeText(getActivity(), "Received broadcast from service",
////            Toast.LENGTH_LONG).show();
//            pageAdapter = new TabsPagerAdapter(getSupportFragmentManager());
//            pager.setAdapter(pageAdapter);
//            int n=0;
//            for (; n < 5; n++){
//                Log.d("BROADCAST###", "LOOPING INSIDE BROADCAST");
//            }
//        }
//    };
}
