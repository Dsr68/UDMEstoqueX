package com.example.umdestoquex.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.umdestoquex.R;
import com.example.umdestoquex.tarefas.CallProduto;
import com.example.umdestoquex.tarefas.ThreadPedido;
import com.example.umdestoquex.tarefas.ThreadProduto;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class PedidoActive extends AppCompatActivity {
    TextView descricao;
    int cont = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pedido_active);
        
        Intent recebedora = getIntent();
        Bundle parametro = recebedora.getExtras();

        String nome = parametro.getString("parametro");

        EditText usuario = (EditText) findViewById(R.id.txtNomeLogin);
        EditText quantidade = (EditText) findViewById(R.id.txtQuantidade);
        Button mais = (Button) findViewById(R.id.btnMais);
        Button menos = (Button) findViewById(R.id.btnMenos);
        Button enviar = (Button) findViewById(R.id.btnEnviar);
        Button cancelar = (Button) findViewById(R.id.btnCancelarPedido);
        Button sair = (Button) findViewById(R.id.btnSair);
        TextView ajuda = (TextView) findViewById(R.id.txtDescricao);

        ajuda.setText("Toque no ícone a esquerda");

        usuario.setText(nome);
        quantidade.setText("0");

        final Activity atividade = this;
        ImageButton scan = (ImageButton) findViewById(R.id.btnScan);

        scan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                IntentIntegrator intente = new IntentIntegrator(atividade);
                intente.setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES);
                intente.setPrompt("Camera scan");
                intente.setCameraId(0);
                intente.initiateScan();


            }
        });

        mais.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                quantidade.setText(Integer.toString(Integer.parseInt(quantidade.getText().toString()) + cont));
            }
        });

        menos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(cont > 0){
                    quantidade.setText(Integer.toString(Integer.parseInt(quantidade.getText().toString()) - cont));
                }
            }
        });

        enviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                descricao = (TextView) findViewById(R.id.txtDescricao);
                ThreadPedido pedido = new ThreadPedido(
                        usuario.getText().toString(),
                        descricao.getText().toString(),
                        quantidade.getText().toString()
                );
               if(!descricao.equals("")){TextView ajuda = (TextView) findViewById(R.id.txtDescricao);
                   Thread threadPedido = new Thread(pedido);
                   threadPedido.start();

                   ajuda.setText("Toque no ícone a esquerda");
                   quantidade.setText("0");

                   Toast.makeText(getApplicationContext(), "Pedido enviado", Toast.LENGTH_LONG).show();
               }
            }
        });

        cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                descricao = (TextView) findViewById(R.id.txtDescricao);
                quantidade.setText("0");
                descricao.setText("Toque no ícone a esquerda");
            }
        });

        sair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode,
                data);
        final ExecutorService servico = Executors.newFixedThreadPool(100);
        CallProduto t = new CallProduto();
        Future<String> futuro = servico.submit(t);

        if(result != null){
            if(result.getContents() != null){
            String codigo = result.getContents();
                ThreadProduto produto = new ThreadProduto(codigo);
                Thread thread = new Thread(produto);
                thread.start();

                System.out.println(codigo);

                while (!futuro.isDone()){
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                try {
                    descricao = (TextView) findViewById(R.id.txtDescricao);
                    descricao.setText(futuro.get());
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            else{
                System.out.println("Scanner cancelado");
            }
        }
        else {
            super.onActivityResult(requestCode, resultCode, data);
        }

    }
}