package proyecto;

import javax.naming.OperationNotSupportedException;
import lombok.Getter;
import lombok.Setter;
import java.util.Arrays;
import java.io.FileNotFoundException;

public class SparseMatrixCSR {
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
        //Load data
        loader.loadFile(inputFile);
        matrix = loader.getMatrix();

        numRows = matrix.length;
        numCols = matrix[0].length;

        //cantidad de datos que no son cero
        int cantVal = 0;
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) {
                if (matrix[i][j] != 0) {
                    cantVal++;
                }
            }
        }

        int[] val = new int[cantVal];
        int[] col = new int[val.length];
        int[] fil = new int[numRows + 1];

        // inidice actual
        int pos = 0;

        for (int i = 0; i < numRows; i++) {
            //donde comienza la actual
            fil[i] = pos;
            for (int j = 0; j < numCols; j++) {
                if (matrix[i][j] != 0) {
                    val[pos] = matrix[i][j];
                    col[pos] = j;
                    pos++;
                }
            }
        }
        //donde fue la ultima que fue diferente a 0
        fil[numRows] = pos;

        this.setRows(fil);
        this.setColumns(col);
        this.setValues(val);

    }

    public int getElement(int i, int j) throws OperationNotSupportedException {

        //limites
        int inicio = rows[i];
        int fin = rows[i + 1] - 1;

        //al saber los limites se recorre y si encuentra el valor lo mando, sino un 0
        for (int k = inicio; k <= fin; k++) {
            if (columns[k] == j) {
                return values[k];
            }
        }
        return 0;
    }

    public int[] getRow(int fila) throws OperationNotSupportedException {
        //num max de la cantidad
        int canCol = Arrays.stream(columns).max().getAsInt();

        System.out.println(canCol);
        int row = rows[fila];


        int[] filaRetornada = new int[canCol + 1];

        //rellena el array con ceros
        Arrays.fill(filaRetornada, 0);

        //lo rellena con los datos que encuentra
        for (int i = row; i < rows[fila + 1]; i++) {
            filaRetornada[columns[i]] = values[i];
        }
        return filaRetornada;
    }

    public int[] getColumn(int j) throws OperationNotSupportedException {
        //se le asigna tamaño
        int n = rows.length - 1;
        int[] column = new int[n];

        //manda los datos que se ecuentra en la columna
        for (int i = 0; i < column.length; i++) {
            column[i] = getElement(i, j);
        }

        return column;
    }

    public void setValue(int i, int j, int value) throws OperationNotSupportedException
    {
        int inicio = rows[i];
        int fin = rows[i + 1] - 1;

        int[] newColumns = new int[columns.length + 1];
        int[] newValues = new int[values.length + 1];

        //para el caso donde inicio > fin
        //Significa que no hay valores diferentes a cero en esa fila
        if (fin < inicio) {
            //Se copia los valores desde el inicio hasta la nueva posicion
            for (int l = 0; l < inicio; l++) {
                newColumns[l] = columns[l];
                newValues[l] = values[l];
            }
            //se adiciona el nuevo valor en los listados nuevos
            newColumns[inicio] = j;
            newValues[inicio] = value;
            //Se copian el resto de valores
            for (int l = inicio; l < values.length; l++) {
                newColumns[l + 1] =   columns[l];
                newValues[l + 1] = values[l];
            }
            //Se redefinen las columnas y los valores
            columns = newColumns;
            values = newValues;
            //se suma 1 a los valores en adelante para las filas
            for (int l = i + 1; l < rows.length; l++) {
                rows[l]++;
            }

        }

        for (int k = inicio; k <= fin; k++) {
            //Busca si el indice ya existe,
            //en este caso solo se redefine el valor almacenado
            if (columns[k] == j) {
                values[k] = value;
                break;
            } else if (columns[k] > j) {
                //Se copian los valores hasta la posicion k-1
                for (int l = 0; l < k; l++) {
                    newColumns[l] = columns[l];
                    newValues[l] = values[l];
                }
                //se inserta el nuevo valor
                newColumns[k] = j;
                newValues[k] = value;
                //Se copian el resto de valores
                for (int l = k; l < values.length; l++) {
                    newColumns[l + 1] = columns[l];
                    newValues[l + 1] = values[l];
                }
                //Se redefinen las columnas y los valores
                columns = newColumns;
                values = newValues;
                //se suma 1 a los valores en adelante para las filas
                for (int l = i + 1; l < rows.length; l++) {
                    rows[l]++;
                }
                break;
            } else if (k == fin) {
                //Se copian los valores hasta la posicion k-1
                for (int l = 0; l <= k; l++) {
                    newColumns[l] = columns[l];
                    newValues[l] = values[l];
                }
                //se inserta el nuevo valor
                newColumns[k + 1] = j;
                newValues[k + 1] = value;

                //Se copian el resto de valores
                for (int l = k + 1; l < values.length; l++) {
                    newColumns[l + 1] = columns[l];
                    newValues[l + 1] = values[l];
                }

                //Se redefinen las columnas y los valores
                columns = newColumns;
                values = newValues;
                //se suma 1 a los valores en adelante para las filas
                for (int l = i + 1; l < rows.length; l++) {
                    rows[l]++;
                }
                break;
            }
        }
    }

    /*
     * This method returns a representation of the Squared matrix
     * @return object that contests the squared matrix;
     */
    public SparseMatrixCSR getSquareMatrix() throws OperationNotSupportedException {
        SparseMatrixCSR squaredMatrix = new SparseMatrixCSR();


        //se multiplica por si misma
        for (int i = 0; i < this.values.length; i++) {
            this.values[i] = this.values[i] * this.values[i];
        }

        //se setean los valores
        squaredMatrix.setRows(this.rows);
        squaredMatrix.setColumns(this.columns);
        squaredMatrix.setValues(this.values);
        return squaredMatrix;
    }

    /*
     * This method returns a representation of the transposed matrix
     * @return object that contests the transposed matrix;
     */
    public SparseMatrixCSR getTransposedMatrix() throws OperationNotSupportedException
    {
        SparseMatrixCSR squaredMatrix = new SparseMatrixCSR();
        throw new OperationNotSupportedException();
    }

}
