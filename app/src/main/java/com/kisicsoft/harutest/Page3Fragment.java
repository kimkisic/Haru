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

public class Page3Fragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_page3, container, false);
        final ImageView imageView = (ImageView)view.findViewById(R.id.iv5);
        final ImageView imageView2 = (ImageView)view.findViewById(R.id.iv6);
        final ImageView imageView3 = (ImageView)view.findViewById(R.id.iv7);
        FirebaseStorage firebaseStorage = FirebaseStorage.getInstance();
        StorageReference storageRef = firebaseStorage.getReference();
        StorageReference imagesRef = storageRef.child("images/tokyocafe.jpg");
        imagesRef.getDownloadUrl().addOnCompleteListener(new OnCompleteListener<Uri>() {
            @Override
            public void onComplete(@NonNull Task<Uri> task) {
                Glide.with(Page3Fragment.this).load(task.getResult()).apply(RequestOptions.bitmapTransform(new RoundedCorners(14))).into(imageView);
            }
        });
        StorageReference imagesRef2 = storageRef.child("images/coffee.jpg");
        imagesRef2.getDownloadUrl().addOnCompleteListener(new OnCompleteListener<Uri>() {
            @Override
            public void onComplete(@NonNull Task<Uri> task) {
                Glide.with(Page3Fragment.this).load(task.getResult()).apply(RequestOptions.bitmapTransform(new RoundedCorners(14))).into(imageView2);
            }
        });
        StorageReference imagesRef3 = storageRef.child("images/coffeeshop.jpg");
        imagesRef3.getDownloadUrl().addOnCompleteListener(new OnCompleteListener<Uri>() {
            @Override
            public void onComplete(@NonNull Task<Uri> task) {
                Glide.with(Page3Fragment.this).load(task.getResult()).apply(RequestOptions.bitmapTransform(new RoundedCorners(14))).into(imageView3);
            }
        });
        return view;
    }
}
