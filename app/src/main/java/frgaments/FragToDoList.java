package frgaments;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.pathfinder.anup.imptodo.ImpToDo;
import com.pathfinder.anup.imptodo.R;

/**
 * Created by Anup on 2/3/2017.
 */

public class FragToDoList extends Fragment {

    Button btn_markCalender;

    ImpToDo.FRAGMENT_STATES mSrcFrag;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_to_do, container, false);
        btn_markCalender = (Button) view.findViewById(R.id.btn_mark_on_calender);
        btn_markCalender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "Clicked!", Toast.LENGTH_LONG).show();
                /*FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                FragCalender fragCalender = new FragCalender();
                fragmentTransaction.replace(R.id.fragment_container,fragCalender, ImpToDo.FRAG_CALENDER);
                //fragCalender.setSourceFragment("Calender");
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();*/
            }
        });
        return view;
    }

    public void setSourceFragment(ImpToDo.FRAGMENT_STATES srcFrag) {
        this.mSrcFrag = srcFrag;
    }

    @Override
    public void onDestroy() {
        try{
            // this code will clear any existing fragment left in the backstack
            getFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        } catch (Exception e){
            e.printStackTrace();
        }
        super.onDestroy();
    }
}
