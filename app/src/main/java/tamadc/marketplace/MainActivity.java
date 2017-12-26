package tamadc.marketplace;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {

    EditText editname, editprice, editketerangan, edirkualitas;
    Button btnChoose, btnAdd, btnList;
    ImageView imageView;

    final int REQUEST_CODE_GALLERY = 999;

    public static SQLiteHelper sqLiteHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();

        sqLiteHelper = new SQLiteHelper(this, "DaDB.sqlite", null, 1);


        sqLiteHelper.queryData("CREATE TABLE IF NOT EXISTS HANDMADE (Id INTEGER PRIMARY KEY AUTOINCREMENT, name VARCHAR, price VARCHAR, keterangan VARCHAR, kualitas VARCHAR, image BLOG)");


        /// menuju menu list
        btnChoose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ActivityCompat.requestPermissions(
                        MainActivity.this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        REQUEST_CODE_GALLERY
                );
            }
        });


        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    sqLiteHelper.insertData(
                            editname.getText().toString().trim(),
                            editprice.getText().toString().trim(),
                            imageViewToByte(imageView),
                            editketerangan.getText().toString().trim(),
                            edirkualitas.getText().toString().trim()

                    );
                    Toast.makeText(getApplicationContext(),"Add File Sukses",Toast.LENGTH_SHORT).show();
                    editname.setText("");
                    editprice.setText("");
                    imageView.setImageResource(R.mipmap.ic_launcher);
                    editketerangan.setText("");
                    edirkualitas.setText("");
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });

        btnList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, handmadelist.class);
                startActivity(intent);
            }
        });

    }

    public static byte[] imageViewToByte(ImageView image){
        Bitmap bitmap = ((BitmapDrawable)image.getDrawable()).getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] byteArray = stream.toByteArray();
        return  byteArray;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode == REQUEST_CODE_GALLERY) {
            if (grantResults.length > 0  && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                    startActivityForResult(intent, REQUEST_CODE_GALLERY);
            } else {
                Toast.makeText(getApplicationContext(), "You don't have permission to access file location!", Toast.LENGTH_SHORT).show();
            }
            return;
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == REQUEST_CODE_GALLERY && resultCode == RESULT_OK && data != null ){
            Uri uri = data.getData();
            try {
                InputStream inputStream = getContentResolver().openInputStream(uri);

                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                imageView.setImageBitmap(bitmap);
            }catch (FileNotFoundException e){
                e.printStackTrace();
            }
        }


        super.onActivityResult(requestCode, resultCode, data);
    }

    public void init(){
        editname = (EditText) findViewById(R.id.Name);
        editprice = (EditText) findViewById(R.id.total1);
        editketerangan = (EditText) findViewById(R.id.keterangan);
        edirkualitas = (EditText) findViewById(R.id.kualitas);
        btnChoose = (Button) findViewById(R.id.menu1);
        btnAdd = (Button) findViewById(R.id.add);
        btnList = (Button) findViewById(R.id.list);
        imageView = (ImageView) findViewById(R.id.imageView);

    }
}