package tamadc.marketplace;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MenuCreative extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_creative);
    }

    public void home_list(View view) {
        Intent it = new Intent(MenuCreative.this,Menu.class);
        startActivity(it);
    }
}
