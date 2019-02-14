package com.example.projet_rse;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

public class HistoryFragment extends Fragment {

    private static final String TAG = "HistoryListFragment";
    private HistoryArrayAdapter historyArrayAdapter;
    private ListView listView;

    private StorageHelper storageHelper;

    public static HistoryFragment newInstance() {
        return (new HistoryFragment());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
       View view = inflater.inflate(R.layout.fragment_history, container, false);

       storageHelper = new StorageHelper(getActivity());

        listView = (ListView) view.findViewById(R.id.lv_History);

        listView.addHeaderView(new View(getActivity()));
        listView.addFooterView(new View(getActivity()));

        historyArrayAdapter = new HistoryArrayAdapter(getActivity(), R.layout.list_item_history);
        historyArrayAdapter.addList(storageHelper.GetHistories());

        listView.setAdapter(historyArrayAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                History history = (History) listView.getItemAtPosition(position);
                Toast.makeText(getActivity(),history.getPackagesNumber().toString(),Toast.LENGTH_LONG).show();
            }
        });

        return view;
    }
}
