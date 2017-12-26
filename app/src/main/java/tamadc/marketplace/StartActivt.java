package tamadc.marketplace;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class StartActivt extends AppCompatActivity {

    Button btnHome;

    public static SQLiteHelper sqLiteHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_activt);

    }

    public void login(View view) {
        Intent it = new Intent(StartActivt.this,form_login.class);
        startActivity(it);
    }

    public void home(View view) {
        Intent it = new Intent(StartActivt.this,Menu.class);
        startActivity(it);
    }
}
