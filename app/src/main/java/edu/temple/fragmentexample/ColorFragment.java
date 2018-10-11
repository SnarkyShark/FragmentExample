package edu.temple.fragmentexample;


import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment; // this matches the one we use in MainActivity -- don't use the SDK's fragment manager
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.Random;


/**
 * A simple {@link Fragment} subclass.
 */
public class ColorFragment extends Fragment {

    public static String COLOR_KEY = "color";   // it's my key it goes here
    int color;
    Context context;
    View v;

    public ColorFragment() {
        // Required empty public constructor
        // used by the FragmentManager to kill/recreate
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        this.context = context; // now you can access resourcesColorFragment
    }

    // the below approach only works when I create the fragment
    // if the OS tries, it will always call the default
    // DON'T USE NON-DEFAULT CONSTRUCTORS
    /*
    public ColorFragment(int color) {
        this.color = color;
    } */

    // don't pass arguments through a constructor
    // pass info to the fragment object
    // Android keeps track of the arguments used to set the fragment
    // it will replace those arguments later


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // example of grabbing resources: String[] values = context.getResources().getStringArray();

        Bundle bundle = getArguments(); // always returns whatever arguments were used to create the fragment (this is the proper place, in onCreate)

        this.color = bundle.getInt(COLOR_KEY);  // setting the color
    }

    // here we implement the same thing as the factory methods
    public static ColorFragment newInstance(int color){
        // Creates an instance of the defined fragments
        ColorFragment colorFragment = new ColorFragment();  // there is a better way, but we'll learn later

        Bundle bundle = new Bundle();

        bundle.putInt(ColorFragment.COLOR_KEY, color);  // always use variables for keys


        colorFragment.setArguments(bundle); // bundle is a collection of key value pairs because android loves those

        return colorFragment;
    }

    //NEW STUUUUFFFFF
    //WOOOOOOOW
    public void changeColor(int color) {
        v.setBackgroundColor(color);
    }

    // a callback that gives the view to be displayed
    // we added some stuff to it
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        Button button;

        // intercept the view & inflate it
        v =  inflater.inflate(R.layout.fragment_color, container, false);

        button = v.findViewById(R.id.change_color_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                v.setBackgroundColor((new Random()).nextInt() % 2 == 0 ? Color.BLACK : Color.GREEN);
            }
        });


        //v.setBackgroundColor((new Random()).nextInt() % 2 == 0 ? Color.RED : Color.GREEN);  // highly inefficient
        v.setBackgroundColor(color);

        return v;
    }

}
