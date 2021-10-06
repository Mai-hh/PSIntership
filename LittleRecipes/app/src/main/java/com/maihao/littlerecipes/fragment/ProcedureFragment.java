package com.maihao.littlerecipes.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.maihao.littlerecipes.R;

public class ProcedureFragment extends Fragment {

    public static final String ARG_TEXT = "param1";

    private String procedure;

    private View rootView;

    public static ProcedureFragment newInstance(String param1) {
        ProcedureFragment fragment = new ProcedureFragment();
        Bundle bundle = new Bundle();
        bundle.putString(ARG_TEXT, param1);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            procedure = getArguments().getString(ARG_TEXT);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        if (rootView == null) {
            rootView = inflater.inflate(R.layout.fragment_procedure, container, false);
            TextView textView = (TextView) rootView.findViewById(R.id.procedure_text_view);
            textView.setText(procedure);
        }
        return rootView;
    }

}
