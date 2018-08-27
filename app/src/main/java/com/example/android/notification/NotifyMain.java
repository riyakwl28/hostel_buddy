package  com.example.android.notification;

import android.support.design.widget.NavigationView;
import android.support.v7.app.AppCompatActivity;

import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.android.notification.Activities.ComplaintActivity;
import com.example.android.notification.Activities.LoginActivity;
import com.example.android.notification.Activities.Show_All_Items_;
import com.example.android.notification.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class NotifyMain extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private TextView mProfileLabel;
    private TextView mUsersLabel;
    private TextView mNotificationsLabel;
    private ViewPager mMainPager;
    private PagerViewAdapter mPagerViewAdapter;
    private FirebaseAuth mAuth;
    private FirebaseFirestore mFirestore;
    String user_name,user_image;
    private  String mUserId;
    private ImageView image;
    private TextView name;

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser currentUser=mAuth.getCurrentUser();
        if(currentUser==null){
            sendToLogin();
        }
    }
    private void sendToLogin(){
        Intent loginIntent=new Intent(NotifyMain.this,LoginActivity.class);
        startActivity(loginIntent);
        finish();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notifymain);
        mAuth=FirebaseAuth.getInstance();
        mProfileLabel=(TextView)findViewById(R.id.profileLabel);
        mUsersLabel=(TextView)findViewById(R.id.usersLabel);
        mNotificationsLabel=(TextView)findViewById(R.id.notificationsLabel);
        mMainPager=(ViewPager)findViewById(R.id.mainPager);
        mMainPager.setOffscreenPageLimit(2);
        mPagerViewAdapter=new PagerViewAdapter(getSupportFragmentManager());
        mMainPager.setAdapter(mPagerViewAdapter);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_2);
        setSupportActionBar(toolbar);

        mAuth=FirebaseAuth.getInstance();
        mFirestore=FirebaseFirestore.getInstance();
        mUserId=mAuth.getCurrentUser().getUid();

        mFirestore.collection("Users").document(mUserId).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                user_name=documentSnapshot.getString("name");
                user_image=documentSnapshot.getString("image");



            }
      });

//        image=(ImageView)findViewById(R.id.imageView);
//        name=(TextView)findViewById(R.id.idname);
//       // name.setText(user_name);
//        RequestOptions placeHolderOption=new RequestOptions();
//        placeHolderOption.placeholder(R.drawable.download);
//        Glide.with(NotifyMain.this).setDefaultRequestOptions(placeHolderOption).load(user_image).into(image);



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_2);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view_2);
        navigationView.setNavigationItemSelectedListener(this);
        mProfileLabel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMainPager.setCurrentItem(0);

            }
        });
        mUsersLabel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMainPager.setCurrentItem(1);
            }
        });
        mNotificationsLabel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMainPager.setCurrentItem(2);
            }
        });
        mMainPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                changeTabs(position);

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_2);
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

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.portal) {
            Intent i =new Intent(NotifyMain.this, ComplaintActivity.class);
            i.putExtra("user_name",user_name);
            i.putExtra("user_image",user_image);
            startActivity(i);

        } else if (id == R.id.pocket_manager) {
            Intent i = new Intent(NotifyMain.this, PocketMain.class);
            startActivity(i);
        }

         else if (id == R.id.lendlist) {
            Intent i=new Intent(NotifyMain.this,Show_All_Items_.class);
            i.putExtra("user_name", user_name);
            startActivity(i);

        }
            else if (id == R.id.about_developers) {



        } else if (id == R.id.logout) {

            Map<String,Object> tokenMapRemove=new HashMap<>();
            tokenMapRemove.put("token_id", FieldValue.delete());
            mFirestore.collection("Users").document(mUserId).update(tokenMapRemove).addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void aVoid) {
                    mAuth.signOut();

                    Intent logIntent=new Intent(NotifyMain.this,LoginActivity.class);
                    startActivity(logIntent);

                }
            });

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_2);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    private  void changeTabs(int position){
        if(position==0){
            mProfileLabel.setTextColor(getColor(R.color.textTabBright));
            mProfileLabel.setTextSize(22);
            mUsersLabel.setTextColor(getColor(R.color.textTabLight));
            mUsersLabel.setTextSize(16);
            mNotificationsLabel.setTextColor(getColor(R.color.textTabLight));
            mNotificationsLabel.setTextSize(16);
        }
        if(position==1){
            mProfileLabel.setTextColor(getColor(R.color.textTabLight));
            mProfileLabel.setTextSize(16);
            mUsersLabel.setTextColor(getColor(R.color.textTabBright));
            mUsersLabel.setTextSize(22);
            mNotificationsLabel.setTextColor(getColor(R.color.textTabLight));
            mNotificationsLabel.setTextSize(16);
        }
        if(position==2){
            mProfileLabel.setTextColor(getColor(R.color.textTabLight));
            mProfileLabel.setTextSize(16);
            mUsersLabel.setTextColor(getColor(R.color.textTabLight));
            mUsersLabel.setTextSize(16);
            mNotificationsLabel.setTextColor(getColor(R.color.textTabBright));
            mNotificationsLabel.setTextSize(22);
        }
    }
}
