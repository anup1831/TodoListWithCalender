package com.pathfinder.anup.imptodo;

import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import frgaments.FragCalender;
import frgaments.FragToDoList;

public class ImpToDo extends AppCompatActivity {

    public static final String FRAG_TO_DO_LIST = "To to list";
    public static final String FRAG_CALENDER = "calender";
    public static final String FRAG_ACTION_DONE = "Completed task";

    public enum FRAGMENT_STATES {
        FRAG_TO_DO_LIST, FRAG_CALENDER, FRAG_ACTION_DONE
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imp_to_do);


        FragToDoList fragToDoList = new FragToDoList();
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.add(R.id.fragment_container, fragToDoList, FRAG_TO_DO_LIST);
        ft.commit();
    }

    public void onFragmentChange(FRAGMENT_STATES srcFrg, FRAGMENT_STATES destFrg){

        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();

        if(destFrg == FRAGMENT_STATES.FRAG_CALENDER){
            FragCalender fragCalender = new FragCalender();
            fragmentTransaction.replace(R.id.fragment_container,fragCalender, FRAG_CALENDER);
            fragCalender.setSourceFragment(destFrg);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        }

    }
}
