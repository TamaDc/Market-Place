package tamadc.marketplace;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Asus A455L on 19/12/2017.
 */

public class HandmadeListAdapter extends BaseAdapter {

    private Context context;
    private int layout;
    private ArrayList<handmade> handmadelist;

    public HandmadeListAdapter(Context context, int layout, ArrayList<handmade> handmadelist) {
        this.context = context;
        this.layout = layout;
        this.handmadelist = handmadelist;
    }


    @Override
    public int getCount() {
        return handmadelist.size();
    }

    @Override
    public Object getItem(int position) {
        return handmadelist.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    private class ViewHolder{
        ImageView imageView;
        TextView txtname,txtprice,txtketerangan,txtkualitas;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {

        View row = view;
        ViewHolder holder = new ViewHolder();

        if(row == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(layout, null);

            holder.txtname = (TextView) row.findViewById(R.id.judul);
            holder.txtprice = (TextView) row.findViewById(R.id.total1);
            holder.imageView = (ImageView) row.findViewById(R.id.imageView2);
            holder.txtketerangan = (TextView) row.findViewById(R.id.keterangan);
            holder.txtkualitas = (TextView) row.findViewById(R.id.kualitas);
            row.setTag(holder);

        }else{
            holder = (ViewHolder) row.getTag();

        }

        handmade Handmade = handmadelist.get(position);
        holder.txtname.setText(Handmade.getName());
        holder.txtprice.setText(Handmade.getPrice());
        byte[] handmadeiamge = Handmade.getImage();
        holder.txtketerangan.setText(Handmade.getKeterangan());
        holder.txtkualitas.setText(Handmade.getKualitas());


        Bitmap bitmap = BitmapFactory.decodeByteArray(handmadeiamge, 0 , handmadeiamge.length);
        holder.imageView.setImageBitmap(bitmap);

        return row;
    }
}
