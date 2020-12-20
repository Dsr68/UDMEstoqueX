package com.example.umdestoquex.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.umdestoquex.R;
import com.example.umdestoquex.tarefas.ThreadProblema;

public class Problema extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_problema);

        EditText msg = (EditText) findViewById(R.id.txtProblema);
        Button enviar = (Button) findViewById(R.id.btnProblema);
        Button cancelar = (Button) findViewById(R.id.btnCancelar);

        enviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ThreadProblema problema = new ThreadProblema(msg.getText().toString());
                problema.start();

                Toast.makeText(getApplicationContext(), "Mensagem enviada", Toast.LENGTH_LONG).show();
            }
        });

        cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}