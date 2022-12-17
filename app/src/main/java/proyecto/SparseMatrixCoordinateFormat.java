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

    public void createRepresentation(String inputFile) throws OperationNotSupportedException, FileNotFoundException {
        //Load data
        loader.loadFile(inputFile);
        matrix = loader.getMatrix();
        ArrayList<Integer> valores = new ArrayList<Integer>();
        ArrayList<Integer> fil = new ArrayList<Integer>();
        ArrayList<Integer> col = new ArrayList<Integer>();


        //sacar los values de
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {

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


        for (Integer elemento : fil)
            System.out.print(elemento + "-");
        System.out.println();


        for (Integer elemento : col)
            System.out.print(elemento + "-");
        System.out.println();


        values = new  int[valores.size()];
        rows = new  int[valores.size()];
        columns = new  int[valores.size()];
        for (int i = 0; i < values.length; i++){
            values[i] = valores.get(i);

            rows[i] = fil.get(i);

            columns[i] = col.get(i);
            //System.out.println(values[i]);

        }

    }

    public int getElement(int fila, int columna) throws OperationNotSupportedException {
        //No usar this.matrix aqui.


        for (int i = 0; i < this.rows.length; i++){
            if(this.rows[i] ==  fila ){
                for (int j = 0; j < this.columns.length; i++){
                    if (this.columns[j] == columna){
                        return this.values[j];
                    }

                }
            }

        }



        throw new OperationNotSupportedException();
    }

    public int[] getRow(int i) throws OperationNotSupportedException {
        //No usar this.matrix aqui.
        throw new OperationNotSupportedException();
    }

    public int[] getColumn(int j) throws OperationNotSupportedException {
        //No usar this.matrix aqui.
        throw new OperationNotSupportedException();
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
        throw new OperationNotSupportedException();
    }

    /*
     * This method returns a representation of the transposed matrix
     * @return object that contests the transposed matrix;
     */
    public SparseMatrixCoordinateFormat getTransposedMatrix() throws OperationNotSupportedException {
        SparseMatrixCoordinateFormat squaredMatrix = new SparseMatrixCoordinateFormat();
        //Usar los metodos Set aqui de los atributos
        throw new OperationNotSupportedException();
    }

}
