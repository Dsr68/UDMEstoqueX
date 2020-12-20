package com.example.umdestoquex.tarefas;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ThreadProduto extends Thread{
    private String codigo;

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public ThreadProduto(String cod){
        this.setCodigo(cod);
    }
    @Override
    public void run() {
        Socket cliente;
        try {
            cliente = new Socket("192.168.0.4", 12342);
            ObjectOutputStream saida = new ObjectOutputStream(cliente.getOutputStream());
            saida.flush();
            saida.writeObject(this.getCodigo());

            saida.close();
            cliente.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
