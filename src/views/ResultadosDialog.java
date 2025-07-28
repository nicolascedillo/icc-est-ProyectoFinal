package views;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Font;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import dao.AlgorithmResultDAO;
import models.AlgorithmResult;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

public class ResultadosDialog extends JDialog {

    private final DefaultTableModel model;
    private final AlgorithmResultDAO resultadoDAO;
    private List<AlgorithmResult> resultados;

    public ResultadosDialog(JFrame frame, AlgorithmResultDAO paramAlgorithmResultDAO) {
        super(frame, "Resultados Guardados", true);
        this.resultadoDAO = paramAlgorithmResultDAO;
        setLayout(new BorderLayout());

        JPanel panelSuperior = new JPanel();
        JLabel etiqueta = new JLabel("Resultados de Algoritmos:");
        etiqueta.setHorizontalAlignment(JLabel.CENTER);
        etiqueta.setFont(new Font("Arial", Font.BOLD, 16));
        panelSuperior.add(etiqueta);
        add(panelSuperior, BorderLayout.NORTH);

        Object[] titulos = { "Algoritmo", "Celdas Caminadas", "Tiempo (ns)" };
        this.model = new DefaultTableModel(titulos, 0);
        JTable tabla = new JTable(this.model);
        JScrollPane scrollPane = new JScrollPane(tabla);
        add(scrollPane, BorderLayout.CENTER);
        cargarDatos();

        JPanel panel = new JPanel();
        JButton botonLimpiar = new JButton("Limpiar Resultados");
        botonLimpiar.addActionListener(e -> {
            int confirmacion = JOptionPane.showConfirmDialog(this, "Desea borrar los resultados?", "Confirmacion", 0);
            if (confirmacion == 0) {
                paramAlgorithmResultDAO.limpiar();
                this.model.setRowCount(0);
            }
        });

        JButton botonGraficar = new JButton("Graficar");
        botonGraficar.addActionListener(e -> mostrarGrafica());
        panel.add(botonLimpiar);
        panel.add(botonGraficar);
        add(panel, BorderLayout.SOUTH);
        setSize(500, 300);
        setLocationRelativeTo(frame);
    }

    private void cargarDatos() {
        this.resultados = this.resultadoDAO.encontrarTodos();
        for (AlgorithmResult resultados : this.resultados) {
            this.model.addRow(new Object[] { resultados
                    .getNombreDelAlgoritmo(),
                    Integer.valueOf(resultados.getLongitudDelCamino()),
                    Long.valueOf(resultados.getTiempo()) });
        }
    }

    private void mostrarGrafica() {

        if (this.resultados.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No hay datos para graficar.");
            return;
        }

        DefaultCategoryDataset defaultCategoryDataset = new DefaultCategoryDataset();
        for (AlgorithmResult resultado : this.resultados){
            defaultCategoryDataset.addValue(resultado.getTiempo(), "Tiempo",
                    resultado.getNombreDelAlgoritmo());
        }

        JFreeChart jFreeChart = ChartFactory.createLineChart("Tiempo de Ejecución", "Algoritmo",
                "Tiempo", (CategoryDataset) defaultCategoryDataset, PlotOrientation.VERTICAL, true, true, false);
        ChartPanel chartPanel = new ChartPanel(jFreeChart);
        
        JDialog jDialog = new JDialog(this, "Resultados", true);
        jDialog.setContentPane((Container) chartPanel);
        jDialog.setSize(500, 400);
        jDialog.setLocationRelativeTo(this);
        jDialog.setVisible(true);
    }
}