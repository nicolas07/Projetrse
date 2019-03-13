package com.example.projet_rse;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

class HistoryArrayAdapter extends ArrayAdapter<History> {
    private final List<History> Histories = new ArrayList<>();

    static class HistoryViewHolder {
        TextView TextViewDate;
        TextView TextViewPackageNumber;
        TextView TextViewAmount;
//        TextView TextViewAdress;
    }

    public HistoryArrayAdapter(Context context, int textViewResourceId) {
        super(context, textViewResourceId);
    }

    @Override
    public void add(History object) {
        Histories.add(object);
        super.add(object);
    }

    public void addList(List<History> histories) {
        Histories.addAll(histories);
    }

    @Override
    public int getCount() {
        return this.Histories.size();
    }

    @Override
    public History getItem(int index) {
        return this.Histories.get(index);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        HistoryViewHolder viewHolder;
        if (row == null) {
            LayoutInflater inflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(R.layout.list_item_history, parent, false);
            viewHolder = new HistoryViewHolder();
            viewHolder.TextViewDate = row.findViewById(R.id.tv_Date);
            viewHolder.TextViewPackageNumber = row.findViewById(R.id.tv_PackageNumber);
            viewHolder.TextViewAmount = row.findViewById(R.id.tv_Amount);
            row.setTag(viewHolder);
        } else {
            viewHolder = (HistoryViewHolder) row.getTag();
        }
        History history = getItem(position);
        viewHolder.TextViewDate.setText(new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(history.getDate()));
        viewHolder.TextViewPackageNumber.setText(Html.fromHtml("<u>Nombre de colis : </u>" + history.getPackagesNumber(), 0));
        viewHolder.TextViewAmount.setText(history.getStringAmount());
        return row;
    }


}
