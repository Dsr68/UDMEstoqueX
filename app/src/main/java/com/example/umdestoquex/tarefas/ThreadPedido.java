package com.example.umdestoquex.tarefas;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ThreadPedido extends Thread{
    private String nome;
    private String descricao;
    private String quantidade;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(String quantidade) {
        this.quantidade = quantidade;
    }

    public ThreadPedido(String nome, String descricao, String quantidade){
        this.setNome(nome);
        this.setDescricao(descricao);
        this.setQuantidade(quantidade);
    }

    @Override
    public void run() {
        try {
            Socket cliente = new Socket("192.168.0.4", 12340);
            String pedido[] = {this.getNome(), this.getDescricao(), this.getQuantidade()};
            ObjectOutputStream saida = new ObjectOutputStream(cliente.getOutputStream());
            saida.flush();
            saida.writeObject(pedido);

            saida.close();
            saida.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
