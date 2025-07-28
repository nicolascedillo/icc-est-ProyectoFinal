package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import models.Cell;
import models.CellState;

public class MazeFrame extends JFrame {

    private MazePanel mazePanel;
    private final JComboBox<String> algorithmSelector;
    private final JButton solveButton;
    private final JButton pasoAPasoButton;
    private final Map<CellState, Color> colores;
    private JButton botonComienzo;
    private JButton botonFin;
    private JButton botonPared;
    private JMenuItem menuItemNuevoLab;
    private JButton botonLimpiar;
    private JMenuItem menuItemVerResultados;

    public MazeFrame(int fila, int columna) {
        setTitle("Laberinto");
        setDefaultCloseOperation(3);
        setSize(800, 700);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);

        this.mazePanel = new MazePanel(fila, columna);
        this.colores = new HashMap<>();
        this.pasoAPasoButton = new JButton("Paso a Paso");

        colores.put(CellState.FIN, new Color(245, 0, 79));
        colores.put(CellState.PARED, new Color(255, 11, 85));
        colores.put(CellState.CAMINO, new Color(255, 175, 0));
        colores.put(CellState.COMIENZO, new Color(249, 228, 0));
        colores.put(CellState.VACIO, new Color(93, 98, 106));

        add(this.mazePanel, BorderLayout.CENTER);

        JPanel panelNorte = new JPanel();
        this.botonComienzo = new JButton("Seleccionar Inicio");
        this.botonFin = new JButton("Seleccionar Fin");
        this.botonPared = new JButton("Colocar Pared");

        panelNorte.add(botonComienzo);
        panelNorte.add(botonFin);
        panelNorte.add(botonPared);
        add(panelNorte, BorderLayout.NORTH);

        String[] nombreMetodos = { "Recursivo", "Recursivo Completo", "Recursivo Completo BT", "BFS", "DFS" };
        this.algorithmSelector = new JComboBox<>(nombreMetodos);
        this.solveButton = new JButton("Resolver");

        JPanel panelSur = new JPanel();
        panelSur.add(new JLabel("Algoritmo:"));
        panelSur.add(this.algorithmSelector);
        panelSur.add(this.solveButton);
        panelSur.add(this.pasoAPasoButton);
        add(panelSur, BorderLayout.SOUTH);

        this.botonLimpiar = new JButton("Limpiar");
        panelSur.add(botonLimpiar);

        JMenuBar barraMenu = new JMenuBar();

        JMenu menuArchivo = new JMenu("Datos (Archivo)");
        this.menuItemNuevoLab = new JMenuItem("Nuevo laberinto");
        menuArchivo.add(menuItemNuevoLab);
        barraMenu.add(menuArchivo);

        JMenu menuAyuda = new JMenu("Ayuda");
        JMenuItem menuItemAcercaDe = new JMenuItem("Acerca de");
        menuItemAcercaDe.addActionListener(e -> mostrarAcercaDe());
        menuAyuda.add(menuItemAcercaDe);
        barraMenu.add(menuAyuda);

        this.menuItemVerResultados = new JMenuItem("Ver resultados");

