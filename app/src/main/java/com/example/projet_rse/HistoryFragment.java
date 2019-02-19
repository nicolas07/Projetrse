package com.example.projet_rse;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

import java.util.EnumMap;
import java.util.Map;


public class HistoryFragment extends Fragment {

    private static final int WHITE = 0xFFFFFFFF;
    private static final int BLACK = 0xFF000000;

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

        ((MainActivity) getActivity()).setActionBarTitle("Historique");

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
//
//                LinearLayout l = new LinearLayout(getActivity());
//                l.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
//                l.setOrientation(LinearLayout.VERTICAL);
//
//
//                ImageView iv = new ImageView(getActivity());
//                iv.setImageBitmap(bitmap);
//
//                l.addView(iv);

//                //barcode text
//                TextView tv = new TextView(getActivity());
//                tv.setGravity(Gravity.CENTER_HORIZONTAL);
//                tv.setText(history.getAmount() + " (Valable jusqu'au 06/06/2019)");
//
//                l.addView(tv);

                android.support.v7.app.AlertDialog.Builder alert = new android.support.v7.app.AlertDialog.Builder(getActivity());

                View vBarCode = LayoutInflater.from(getActivity())
                        .inflate(R.layout.dialog_imageview,null);

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
