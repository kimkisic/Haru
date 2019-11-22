package com.kisicsoft.harutest;

import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class Page2Fragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_page2, container, false);
        final ImageView imageView = (ImageView)view.findViewById(R.id.iv3);
        final ImageView imageView2 = (ImageView)view.findViewById(R.id.iv4);
        FirebaseStorage firebaseStorage = FirebaseStorage.getInstance();
        StorageReference storageRef = firebaseStorage.getReference();
        StorageReference imagesRef = storageRef.child("images/pcc.jpg");
        imagesRef.getDownloadUrl().addOnCompleteListener(new OnCompleteListener<Uri>() {
            @Override
            public void onComplete(@NonNull Task<Uri> task) {
                Glide.with(Page2Fragment.this).load(task.getResult()).apply(RequestOptions.bitmapTransform(new RoundedCorners(14))).into(imageView);
            }
        });
        StorageReference imagesRef2 = storageRef.child("images/cp.jpg");
        imagesRef2.getDownloadUrl().addOnCompleteListener(new OnCompleteListener<Uri>() {
            @Override
            public void onComplete(@NonNull Task<Uri> task) {
                Glide.with(Page2Fragment.this).load(task.getResult()).apply(RequestOptions.bitmapTransform(new RoundedCorners(14))).into(imageView2);
            }
        });
        return view;
    }
}
