package com.dm.zbar.android.examples;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Random;

/**
 * Created by ribu on 14-Sep-15.
 */
public class SecondActivity extends ActionBarActivity implements View.OnClickListener {

    SharedPreferences sharedPrefs;
    private String LOG_TAG = "GenerateQRCode";
    private Toolbar toolbar;
    Button sharebtn;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.generator);
        toolbar = (Toolbar) findViewById(R.id.tool_bar2); // Attaching the layout to the toolbar object
        setSupportActionBar(toolbar);                   // Setting toolbar as the ActionBar with setSupportActionBar() call
        Button button1 = (Button) findViewById(R.id.button13);
        sharebtn=(Button)findViewById(R.id.sharebtn);
        button1.setOnClickListener(this);
        sharedPrefs=getSharedPreferences("share", Context.MODE_PRIVATE);

    }

    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.button13:
                EditText qrInput = (EditText) findViewById(R.id.qrInput);
                String qrInputText = qrInput.getText().toString();
                Log.v(LOG_TAG, qrInputText);

                //Find screen size
                WindowManager manager = (WindowManager) getSystemService(WINDOW_SERVICE);
                Display display = manager.getDefaultDisplay();
                Point point = new Point();
                display.getSize(point);
                int width = point.x;
                int height = point.y;
                int smallerDimension = width < height ? width : height;
                smallerDimension = smallerDimension * 3 / 4;

                //Encode with a QR Code image
                QRCodeEncoder qrCodeEncoder = new QRCodeEncoder(qrInputText,
                        null,
                        Contents.Type.TEXT,
                        BarcodeFormat.QR_CODE.toString(),
                        smallerDimension);
                try {
                    Bitmap bitmap = qrCodeEncoder.encodeAsBitmap();
                    ImageView myImage = (ImageView) findViewById(R.id.imageView1);
                    myImage.setImageBitmap(bitmap);
                    bitMapToString(bitmap);

                } catch (WriterException e) {
                    e.printStackTrace();
                }


                break;

            // More buttons go here (if any) ...

        }

    }
    public void bitMapToString(Bitmap bitmap){
        String root = Environment.getExternalStorageDirectory().toString();
        File myDir = new File(root + "/qr_scanner");
        myDir.mkdirs();

        String fname = "sampleqr" +".jpg";
        File file = new File(myDir, fname);
        SharedPreferences.Editor editor=sharedPrefs.edit();
        editor.putString("bitmap", String.valueOf(file));
        editor.commit();
        Log.i("Helloooo", "" + file);
        sharebtn.setClickable(true);
        if (file.exists())
            file.delete();
        try {
            FileOutputStream out = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 90, out);
            out.flush();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
//        ByteArrayOutputStream baos=new  ByteArrayOutputStream();
//        bitmap.compress(Bitmap.CompressFormat.PNG,100, baos);
//        byte [] b=baos.toByteArray();
//        String temp= Base64.encodeToString(b, Base64.DEFAULT);
//        SharedPreferences.Editor editor=sharedPrefs.edit();
//        editor.putString("bitmap", temp);
////        System.out.println("BITMAP::"+temp);
//        editor.commit();
//
//    }
//    public void sharegen(View v)
//    {
//        sharedPrefs=getSharedPreferences("share",MODE_PRIVATE);
//        String decode1=sharedPrefs.getString("bitmap",null);
////        System.out.println("BITMAP2::"+decode1);
//        try {
//            byte [] encodeByte=Base64.decode(decode1,Base64.DEFAULT);
//            Bitmap bitmap= BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length);
//            Intent sharingIntent1 = new Intent(Intent.ACTION_SEND);
//            sharingIntent1.setType("image/*");
//            sharingIntent1.putExtra(Intent.EXTRA_STREAM, bitmap);
//            startActivity(Intent.createChooser(sharingIntent1, getResources().getString(R.string.share)));
//        } catch(Exception e) {
////            e.getMessage();
//            System.out.println(e.getMessage()+"errrrrrrr");
//
//        }
    }
    public void sharegen(View v)
    {
        sharedPrefs=getSharedPreferences("share",MODE_PRIVATE);
        String decode1=sharedPrefs.getString("bitmap",null);
        File f=new File(decode1);
        Intent share = new Intent(Intent.ACTION_SEND);

        // Type of file to share
        share.setType("image/jpeg");
        Uri uri = Uri.fromFile(f);

        // Pass the image into an Intnet
        share.putExtra(Intent.EXTRA_STREAM, uri);

        // Show the social share chooser list
        startActivity(Intent.createChooser(share, "Share Image Tutorial"));
    }


}