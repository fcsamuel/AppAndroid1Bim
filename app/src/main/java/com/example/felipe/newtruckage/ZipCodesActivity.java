package com.example.felipe.newtruckage;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;

import com.example.felipe.newtruckage.Model.City;
import com.example.felipe.newtruckage.Model.State;

import java.util.ArrayList;
import java.util.List;

public class ZipCodesActivity extends AppCompatActivity {

    private Spinner spState, spCity;
    private Button btSearchZipCode;
    private State selectedState;
    private City selectedCity;
    private ListView lvZipCode;
    private ArrayAdapter<String> zipCodeAdapter;
    private static List<State> states = new ArrayList<>();
    private static List<City> cities = new ArrayList<>();
    private static List<String> zipCodes = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zip_codes);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        loadData();
        loadComponentes();
        loadEvents();
        spCity.setEnabled(false);
    }

    private void loadComponentes() {
        spState = findViewById(R.id.spState);
        spCity = findViewById(R.id.spCity);
        btSearchZipCode = findViewById(R.id.btSearchZipCode);
        lvZipCode = findViewById(R.id.lvZipCode);
    }

    private void loadEvents() {
        ArrayAdapter<State> stateAdapter = new ArrayAdapter<>(ZipCodesActivity.this,
                android.R.layout.simple_spinner_dropdown_item, states);
        stateAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spState.setAdapter(stateAdapter);
        spState.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedState = (State) parent.getItemAtPosition(position);
                cities = selectedState.getCities();
                ArrayAdapter<City> cityAdapter = new ArrayAdapter<>(ZipCodesActivity.this,
                        android.R.layout.simple_spinner_dropdown_item, cities);
                cityAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
                spCity.setAdapter(cityAdapter);
                spCity.setEnabled(true);
                spCity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        selectedCity = (City) parent.getItemAtPosition(position);
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        btSearchZipCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                zipCodes = selectedCity.getZipCodes();
                zipCodeAdapter = new ArrayAdapter<>(ZipCodesActivity.this,
                        R.layout.item_custom_list, zipCodes);
                lvZipCode.setAdapter(zipCodeAdapter);
            }
        });
        lvZipCode.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String cep = zipCodeAdapter.getItem(position);
                Intent output = new Intent();
                output.putExtra("SELECTED_ZIP_CODE", cep);
                output.putExtra("SELECTED_STATE", selectedState);
                output.putExtra("SELECTED_CITY", selectedCity);
                setResult(RESULT_OK, output);
                finish();
            }
        });
    }

    private void loadStates() {
        states = new ArrayList<>();
        State s = new State(1, "Paraná", "PR");
        states.add(s);
        s = new State(2, "Santa Catarina", "SC");
        states.add(s);
        s = new State(3, "Rio Grande do Sul", "RS");
        states.add(s);
        s = new State(4, "São Paulo", "SP");
        states.add(s);
        s = new State(5, "Rio de Janeiro", "RJ");
        states.add(s);
        s = new State(6, "Espírito Santo", "ES");
        states.add(s);
    }

    private void loadCities() {
        City c = new City(1, "Toledo", states.get(0));
        states.get(0).addCity(c);
        c = new City(2, "Cascavel", states.get(0));
        states.get(0).addCity(c);
        c = new City(3, "Curitiba", states.get(0));
        states.get(0).addCity(c);
    }


    private void loadCeps() {
        states.get(0).getCity(0).addCep("85905-620");
        states.get(0).getCity(0).addCep("85950-050");
        states.get(0).getCity(0).addCep("85620-020");
        states.get(0).getCity(0).addCep("85905-410");
        states.get(0).getCity(0).addCep("85910-850");
    }

    private void loadData() {
        loadStates();
        loadCities();
        loadCeps();
    }

}
