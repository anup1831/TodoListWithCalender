package com.pathfinder.anup.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import com.pathfinder.anup.dailyaction.DailyActivityMainView;
import com.pathfinder.anup.dailyaction.DailyActivityMainViewInteractor;
import com.pathfinder.anup.dailyaction.DailyActivityScreen;
import com.pathfinder.anup.imptodo.R;
import com.pathfinder.anup.model.WishItemModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Anup on 2/14/2017.
 */

public class WishListAdapter extends BaseAdapter{
    private Context context;
    public List<WishItemModel> todoList;
    DailyActivityMainView mainView;
   // private DBDataListener dbDataListener;


    public WishListAdapter(Context context, List<WishItemModel> todoList) {
        this.context = context;
        this.todoList = todoList;
        DailyActivityScreen.modelList = new ArrayList<>();
        //dbDataListener = (DBDataListener) context;
    }

    @Override

    public int getCount() {
        return todoList.size();
    }

    @Override
    public Object getItem(int i) {
        return todoList.get(i);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        if(view == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.list_item_rows, null);
        }

        TextView textView = (TextView) view.findViewById(R.id.text_item);
        final CheckBox checkBox = (CheckBox) view.findViewById(R.id.checkbox_id);

        if(todoList.size() > 0){
        textView.setText(todoList.get(i).getWishItem());
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                checkBox.setChecked(b);
               if(b) {
                  todoList.get(i).setValue(1);
                   DailyActivityScreen.modelList.add(todoList.get(i));
               } else {
                   todoList.get(i).setValue(0);
                   DailyActivityScreen.modelList.remove(todoList.get(i));
               }
                //dbDataListener.saveDataInDB(todoList);
                Log.i("Anup",  "checkBox -"+todoList.get(i).getValue() + " - "+ todoList.get(i).getWishItem());

            }
        });
        }
        return view;
    }

   /*  public interface DBDataListener{
        void saveDataInDB(List<WishItemModel> todoList);
    }*/

}
