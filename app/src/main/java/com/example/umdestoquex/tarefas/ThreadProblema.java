package com.example.umdestoquex.tarefas;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ThreadProblema extends Thread{
    private String msg;

    public ThreadProblema(String msg){
        this.msg = msg;
    }

    @Override
    public void run() {
        try {
            Socket cliente  = new Socket("192.168.0.4", 12339);
            ObjectOutputStream saida = new ObjectOutputStream(cliente.getOutputStream());
            saida.flush();
            saida.writeObject(this.msg);

            saida.close();
            cliente.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
