package com.example.umdestoquex.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.umdestoquex.R;

public class Sobre extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sobre);

        TextView sobre = (TextView) findViewById(R.id.txtSobreUMD);
        String texto = "" +
                "O UMDEstoque é uma aplicativo desenvolvido\n pelos Alunos: Ulisses Luiz de Oliveira" +
                " da\n Silva, Marcone Brasil Silva de Oliveira e Dalison Sergio da Rocha, que estudam " +
                "no curso de Ciência da Computação na UniFG. O objetivo foi o de criar uma aplica" +
                " tivo que solicite a reposição de produtos de uma empresa de exportação e importa"
                + " ção.";
        sobre.setText(texto);
    }
}