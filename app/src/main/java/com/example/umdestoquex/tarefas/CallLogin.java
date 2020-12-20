package com.example.umdestoquex.tarefas;

import android.content.Intent;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CallLogin implements Callable<Boolean> {
    private String dados[] = new String[2];
    private boolean resposta;

    public String[] getDados() {
        return dados;
    }

    public boolean isResposta() {
        return resposta;
    }

    public CallLogin(String[] entrada) {
        this.setDados(entrada);
    }

    public void setDados(String[] dados) {
        this.dados = dados;
    }

    public void setResposta(boolean resposta) {
        this.resposta = resposta;
    }


    @Override
    public Boolean call() throws Exception {
        Socket cliente = null;
        Socket cliente2 = null;
        try {
            cliente = new Socket("192.168.0.4", 12344);
            ObjectOutputStream saida = new ObjectOutputStream(cliente.getOutputStream());

            saida.flush();
            saida.writeObject(dados);

            cliente2 = new Socket("192.168.0.4", 12343);
            ObjectInputStream entrada = new ObjectInputStream(cliente2.getInputStream());

            this.setResposta( entrada.readBoolean());

            saida.close();
            entrada.close();
            cliente.close();
            cliente2.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(this.isResposta());

        return this.isResposta();
    }
}
