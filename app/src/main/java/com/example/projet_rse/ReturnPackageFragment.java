package com.example.projet_rse;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class ReturnPackageFragment extends Fragment {

        public static ReturnPackageFragment newInstance() {
            return (new ReturnPackageFragment());
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            return inflater.inflate(R.layout.fragment_returnpackage, container, false);
        }
}
