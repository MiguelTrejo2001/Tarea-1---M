package com.example.proyecto_dam_tarea01;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SecondActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        TextView tvAlumno = findViewById(R.id.tvAlumno);
        TextView tvEscuela = findViewById(R.id.tvEscuela);
        TextView tvCarrera = findViewById(R.id.tvCarrera);
        TextView tvGastosAdicionales = findViewById(R.id.tvGastosAdicionalesLabel);
        TextView tvMontoGastosAdicionales = findViewById(R.id.tvMontoGastosAdicionales);
        TextView tvNumCuotas = findViewById(R.id.tvNumCuotas);
        TextView tvCostoCarrera = findViewById(R.id.tvCostoCarreraLabel);
        TextView tvPension = findViewById(R.id.tvPension);
        TextView tvGastosAdicionalesTotal = findViewById(R.id.tvGastosAdicionalesTotal);
        TextView tvTotalPagar = findViewById(R.id.tvTotalPagar);

        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("matriculaData")) {
            MatriculaData matriculaData = intent.getParcelableExtra("matriculaData");
            if (matriculaData != null) {
                tvAlumno.setText("Alumno: " + matriculaData.getAlumno());
                tvEscuela.setText("Escuela: " + matriculaData.getEscuela());
                tvCarrera.setText("Carrera: " + matriculaData.getCarrera());
                tvGastosAdicionales.setText("Gastos Adicionales: " +
                        (matriculaData.isCarnetBiblioteca() ? "Carnet Biblioteca" : "") +
                        (matriculaData.isCarnetMedioPasaje() ? ", Carnet Medio Pasaje" : ""));
                tvMontoGastosAdicionales.setText("Monto Gastos Adicionales: S/." +
                        matriculaData.getMontoGastosAdicionales());
                tvNumCuotas.setText("Número de Cuotas: " + matriculaData.getNumCuotas());
                tvCostoCarrera.setText("Costo Carrera: S/ " + matriculaData.getCostoCarrera());
                tvPension.setText("Pensión: S/ " + matriculaData.getPension());
                tvGastosAdicionalesTotal.setText("Gastos Adicionales: S/ " + matriculaData.getMontoGastosAdicionales());
                tvTotalPagar.setText("Total a Pagar: S/ " + matriculaData.getTotalPagar());
            }
        }
        Button btnVolver = findViewById(R.id.btnVolver);
        btnVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); // Cierra la actividad actual
            }
        });
    }
}