package controllers;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import dao.AlgorithmResultDAO;
import dao.daoImpl.AlgorithmResultDAOFile;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import models.AlgorithmResult;
import models.Cell;
import models.CellState;
import models.SolveResult;
import solver.MazeSolver;
import solver.solverImpl.MazeSolverBFS;
import solver.solverImpl.MazeSolverDFS;
import solver.solverImpl.MazeSolverRecursivo;
import solver.solverImpl.MazeSolverRecursivoCompleto;
import solver.solverImpl.MazeSolverRecursivoCompletoBT;
import views.MazeFrame;
import views.MazePanel;
import views.ResultadosDialog;

public class MazeController {

    private MazePanel panel;
    private final MazeFrame frame;
    private Cell inicial;
    private Cell celdaFinal;
    private State estadoActual;
    private final AlgorithmResultDAO resultDAO;
    private MazeSolver mazeSolver;
    private List<Cell> pasoCeldasVisitadas;
    private List<Cell> pasoCamino;
    private int pasoIndex = 0;
    private Cell[][] celdasPanel;
    private boolean resolvioPasoAPaso = false;

    public MazeController(MazeFrame frame) {
        this.resultDAO = (AlgorithmResultDAO) new AlgorithmResultDAOFile("results.csv");
        this.frame = frame;
        this.panel = frame.getMazePanel();
        this.celdasPanel = new Cell[panel.getFilas()][panel.getColuumnas()];
        estadoActual = State.WALL;
        initGrid();
        addListeners();
        addListenersFrame();

    }

    public void celdaClickeada(int fila, int col) {

        switch (this.estadoActual) {
            case START:
                setCeldaInicial(fila, col);
                break;
            case END:
                setCeldaFinal(fila, col);
                break;
            case WALL:
                toggleWall(fila, col);
                break;
        }
    }

    public void setCeldaFinal(int fila, int col) {

        Cell celda = celdasPanel[fila][col];
        JButton boton = this.panel.getButton(fila, col);

        if (this.celdaFinal != null) {
            this.panel.getButton(this.celdaFinal.getFila(), this.celdaFinal.getColumna()).setBackground(Color.black);
            this.celdaFinal.setEstado(CellState.VACIO);
        }

        this.celdaFinal = celda;
        celda.setEstado(CellState.FIN);
        boton.setBackground(new Color(245, 0, 79));
    }

    public void setCeldaInicial(int fila, int col) {
        Cell celda = celdasPanel[fila][col];
        JButton boton = this.panel.getButton(fila, col);
        if (this.inicial != null) {
            this.panel.getButton(this.inicial.getFila(), this.inicial.getColumna()).setBackground(Color.black);
            this.inicial.setEstado(CellState.VACIO);
        }
        this.inicial = celda;
        celda.setEstado(CellState.COMIENZO);
        boton.setBackground(new Color(249, 228, 0));
    }

    public void toggleWall(int fila, int col) {

        Cell cell = celdasPanel[fila][col];

        if (cell.getEstado() == CellState.VACIO) {
            cell.setEstado(CellState.PARED);
            this.panel.getButton(fila, col).setBackground(new Color(38, 31, 179));

        } else if (cell.getEstado() == CellState.PARED) {
            cell.setEstado(CellState.VACIO);
            this.panel.getButton(fila, col).setBackground(Color.black);
        }
    }

