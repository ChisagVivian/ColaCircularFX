/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espoch.colacircular.model;

/**
 *
 * @author Usuario
 */
public class ColaCircular {
    
    public Nodo primeraLlamada;
    public Nodo ultimaLlamada;
    public int contador;
    public int tamanioCola = 10;
    // private Nodo[] clientes = new Nodo[tamanio];

    public ColaCircular() {
        this.primeraLlamada = null;
        this.ultimaLlamada = null;
        this.contador = 0;
        this.tamanioCola = tamanioCola;
        
        // this.tamanio = tamanio;
    }

    public boolean encolar(Cliente cliente) {
        if (isFull()) return false;
        
        Nodo nuevoCliente = new Nodo(cliente);
        boolean respuesta = false;
        
        if (isEmpty()) {
                primeraLlamada = nuevoCliente;
                ultimaLlamada = nuevoCliente;
                nuevoCliente.setClienteSiguiente(nuevoCliente);
                respuesta = true;
            } else {
                nuevoCliente.setClienteSiguiente(primeraLlamada);
                ultimaLlamada.setClienteSiguiente(nuevoCliente);
                ultimaLlamada = nuevoCliente;
                respuesta = true;
        }
        contador++;
        return respuesta;
        
    }

    public boolean desencolar() {
        boolean respuesta = false;
        if (isEmpty()) {
            return respuesta;
        } else {
            primeraLlamada = primeraLlamada.getClienteSiguiente();
            ultimaLlamada.setClienteSiguiente(primeraLlamada);
            contador--;
            return respuesta;
        }
        
    }

    // LÓGICA TERMINADA
    public boolean isEmpty() {
        return primeraLlamada == null;
    }

    public boolean isFull() {
        if(contador == tamanioCola){
            System.out.println("El servivio esta lleno, espere por favor.");  
            return true;
        }
        return false;
    }

    public Cliente peek() {
        if(isEmpty()){
            System.out.println("No hay llamadas en espera");
           return null; 
        }
        return primeraLlamada.getCliente();
    }

    public void mostrar() {
        Nodo actual = primeraLlamada;
        do {
            
            System.out.print("\nNombre: " + actual.getCliente().getNombre());
            System.out.println("\nCelular: " + actual.getCliente().getCelular());
            actual = actual.getClienteSiguiente();
        }while (actual != primeraLlamada);
        System.out.println();
    }
           
}
