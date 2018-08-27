package com.example.android.notification.Adapters;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.android.notification.R;
import com.example.android.notification.models.To_Items;
import com.example.android.notification.models.complaint;

import java.util.ArrayList;

/**
 * Created by sakshi on 16/4/18.
 */

public class ToItemsAdapter extends ArrayAdapter<To_Items> {


    public ToItemsAdapter(@NonNull Context context, ArrayList<To_Items> objects) {
        super(context, 0, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = ((Activity) getContext()).getLayoutInflater().inflate(R.layout.to_items_items, parent, false);

            TextView itemsNameTextView = (TextView) convertView.findViewById(R.id.item_name);
            TextView nameTextView = (TextView) convertView.findViewById(R.id.name);

            To_Items new_items =  getItem(position);
            itemsNameTextView.setText(new_items.getTo_item_name());
            nameTextView.setText(new_items.getTo_name());
        }


        return convertView;
    }
}
