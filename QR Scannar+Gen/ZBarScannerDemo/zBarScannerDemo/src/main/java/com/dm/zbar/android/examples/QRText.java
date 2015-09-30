package com.dm.zbar.android.examples;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

/**
 * Created by ribu on 14-Sep-15.
 */
public class QRText extends ActionBarActivity {
    TextView tv;
    Intent in;
    String res, shareBody;
    private Toolbar toolbar1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        toolbar1 = (Toolbar) findViewById(R.id.tool_bar1); // Attaching the layout to the toolbar object
        setSupportActionBar(toolbar1);                   // Setting toolbar as the ActionBar with setSupportActionBar() call
        tv = (TextView) findViewById(R.id.text1);
        in = getIntent();
        res = in.getExtras().getString("key1");
        tv.setText(res);
    }

    public void sharebtn(View v) {
        in = getIntent();
        res = in.getExtras().getString("key1");
        shareBody = res;
        Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
        sharingIntent.setType("text/plain");
        sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Subject Here");
        sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
        startActivity(Intent.createChooser(sharingIntent, getResources().getString(R.string.share)));
    }
}
