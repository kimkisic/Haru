package com.kisicsoft.harutest;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.net.Uri;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.bumptech.glide.annotation.GlideModule;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.module.AppGlideModule;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.InputStream;

public class Page1Fragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_page1, container, false);
        final ImageView imageView = (ImageView)view.findViewById(R.id.iv1);
        final ImageView imageView2 = (ImageView)view.findViewById(R.id.iv2);
        GradientDrawable drawable = (GradientDrawable) getContext().getDrawable(R.drawable.rounding);
        imageView.setBackground(drawable);
        imageView.setClipToOutline(true);
        FirebaseStorage firebaseStorage = FirebaseStorage.getInstance();
        StorageReference storageRef = firebaseStorage.getReference();
        StorageReference imagesRef = storageRef.child("images/ggasu.jpg");
        StorageReference imagesRef2 = storageRef.child("images/gasu.jpg");
        imagesRef.getDownloadUrl().addOnCompleteListener(new OnCompleteListener<Uri>() {
            @Override
            public void onComplete(@NonNull Task<Uri> task) {
                Glide.with(Page1Fragment.this).load(task.getResult()).apply(RequestOptions.bitmapTransform(new RoundedCorners(14))).into(imageView);
            }
        });
        imagesRef2.getDownloadUrl().addOnCompleteListener(new OnCompleteListener<Uri>() {
            @Override
            public void onComplete(@NonNull Task<Uri> task) {
                Glide.with(Page1Fragment.this).load(task.getResult()).apply(RequestOptions.bitmapTransform(new RoundedCorners(14))).into(imageView2);
            }
        });

        return view;
    }
}
