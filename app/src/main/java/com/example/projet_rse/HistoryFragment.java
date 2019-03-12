package com.example.projet_rse;

import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;


public class HistoryFragment extends Fragment {

    private ListView listView;

    public static HistoryFragment newInstance() {
        return (new HistoryFragment());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_history, container, false);

        ((MainActivity) getActivity()).setActionBarTitle("Historique");

        StorageHelper storageHelper = new StorageHelper(getActivity());

        listView = view.findViewById(R.id.lv_History);

        listView.addHeaderView(new View(getActivity()));
        listView.addFooterView(new View(getActivity()));

        HistoryArrayAdapter historyArrayAdapter = new HistoryArrayAdapter(getActivity(), R.layout.list_item_history);
        List<History> sortedList = storageHelper.GetHistories();
        Collections.sort(sortedList, new Comparator<History>() {
            @Override
            public int compare(History o1, History o2) {
                return o2.getDate().compareTo(o1.getDate());
            }
        });

        historyArrayAdapter.addList(sortedList);

        listView.setAdapter(historyArrayAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                History history = (History) listView.getItemAtPosition(position);

                android.support.v7.app.AlertDialog.Builder alert = new android.support.v7.app.AlertDialog.Builder(getActivity());

                View vBarCode = LayoutInflater.from(getActivity())
                        .inflate(R.layout.dialog_imageview, null);

                TextView LegendBarCode = vBarCode.findViewById(R.id.tv_LegendBarcode);
                ImageView BarCode = vBarCode.findViewById(R.id.iv_BarCode);

                BarCodeHelper barCodeHelper = new BarCodeHelper();
                Bitmap bitmap = barCodeHelper.GenerateBarCode();
                BarCode.setImageBitmap(bitmap);

                LegendBarCode.setText(history.getAmount() + " (Valable jusqu'au 06/06/2019)");
                alert.setView(vBarCode);
                alert.setPositiveButton("Retour", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {

                    }
                });
                alert.show();

            }
        });

        return view;
    }

}
