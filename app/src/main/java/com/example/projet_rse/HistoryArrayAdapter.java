package com.example.projet_rse;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class HistoryArrayAdapter  extends ArrayAdapter<History> {
    private static final String TAG = "CardArrayAdapter";
    private List<History> cardList = new ArrayList<History>();

    static class HistoryViewHolder {
        TextView Date;
        TextView PackageNumber;
        TextView PurchasingVoucherAmount;
    }

    public HistoryArrayAdapter(Context context, int textViewResourceId) {
        super(context, textViewResourceId);
    }

    @Override
    public void add(History object) {
        cardList.add(object);
        super.add(object);
    }

    @Override
    public int getCount() {
        return this.cardList.size();
    }

    @Override
    public History getItem(int index) {
        return this.cardList.get(index);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        HistoryViewHolder viewHolder;
        if (row == null) {
            LayoutInflater inflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(R.layout.list_item_history, parent, false);
            viewHolder = new HistoryViewHolder();
            viewHolder.Date = (TextView) row.findViewById(R.id.tv_Date);
            viewHolder.PackageNumber = (TextView) row.findViewById(R.id.tv_PackageNumber);
            viewHolder.PurchasingVoucherAmount = (TextView) row.findViewById(R.id.tv_Amount);
            row.setTag(viewHolder);
        } else {
            viewHolder = (HistoryViewHolder)row.getTag();
        }
        History history = getItem(position);
        viewHolder.Date.setText(history.getDate());
        viewHolder.PackageNumber.setText(history.getPackagesNumber());
//        viewHolder.PurchasingVoucherAmount.setText(history.getPurchasingVoucher().getAmount());
        return row;
    }

    public Bitmap decodeToBitmap(byte[] decodedByte) {
        return BitmapFactory.decodeByteArray(decodedByte, 0, decodedByte.length);
    }
}
