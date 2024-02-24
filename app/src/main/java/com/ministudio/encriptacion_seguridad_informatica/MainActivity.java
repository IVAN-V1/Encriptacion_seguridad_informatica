package com.ministudio.encriptacion_seguridad_informatica;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputLayout;
import com.ministudio.encriptacion_seguridad_informatica.Clases.UTF_8_encriptacion;
import com.ministudio.encriptacion_seguridad_informatica.Clases.cifrado;

import java.nio.charset.StandardCharsets;


public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



          AutoCompleteTextView Remolques = findViewById(R.id.Auto_completext);
          // Define una lista de elementos de autocompletar
                  String[] Remolques_ = {

                          "MD5","SHA 256","UTF-8","Cifrado Cesar","Sustitucion simple"
                  };
          // Crea un adaptador ArrayAdapter y configúralo con la lista de elementos
                  ArrayAdapter<String> adapter7 = new ArrayAdapter<>(this, android.R.layout.simple_list_item_activated_1, Remolques_);
          // Asigna el adaptador al AutoCompleteTextView
                  Remolques.setAdapter(adapter7);


        MaterialButton button_cifrar = findViewById(R.id.BTN_cifrar);

        button_cifrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AutoCompleteTextView tipo_encriptacion = findViewById(R.id.Auto_completext);
                String tipo_encrip = tipo_encriptacion.getText().toString();


                EditText texto_p_cifrar=findViewById(R.id.editext_texto_input);

                String texto= texto_p_cifrar.getText().toString();

                EditText resultado = findViewById(R.id.resultado);

                EditText text_no_cifrado = findViewById(R.id.texto_desifrado);


                if (texto.equals("")) {

                    // Obtén una referencia al TextInputLayout
                    TextInputLayout input_text_normal = findViewById(R.id.textInputLayout_texto_normal);
                    input_text_normal.setError(getString(R.string.error));
                    // Obtén una referencia al TextInputLayout


                } else if (tipo_encrip.equals("")) {

                    TextInputLayout input_tipo_cifrado = findViewById(R.id.tipo_encriptacion);
                    input_tipo_cifrado.setError(getString(R.string.error));

                } else if (tipo_encrip.equals("MD5")) {
                    // Lógica para MD5
                } else if (tipo_encrip.equals("SHA 256")) {
                    // Lógica para SHA 256
                } else if (tipo_encrip.equals("UTF-8")) {


                    cifrado cifrado=new cifrado();
                    try {

                        resultado.setText(cifrado.cifra(texto).toString());

                        byte[] des=cifrado.cifra(texto);
                        text_no_cifrado.setText(cifrado.descifra(des));

                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }

                }
            }
        });







    }



}