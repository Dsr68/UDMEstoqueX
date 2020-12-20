package com.example.umdestoquex.tarefas;

import com.example.umdestoquex.modelbean.ClienteCadastro;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ThreadCadastro extends Thread{
    public ClienteCadastro dados;

    public ThreadCadastro(ClienteCadastro cadastro){
        dados = cadastro;
    }

    @Override
    public void run() {
        Socket cliente = null;
        try {
            cliente = new Socket("192.168.0.4", 12345);
            ObjectOutputStream saida = new ObjectOutputStream(cliente.getOutputStream());

            String armazenado [] = new String[5];
            armazenado[0] = dados.getNome();
            armazenado[1] = dados.getSobrenome();
            armazenado[2] = dados.getRg();
            armazenado[3] = dados.getSenha();
            armazenado[4] = dados.getLoja();

            saida.flush();
            saida.writeObject(armazenado);

            saida.close();
            cliente.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
