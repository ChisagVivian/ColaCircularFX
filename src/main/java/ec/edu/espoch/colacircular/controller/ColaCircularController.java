package ec.edu.espoch.colacircular.controller;

import ec.edu.espoch.colacircular.model.*;
import ec.edu.espoch.colacircular.vista.*;
import javafx.fxml.FXML;
import javafx.scene.control.Label; 
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;

import java.util.ArrayList;
import java.util.List;
public class ColaCircularController {

 @FXML
    private TextField txtNombre;

    @FXML
    private TextField txtCelular;

    @FXML
    private Label lblEstado;

    @FXML
    private StackPane canvasPane;

    private ColaCircular cola;
    private ColaCircularCanvas canvas;

    @FXML
    private void initialize() {
        cola = new ColaCircular();
        canvas = new ColaCircularCanvas();
        canvasPane.getChildren().add(canvas);

        lblEstado.setText("Cola Circular inicializada (vacía)");
        refreshView();
    }

    @FXML
    private void encolar() {
        String nombre = txtNombre.getText().trim();
        String celular = txtCelular.getText().trim();
        if (nombre.isEmpty() || celular.isEmpty()) return;

        Cliente cliente = new Cliente(nombre, celular);
        boolean ok = cola.encolar(cliente);

        lblEstado.setText(ok ? "Cliente encolado: " + nombre : "Cola llena, espere...");
        txtNombre.clear();
        txtCelular.clear();
        refreshView();
    }

    @FXML
    private void desencolar() {
        boolean ok = cola.desencolar();

        lblEstado.setText(ok ? "Cliente atendido." : "Cola vacía, nada que atender.");
        refreshView();
    }

    @FXML
    private void peek() {
        Cliente cliente = cola.peek();
        lblEstado.setText(cliente != null ? "Próximo cliente: " + cliente.getNombre() : "Cola vacía");
    }

    @FXML
    private void isEmpty() {
        lblEstado.setText(cola.isEmpty() ? "La cola está vacía" : "La cola NO está vacía");
    }

    private void refreshView() {
        List<Cliente> clientes = new ArrayList<>();
        if (!cola.isEmpty()) {
            Nodo nodo = cola.primeraLlamada;
            int cont = 0;
            do {
                clientes.add(nodo.getCliente());
                nodo = nodo.getClienteSiguiente();
                cont++;
            } while (nodo != cola.primeraLlamada && cont < cola.tamanioCola);
        }
        canvas.setClientes(clientes);
        canvas.render();

        canvasPane.setPrefWidth(canvas.getWidth());
        canvasPane.setPrefHeight(canvas.getHeight());
    }
}
