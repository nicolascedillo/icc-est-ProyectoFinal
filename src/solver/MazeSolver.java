package solver;

import models.Cell;
import models.SolveResult;

public interface MazeSolver {
    SolveResult getPath(Cell[][] matrix, Cell startCell, Cell endCell);
}
