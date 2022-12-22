package proyecto;

import javax.naming.OperationNotSupportedException;
import java.util.ArrayList;

import lombok.Getter;
import lombok.Setter;

import java.io.FileNotFoundException;

public class SparseMatrixCoordinateFormat {

    private LoadFile loader = LoadFile.getInstance();
    @Setter
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

    private int tamFilas;
    private int tamCol;

    public void createRepresentation(String inputFile) throws OperationNotSupportedException, FileNotFoundException {
        //Load data
        loader.loadFile(inputFile);
        matrix = loader.getMatrix();


        //Arrayslist para manejar mas facil los arrays
        ArrayList<Integer> valores = new ArrayList<Integer>();
        ArrayList<Integer> fil = new ArrayList<Integer>();
        ArrayList<Integer> col = new ArrayList<Integer>();


        //tam de las filas
        tamFilas = matrix.length;

        //recorrer la matrix
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                //tam de columnas
                tamCol = matrix[0].length;

                //Comprueba si es diferente a 0 y los agrega al arraylist
                if (matrix[i][j] != 0) {
                    valores.add(matrix[i][j]);
                    fil.add(i);
                    col.add(j);
                }
            }
        }

        for (Integer elemento : valores)
            System.out.print(elemento + "-");
        System.out.println();

        //Le da tamaño a los arrays resultantes
        values = new  int[valores.size()];
        rows = new  int[valores.size()];
        columns = new  int[valores.size()];
        //Pasa de los arraylist a los arrays resultados
        for (int i = 0; i < values.length; i++) {
            values[i] = valores.get(i);

            rows[i] = fil.get(i);

            columns[i] = col.get(i);
        }

    }



    public int getElement(int fila, int columna) throws OperationNotSupportedException {
        //No usar this.matrix aqui.


        //Recorre las filas
        for (int i = 0; i < rows.length; i++) {
            //Comprueba si son iguales a la busqueda
            if  (this.rows[i] == fila && this.columns[i] == columna) {
               return this.values[i];
            }
        }
        //sino retorna 0
        return 0;
    }

    public int[] getRow(int fila) throws OperationNotSupportedException {
        //No usar this.matrix aqui.

        ArrayList<Integer> filas = new ArrayList<Integer>();
        ArrayList<Integer> res = new ArrayList<Integer>();

        //Crea un array de ceros
        int resul[] = new int[tamCol];
        for (int i = 0; i < resul.length; i++) {
            resul[i] = 0;
        }

        //guarda las posiciones de la columna donde row sea igual a la fila
        for (int i = 0; i < rows.length; i++) {
            if (this.rows[i] == fila) {
                filas.add(this.columns[i]);
            }
        }

        //guarda el elemento donde la posicion de su columna sea igual al recorrido
        for (int i = 0; i < resul.length; i++) {
            for (Integer elemento : filas) {
                if (i == elemento) {
                    resul[i] = this.getElement(fila, elemento);
                }
            }
        }

        return resul;
    }

    public int[] getColumn(int columna) throws OperationNotSupportedException {
        //No usar this.matrix aqui.

        ArrayList<Integer> columnas = new ArrayList<Integer>();
        ArrayList<Integer> res = new ArrayList<Integer>(8);


        //Crea un array de ceros
        int resul[] = new int[tamFilas];
        for (int i = 0; i < resul.length; i++) {
            resul[i] = 0;
        }

        //guarda las posiciones de la fila donde row sea igual a la columna
        for (int i = 0; i < rows.length; i++) {
            if (columns[i] == columna) {
                columnas.add(this.rows[i]);
            }
        }

        //guarda el elemento donde la posicion de su fila sea igual al recorrido
        for (int i = 0; i < resul.length; i++) {
            for (Integer elemento : columnas) {
                if (i == elemento) {
                    resul[i] = this.getElement(elemento, columna);
                }
            }

        }

        return resul;
    }

    public void setValue(int i, int j, int value) throws OperationNotSupportedException {
        //Cambiar los atributos rows, cols, values y matrix aqui

        throw new OperationNotSupportedException();

    }

    /*
     * This method returns a representation of the Squared matrix
     * @return object that contests the squared matrix;
     */
    public SparseMatrixCoordinateFormat getSquareMatrix() throws OperationNotSupportedException {
        SparseMatrixCoordinateFormat squaredMatrix = new SparseMatrixCoordinateFormat();
        //Usar los metodos Set aqui de los atributos


        //multiplica por si mismo cada elemento del array values
        for (int i = 0; i < this.rows.length; i++) {
            this.values[i] = this.values[i] * this.values[i];
        }

        //setea los arrays
        squaredMatrix.setRows(this.rows);
        squaredMatrix.setColumns(this.columns);
        squaredMatrix.setValues(this.values);
        return squaredMatrix;
    }

    /*
     * This method returns a representation of the transposed matrix
     * @return object that contests the transposed matrix;
     */
    public SparseMatrixCoordinateFormat getTransposedMatrix() throws OperationNotSupportedException {
        SparseMatrixCoordinateFormat squaredMatrix = new SparseMatrixCoordinateFormat();

        int[][] nMatrix = new int[matrix[0].length][matrix.length];

        //se ponen las columnas en la filas y las filas en las columnas
        for (int j = 0; j < matrix[0].length; j++) {
            for (int i = 0; i < matrix.length; i++) {
                nMatrix[j][i] = matrix[i][j];
            }
        }
        squaredMatrix.setMatrix(nMatrix);


        // Creamos los arrays con las capacidades correspondientes
        int[] nFilas = new int[this.values.length];
        int[] nCol = new int[this.values.length];
        int[] nVal = new int[this.values.length];

        // se llenan los arrays con los nuevos datos
        int index = 0;
        for (int i = 0; i < nMatrix.length; i++) {
            for (int j = 0; j < nMatrix[0].length; j++) {
                if (nMatrix[i][j] != 0) {
                    nFilas[index] = i;
                    nCol[index] = j;
                    nVal[index] = nMatrix[i][j];
                    index++;
                }
            }
        }

        squaredMatrix.setRows(nFilas);
        squaredMatrix.setColumns(nCol);
        squaredMatrix.setValues(nVal);

        return squaredMatrix;
    }

}
