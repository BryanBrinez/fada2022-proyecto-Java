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
        ArrayList<Integer> valores = new ArrayList<Integer>();
        ArrayList<Integer> fil = new ArrayList<Integer>();
        ArrayList<Integer> col = new ArrayList<Integer>();


        tamFilas =matrix.length;

        //sacar los values de
        for (int i = 0; i < matrix.length; i++) {

            for (int j = 0; j < matrix[i].length; j++) {
                tamCol = matrix[i].length;

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
        boolean dato = false;

        for (int i = 0; i < rows.length; i++){
            if(this.rows[i] == fila && this.columns[i] == columna){
                dato = true;
               return this.values[i];
            }
        }
        if(dato==false){
            return 0;
        }

        throw new OperationNotSupportedException();
    }

    public int[] getRow(int fila) throws OperationNotSupportedException {
        //No usar this.matrix aqui.

        ArrayList<Integer> filas = new ArrayList<Integer>();
        ArrayList<Integer> res = new ArrayList<Integer>(tamCol);

        int resul[] = new int[tamCol];

        for (int i = 0; i < resul.length; i++){
            resul[i] = 0;
        }

        for (int i = 0; i < rows.length; i++){
            if(this.rows[i] == fila){
                filas.add(this.columns[i]);
            }
        }

        for (int i = 0; i < tamCol; i++){
            for (Integer elemento : filas){
                if(i == elemento){
                    resul[i] = this.getElement(fila,elemento);
                }
            }
        }

        return resul;
    }

    public int[] getColumn(int columna) throws OperationNotSupportedException {
        //No usar this.matrix aqui.

        ArrayList<Integer> columnas = new ArrayList<Integer>();
        ArrayList<Integer> res = new ArrayList<Integer>(8);

        int resul[] = new int[tamFilas];

        for (int i = 0; i < resul.length; i++){
            resul[i] = 0;
        }

        for (int i = 0; i < rows.length; i++){
            if(columns[i] == columna){
                columnas.add(this.rows[i]);
            }
        }

        for (int i = 0; i < resul.length; i++){
            for (Integer elemento : columnas){
                if(i == elemento){
                    resul[i] = this.getElement(elemento,columna);
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


        for (int i = 0; i < this.rows.length; i++) {
            this.values[i] = this.values[i] * this.values[i];
        }

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
        //Usar los metodos Set aqui de los atributos
        throw new OperationNotSupportedException();
    }

}
