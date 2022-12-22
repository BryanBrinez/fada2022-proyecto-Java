package proyecto;

import javax.naming.OperationNotSupportedException;
import lombok.Getter;
import lombok.Setter;
import java.io.FileNotFoundException;

public class SparseMatrixCSC {
    private LoadFile loader = LoadFile.getInstance();
    private int[][] matrix;
    @Getter
    @Setter
    private int[] rows;
    @Getter
    @Setter
    private int[] columns;
    @Getter
    @Setter
    private int[] values;
    private int numRows;
    private int numCols;
    public void createRepresentation(String inputFile) throws OperationNotSupportedException, FileNotFoundException {
        // Load data
        loader.loadFile(inputFile);
        matrix = loader.getMatrix();

        numRows = matrix.length;
        numCols = matrix[0].length;

        // Arreglo el cual almacena el numero de elementos no nulos por cada columna
        int cantVal = 0;
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) {
                if (matrix[i][j] != 0) {
                    cantVal++;
                }
            }
        }

        int[] val = new int[cantVal];
        int[] row = new int[val.length];
        int[] col = new int[numCols + 1];

        // índice actual
        int pos = 0;

        for (int j = 0; j < numCols; j++) {
            // Actualiza col para indicar dónde comienza la columna actual
            col[j] = pos;
            System.out.println(col[j]);
            for (int i = 0; i < numRows; i++) {
                if (matrix[i][j] != 0) {
                    val[pos] = matrix[i][j];
                    row[pos] = i;
                    pos++;
                }
            }
        }
        col[numCols] = pos;

        this.rows = row;
        this.columns = col;
        this.values = val;
    }

    public int getElement(int i, int j) throws OperationNotSupportedException {
        //limites
        int inicio = columns[j];
        int fin = columns[j + 1] - 1;

        //al saber los limites se recorre y si encuentra el valor lo mando, sino un 0
        for (int k = inicio; k <= fin; k++) {
            if (rows[k] == i) {
                return values[k];
            }
        }

        return 0;
    }

    public int[] getRow(int fila) throws OperationNotSupportedException {
        int[] a = new int[columns.length - 1];
        //recorre la columna
        for (int j = 0; j < columns.length - 1; j++) {
            //recorre con sus limites y va copiando los datos
            for (int k = columns[j]; k < columns[j + 1]; k++) {
                if (rows[k] == fila) {
                    a[j] = values[k];
                    break;
                }
            }
        }
        return a;
    }

    public int[] getColumn(int j) throws OperationNotSupportedException {
        //se le asigna tamaño
        int n = this.matrix.length;
        int[] column = new int[n];

        //manda los datos que se ecuentra en values
        for (int i = columns[j]; i < columns[j + 1]; i++) {
            column[rows[i]] = values[i];
        }
        return column;
    }

    public void setValue(int i, int j, int value) throws OperationNotSupportedException
    {
        throw new OperationNotSupportedException();
    }

    /*
     * This method returns a representation of the Squared matrix
     * @return object that contests the squared matrix;
     */
    public SparseMatrixCSC getSquareMatrix() throws OperationNotSupportedException
    {
        SparseMatrixCSC squaredMatrix = new SparseMatrixCSC();

       //se multiplica por si misma
        for (int i = 0; i < this.values.length; i++) {
            this.values[i] = this.values[i] * this.values[i];
        }

        ////se setean los valores
        squaredMatrix.setRows(this.rows);
        squaredMatrix.setColumns(this.columns);
        squaredMatrix.setValues(this.values);
        return squaredMatrix;
    }

    /*
     * This method returns a representation of the transposed matrix
     * @return object that contests the transposed matrix;
     */
    public SparseMatrixCSC getTransposedMatrix() throws OperationNotSupportedException
    {
        SparseMatrixCSC squaredMatrix = new SparseMatrixCSC();
        throw new OperationNotSupportedException();
    }
}
