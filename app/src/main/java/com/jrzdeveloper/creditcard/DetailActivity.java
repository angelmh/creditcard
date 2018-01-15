package com.jrzdeveloper.creditcard;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Bundle extras = getIntent().getExtras();

        PersonaCliente client = extras.getParcelable(MainActivity.CLIENT_KEY);
        Card card = extras.getParcelable(MainActivity.CARD_KEY);


        TextView nombre = (TextView)findViewById(R.id.nombreText);
        TextView numeroTargeta = (TextView)findViewById(R.id.number_target_Text);
        TextView mesAnio = (TextView)findViewById(R.id.month_year_target_Text);

        if (client!=null && card!=null)
        {
            nombre.setText(client.getNombre() +" "+client.getApellido() );
            numeroTargeta.setText(""+ card.getNumero() );
            mesAnio.setText(""+card.getMes()+"/"+card.getAnio());
        }


    }
}
