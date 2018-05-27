package com.example.diogo.webchanel.util;
import com.example.diogo.webchanel.R;
import com.example.diogo.webchanel.model.EnterpriseTest;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class EnterpriseAdapter extends ArrayAdapter<EnterpriseTest> {

    private final Context context;
    private final ArrayList<EnterpriseTest> elementos;
    public EnterpriseAdapter(Context context, ArrayList<EnterpriseTest> elementos) {
        super(context, R.layout.activity_enterprises, elementos);
        this.context = context;
        this.elementos = elementos;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.activity_enterprises, parent, false);
        TextView nameEnterprise = (TextView) rowView.findViewById(R.id.name);
        TextView address = (TextView) rowView.findViewById(R.id.address);
        ImageView image = (ImageView) rowView.findViewById(R.id.image);
        nameEnterprise.setText(elementos.get(position).getName());
        address.setText(elementos.get(position).getAddress());
        image.setImageResource(elementos.get(position).getImage());
        return rowView;
    }

}
