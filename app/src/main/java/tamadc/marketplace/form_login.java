package tamadc.marketplace;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class form_login extends AppCompatActivity {

    TextView username;
    TextView password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_login);

        username = (TextView) findViewById(R.id.username);
        password = (TextView) findViewById(R.id.passwordd);


    }

    public void Acces(View view) {
        String sName = username.getText().toString();
        String sPass = password.getText().toString();

        if(sName.equals("admin")&& sPass.equals("admin")){
            Toast.makeText(getApplicationContext(),"Suksess Login!!!",Toast.LENGTH_SHORT).show();
            Intent it = new Intent(form_login.this,MainActivity.class);
            startActivity(it);
        }else
        {
           username.setText("");
            password.setText("");
            Toast.makeText(getApplicationContext(),"username atau password salah !!!",Toast.LENGTH_SHORT).show();

        }
    }
}
