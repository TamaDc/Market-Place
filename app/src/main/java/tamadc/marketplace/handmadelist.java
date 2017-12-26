package tamadc.marketplace;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;

/**
 * Created by Asus A455L on 19/12/2017.
 */

public class handmadelist extends AppCompatActivity {

    GridView gridView;
    ArrayList<handmade> list;
    HandmadeListAdapter adapter = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.menu_list_activity);

        gridView = (GridView) findViewById(R.id.gridView);
        list = new ArrayList<>();
        adapter = new HandmadeListAdapter(this, R.layout.menu_item, list);
        gridView.setAdapter(adapter);

        Cursor cursor = MainActivity.sqLiteHelper.getData("SELECT * FROM HANDMADE");
        list.clear();
        while (cursor.moveToNext()){
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            String price = cursor.getString(2);
            byte[] image = cursor.getBlob(3);
            String keterangan = cursor.getString(4);
            String kualitas = cursor.getString(5);

            list.add(new handmade( name, price, image, keterangan, kualitas ,id));
        }

        adapter.notifyDataSetChanged();

        /// menu edit delete
        gridView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, final int position, long id) {

                final CharSequence[] items = {"Update", "Delete"};
                AlertDialog.Builder dialog = new AlertDialog.Builder(handmadelist.this);

                dialog.setTitle("Choose and action");
                dialog.setItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int item) {
                        if (item == 0) {


                            Cursor c = MainActivity.sqLiteHelper.getData("SELECT id FROM HANDMADE");
                                ArrayList<Integer> arrID = new ArrayList<Integer>();
                            while(c.moveToNext()){
                                arrID.add(c.getInt(0));
                            }
                            /*Toast.makeText(getApplicationContext(), "Update..", Toast.LENGTH_SHORT).show();*/
                            showDialogUpdate(handmadelist.this, arrID.get(position));
                        } else {
                            Cursor c = MainActivity.sqLiteHelper.getData("SELECT id FROM HANDMADE");
                            ArrayList<Integer> arrID = new ArrayList<Integer>();
                            while(c.moveToNext()){
                                arrID.add(c.getInt(0));
                            }
                            showDialogDelete(arrID.get(position));
                        }
                    }
                });
                dialog.show();
                return true;
            }
        });
    }


    /// update show
    ImageView image_edit;
    private void showDialogUpdate(Activity activity , final int position){
        final Dialog dialog = new Dialog(activity);
        dialog.setContentView(R.layout.update_menu_activity);
        dialog.setTitle("Update");

        image_edit = (ImageView) dialog.findViewById(R.id.image_edit);
        final EditText editnama = (EditText) dialog.findViewById(R.id.nama_edit);
        final EditText editprice = (EditText) dialog.findViewById(R.id.harga_edit);
        final EditText editketerangan = (EditText) dialog.findViewById(R.id.keterangan_edit);
        final EditText editkualitas = (EditText) dialog.findViewById(R.id.kualitas_edit);
        Button btnUpdate = (Button) dialog.findViewById(R.id.update);



        int width =(int) (activity.getResources().getDisplayMetrics().widthPixels * 0.95);

        int height =(int) (activity.getResources().getDisplayMetrics().heightPixels * 0.9);
        dialog.getWindow().setLayout(width,height);
        dialog.show();

        image_edit.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                ActivityCompat.requestPermissions(
                        handmadelist.this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        888
                );
            }
        });



        btnUpdate.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                try{
                    MainActivity.sqLiteHelper.UpdateData(
                            editnama.getText().toString().trim(),
                            editprice.getText().toString().trim(),
                            MainActivity.imageViewToByte(image_edit),
                            editketerangan.getText().toString().trim(),
                            editkualitas.getText().toString().trim(),
                            position
                    );
                    dialog.dismiss();
                    Toast.makeText(getApplicationContext(),"Update SS",Toast.LENGTH_SHORT).show();
                }catch (Exception error){
                    Log.d("Update eror : ", error.getMessage());
                }
                updatehandmalist();

            }
        });


    }

    /// show delete
    private void showDialogDelete (final int id){
        final AlertDialog.Builder dialogDelete = new AlertDialog.Builder(handmadelist.this);

        dialogDelete.setTitle("Warning!!");
        dialogDelete.setMessage("are you sorious ? ");
        dialogDelete.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                try {
                    MainActivity.sqLiteHelper.deleteData(id);
                    Toast.makeText(getApplicationContext(),"Delete Sukses!!!",Toast.LENGTH_SHORT).show();
                }catch (Exception e){
                    Log.e("error", e.getMessage());

                }
                updatehandmalist();

            }
        });

        dialogDelete.setNegativeButton("cancel ", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
           dialogDelete.show();
    }


    ///delete show
    private void updatehandmalist(){
        Cursor cursor = MainActivity.sqLiteHelper.getData("SELECT * FROM HANDMADE");
                list.clear();
        while(cursor.moveToNext()){
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            String price = cursor.getString(2);
            byte[] image = cursor.getBlob(3);
            String keterangan = cursor.getString(4);
            String kualitas = cursor.getString(5);

            list.add(new handmade(name,price,image,keterangan,kualitas,id));
        }
        adapter.notifyDataSetChanged();
    }

    //// MAINACTIVTY
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode == 888) {
            if (grantResults.length > 0  && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, 888);
            } else {
                Toast.makeText(getApplicationContext(), "You don't have permission to access file location!", Toast.LENGTH_SHORT).show();
            }
            return;
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == 888 && resultCode == RESULT_OK && data != null ){
            Uri uri = data.getData();
            try {
                InputStream inputStream = getContentResolver().openInputStream(uri);

                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                image_edit.setImageBitmap(bitmap);
            }catch (FileNotFoundException e){
                e.printStackTrace();
            }
        }


        super.onActivityResult(requestCode, resultCode, data);
    }

    public void shop(View view) {
        Intent it = new Intent(handmadelist.this,ShopActivity.class);
        startActivity(it);
    }
}
