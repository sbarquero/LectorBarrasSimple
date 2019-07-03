package com.sbarquero.lector;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Button;
import android.widget.Toast;
import android.content.*;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView txtResultado;
    private Button btnEscanear;
    private Button btnSalir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // enlazar controles
        txtResultado = (TextView) findViewById(R.id.txtResultado);
        btnEscanear = (Button) findViewById(R.id.btnEscanear);
        btnSalir = (Button) findViewById(R.id.btnSalir);
        btnSalir.setOnClickListener(this);
        btnEscanear.setOnClickListener(this);

        txtResultado.setText("");
    }

    @Override
    public void onClick(View v) {

        switch (v.getId())
        {
            case R.id.btnEscanear:
                new IntentIntegrator(this).initiateScan(); // iniciar el escaneo
                break;
            case R.id.btnSalir:
                finish();
                break;
        }
    }

    @Override
    protected final void onActivityResult(final int requestCode, final int resultCode,  final Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null) {
            if (result.getContents() == null) {
                Toast.makeText(this, "Lectura cancelada", Toast.LENGTH_LONG).show();
            } else {
                txtResultado.setText("El código leído es:\r\n\n" + result.getContents());
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

}
