package com.example.umdestoquex.tarefas;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.concurrent.Callable;

public class CallProduto implements Callable<String> {
    @Override
    public String call() throws Exception {
        Socket cliente;
        String descricao;
        try{
            cliente = new Socket("192.168.0.4", 12341);
            ObjectInputStream entrada = new ObjectInputStream(cliente.getInputStream());
           descricao = (String) entrada.readObject();
            System.out.println(descricao);
           cliente.close();
           entrada.close();
           return descricao;
        } catch (UnknownHostException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
