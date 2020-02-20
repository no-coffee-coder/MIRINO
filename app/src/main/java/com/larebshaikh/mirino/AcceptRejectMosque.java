package com.larebshaikh.mirino;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

public class AcceptRejectMosque extends AdminsOptionMenu {
    private Toolbar admintoolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accept_reject_mosque);
        admintoolbar=findViewById(R.id.usertoolbar);

    }
}
