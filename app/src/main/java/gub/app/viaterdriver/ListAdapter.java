package gub.app.viaterdriver;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ListAdapter extends BaseAdapter {


    private Context context;
    private ArrayList<DataModel> dataModelArrayList;

    public ListAdapter(Context context, ArrayList<DataModel> dataModelArrayList) {
        this.context = context;
        this.dataModelArrayList = dataModelArrayList;
    }

    @Override
    public int getViewTypeCount() {
        return getCount();
    }
    @Override
    public int getItemViewType(int position) {

        return position;
    }

    @Override
    public int getCount() {
        return dataModelArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return dataModelArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.listview_format, null, true);


            //Finding view ID
            holder.tvID = (TextView) convertView.findViewById(R.id.id);
            holder.tvFrom_lat = (TextView) convertView.findViewById(R.id.from_latID);
            holder.tvFrom_lng = (TextView) convertView.findViewById(R.id.from_lngID);
            holder.tvTo_lat = (TextView) convertView.findViewById(R.id.to_latID);
            holder.tvTo_lng = (TextView) convertView.findViewById(R.id.to_lngID);
            holder.tvBudget = (TextView) convertView.findViewById(R.id.budgetID);
            holder.tvAdditional_Req = (TextView) convertView.findViewById(R.id.additionalInfoID);
            holder.tvCreatedAt = (TextView) convertView.findViewById(R.id.createAtID);
            holder.tvDeparture_time = (TextView) convertView.findViewById(R.id.departureTimeID);
            holder.tvStatus = (TextView) convertView.findViewById(R.id.statusID);




            convertView.setTag(holder);
        }else {
            // the getTag returns the viewHolder object set as a tag to the view
            holder = (ViewHolder)convertView.getTag();
        }


        holder.tvID.setText("ID: "+dataModelArrayList.get(position).getmID());
        holder.tvFrom_lat.setText("from_lat: "+dataModelArrayList.get(position).getmFrom_lat());
        holder.tvFrom_lng.setText("from_lng: "+dataModelArrayList.get(position).getmFrom_lng());
        holder.tvTo_lat.setText("to_lat: "+dataModelArrayList.get(position).getMto_lat());
        holder.tvTo_lng.setText("to_lng: "+dataModelArrayList.get(position).getMto_lng());
        holder.tvBudget.setText("Budget: "+dataModelArrayList.get(position).getmBudget());
        holder.tvAdditional_Req.setText("Budget: "+dataModelArrayList.get(position).getmAdditionalInfo());
        holder.tvCreatedAt.setText("Budget: "+dataModelArrayList.get(position).getmCreatedAT());
        holder.tvDeparture_time.setText("Budget: "+dataModelArrayList.get(position).getmDeparture_time());
        holder.tvStatus.setText("Budget: "+dataModelArrayList.get(position).getmStatus());


        return convertView;
    }
    private class ViewHolder {

        protected TextView tvID, tvFrom_lat, tvFrom_lng,tvTo_lat,tvTo_lng,tvBudget,tvAdditional_Req,tvCreatedAt,tvDeparture_time,tvStatus;


    }




}


