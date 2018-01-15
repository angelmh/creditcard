package com.jrzdeveloper.creditcard;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements View.OnFocusChangeListener, View.OnClickListener{
    public static final String CLIENT_KEY = "client_key";
    public static final String CARD_KEY ="card_key";
    EditText nombre,apellido,calleynumero,ciudad,estado,codigopostal,codigo,anio,mes,numero;
    Button btn;
    Intent detailActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn =  (Button)findViewById(R.id.guardar_button);
        btn.setOnClickListener(this);

        nombre = (EditText)findViewById(R.id.name_edit);
        apellido = (EditText)findViewById(R.id.lastname_edit);
        calleynumero = (EditText)findViewById(R.id.street_number_edit);
        ciudad = (EditText)findViewById(R.id.city_edit);
        estado = (EditText)findViewById(R.id.state_edit);

        codigopostal = (EditText)findViewById(R.id.code_zip_edit);
        numero = (EditText)findViewById(R.id.tarjet_number_edit);
        mes = (EditText)findViewById(R.id.month_tarjet_edit);
        anio = (EditText)findViewById(R.id.year_tarjet_edit);
        codigo = (EditText)findViewById(R.id.code_tarjet_edit);

        codigopostal.setOnFocusChangeListener(this);
        numero.setOnFocusChangeListener(this);
        mes.setOnFocusChangeListener(this);
        anio.setOnFocusChangeListener(this);
        codigo.setOnFocusChangeListener(this);
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        int Id = v.getId();
        switch (Id)
        {
            case R.id.code_tarjet_edit:
                validarMin(hasFocus,3,codigo);
                break;
            case R.id.year_tarjet_edit:
                validarMin(hasFocus,2,anio);
                break;
            case R.id.month_tarjet_edit:
                validarMin(hasFocus,2,mes);
                break;
            case R.id.tarjet_number_edit:
                validarMin(hasFocus,16,numero);
                break;
            case R.id.code_zip_edit:
                validarMin(hasFocus,5,codigopostal);
                break;
            default:
                break;
        }
    }

    public void validarMin(boolean hasFocus, int valor, EditText campo)
    {

        if (hasFocus)
        {
            if (campo.getText().toString().trim().length() == valor)
            {
               campo.setError(null);
            }
            else
            {
               campo.setError("Completar");
            }

        }
        else
        {
            if (campo.getText().toString().trim().length() == valor)
            {
               campo.setError(null);
            }
            else
            {
                campo.setError("Completar");
            }

        }
    }

    @Override
    public void onClick(View v)
    {
        detailActivity = new Intent(MainActivity.this, DetailActivity.class);
        int buttonId = v.getId();
        switch (buttonId)
        {
            case R.id.guardar_button:
                guardarDatos(detailActivity);
                break;
            default:
                break;
        }
    }

    public void guardarDatos(Intent detailActivity)
    {
        List<String> list = new ArrayList<String>();//Lista para validar los campos que no esten vacios
        list.add(nombre.getText().toString());
        list.add(apellido.getText().toString());
        list.add(calleynumero.getText().toString());
        list.add(ciudad.getText().toString());
        list.add(estado.getText().toString());
        list.add(codigopostal.getText().toString());
        list.add(numero.getText().toString());//Numero Tarjeta
        list.add(mes.getText().toString());
        list.add(anio.getText().toString());
        list.add(codigo.getText().toString());
        boolean var1= validarCamposVacios(list);

        List<String> list2 = new ArrayList<String>();
        list2.add(nombre.getText().toString());
        list2.add(apellido.getText().toString());
        list2.add(calleynumero.getText().toString());
        list2.add(ciudad.getText().toString());
        list2.add(estado.getText().toString());
        boolean var2= validarMenorTamanio(list2);//validar que no sean menor a 3 caracteres.

        Map<Integer, String> map2 = new HashMap<Integer,String>();
        map2.put(5, codigopostal.getText().toString());
        map2.put(16, numero.getText().toString());//Numero Tarjeta
        map2.put(2, mes.getText().toString());
        map2.put(2, anio.getText().toString());
        map2.put(3, codigo.getText().toString());
        boolean var3 = validarTamanioIgual(map2);//validar que sean igual a n numeros de caracteres.

        if(var1==true)
        {
            if( (var2==true) && (var3==true) )
            {
                try{

                    final PersonaCliente cliente = new PersonaCliente(
                            nombre.getText().toString(),
                            apellido.getText().toString(),
                            calleynumero.getText().toString(),
                            ciudad.getText().toString(),
                            estado.getText().toString(),
                            Integer.parseInt(codigopostal.getText().toString())
                    );
                    final Card card = new Card(Long.parseLong(numero.getText().toString()),
                            Integer.parseInt(mes.getText().toString()),
                            Integer.parseInt(anio.getText().toString()),
                            Integer.parseInt(codigo.getText().toString())
                    );

                    detailActivity.putExtra(CLIENT_KEY,cliente);
                    detailActivity.putExtra(CARD_KEY,card);
                    startActivity(detailActivity);
                }
                catch(NumberFormatException e)
                {
                    Toast.makeText(MainActivity.this,"Por favor agregue los campos correctamente.",Toast.LENGTH_SHORT).show();
                }
            }
            else
            {
                Toast.makeText(MainActivity.this,"Por favor rellene los campos correctamente.",Toast.LENGTH_SHORT).show();
            }
        }
        else
        {
            Toast.makeText(MainActivity.this,"Por favor no deje ningun campo vacio ",Toast.LENGTH_SHORT).show();
        }
    }

    public boolean validarCamposVacios( List<String> datos)
    {
        for (String dato:datos) {
            if (dato.trim().length() ==0 )
            {
                return false;
            }

        }
        return true;
    }

    public boolean validarMenorTamanio( List<String> datos)
    {
        for (String dato:datos) {
            if (dato.trim().length() < 3)
                return false;
        }
        return true;
    }

    public boolean validarTamanioIgual(Map<Integer, String> map)
    {
        for (Map.Entry<Integer, String> dato:map.entrySet()) {
            if (dato.getValue().trim().length() != dato.getKey())
                return false;
        }
        return true;
    }

}
