package com.example.conversorunidades;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.ArrayAdapter;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText editTextInput;
    private TextView textViewResult;
    private Spinner spinnerConversion;
    private Button buttonConvert;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Vincular las vistas
        editTextInput = findViewById(R.id.editTextInput);
        textViewResult = findViewById(R.id.textViewResult); // Asegúrate de tener el TextView con este ID en tu XML
        spinnerConversion = findViewById(R.id.spinnerConversion);
        buttonConvert = findViewById(R.id.buttonConvert);

        // Configuración del Spinner
        String[] conversionOptions = {"Temperatura", "Longitud", "Monedas"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, conversionOptions);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerConversion.setAdapter(adapter);

        // Acción cuando el botón es presionado
        buttonConvert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String inputValue = editTextInput.getText().toString();
                if (inputValue.isEmpty()) {
                    textViewResult.setText("Ingrese un valor por favor");
                } else {
                    double value = Double.parseDouble(inputValue);  // Convertir el valor de String a double
                    String conversionType = spinnerConversion.getSelectedItem().toString();  // Obtener tipo de conversión
                    double result = convertUnits(conversionType, value);  // Realizar la conversión
                    textViewResult.setText("Resultado: " + result);  // Mostrar el resultado
                }
            }
        });
    }

    // Método para realizar la conversión de unidades
    private double convertUnits(String conversionType, double value) {
        switch (conversionType) {
            case "Temperatura":
                // Ejemplo: Conversión de Celsius a Fahrenheit
                return celsiusToFahrenheit(value);
            case "Longitud":
                // Ejemplo: Conversión de metros a kilómetros
                return metersToKilometers(value);
            case "Monedas":
                // Ejemplo: Conversión de USD a EUR (esto es solo un ejemplo ficticio)
                return usdToEur(value);
            default:
                return 0;
        }
    }

    // Conversión de Celsius a Fahrenheit
    private double celsiusToFahrenheit(double celsius) {
        return (celsius * 9/5) + 32;
    }

    // Conversión de metros a kilómetros
    private double metersToKilometers(double meters) {
        return meters / 1000;
    }

    // Conversión de USD a EUR (tasa ficticia, puedes actualizar con tasas reales)
    private double usdToEur(double usd) {
        return usd * 0.85;  // Tasa de cambio ficticia: 1 USD = 0.85 EUR
    }
}
