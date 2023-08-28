package com.example.proyecto_dam_tarea01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
public class MainActivity extends AppCompatActivity {

    private Spinner spinnerEscuela;
    private Spinner spinnerCarrera;
    private CheckBox chkCBiblioteca;
    private CheckBox chkCMedio;
    private RadioGroup radioGroupCuotas;
    private TextView tvPension;

    private TextView tvCostoCarrera;
    private TextView tvGastosAdicionales;
    private TextView tvTotalPagar;

    private EditText ed_Alumno;
    private Button btnCalcular;

    private double costoCarrera = 3000;
    private double porcentajeInteres = 0.12;
    private double costoCarnetBiblioteca = 25;
    private double costoCarnetMedioPasaje = 22;
    private int cuotasSeleccionadas = 0;
    private boolean carnetBibliotecaSeleccionado = false;
    private boolean carnetMedioPasajeSeleccionado = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spinnerEscuela = findViewById(R.id.spnEscuela);
        spinnerCarrera = findViewById(R.id.spnCarrera);
        chkCBiblioteca = findViewById(R.id.chkCBiblioteca);
        chkCMedio = findViewById(R.id.chkCMedio);
        radioGroupCuotas = findViewById(R.id.radioGroupCuotas);
        tvPension = findViewById(R.id.tvPension);
        tvGastosAdicionales = findViewById(R.id.tvGastosAdicionales);
        tvTotalPagar = findViewById(R.id.tvTotalPagar);
        btnCalcular = findViewById(R.id.btnCalcular);
        tvCostoCarrera = findViewById(R.id.tvCostoCarrera);


        setupSpinners();
        setupListeners();
        setupCalcularButton();

        Button btnImprimir = findViewById(R.id.btnImprimir);
        btnImprimir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EditText edAlumno = findViewById(R.id.ed_Alumno); // Obtén la referencia al EditText
                String nombreAlumno = edAlumno.getText().toString(); // Obtén el valor ingresado por el usuario
                // Crear una instancia de MatriculaData
                MatriculaData matriculaData = new MatriculaData();


                // Obtener y asignar los valores de los elementos de la interfaz de usuario
                matriculaData.setAlumno(nombreAlumno);
                matriculaData.setEscuela(spinnerEscuela.getSelectedItem().toString());
                matriculaData.setCarrera(spinnerCarrera.getSelectedItem().toString());
                matriculaData.setCarnetBiblioteca(chkCBiblioteca.isChecked());
                matriculaData.setCarnetMedioPasaje(chkCMedio.isChecked());
                matriculaData.setNumCuotas(cuotasSeleccionadas);
                matriculaData.setCostoCarrera(costoCarrera);
                matriculaData.setPension(Double.parseDouble(tvPension.getText().toString()));
                matriculaData.setMontoGastosAdicionales(Double.parseDouble(tvGastosAdicionales.getText().toString()));
                matriculaData.setTotalPagar(Double.parseDouble(tvTotalPagar.getText().toString()));

                // Calcular los gastos adicionales
                double gastosAdicionales = 0;
                if (matriculaData.isCarnetBiblioteca()) {
                    gastosAdicionales += costoCarnetBiblioteca;
                }
                if (matriculaData.isCarnetMedioPasaje()) {
                    gastosAdicionales += costoCarnetMedioPasaje;
                }
                matriculaData.setGastosAdicionales(gastosAdicionales);

                // Crear el Intent y enviar matriculaData como extra
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                intent.putExtra("matriculaData", matriculaData);
                startActivity(intent);
            }
        });
    }

    private void setupSpinners() {
        String[] schoolArray = {"Escuela", "Ciencias Empresariales", "Ciencias Humanas y Educación", "Ingeniería y Arquitectura"};
        String[] carreraArray = {"Carrera","Tecnologías de la Información", "Administración", "Ingenieria de Sistemas"};

        ArrayAdapter<String> adapterEscuela = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, schoolArray);
        adapterEscuela.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        ArrayAdapter<String> adapterCarrera = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, carreraArray);
        adapterCarrera.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinnerEscuela.setAdapter(adapterEscuela);
        spinnerCarrera.setAdapter(adapterCarrera);

        spinnerEscuela.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedItem = adapterEscuela.getItem(position);
                if (!selectedItem.equals("Escuela")) {
                    Toast.makeText(MainActivity.this, "Seleccionaste: " + selectedItem, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        spinnerCarrera.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedItem = adapterCarrera.getItem(position);
                if (!selectedItem.equals("Carrera")) {
                    Toast.makeText(MainActivity.this, "Seleccionaste: " + selectedItem, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    private void setupListeners() {
        radioGroupCuotas.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton selectedRadioButton = findViewById(checkedId);
                cuotasSeleccionadas = Integer.parseInt(selectedRadioButton.getText().toString());
            }
        });

        chkCBiblioteca.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                carnetBibliotecaSeleccionado = isChecked;
            }
        });

        chkCMedio.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                carnetMedioPasajeSeleccionado = isChecked;
            }
        });
    }


    private void setupCalcularButton() {
        btnCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calcularPensionYTotal();
            }
        });
    }



    private void calcularPensionYTotal() {
        double pension = (costoCarrera + (costoCarrera * porcentajeInteres)) / cuotasSeleccionadas;

        double gastosAdicionales = 0;
        if (carnetBibliotecaSeleccionado) {
            gastosAdicionales += costoCarnetBiblioteca;
        }
        if (carnetMedioPasajeSeleccionado) {
            gastosAdicionales += costoCarnetMedioPasaje;
        }

        double totalAPagar = pension + gastosAdicionales;

        tvCostoCarrera.setText(String.format("%.2f", costoCarrera)); // Agrega esta línea
        tvPension.setText(String.format("%.2f", pension));
        tvGastosAdicionales.setText(String.format("%.2f", gastosAdicionales));
        tvTotalPagar.setText(String.format("%.2f", totalAPagar));
    }



}