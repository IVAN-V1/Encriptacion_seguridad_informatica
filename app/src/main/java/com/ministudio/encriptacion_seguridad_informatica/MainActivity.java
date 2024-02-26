package com.ministudio.encriptacion_seguridad_informatica;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputLayout;
import com.ministudio.encriptacion_seguridad_informatica.Clases.CifradoCesar;
import com.ministudio.encriptacion_seguridad_informatica.Clases.UTF_8;
import com.ministudio.encriptacion_seguridad_informatica.Clases.md5;
import com.ministudio.encriptacion_seguridad_informatica.Clases.sha256;

import java.math.BigInteger;


public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        AutoCompleteTextView lista_encriptacion = findViewById(R.id.Auto_completext);
          // Define una lista de elementos de autocompletar
                  String[] Remolques_ = {

                          "MD5","SHA 256","UTF-8","Cifrado Cesar","Sustitucion simple"
                  };
          // Crea un adaptador ArrayAdapter y configúralo con la lista de elementos
                  ArrayAdapter<String> adapter7 = new ArrayAdapter<>(this, android.R.layout.simple_list_item_activated_1, Remolques_);
          // Asigna el adaptador al AutoCompleteTextView
                  lista_encriptacion.setAdapter(adapter7);


        TextInputLayout textInputLayoutPosicion = findViewById(R.id.text_input_posicion);
        textInputLayoutPosicion.setVisibility(View.GONE);


        lista_encriptacion.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedOption = (String) parent.getItemAtPosition(position);
                if (selectedOption.equals("Cifrado Cesar")) {
                    textInputLayoutPosicion.setVisibility(View.VISIBLE);
                } else {
                    textInputLayoutPosicion.setVisibility(View.GONE);
                }

            }
        });





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

                    UTF_8 UTF_8 =new UTF_8();

                    EditText etInput =  findViewById(R.id.editext_texto_input);
                    EditText tvOutput = findViewById(R.id.resultado);

                    byte [] md5Input = etInput.getText().toString().getBytes();
                    BigInteger md5Data = null;

                    try {
                        md5Data = new BigInteger(1, md5.encryptMD5(md5Input));
                        byte[] des= UTF_8.cifra(texto);
                        text_no_cifrado.setText(UTF_8.descifra(des));

                    }catch (Exception e) {
                     e.printStackTrace();
                    }


                    String md5Str = md5Data.toString(16);
                    tvOutput.setText(md5Str);



                } else if (tipo_encrip.equals("SHA 256")) {
                    // Lógica para SHA 256

                    UTF_8 UTF_8 =new UTF_8();
                    EditText etInput = findViewById(R.id.editext_texto_input);
                    EditText tvOutput = findViewById(R.id.resultado);

                    byte[] sha256Input = etInput.getText().toString().getBytes();
                    BigInteger sha256Data = null;

                    try {
                        sha256Data = new BigInteger(1, sha256.encryptSHA256(sha256Input));
                        byte[] des= UTF_8.cifra(texto);
                        text_no_cifrado.setText(UTF_8.descifra(des));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    String sha256Str = sha256Data.toString(16).toUpperCase();
                    tvOutput.setText(sha256Str);
                    // terminación




                } else if (tipo_encrip.equals("UTF-8")) {


                    UTF_8 UTF_8 =new UTF_8();
                    try {

                        resultado.setText(UTF_8.cifra(texto).toString());

                        byte[] des= UTF_8.cifra(texto);
                        text_no_cifrado.setText(UTF_8.descifra(des));

                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }

                } else if (tipo_encrip.equals("Cifrado Cesar")) {


                    EditText editText_posicion = findViewById(R.id.posicion);
                    String posicion = editText_posicion.getText().toString().trim(); // Asegúrate de quitar espacios en blanco al principio y al final

                    if (posicion.isEmpty()) {
                        // Obtén una referencia al TextInputLayout
                        TextInputLayout input_text_normal = findViewById(R.id.text_input_posicion);
                        input_text_normal.setError(getString(R.string.error));
                    } else {


                        try {
                            int change_posicion = Integer.parseInt(posicion);

                            if (change_posicion >= 1) {
                                resultado.setText(CifradoCesar.cifrar(texto, change_posicion));
                                String text_p_desifra = CifradoCesar.cifrar(texto, change_posicion);
                                text_no_cifrado.setText(CifradoCesar.descifrar(text_p_desifra, change_posicion));
                            }
                        } catch (NumberFormatException e) {
                            // Manejar la excepción si la entrada no es un número válido
                            e.printStackTrace(); // O cualquier otra acción que desees realizar en caso de excepción
                        }
                    }



                }



                }


        });







    }



}