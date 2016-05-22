package com.xiaoshan.polly.followme.fragment;


import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.text.format.Formatter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.xiaoshan.polly.followme.R;

import java.io.File;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentAnother extends Fragment {


    private TextView memInfo;

    public FragmentAnother() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fragment_another, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        memInfo = (TextView) getActivity().findViewById(R.id.tv_myphone_memory);
        memInfo.setText(getMemInfo());
    }

    private String getMemInfo() {
        File dataDirectory = Environment.getDataDirectory();
        File externalStorageDirectory = Environment.getExternalStorageDirectory();
        long dataDirectoryTotalSpace = dataDirectory.getTotalSpace();
        long externalStorageDirectoryTotalSpace = externalStorageDirectory.getTotalSpace();
        String dataSpace = Formatter.formatFileSize(getActivity(), dataDirectoryTotalSpace);
        String externalSpace = Formatter.formatFileSize(getActivity(), externalStorageDirectoryTotalSpace);

        return "手机内存: "+dataSpace+"\n"+"外部存储: "+externalSpace;
    }
}
