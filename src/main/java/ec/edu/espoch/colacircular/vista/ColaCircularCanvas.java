/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espoch.colacircular.vista;
import ec.edu.espoch.colacircular.model.Cliente;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.util.ArrayList;
import java.util.List;

public class ColaCircularCanvas extends Canvas {

    private List<Cliente> clientes = new ArrayList<>();
    private final double margin = 24;
    private final double nodeW = 160;
    private final double nodeH = 60;
    private final double spacing = 40;
    private final double baseY = 120;

    public ColaCircularCanvas() {
        setWidth(800);
        setHeight(250);
    }

    public void setClientes(List<Cliente> clientes) {
        this.clientes = (clientes != null) ? clientes : new ArrayList<>();
    }

    public void render() {
        int n = clientes.size();
        double neededW = (n == 0) ? 800 : margin * 2 + n * nodeW + (n - 1) * spacing + 100;
        setWidth(Math.max(800, neededW));
        setHeight(250);

        GraphicsContext g = getGraphicsContext2D();

        // Fondo
        g.setFill(Color.WHITE);
        g.fillRect(0, 0, getWidth(), getHeight());

        g.setFill(Color.BLACK);
        g.setFont(Font.font(16));
        g.fillText("Cola Circular de Clientes", margin, 30);

        if (n == 0) {
            g.setFill(Color.GRAY);
            g.setFont(Font.font(14));
            g.fillText("No hay clientes en espera.", margin, baseY);
            return;
        }

        // Dibujo de nodos
        double x = margin;
        for (int i = 0; i < n; i++) {
            Cliente c = clientes.get(i);
            drawNode(g, x, baseY, c.getNombre(), c.getCelular());

            double midY = baseY + nodeH / 2;
            double x2 = x + nodeW + spacing;

            // Flechas hacia el siguiente
            g.setStroke(Color.DARKGRAY);
            g.setLineWidth(2);
            g.strokeLine(x + nodeW, midY, x2, midY);
            arrowHead(g, x + nodeW, midY, x2, midY);

            // Último nodo apunta al primero
            if (i == n - 1) {
                g.setStroke(Color.RED);
                g.strokeLine(x + nodeW, midY, margin + nodeW / 2, midY - 50);
                arrowHead(g, margin + nodeW / 2, midY - 50, x + nodeW, midY);
            }

            x += nodeW + spacing;
        }
    }

    private void drawNode(GraphicsContext g, double x, double y, String nombre, String celular) {
        g.setFill(Color.LIGHTBLUE);
        g.fillRoundRect(x, y, nodeW, nodeH, 15, 15);

        g.setStroke(Color.BLACK);
        g.setLineWidth(2);
        g.strokeRoundRect(x, y, nodeW, nodeH, 15, 15);

        g.setFill(Color.BLACK);
        g.setFont(Font.font(12));
        g.fillText("Nombre: " + nombre, x + 8, y + 22);
        g.fillText("Celular: " + celular, x + 8, y + 42);
    }

    private void arrowHead(GraphicsContext g, double x1, double y1, double x2, double y2) {
        double angle = Math.atan2(y2 - y1, x2 - x1);
        double len = 10;
        double a1 = angle - Math.PI / 8;
        double a2 = angle + Math.PI / 8;
        double ax1 = x2 - len * Math.cos(a1);
        double ay1 = y2 - len * Math.sin(a1);
        double ax2 = x2 - len * Math.cos(a2);
        double ay2 = y2 - len * Math.sin(a2);
        g.strokeLine(x2, y2, ax1, ay1);
        g.strokeLine(x2, y2, ax2, ay2);
    }
}