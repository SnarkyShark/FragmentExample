package edu.temple.fragmentexample;

import android.graphics.Color;
import android.net.Uri;
import android.support.v4.app.FragmentManager;  // this one is the support library and lines up with my fragment type
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.util.Random;

public class MainActivity extends AppCompatActivity implements NewColorFragment.ColorInterface {
    //first, set up your activity to hold the fragments
    //do this by adding containers to the activity's layout file
    // <fragment> or <FrameLayout> FrameLayout is more flexible
    // we use FrameLayout to define location, then add the fragments in code later

    NewColorFragment colorFragment3;
    NewColorFragment colorFragment4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Creates an instance of the defined fragments
        final ColorFragment colorFragment = new ColorFragment();  // there is a better way, but we'll learn later

        Bundle bundle = new Bundle();

        bundle.putInt(ColorFragment.COLOR_KEY, Color.RED);  // always use variables for keys


        colorFragment.setArguments(bundle); // bundle is a collection of key value pairs because android loves those


        // Get fragment manager
        // We are using AppCompat though, so we don't just say "getFragment"
        FragmentManager fm = getSupportFragmentManager();

        // Get a transaction
        FragmentTransaction ft = fm.beginTransaction(); // it returns itself, so you can add stuff

        // now we can define the action we want to perform
        // in my transaction, I want to add where, then what
        // we're adding the fragment to container_1
        ft.add(R.id.container_1, colorFragment);

        ft.commit();    // this happens, but we added stuff
        // the builder pattern: you can chain all your function calls

        //fm.beginTransaction().add(R.id.container_2, new ColorFragment()).commit();

        // theoretically, we could use replace instead of add to replace the old fragment in the container
        // if you use add, the old thing is still there, but the new thing is now on top
        // replace completely replaces things
        // each activity has a back stack for its fragments
        // for fragments, it tracks transactions
        // to revert, you need to explicitly tell it to add the transaction to the back stack
        // using .addToBackStack(null)
        fm.beginTransaction().add(R.id.container_2, ColorFragment.newInstance(Color.BLUE)).commit();


        // This stuff is fragment to fragment communication
        colorFragment3 = NewColorFragment.newInstance(Color.GREEN, 3);
        fm.beginTransaction().add(R.id.container_3, colorFragment3).commit();

        colorFragment4 = NewColorFragment.newInstance(Color.BLACK, 4);
        fm.beginTransaction().add(R.id.container_4, colorFragment4).commit();


        //THE NEW STUFF
        //SO MUCH NEW
        //OH MY GOOOD
        //Activity --> Fragment
        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                colorFragment.changeColor(Color.GRAY);  // it's fine to do this in a button
                // otherwise, you'll get a null pointer because the View and fragment haven't been attached yet
                // you could ask "if v != null"
            }
        });


        /*
        Possible implementation:

        fm.beginTransaction()
            .add(R.id.container_1, colorFragment)
            .commit();

        here, you never need to really "create" ft in the code
        it automatically is returned and executed
         */
    }


    //YOU NEED TO IMPLEMENT THIS!!!
    //NEW STUFF
    @Override
    public void userClick(int id) {
        if (id == 3) {
            colorFragment4.changeColor((new Random()).nextInt() % 2 == 0 ? Color.MAGENTA : Color.BLUE);
        }
        else if (id == 4) {
            colorFragment3.changeColor((new Random()).nextInt() % 2 == 0 ? Color.GREEN : Color.BLACK);
        }
    }
}
