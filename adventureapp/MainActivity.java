package com.example.adventureapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.widget.FrameLayout;

import com.example.adventureapp.database.DatabaseHandler;
import com.example.adventureapp.fragment.FragmentDetails;
import com.example.adventureapp.fragment.FragmentHome;
import com.example.adventureapp.model.Adventure;

public class MainActivity extends AppCompatActivity implements FragmentCommunicator {

    FrameLayout frameMain;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;
    FragmentHome fragmentHome;

    DatabaseHandler db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inits();
        db = new DatabaseHandler(this);
        insertData();
        fragmentHome =new FragmentHome();
        displayFramgnet(fragmentHome);

    }
    private void inits()
    {
        frameMain = findViewById(R.id.frame_main);
    }
    private void displayFramgnet(Fragment fragment)
    {
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_main,fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        fragmentTransaction.commit();
    }

    private void insertData()
    {
        db.addAdvantureItem(new Adventure("Kayaking","Journey over wild waterfalls and take the chance to explore nature with your friends and family. Whether you're having a friendly competition or simply watch the sunset, you are bound to have a great time traversing over our waters!","$65 per person"));
        db.addAdvantureItem(new Adventure("Rock Climbing","Secured by harnesses and ropes, come along for an adrenaline pumping adventure course where you climb complex courses. Our beginner, intermediate, and advanced course ensures everyone is comfortable and having a good time.","$35 per hour per person"));
        db.addAdvantureItem(new Adventure("Bungee Jumping","With a rubber cord attached to you, jump into the sky with the feeling of weightlessness. The thrill of that first leap really makes the experience enjoyable with your friends and family cheering you on from above.","$20 per person"));
        db.addAdvantureItem(new Adventure("Zip Lining","Zipping through sky overlooking the forest, you will have a great time. You can pick the length of the course and take the time experiencing the rush of the wind blowing on your face.","$50 per person"));
        db.addAdvantureItem(new Adventure("Scuba Diving","Equipped with a snorkeling mask and other gear, explore the world in the water. Experience weightlessness and move freely while you're immersed in the water.","$100 per person"));
        db.addAdvantureItem(new Adventure("Swimming with the Sharks","See the magnificent sharks up close while you being in a fully protected cage. Don't worry, you won't get eaten! These sharks are relatively safe and you'll get the experience of a lifetime","$120 per person"));
        db.addAdvantureItem(new Adventure("Hiking","Take a journey surrounded by the natural world. While getting your steps in, you can observe wildlife and reduce your stress.","$15 per person"));
    }

    @Override
    public void passDataFragment(int id) {
        Bundle bundle = new Bundle();
        bundle.putInt("id",id);

        FragmentDetails fragmentDetails = new FragmentDetails();
        fragmentDetails.setArguments(bundle);
        displayFramgnet(fragmentDetails);


    }
}