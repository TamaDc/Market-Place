package tamadc.marketplace;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MenuProfil extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_profil);
    }

    public void HomeProfil(View view) {
        Intent it = new Intent(MenuProfil.this,Menu.class);
        startActivity(it);
    }
}
