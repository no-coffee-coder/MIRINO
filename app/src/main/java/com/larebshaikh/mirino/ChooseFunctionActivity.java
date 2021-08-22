package com.larebshaikh.mirino;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

public class ChooseFunctionActivity extends UsersOptionMenu {

    private Toolbar userToolBar;
    private Button btnMosque;
    private Button btnTemple;
    private Button btnChurch;
    String nodeName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choosefunction);
        userToolBar=findViewById(R.id.usertoolbar);
        setSupportActionBar(userToolBar);
        btnChurch=findViewById(R.id.btnChurch);
        btnMosque=findViewById(R.id.btnMosque);
        btnTemple=findViewById(R.id.btnTemple);

        btnMosque.setOnClickListener(new View.OnClickListener() {
                                                  @Override
                                                  public void onClick(View v) {
                                                     activityNavigator(btnMosque.getText().toString());
                                                  }
                                              }
        );
        btnTemple.setOnClickListener(new View.OnClickListener() {
                                         @Override
                                         public void onClick(View v) {
                                             activityNavigator(btnTemple.getText().toString());
                                         }
                                     }
        );
        btnChurch.setOnClickListener(new View.OnClickListener() {
                                         @Override
                                         public void onClick(View v) {
                                             activityNavigator(btnChurch.getText().toString());
                                         }
                                     }
        );


    }

    public void activityNavigator(String btnid){
        if(btnMosque.getText().toString().equals(btnid)){
            Intent intent=new Intent(ChooseFunctionActivity.this,MosqueSearchActivity.class);
            intent.putExtra(nodeName,"Mosque");
            startActivity(intent);
        }else if(btnTemple.getText().toString().equals(btnid)){
            Intent intent=new Intent(ChooseFunctionActivity.this,MosqueSearchActivity.class);
            intent.putExtra(nodeName,"Temple");
            startActivity(intent);
        }else if(btnChurch.getText().toString().equals(btnid)){
            Intent intent=new Intent(ChooseFunctionActivity.this,MosqueSearchActivity.class);
            intent.putExtra(nodeName,"Church");
            startActivity(intent);
        }

    }


}
