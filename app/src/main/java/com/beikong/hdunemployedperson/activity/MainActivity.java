package com.beikong.hdunemployedperson.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CompoundButton;
import android.widget.RadioButton;

import com.beikong.hdunemployedperson.R;
import com.beikong.hdunemployedperson.fragment.ManageFragment;
import com.beikong.hdunemployedperson.fragment.MessageFragment;
import com.beikong.hdunemployedperson.fragment.PersonFragment;
import com.beikong.hdunemployedperson.fragment.StatisticsFragment;

public class MainActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener{
    private Context mContext = this;

    private RadioButton radioButtonStatic;
    private RadioButton radioButtonMessage;
    private RadioButton radioButtonManage;
    private RadioButton radioButtonPerson;


    private StatisticsFragment statisticsFragment = null;
    private ManageFragment manageFragment = null;
    private PersonFragment personFragment = null;
    private MessageFragment messageFragment = null;

    private FragmentManager fragmentManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //初始化View
        initView();

        //初始化时加载第一个Fragment
        initFirstSet();

        //初始化ToolBar
        initToolBar();
    }

    private void initToolBar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        toolbar.setTitle("");
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
    }

    /**
     * 默认第一个Fragment被选中
     */
    private void initFirstSet() {
        setTabSelection(0);
        radioButtonStatic.setChecked(true);
    }


    private void initView() {

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        //navigationView.setNavigationItemSelectedListener(this);

        radioButtonStatic = (RadioButton) findViewById(R.id.radioButton_static);
        radioButtonMessage = (RadioButton) findViewById(R.id.radioButton_message);
        radioButtonManage = (RadioButton) findViewById(R.id.radioButton_manage);
        radioButtonPerson = (RadioButton) findViewById(R.id.radioButton_person);


        radioButtonStatic.setOnCheckedChangeListener(this);
        radioButtonMessage.setOnCheckedChangeListener(this);
        radioButtonManage.setOnCheckedChangeListener(this);
        radioButtonPerson.setOnCheckedChangeListener(this);

        //初始化fragment管理器
        fragmentManager = getSupportFragmentManager();

        navigationView.setItemIconTintList(null);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                return true;
            }
        });
    }


    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        switch (compoundButton.getId()){
            case R.id.radioButton_static:
                setTabSelection(0);
                break;
            case R.id.radioButton_message:
                setTabSelection(1);
                break;
            case R.id.radioButton_manage:
                setTabSelection(2);
                break;
            case R.id.radioButton_person:
                setTabSelection(3);
                break;
        }
    }

    public void setTabSelection(int tabSelection) {
        //开启一个Fragment的事物
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        //先隐藏掉所有的Fragment,以防多个Fragment同时出现在界面上
        hideFragment(fragmentTransaction);
        switch (tabSelection){
            case 0:
                if (statisticsFragment == null){
                    statisticsFragment = new StatisticsFragment();
                    fragmentTransaction.add(R.id.id_content,statisticsFragment);
                }else {
                    fragmentTransaction.show(statisticsFragment);
                }
            break;
            case 1:
                if (messageFragment == null){
                    messageFragment = new MessageFragment();
                    fragmentTransaction.add(R.id.id_content,messageFragment);
                }else {
                    fragmentTransaction.show(statisticsFragment);
                }
            break;
            case 2:
                if (manageFragment == null){
                    manageFragment = new ManageFragment();
                    fragmentTransaction.add(R.id.id_content,manageFragment);
                }else {
                    fragmentTransaction.show(manageFragment);
                }
            break;
            case 3:
                if (personFragment == null){
                    personFragment = new PersonFragment();
                    fragmentTransaction.add(R.id.id_content,personFragment);
                }else {
                    fragmentTransaction.show(personFragment);
                }
            break;
        }
        fragmentTransaction.commit();
    }

    private void hideFragment(FragmentTransaction fragmentTransaction) {
        if (statisticsFragment != null){
            fragmentTransaction.hide(statisticsFragment);
        }
        if (messageFragment != null){
            fragmentTransaction.hide(messageFragment);
        }
        if (personFragment != null){
            fragmentTransaction.hide(personFragment);
        }
        if (manageFragment != null){
            fragmentTransaction.hide(manageFragment);
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
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
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
