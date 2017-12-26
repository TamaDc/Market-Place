package tamadc.marketplace;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class ShopActivity extends AppCompatActivity {

    EditText menu1,menu2,menu3,menu4,menu5,menu6;
    int harga1 = 20000;
    int harga2 = 5000;
    int harga3 = 40000;
    int harga4 = 20000;
    int harga5 = 15000;
    int harga6 = 25000;
    String menus;
    EditText total1;

    int Jumlah = 0 ;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);

        menu1 = (EditText) findViewById(R.id.menu1);
        menu2 = (EditText) findViewById(R.id.menu2);
        menu3 = (EditText) findViewById(R.id.menu3);
        menu4 = (EditText) findViewById(R.id.menu4);
        menu5 = (EditText) findViewById(R.id.menu5);
        menu6 = (EditText) findViewById(R.id.menu6);
        total1 = (EditText) findViewById(R.id.total1);



    }


    public void Shop(View view) {

        int harga = 0 ;
        String jum1 = menu1.getText().toString().trim();
        String jum2 = menu2.getText().toString().trim();
        String jum3 = menu3.getText().toString().trim();
        String jum4 = menu4.getText().toString().trim();
        String jum5 = menu5.getText().toString().trim();
        String jum6 = menu6.getText().toString().trim();



        int pil1 =  Integer.parseInt(jum1);
        int pil2 =  Integer.parseInt(jum2);
        int pil3 =  Integer.parseInt(jum3);
        int pil4 =  Integer.parseInt(jum4);
        int pil5 =  Integer.parseInt(jum5);
        int pil6 =  Integer.parseInt(jum6);

        harga = pil1 * harga1 +  pil2 * harga2 + pil3 * harga3+ pil4 * harga4+ pil5 * + harga5 +pil6 *harga6;


        total1.setText(""+harga);
    }

    public void homes(View view) {

        Intent it = new Intent(ShopActivity.this,Menu.class);
        startActivity(it);
    }
}