    private void addListeners() {
        int filas = celdasPanel.length;
        int columnas = celdasPanel[0].length;
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                JButton button = this.panel.getButton(i, j);
                int finalI = i;
                int finalJ = j;
                button.addActionListener(e -> celdaClickeada(finalI, finalJ));
            }
        }
    }

    private void addListenersFrame() {

        frame.getBotonComienzo().addActionListener((ActionListener) new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent paramActionEvent) {
                setEstado(State.START);
            }
        });

        frame.getBotonFin().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent paramActionEvent) {
                setEstado(State.END);
            }
        });

        frame.getBotonPared().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent paramActionEvent) {
                setEstado(State.WALL);
            }
        });

        frame.getSolveButton().addActionListener(e -> {

            SolveResult resultados = resolverYObtenerResultados();
            if (resultados != null)
                frame.animarVisitadas(resultados.getVisitadas(), resultados.getCamino());
        });

        frame.getPasoAPasoButton().addActionListener(e -> {

            if (!this.resolvioPasoAPaso) {
                SolveResult resultados = resolverYObtenerResultados();
                if (resultados != null) {
                    this.pasoCeldasVisitadas = resultados.getVisitadas();
                    this.pasoCamino = resultados.getCamino();
                    this.pasoIndex = 0;
                    this.resolvioPasoAPaso = true;
                }

            } else if (this.pasoIndex < this.pasoCeldasVisitadas.size()) {

                Cell cell = this.pasoCeldasVisitadas.get(this.pasoIndex++);
                if (cell.getEstado() == CellState.VACIO)
                    frame.pintarCelda(cell, CellState.VACIO);

            } else if (this.pasoIndex - this.pasoCeldasVisitadas.size() < this.pasoCamino.size()) {

                int i = this.pasoIndex - this.pasoCeldasVisitadas.size();
                Cell cell = this.pasoCamino.get(i);
                this.pasoIndex++;
                if (cell.getEstado() != CellState.COMIENZO && cell.getEstado() != CellState.FIN) {
                    frame.pintarCelda(cell, CellState.CAMINO);
                }

            } else {
                frame.mostrarMensaje("Ya no hay más pasos.");
                this.resolvioPasoAPaso = false;
            }
        });

        frame.getBotonLimpiar().addActionListener(e -> {
            limpiarCeldasVisitadas();
            limpiarPasoAPaso();
        });

        frame.getMenuItemVerResultados().addActionListener(e -> {
            ResultadosDialog resultadosDialog = new ResultadosDialog(frame, this.resultDAO);
            resultadosDialog.setVisible(true);
        });
    }

    public void limpiar() {
        limpiarCeldasVisitadas();
    }

    public void limpiarPasoAPaso() {
        this.pasoCeldasVisitadas = null;
        this.pasoCamino = null;
        this.pasoIndex = 0;
        this.resolvioPasoAPaso = false;
    }

    private SolveResult resolverYObtenerResultados() {

        if (inicial == null || celdaFinal == null) {
            frame.mostrarMensaje("Debe haber un comienzo y un fin");
            return null;
        }

        limpiarCeldasVisitadas();
        limpiarPasoAPaso();

        String metodoSeleccionado = (String) frame.getAlgorithmSelector().getSelectedItem();
        switch (metodoSeleccionado) {
            case "Recursivo":
                mazeSolver = new MazeSolverRecursivo();
                break;
            case "Recursivo Completo":
                mazeSolver = new MazeSolverRecursivoCompleto();
                break;
            case "Recursivo Completo BT":
                mazeSolver = new MazeSolverRecursivoCompletoBT();
                break;
            case "DFS":
                mazeSolver = new MazeSolverDFS();
                break;
            case "BFS":
                mazeSolver = new MazeSolverBFS();
                break;
            default:
                mazeSolver = new MazeSolverRecursivo();
                break;
        }

        int tiempoInicio = (int) System.nanoTime();
        SolveResult resultadoResolucion = mazeSolver.getPath(celdasPanel, inicial, celdaFinal);
        int tiempoFin = (int) System.nanoTime();

        if (resultadoResolucion != null && !resultadoResolucion.getCamino().isEmpty()) {
            AlgorithmResult resultadoAlgoritmo = new AlgorithmResult(metodoSeleccionado,
                    resultadoResolucion.getCamino().size(), tiempoFin - tiempoInicio);
            this.resultDAO.guardar(resultadoAlgoritmo);
        }
        return resultadoResolucion;
    }

    public void limpiarCeldasVisitadas() {

        for (int i = 0; i < panel.getFilas(); i++) {

            for (int j = 0; j < panel.getColuumnas(); j++) {

                Cell cell = celdasPanel[i][j];

                if (cell.getEstado() != CellState.PARED && cell.getEstado() != CellState.COMIENZO &&
                        cell.getEstado() != CellState.FIN) {

                    cell.setEstado(CellState.VACIO);
                    panel.getBotones()[i][j].setBackground(Color.black);
                }
            }
        }
    }

    public void initGrid() {

        for (int i = 0; i < panel.getFilas(); i++) {

            for (int j = 0; j < panel.getColuumnas(); j++) {

                Cell cell = new Cell(i, j);
                JButton jButton = new JButton();

                jButton.setBackground(Color.black);
                jButton.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));

                panel.add(jButton);
                this.celdasPanel[i][j] = cell;
                panel.getBotones()[i][j] = jButton;
            }
        }
    }

    // Getters and Setters

    public void setEstado(State nuevoEstado) {
        this.estadoActual = nuevoEstado;
    }

    public Cell getInicial() {
        return this.inicial;
    }

    public Cell getCeldaFinal() {
        return this.celdaFinal;
    }

    public MazePanel getPanel() {
        return panel;
    }

    public void setPanel(MazePanel panel) {
        this.panel = panel;
    }

    public void setInicial(Cell inicial) {
        this.inicial = inicial;
    }

    public void setCeldaFinal(Cell celdaFinal) {
        this.celdaFinal = celdaFinal;
    }

    public State getEstadoActual() {
        return estadoActual;
    }

    public void setEstadoActual(State estadoActual) {
        this.estadoActual = estadoActual;
    }

}