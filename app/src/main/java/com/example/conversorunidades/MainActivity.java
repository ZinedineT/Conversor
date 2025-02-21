package com.example.conversorunidades;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.ArrayAdapter;
import android.widget.AdapterView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText editTextInput;
    private TextView textViewResult;
    private Spinner spinnerConversion;
    private Button buttonConvert;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Vincular las vistas
        editTextInput = findViewById(R.id.editTextInput);
        textViewResult = findViewById(R.id.textViewResult); // Asegúrate de tener el TextView con este ID en tu XML
        spinnerConversion = findViewById(R.id.spinnerConversion);
        buttonConvert = findViewById(R.id.buttonConvert);
        ImageView imageView = findViewById(R.id.imageView);

        // Configuración del Spinner
        String[] conversionOptions = {"Temperatura", "Longitud", "Monedas"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, conversionOptions);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerConversion.setAdapter(adapter);

        // Cambiar la imagen según el item seleccionado en el Spinner
        spinnerConversion.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                // Cambiar la imagen según la opción seleccionada
                switch (position) {
                    case 0: // Temperatura
                        imageView.setImageResource(R.drawable.temperatura); // Imagen para temperatura
                        break;
                    case 1: // Longitud
                        imageView.setImageResource(R.drawable.longitud); // Imagen para longitud
                        break;
                    case 2: // Monedas
                        imageView.setImageResource(R.drawable.monedas); // Imagen para monedas
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // Si no se selecciona nada, puedes mostrar una imagen predeterminada
                imageView.setImageResource(R.drawable.placeholder_image);
            }
        });

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
                return usdToSoles(value);
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

    // Conversión de USD a Soles
    private double usdToSoles(double usd) {
        return usd * 3.8; // 1 USD = 3.8 Soles
    }
}
