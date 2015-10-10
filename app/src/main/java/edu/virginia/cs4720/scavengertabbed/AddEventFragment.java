package edu.virginia.cs4720.scavengertabbed;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;


public class AddEventFragment extends Fragment {

    Location location;
    Double latitude;
    Double longitude;
    View v;

    ImageView imgView;
    Bitmap bitmap;


    public AddEventFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this frag

        MainActivity mainActivity = (MainActivity) getActivity();
        location = mainActivity.current;
        latitude = location.getLatitude();
        longitude = location.getLongitude();
        v = inflater.inflate(R.layout.fragment_add_event, container, false);

        imgView = (ImageView) v.findViewById(R.id.imageUploadView);

        Button imageButton = (Button) v.findViewById(R.id.uploadImageButton);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*//for the camera
                Intent imageIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(imageIntent, 0);*/

                //for the photos on the device
                Intent photoSelect = new Intent(Intent.ACTION_PICK);
                photoSelect.setType("image/*");
                startActivityForResult(photoSelect, 1);
            }
        });

        return v;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        /*//old code
        bitmap = (Bitmap) data.getExtras().get("data");
        imgView.setImageBitmap(bitmap);*/
        Uri selectedImg = data.getData();
        imgView.setImageURI(selectedImg);

    }
}

