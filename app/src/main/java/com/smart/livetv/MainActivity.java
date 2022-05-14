package com.smart.livetv;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.navigation.NavigationView;
import com.smart.livetv.Adapters.ChannelAdapter;
import com.smart.livetv.Models.Channel;
import com.smart.livetv.Services.ChannelDataService;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import com.smart.livetv.Adapters.ChannelAdapter;
import com.smart.livetv.Models.Channel;
import com.smart.livetv.Services.ChannelDataService;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    public static final String TAG = "TAG";
    RecyclerView bigSliderList,newsChannelList,sportsChannelList,enterChannelList;
    ChannelAdapter bigSliderAdapter,newsChannelAdapter,sportsChannelAdapter,enterChannelAdapter;
    List<Channel> channelList,newsChannels,sportsChannel,enterChannel;
    ChannelDataService service;
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle toggle;
    NavigationView navigationView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawerLayout = findViewById(R.id.drawer);
        navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.open,R.string.close);
        toggle.setDrawerIndicatorEnabled(true);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();


        channelList = new ArrayList<>();
        service = new ChannelDataService(this);

        bigSliderList = findViewById(R.id.big_slider_list);
        bigSliderList.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        bigSliderAdapter = new ChannelAdapter(channelList,"slider");
        bigSliderList.setAdapter(bigSliderAdapter);

        getSliderData("http://192.168.0.102/mytv/api.php?key=1A4mgi2rBHCJdqggsYVx&id=1&channels=all");
        getNewsChannels("http://192.168.0.102/mytv/api.php?key=1A4mgi2rBHCJdqggsYVx&id=1&cat=News");
        getSportsChannel("http://192.168.0.102/mytv/api.php?key=1A4mgi2rBHCJdqggsYVx&id=1&cat=Sports");
        getEnterChannel("http://192.168.0.102/mytv/api.php?key=1A4mgi2rBHCJdqggsYVx&id=1&cat=Entertainment");


    }

    public void getSliderData(String url){
        service.getChannelData(url, new ChannelDataService.OnDataResponse() {
            @Override
            public void onResponse(JSONObject response) {
                for(int i = 0; i<response.length();i++){
                    try {
                        JSONObject channelData = response.getJSONObject(String.valueOf(i));
                        Channel c = new Channel();
                        c.setId(channelData.getInt("id"));
                        c.setName(channelData.getString("name"));
                        c.setDescription(channelData.getString("description"));
                        c.setThumbnail(channelData.getString("thumbnail"));
                        c.setLive_url(channelData.getString("live_url"));
                        c.setFacebook(channelData.getString("facebook"));
                        c.setTwitter(channelData.getString("twitter"));
                        c.setYoutube(channelData.getString("youtube"));
                        c.setWebsite(channelData.getString("website"));
                        c.setCategory(channelData.getString("category"));

                        channelList.add(c);
                        bigSliderAdapter.notifyDataSetChanged();


//                        Log.d(TAG, "onResponse: " + c.toString());

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onError(String error) {
                Log.d(TAG, "onErrorResponse: " + error);
            }
        });
    }

    public void getNewsChannels(String url){
        newsChannelList = findViewById(R.id.news_channel_list);
        newsChannels = new ArrayList<>();
        newsChannelList.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        newsChannelAdapter = new ChannelAdapter(newsChannels,"category");
        newsChannelList.setAdapter(newsChannelAdapter);

        service.getChannelData(url, new ChannelDataService.OnDataResponse() {
            @Override
            public void onResponse(JSONObject response) {
                for(int i = 0; i<response.length();i++){
                    try {
                        JSONObject channelData = response.getJSONObject(String.valueOf(i));
                        Channel c = new Channel();
                        c.setId(channelData.getInt("id"));
                        c.setName(channelData.getString("name"));
                        c.setDescription(channelData.getString("description"));
                        c.setThumbnail(channelData.getString("thumbnail"));
                        c.setLive_url(channelData.getString("live_url"));
                        c.setFacebook(channelData.getString("facebook"));
                        c.setTwitter(channelData.getString("twitter"));
                        c.setYoutube(channelData.getString("youtube"));
                        c.setWebsite(channelData.getString("website"));
                        c.setCategory(channelData.getString("category"));

                        newsChannels.add(c);
                        newsChannelAdapter.notifyDataSetChanged();


//                        Log.d(TAG, "onResponse: " + c.toString());

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onError(String error) {
                Log.d(TAG, "onError: " + error);
            }
        });


    }

    public void getSportsChannel(String url){
        sportsChannelList = findViewById(R.id.sports_channel_list);
        sportsChannel = new ArrayList<>();
        sportsChannelList.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        sportsChannelAdapter = new ChannelAdapter(sportsChannel,"category");
        sportsChannelList.setAdapter(sportsChannelAdapter);

        service.getChannelData(url, new ChannelDataService.OnDataResponse() {
            @Override
            public void onResponse(JSONObject response) {
//                Log.d(TAG, "onResponse: sports" + response.toString());
                for(int i = 0; i<response.length();i++){
                    try {
                        JSONObject channelData = response.getJSONObject(String.valueOf(i));
                        Channel c = new Channel();
                        c.setId(channelData.getInt("id"));
                        c.setName(channelData.getString("name"));
                        c.setDescription(channelData.getString("description"));
                        c.setThumbnail(channelData.getString("thumbnail"));
                        c.setLive_url(channelData.getString("live_url"));
                        c.setFacebook(channelData.getString("facebook"));
                        c.setTwitter(channelData.getString("twitter"));
                        c.setYoutube(channelData.getString("youtube"));
                        c.setWebsite(channelData.getString("website"));
                        c.setCategory(channelData.getString("category"));
                        Log.d(TAG, "onResponse: " + c.toString());
                        sportsChannel.add(c);
                        sportsChannelAdapter.notifyDataSetChanged();


//                        Log.d(TAG, "onResponse: " + c.toString());

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onError(String error) {
                Log.d(TAG, "onError: " + error);
            }
        });


    }

    public void getEnterChannel(String url){

        enterChannelList = findViewById(R.id.enter_channel_list);
        enterChannel = new ArrayList<>();
        enterChannelList.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        enterChannelAdapter = new ChannelAdapter(enterChannel,"category");
        enterChannelList.setAdapter(enterChannelAdapter);

        service.getChannelData(url, new ChannelDataService.OnDataResponse() {
            @Override
            public void onResponse(JSONObject response) {
                for(int i = 0; i<response.length();i++){
                    try {
                        JSONObject channelData = response.getJSONObject(String.valueOf(i));
                        Channel c = new Channel();
                        c.setId(channelData.getInt("id"));
                        c.setName(channelData.getString("name"));
                        c.setDescription(channelData.getString("description"));
                        c.setThumbnail(channelData.getString("thumbnail"));
                        c.setLive_url(channelData.getString("live_url"));
                        c.setFacebook(channelData.getString("facebook"));
                        c.setTwitter(channelData.getString("twitter"));
                        c.setYoutube(channelData.getString("youtube"));
                        c.setWebsite(channelData.getString("website"));
                        c.setCategory(channelData.getString("category"));

                        enterChannel.add(c);
                        enterChannelAdapter.notifyDataSetChanged();


//                        Log.d(TAG, "onResponse: " + c.toString());

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onError(String error) {
                Log.d(TAG, "onError: " + error);
            }
        });

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        drawerLayout.closeDrawer(GravityCompat.START);
        if(item.getItemId() == R.id.home){

        }

        if(item.getItemId() == R.id.categories){
            // start category activity
            startActivity(new Intent(this,Categories.class));
        }
        return false;
    }

}