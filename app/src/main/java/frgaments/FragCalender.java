package frgaments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pathfinder.anup.imptodo.ImpToDo;
import com.pathfinder.anup.imptodo.R;

/**
 * Created by Anup on 2/3/2017.
 */

public class FragCalender extends Fragment {

    ImpToDo.FRAGMENT_STATES mSrcFrag;


    public static FragCalender newInstance(/*int index*/) {
        FragCalender f = new FragCalender();
        Bundle args = new Bundle();
        //args.putInt("index", index);
        f.setArguments(args);
        return f;
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view  = inflater.inflate(R.layout.layout_calender, container, false);
        return view;
    }

    public void setSourceFragment(ImpToDo.FRAGMENT_STATES srcFrag) {
        this.mSrcFrag = srcFrag;
    }
}
