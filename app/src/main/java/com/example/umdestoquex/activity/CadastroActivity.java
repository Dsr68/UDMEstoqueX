package com.example.umdestoquex.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.umdestoquex.modelbean.ClienteCadastro;
import com.example.umdestoquex.R;
import com.example.umdestoquex.tarefas.ThreadCadastro;

public class CadastroActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        Button botao = (Button) findViewById(R.id.btn);
        TextView nome = (TextView) findViewById(R.id.txtNome);
        TextView sobrenome = (TextView) findViewById(R.id.txtSobrenome);
        TextView rg = (TextView) findViewById(R.id.txtRG);
        TextView senha = (TextView) findViewById(R.id.txtSenha);
        EditText loja = (EditText) findViewById(R.id.txtLoja);

        botao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ClienteCadastro c = new ClienteCadastro();
                c.setNome(nome.getText().toString());
                c.setSobrenome(sobrenome.getText().toString());
                c.setRg(rg.getText().toString());
                c.setSenha(senha.getText().toString());
                c.setLoja(loja.getText().toString());

               if((nome.getText().toString().equals("")) || (sobrenome.getText().toString().equals(""))
               || (rg.getText().toString().equals("")) || (senha.getText().toString().equals("")) ||
                        (loja.getText().toString().equals(""))){

                   Toast.makeText(getApplicationContext(), "Pelo menos um dos campos está " +
                           "vazio", Toast.LENGTH_LONG).show();

                }
               else{
                   ThreadCadastro cadastro = new ThreadCadastro(c);
                   Thread threadcadastro = new Thread(cadastro);
                   threadcadastro.start();

                   Toast.makeText(getApplicationContext(), "Cadastro realizado com sucesso",
                           Toast.LENGTH_LONG).show();
               }
            }
        });
    }
}