package com.larebshaikh.mirino;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;

public class RegisteredUserActivityAdmin extends AdminsOptionMenu {
    private Toolbar admintoolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registered_user_admin);
        admintoolbar=findViewById(R.id.usertoolbar);
        setSupportActionBar(admintoolbar);
    }
}
