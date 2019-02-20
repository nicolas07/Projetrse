package com.example.projet_rse;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    // Note : https://stackoverflow.com/questions/1492554/set-transparent-background-of-an-imageview-on-android
    // Note : https://www.iconfinder.com/icons/103173/edit_new_write_icon
    // Note : https://www.iconfinder.com/icons/415395/box_closed_delivery_package_icon
    // Note : https://www.iconfinder.com/icons/3376410/avatar_circular_profil_ui_user_icon
    // Note : https://www.iconfinder.com/icons/1347412/history_time_timer_icon
    // Note : https://www.iconfinder.com/icons/134077/account_profile_user_icon

    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;

    //FOR FRAGMENTS
    // 1 - Declare fragment handled by Navigation Drawer
    private Fragment fragmentHistory;
    private Fragment fragmentReturnPackage;
    private Fragment fragmentUserAccount;
    private Fragment fragmentHome;

    //FOR DATAS
    // 2 - Identify each fragment with a number
    public static final int FRAGMENT_HISTORY = 0;
    public static final int FRAGMENT_RETURNPACKAGE = 1;
    public static final int FRAGMENT_USERACCOUNT = 2;
    private static final int FRAGMENT_HOME = 3;

    private boolean doubleBackToExitPressedOnce;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        StorageHelper storageHelper = new StorageHelper(getApplicationContext());
        storageHelper.InitData();
        UserAccount userAccount = storageHelper.GetUserAccount();

        View headerView = navigationView.getHeaderView(0);
        TextView navUsername = headerView.findViewById(R.id.tv_NavHeaderName);
        navUsername.setText(userAccount.getName() +" "+ userAccount.getFirstName());
        TextView navEMail = headerView.findViewById(R.id.tv_NavHeaderEmail);
        navEMail.setText(userAccount.getEmail());
        this.configureToolBar();

        this.configureDrawerLayout();

        this.configureNavigationView();

        this.showFirstFragment();
    }

    @Override
    public void onBackPressed() {
        // 5 - Handle back click to close menu

        if (this.drawerLayout.isDrawerOpen(GravityCompat.START)) {
            this.drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            if (doubleBackToExitPressedOnce) {
                super.onBackPressed();
                return;
            }

            this.doubleBackToExitPressedOnce = true;
            Toast.makeText(this, "Appuyez encore une fois pour quitter", Toast.LENGTH_SHORT).show();

            new Handler().postDelayed(new Runnable() {

                @Override
                public void run() {
                    doubleBackToExitPressedOnce=false;
                }
            }, 2000);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        switch(id){
            case R.id.nav_returnPackage:
                this.showFragment(FRAGMENT_RETURNPACKAGE);
                break;
            case R.id.nav_history:
                this.showFragment(FRAGMENT_HISTORY);
                break;
            case R.id.nav_useraccount:
                this.showFragment(FRAGMENT_USERACCOUNT);
                break;
            case R.id.nav_home:
                this.showFragment(FRAGMENT_HOME);
                break;
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void showFragment(int fragmentIdentifier){
        switch (fragmentIdentifier){
            case FRAGMENT_HISTORY :
                this.showHistoryFragment();
                this.navigationView.getMenu().getItem(2).setChecked(true);
                break;
            case FRAGMENT_RETURNPACKAGE:
                this.showReturnPackageFragment();
                this.navigationView.getMenu().getItem(1).setChecked(true);
                break;
            case FRAGMENT_USERACCOUNT:
                this.showUserAccountFragment();
                this.navigationView.getMenu().getItem(3).setChecked(true);
                break;
            case FRAGMENT_HOME:
                this.showHomeFragment();
                break;
            default:
                break;
        }
    }

    private void showHistoryFragment(){
        if (this.fragmentHistory == null) this.fragmentHistory = HistoryFragment.newInstance();
        this.startTransactionFragment(this.fragmentHistory);
    }

    private void showReturnPackageFragment(){
        if (this.fragmentReturnPackage == null) this.fragmentReturnPackage = ReturnPackageFragment.newInstance();
        this.startTransactionFragment(this.fragmentReturnPackage);
    }

    private void showUserAccountFragment(){
        if (this.fragmentUserAccount == null) this.fragmentUserAccount = UserAccountFragment.newInstance();
        this.startTransactionFragment(this.fragmentUserAccount);
    }

    private void showHomeFragment(){
        if (this.fragmentHome == null) this.fragmentHome = HomeFragment.newInstance();
        this.startTransactionFragment(this.fragmentHome);
    }

    private void startTransactionFragment(Fragment fragment){
        if (!fragment.isVisible()){
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.activity_main_frame_layout, fragment).commit();
        }
    }

    private void showFirstFragment(){
        Fragment visibleFragment = getSupportFragmentManager().findFragmentById(R.id.activity_main_frame_layout);
        if (visibleFragment == null){
            // 1.1 - Show News Fragment
            this.showFragment(FRAGMENT_HOME);
            // 1.2 - Mark as selected the menu item corresponding to NewsFragment
            this.navigationView.getMenu().getItem(0).setChecked(true);
        }
    }

    public void setActionBarTitle(String title){
        getSupportActionBar().setTitle(title);
    }

    // ---------------------
    // CONFIGURATION
    // ---------------------

    // 1 - Configure Toolbar
    private void configureToolBar(){
        this.toolbar = findViewById(R.id.activity_main_toolbar);
        setSupportActionBar(toolbar);
    }

    // 2 - Configure Drawer Layout
    private void configureDrawerLayout(){
        this.drawerLayout = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
    }

    // 3 - Configure NavigationView
    private void configureNavigationView(){
        this.navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }
}
