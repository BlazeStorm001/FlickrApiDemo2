package com.academy.flickrapidemo2;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class PhotoDetailActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_photo_detail);
        activateToolbar(true);

        Bundle bundle = getIntent().getExtras();
        Photo photo = (Photo) bundle.getSerializable(PHOTO_TRANSFER);

        if(photo != null) {
            ImageView imageView = findViewById(R.id.photo_image);
            TextView title = findViewById(R.id.photo_title);
            TextView author = findViewById(R.id.photo_author);
            TextView tags = findViewById(R.id.photo_tags);

            Picasso.with(this).load(photo.getLink()).placeholder(R.drawable.placeholder).into(imageView);

            title.setText(photo.getTitle());
            author.setText(photo.getAuthor());
            tags.setText(photo.getTags());
        }

    }
}