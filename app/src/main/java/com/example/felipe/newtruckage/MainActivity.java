package com.example.felipe.newtruckage;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.example.felipe.newtruckage.Model.City;
import com.example.felipe.newtruckage.Model.Truckage;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TextView tvStateOrigin, tvCityOrigin, tvZipOrigin, tvStateDestiny, tvCityDestiny, tvZipDestiny;
    private ImageButton ibZipOrigin, ibZipDestiny;
    private EditText etWeight, etTotalValue;
    private Button btSalvar;
    private Truckage truckage = new Truckage();
    private List<Truckage> truckages = new ArrayList<>();
    private ListView lvTruckage;

    private final int LOV_ZIP_ORIGIN  = 1;
    private final int LOV_ZIP_DESTINY = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        loadComponents();
        loadEvents();
    }

    private void loadComponents() {
        tvZipOrigin = findViewById(R.id.tvZipOrigin);
        ibZipOrigin = findViewById(R.id.ibZipOrigin);
        tvCityOrigin = findViewById(R.id.tvCityOrigin);
        tvStateOrigin = findViewById(R.id.tvStateOrigin);
        tvZipDestiny = findViewById(R.id.tvZipDestiny);
        ibZipDestiny = findViewById(R.id.ibZipDestiny);
        tvCityDestiny = findViewById(R.id.tvCityDestiny);
        tvStateDestiny = findViewById(R.id.tvStateDestiny);
        etWeight = findViewById(R.id.etWeight);
        etTotalValue = (EditText) findViewById(R.id.etTotalValue);
        btSalvar = findViewById(R.id.btSalvar);
        lvTruckage = findViewById(R.id.lvTruckage);
    }

    private void loadEvents() {
        ibZipOrigin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ZipCodesActivity.class);
                startActivityForResult(intent, LOV_ZIP_ORIGIN);
            }
        });
        ibZipDestiny.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ZipCodesActivity.class);
                startActivityForResult(intent, LOV_ZIP_DESTINY);
            }
        });
        btSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                truckage.setTotalValue(Double.valueOf(etTotalValue.getText().toString()));
                truckage.setWeight(Double.valueOf(etWeight.getText().toString()));
                truckages.add(truckage);
                truckage = new Truckage();
                cleanFields();
                loadTruckagesListView();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == LOV_ZIP_ORIGIN) {
            truckage.setOrigin((City) data.getExtras().get("SELECTED_CITY"));
            truckage.setOriginZip((String) data.getExtras().get("SELECTED_ZIP_CODE"));
            tvZipOrigin.setText(truckage.getOriginZip());
            tvStateOrigin.setText(truckage.getOrigin().getState().getInitials());
            tvCityOrigin.setText(String.valueOf(truckage.getOrigin()));
        } else if (resultCode == RESULT_OK && requestCode == LOV_ZIP_DESTINY) {
            truckage.setDestiny((City) data.getExtras().get("SELECTED_CITY"));
            truckage.setDestinyZip((String) data.getExtras().get("SELECTED_ZIP_CODE"));
            tvZipDestiny.setText(truckage.getDestinyZip());
            tvStateDestiny.setText(String.valueOf(truckage.getDestiny().getState().getInitials()));
            tvCityDestiny.setText(String.valueOf(truckage.getDestiny()));
        }
    }

    private void loadTruckagesListView() {
        ArrayAdapter<Truckage> truckageAdapter = new ArrayAdapter<>(MainActivity.this,
                R.layout.item_custom_list, truckages);
        lvTruckage.setAdapter(truckageAdapter);
    }


    private void cleanFields() {
        tvZipOrigin.setText("");
        tvCityOrigin.setText("");
        tvStateOrigin.setText("");
        tvZipDestiny.setText("");
        tvCityDestiny.setText("");
        tvStateDestiny.setText("");
        etWeight.setText("");
        etTotalValue.setText("");
    }

}