        menuArchivo.add(menuItemVerResultados);
        setJMenuBar(barraMenu);
        setVisible(true);
    }

    private void pintarCeldaSwing(Cell paramCell, CellState paramCellState) {
        SwingUtilities.invokeLater(() -> pintarCelda(paramCell, paramCellState));
    }

    public void pintarCelda(Cell paramCell, CellState paramCellState) {
        JButton jButton = this.mazePanel.getButton(paramCell.getFila(), paramCell.getColumna());
        jButton.setBackground(colores.getOrDefault(paramCellState, Color.BLACK));
    }

    public void animarVisitadas(List<Cell> lista1, List<Cell> lista2) {

        Timer timer1 = new Timer(30, null);
        final int[] index1 = { 0 };

        timer1.addActionListener(e -> {
            if (index1[0] < lista1.size()) {
                Cell cell = lista1.get(index1[0]);
                if (cell.getEstado() == CellState.VACIO)
                    pintarCeldaSwing(cell, CellState.VACIO);
                index1[0]++;
            } else {
                timer1.stop();

                if (!lista2.isEmpty()) {
                    animarLista2(lista2);
                }
            }
        });
        timer1.start();
    }

    private void animarLista2(List<Cell> lista2) {

        Timer timer2 = new Timer(80, null);
        final int[] index2 = { 0 };

        timer2.addActionListener(e -> {
            if (index2[0] < lista2.size()) {
                Cell cell = lista2.get(index2[0]);
                if (cell.getEstado() != CellState.COMIENZO && cell.getEstado() != CellState.FIN)
                    pintarCeldaSwing(cell, CellState.CAMINO);
                index2[0]++;
            } else {
                timer2.stop();
            }
        });

        timer2.start();
    }

    private void mostrarAcercaDe() {

        JPanel panel = new JPanel(new BorderLayout());

        JLabel desarrolladores = new JLabel("Desarrollado por: Nicolas Cedillo y Mateo Miller");
        desarrolladores.setHorizontalAlignment(SwingConstants.LEFT);

        JPanel desarrolladoresPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        desarrolladoresPanel.add(desarrolladores);

        panel.add(desarrolladoresPanel, BorderLayout.NORTH);

        JPanel panelDesarrolladores = new JPanel(new FlowLayout(FlowLayout.LEFT));

        JLabel gitMateo = new JLabel("Mateo Miller");
        gitMateo.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        gitMateo.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent param1MouseEvent) {
                try {
                    Desktop.getDesktop().browse(new URI("https://github.com/MJMMiller"));
                } catch (Exception exception) {
                    System.out.println("No se pudo abrir el enlace de GitHub de Mateo.");
                }
            }
        });
        panelDesarrolladores.add(gitMateo);

        JLabel gitNicolas = new JLabel("Nicolas Cedillo");
        gitNicolas.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        gitNicolas.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent param1MouseEvent) {
                try {
                    Desktop.getDesktop().browse(new URI("https://github.com/nicolascedillo"));
                } catch (Exception exception) {
                    System.out.println("No se pudo abrir el enlace de GitHub de Nicolas.");
                }
            }
        });
        panelDesarrolladores.add(gitNicolas);

        panel.add(panelDesarrolladores, BorderLayout.CENTER);
        panel.setBorder(BorderFactory.createEmptyBorder(5, 10, 10, 10));

        JOptionPane.showMessageDialog(this, panel, "Acerca de", 1);
    }

    public void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje);
    }

    // Getters and Setters

    public MazePanel getMazePanel() {
        return this.mazePanel;
    }

    public JComboBox<String> getAlgorithmSelector() {
        return algorithmSelector;
    }

    public JButton getSolveButton() {
        return solveButton;
    }

    public JButton getPasoAPasoButton() {
        return pasoAPasoButton;
    }

    public Map<CellState, Color> getColores() {
        return colores;
    }

    public JButton getBotonComienzo() {
        return botonComienzo;
    }

    public void setBotonComienzo(JButton botonComienzo) {
        this.botonComienzo = botonComienzo;
    }

    public JButton getBotonFin() {
        return botonFin;
    }

    public void setBotonFin(JButton botonFin) {
        this.botonFin = botonFin;
    }

    public JButton getBotonPared() {
        return botonPared;
    }

    public void setBotonPared(JButton botonPared) {
        this.botonPared = botonPared;
    }

    public JMenuItem getMenuItemNuevoLab() {
        return menuItemNuevoLab;
    }

    public void setMenuItemNuevoLab(JMenuItem menuItemNuevoLab) {
        this.menuItemNuevoLab = menuItemNuevoLab;
    }

    public void setMazePanel(MazePanel mazePanel) {
        this.mazePanel = mazePanel;
    }

    public JButton getBotonLimpiar() {
        return botonLimpiar;
    }

    public void setBotonLimpiar(JButton botonLimpiar) {
        this.botonLimpiar = botonLimpiar;
    }

    public JMenuItem getMenuItemVerResultados() {
        return menuItemVerResultados;
    }

    public void setMenuItemVerResultados(JMenuItem menuItemVerResultados) {
        this.menuItemVerResultados = menuItemVerResultados;
    }

}
