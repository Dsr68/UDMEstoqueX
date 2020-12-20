package com.example.umdestoquex.tarefas;

import com.example.umdestoquex.modelbean.ClienteCadastro;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Serializar {
    public static void salvaCadastro(ClienteCadastro dados) throws IOException {
        Socket cliente = new Socket("192.168.0.5", 12345);
        ObjectOutputStream saida = new ObjectOutputStream(cliente.getOutputStream());

        String armazenado [] = new String[4];
        armazenado[0] = dados.getNome();
        armazenado[1] = dados.getSobrenome();
        armazenado[2] = dados.getRg();
        armazenado[3] = dados.getSenha();

        saida.flush();
        saida.writeObject(armazenado);

        saida.close();
        cliente.close();
    }

    public boolean fazerLogin(String[] dados) throws IOException {
        Socket cliente = new Socket("192.168.0.5", 12344);
        ObjectOutputStream saida = new ObjectOutputStream(cliente.getOutputStream());
        ObjectInputStream entrada = new ObjectInputStream(cliente.getInputStream());

        saida.flush();
        saida.writeObject(dados);
        boolean resposta = false;
        resposta = entrada.readBoolean();

        saida.close();
        entrada.close();
        cliente.close();

        return resposta;
    }

}
