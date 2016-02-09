package com.example.felixarpa.proyectojedi.UserSetUp;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.felixarpa.proyectojedi.DB.IntentsOpenHelper;
import com.example.felixarpa.proyectojedi.Fragments.PagerHolder;
import com.example.felixarpa.proyectojedi.R;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class EditProfile extends AppCompatActivity {

    Button done, delete;
    String u;
    EditText name, address, password, confirmPassword;
    TextView differentPasswords;
    FloatingActionButton floatingActionButton, gps;
    View.OnClickListener listener;
    ImageView imageView;
    List<Address> list = null;
    LocationManager locationManager;
    LocationListener locationListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.editprofile_layout);

        setListenerEdit();
        setViewsBecame();
    }

    private void setListenerEdit() {
        final IntentsOpenHelper intentsOpenHelper = new IntentsOpenHelper(getApplicationContext());

        listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.fab:

                        Intent getImage = new Intent(Intent.ACTION_GET_CONTENT);
                        getImage.setType("image/*");
                        Intent pickWay = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                        pickWay.setType("image/*");
                        Intent chooserIntent = Intent.createChooser(getImage, "Select Image");
                        chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, new Intent[]{pickWay});
                        startActivityForResult(chooserIntent, 1);
                        break;

                    case R.id.gps:

                        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
                        locationListener = new LocationListener() {
                            @Override
                            public void onLocationChanged(Location location) {
                                Geocoder geocoder = new Geocoder(getApplicationContext());
                                try {
                                    list = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 3);
                                    for (int i = 0; i < list.size(); ++i) {
                                        Log.v("LOG", list.get(i).getAddressLine(0));
                                        if (i == 0) address.setText("");
                                        address.setText(address.getText() + ", " + list.get(i).getAddressLine(0));
                                    }
                                    String aux = address.getText().toString();
                                    address.setText(aux.substring(2));
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }

                            @Override
                            public void onStatusChanged(String provider, int status, Bundle extras) {
                            }

                            @Override
                            public void onProviderEnabled(String provider) {

                            }

                            @Override
                            public void onProviderDisabled(String provider) {

                            }
                        };

                        if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION)
                                != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getApplicationContext(),
                                Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                            return;
                        }
                        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, locationListener);
                        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);


                        break;

                    case R.id.doneBecame:
                        if (password.getText().toString().equals(confirmPassword.getText().toString())) {

                            String n = name.getText().toString();
                            String p = password.getText().toString();
                            String a = address.getText().toString();

                            // Para la base de datos
                            if (a.length() > 3) intentsOpenHelper.updateAddress(u, a);
                            if (p.length() > 3) intentsOpenHelper.updatePassword(u, p);
                            if (n.length() > 3) intentsOpenHelper.updateName(u, n);


                            Intent intent = new Intent(getApplicationContext(), PagerHolder.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(intent);
                            finish();

                        }
                        else {
                            differentPasswords.setText("Different passwords");
                        }
                        break;

                    case R.id.deleteUser:

                        AlertDialog.Builder builder = new AlertDialog.Builder(EditProfile.this);
                        builder.setMessage("Are you sure you want to stop being a Slot?");
                        builder.setTitle("Delete Sloth");
                        builder.setNegativeButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int witch) {
                                SharedPreferences sharedPreferences = getSharedPreferences("SPSP", Context.MODE_PRIVATE);
                                String usuario = sharedPreferences.getString("username", "nulo");
                                intentsOpenHelper.deleteUser(usuario);

                                SharedPreferences.Editor editor = getSharedPreferences("SPSP", 0).edit();
                                editor.putBoolean("isLogedIn", false);
                                editor.apply();

                                deleteFile(u+"profile");

                                startActivity(new Intent(getApplicationContext(), LogIn.class));
                                finish();
                            }
                        });
                        builder.setPositiveButton("Still being a sloth!", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int witch) {
                            }
                        });
                        AlertDialog alertDialog = builder.create();
                        alertDialog.show();
                }
            }
        };

    }

    private void setViewsBecame() {

        final IntentsOpenHelper intentsOpenHelper = new IntentsOpenHelper(this);

        imageView = (ImageView) findViewById(R.id.imageProfileEdit);

        differentPasswords = (TextView) findViewById(R.id.equalPassword);
        name = (EditText) findViewById(R.id.setName);
        address = (EditText) findViewById(R.id.setAddress);
        password = (EditText) findViewById(R.id.setPassword);
        confirmPassword = (EditText) findViewById(R.id.confirmPassword);

        done = (Button) findViewById(R.id.doneBecame);
        delete = (Button) findViewById(R.id.deleteUser);
        floatingActionButton = (FloatingActionButton) findViewById(R.id.fab);
        gps = (FloatingActionButton) findViewById(R.id.gps);

        gps.setOnClickListener(listener);
        done.setOnClickListener(listener);
        floatingActionButton.setOnClickListener(listener);
        delete.setOnClickListener(listener);

        SharedPreferences sharedPreferences = getSharedPreferences("SPSP", Context.MODE_PRIVATE);
        u = sharedPreferences.getString("username", "nulo");

        // Imagen perfil
        Cursor cursor = intentsOpenHelper.getUsr(u);
        if (cursor.moveToFirst()) {
            int teimg = cursor.getInt(cursor.getColumnIndex("teImatge"));
            if (teimg == 1) {
                try {
                    FileInputStream fis = this.openFileInput(u + "profile");
                    Bitmap bitmap = BitmapFactory.decodeStream(fis);
                    fis.close();
                    imageView.setImageBitmap(bitmap);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        else {
            imageView.setImageResource(R.drawable.sloth_signin);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        final IntentsOpenHelper intentsOpenHelper = new IntentsOpenHelper(getApplicationContext());

        if(resultCode == RESULT_OK){
            if(requestCode == 1){
                data.getData();
                Uri selectedImage = data.getData();
                imageView.setImageURI(selectedImage);
                if (imageView.getDrawable() == null) imageView.setImageResource(R.drawable.sloth_signin);
                else {
                    // Copia imatge al Internal Storage
                    Bitmap bitmap = ((BitmapDrawable)imageView.getDrawable()).getBitmap();
                    FileOutputStream fos;
                    try {
                        fos = openFileOutput(u+"profile",Context.MODE_PRIVATE);
                        bitmap.compress(Bitmap.CompressFormat.PNG, 50,fos);
                        fos.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    // Base dades
                    intentsOpenHelper.updateImageSi(u);

                }
            }
        }
    }
}
