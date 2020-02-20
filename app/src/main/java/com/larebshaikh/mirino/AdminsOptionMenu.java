package com.larebshaikh.mirino;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

public class AdminsOptionMenu extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private FirebaseDatabase firebaseDatabase;

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.adminmenu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.logout:
                logout();
                break;
            case R.id.gotoUsers:
                finish();
                startActivity(new Intent(this,RegisteredUsers.class));
                break;
            case R.id.gotoChangePasswordAdmin:
                finish();
                startActivity(new Intent(this,ChangPasswordAdmin.class));
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return true;

    }
    private void logout() {
        mAuth= FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();
        mAuth.signOut();
        finish();
        startActivity(new Intent(this,LoginMainActivity.class));
    }
}
