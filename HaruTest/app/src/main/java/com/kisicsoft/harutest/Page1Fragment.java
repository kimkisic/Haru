package com.kisicsoft.harutest;

import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class Page1Fragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_page1, container, false);
        ImageView imageView = view.findViewById(R.id.iv1);
        GradientDrawable drawable = (GradientDrawable) getContext().getDrawable(R.drawable.rounding);
        imageView.setBackground(drawable);
        imageView.setClipToOutline(true);
        return view;
    }
}
