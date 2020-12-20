package com.example.umdestoquex.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.umdestoquex.tarefas.CallLogin;
import com.example.umdestoquex.R;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView cadastro = (TextView) findViewById(R.id.txtCadastro);
        TextView relatar = (TextView) findViewById(R.id.txtRelatar);
        TextView sobre = (TextView) findViewById(R.id.txtSobre);
        Button botao = (Button) findViewById(R.id.btnEntrar);
        EditText login = (EditText) findViewById(R.id.txtLogin);
        EditText senha = (EditText) findViewById(R.id.txtSenha2);

        botao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final ExecutorService servico = Executors.newFixedThreadPool(100);

                String dados[] = new String[2];
                dados[0] = login.getText().toString();
                dados[1] = senha.getText().toString();

                CallLogin t = new CallLogin(dados);
                Future<Boolean> futuro = servico.submit(t);

                while (!futuro.isDone()){
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                String nome = login.getText().toString();

                boolean resposta = false;
                try{
                    resposta = futuro.get();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }


                if(resposta == true){
                    Intent intent = new Intent(MainActivity.this,
                            PedidoActive.class);
                    Bundle parametro = new Bundle();
                    parametro.putString("parametro", nome);

                    intent.putExtras(parametro);
                    startActivity(intent);

                }
                else{
                    Toast.makeText(getApplicationContext(), "Login ou senha incorreta"
                     , Toast.LENGTH_LONG).show();
                }
            }
        });

        cadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), CadastroActivity.class);
                startActivity(intent);
            }
        });

        relatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Problema.class);
                startActivity(intent);
            }
        });

        sobre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Sobre.class);
                startActivity(intent);
            }
        });
    }
}