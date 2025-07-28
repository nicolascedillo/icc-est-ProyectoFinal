import javax.swing.JOptionPane;
import controllers.MazeController;
import views.MazeFrame;

public class MazeApp {
    public static void main(String[] args) throws Exception {
        int[] dimensiones = solicitarDimensiones();
        if (dimensiones != null){
            MazeFrame frame = new MazeFrame(dimensiones[0], dimensiones[1]);
            MazeController controller = new MazeController(frame);

            frame.getMenuItemNuevoLab().addActionListener(e -> {
                frame.setVisible(false);
                int[] nuevasDimensiones = solicitarDimensiones();
                if(nuevasDimensiones == null) {
                    return;
                }
                MazeFrame nuevoFrame = new MazeFrame(nuevasDimensiones[0], nuevasDimensiones[1]);
                MazeController nuevoController = new MazeController(nuevoFrame);
                
            });
            
            
        }
            
    }

    public static int[] solicitarDimensiones() {

        try {
            String stringFilas = JOptionPane.showInputDialog("Filas del Laberinto:");
            
            if (stringFilas == null)
                return null;

            String stringColumnas = JOptionPane.showInputDialog("Columnas del Laberinto:");
            if (stringColumnas == null){
                return null;
            }

            int filas = Integer.parseInt(stringFilas.trim());
            int columnas = Integer.parseInt(stringColumnas.trim());

            if (filas <= 5 || columnas <= 5) {
                JOptionPane.showMessageDialog(null, "Deben ser numeros mayores a 5.");
                return null;
            }

            return new int[] { filas, columnas };
            
        } catch (NumberFormatException numberFormatException) {
            JOptionPane.showMessageDialog(null, "Debe ingresar un número válido.");
            return null;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error inesperado: " + e.getMessage());
            return null;
        }
    }
}