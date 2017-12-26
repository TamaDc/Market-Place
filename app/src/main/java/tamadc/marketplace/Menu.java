package tamadc.marketplace;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Menu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
    }

    public void Home(View view) {
        Intent it = new Intent(Menu.this,StartActivt.class);
        startActivity(it);
    }

    public void menu(View view) {
        Intent it = new Intent(Menu.this,handmadelist.class);
        startActivity(it);
    }

    public void telp(View view) {
        Intent it = new Intent(Menu.this,MenuProfil.class);
        startActivity(it);
    }

    public void Info(View view) {
        Intent it = new Intent(Menu.this,MenuCreative.class);
        startActivity(it);
    }
}
