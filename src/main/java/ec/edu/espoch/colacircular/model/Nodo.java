/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espoch.colacircular.model;

/**
 *
 * @author Usuario
 */
public class Nodo {

    public Cliente cliente;
    public Nodo clienteSiguiente;

    public Nodo(Cliente cliente) {
        this.cliente = cliente;
        this.clienteSiguiente = null;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public Nodo getClienteSiguiente() {
        return clienteSiguiente;
    }

    public void setClienteSiguiente(Nodo clienteSiguiente) {
        this.clienteSiguiente = clienteSiguiente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }    
}
