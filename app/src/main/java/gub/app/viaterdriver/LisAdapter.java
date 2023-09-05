package gub.app.viaterdriver;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class LisAdapter extends ArrayAdapter<Heros> {

    private List<Heros> heroList;

    //the context object
    private Context mCtx;


    public LisAdapter(List<Heros> heroList, Context mCtx) {
        super(mCtx, R.layout.list_items, heroList);
        this.heroList = heroList;
        this.mCtx = mCtx;
    }
    public View getView(int position, View convertView, ViewGroup parent) {
        //getting the layoutinflater
        LayoutInflater inflater = LayoutInflater.from(mCtx);

        //creating a view with our xml layout
        View listViewItem = inflater.inflate(R.layout.list_items, null, true);

        //getting text views
        TextView textViewreqID = listViewItem.findViewById(R.id.textViewReqID);
        TextView textViewBudget = listViewItem.findViewById(R.id.textViewBudget);
        TextView textViewInfo = listViewItem.findViewById(R.id.textViewInfo);

        //Getting the hero for the specified position
        Heros hero = heroList.get(position);

        //setting hero values to textviews
        textViewreqID.setText("id :"+hero.getRequestI());
        textViewBudget.setText("Budget :"+hero.getRbudget());
        textViewInfo.setText("Info :"+hero.getrInfo());

        //returning the listitem
        return listViewItem;
    }

}
