package com.larebshaikh.mirino;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.google.firebase.auth.FirebaseAuth;

public class UsersOptionMenu extends AppCompatActivity {
    private FirebaseAuth mAuth;


    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.Logout:
                logout();
                break;
            case R.id.gotoMyProfile:
                finish();
                startActivity(new Intent(this,UserProfileActivity.class));
                break;
            case R.id.gotoReadLayout:
                finish();
                startActivity(new Intent(this,ReadReciteActivity.class));
                break;
            case R.id.gotoAddMosque:
                finish();
                startActivity(new Intent(this,RequestMosqueAdd.class));
                break;
            case R.id.gotoChangePassword:
                finish();
                startActivity(new Intent(this,ChangePasswordActivity.class));
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return true;

    }
    private void logout() {
        mAuth= FirebaseAuth.getInstance();
        mAuth.signOut();
        finish();
        startActivity(new Intent(this,LoginMainActivity.class));
    }
}
