import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;
/**
 * Esta clase representa la salida del programa y contiene métodos relacionados con la lógica principal del algoritmo de cambio dinámico
 * 
 * @author Laura 
 */
public class FSalida 
{
    // Se declara el objeto FEntrada
    private FEntrada datosEntrada;
    // Tipo moneda
    private int STmoneda;
    // Cantidad a devolver
    private int SCantidaDevolver;
    // Valor de las diferentes monedas
    private int[] SVmoneda;
    // Se declara  una matriz que sera mi tabla
    private int [][] TablaMonedas;
    // declaracion de vectores
    private int []vectorMonedas;
    private int []vectorSolucion;
    private int []vectorSolucionValue;
    // Entero que sera el numero de monedas final que necesito
    private int AmountCoins;
    
    // Variable para la opción de traza
    private boolean trace; 
    /**
     * Objetivo: Crea una instancia de FEntrada y llama al método initialize.
     * Parámetros:
     * n: Número de tipos de monedas.
     * A: Valores de los diferentes tipos de monedas.
     * C: Cantidad a devolver.
     */
    public FSalida(int n, int[]A, int C){
     datosEntrada = new FEntrada(n, A, C);
     initialize();
    }
    /**
     * Objetivo: Inicializa las variables de la clase, configurando la instancia de FEntrada y otros atributos.
     */
    private void initialize() {
        this.STmoneda = datosEntrada.getTmoneda();
        this.SCantidaDevolver = datosEntrada.getCantidaDevolver();
        this.SVmoneda = datosEntrada.getVmoneda();
        this.vectorMonedas = getVectorMonedas();
        this.vectorSolucion = new int[this.STmoneda];
        this.vectorSolucionValue = new int[10];
        this.TablaMonedas = new int[this.STmoneda][this.SCantidaDevolver + 1];
    }
    
    public void setTrace(boolean trace) {
        this.trace = trace;
    }
    
    /**
     * Este metodo coge un vector con x enteros (SVmoneda) de diferentes valores
     * lo organiza de menor a mayor y lo introduce en orden en otro vector (vectorMonedas)
     */
    private void setVectorMonedas(){
        Arrays.sort(this.SVmoneda);
        this.vectorMonedas = Arrays.copyOf(this.SVmoneda, this.SVmoneda.length);
     }
    /**
     * Este metodo llama al metodo de esta misma clase setVectorMonedas() 
     * y devuelve el vector ordenado.
     */
    public int[] getVectorMonedas(){
        setVectorMonedas();
        return this.vectorMonedas;
    }
    /**
     * Objetivo: Rellena una tabla con los valores mínimos de monedas necesarios para alcanzar una cantidad.
     * Parámetros:
     * trace: Indica si se debe imprimir una traza detallada durante la ejecución.
     * Proceso:
     * Utiliza un enfoque dinámico para llenar la tabla con la cantidad mínima de monedas necesarias.
     */
    public void setTable(boolean trace){
        if (trace) {
        System.out.println("Iniciando el método setTable...");
        }   
        for(int i = 0; i < this.TablaMonedas.length; i++  ){
        this.TablaMonedas[i][0]=0;
        // System.out.print(" Fila "+i+" Columna 0 es:  " +this.TablaMonedas[i][0]+ "\n");
            
        }
        for(int i = 0; i < this.TablaMonedas.length; i++  ){
            for(int j=1; j<this.TablaMonedas[0].length; j++){
                if(i==0 && vectorMonedas[i] > j){
                    this.TablaMonedas[i][j]=000;
                }else{
                   if(i == 0){
                     this.TablaMonedas[i][j]= 1+ this.TablaMonedas[0][j - this.vectorMonedas[i]];
                     // System.out.print(" Fila "+i+" Columna "+j+" es : " +this.TablaMonedas[i][j]+"\n");
                    }else{
                     if(j < this.vectorMonedas[i]){
                      this.TablaMonedas[i][j]=this.TablaMonedas[i-1][j];
                      // System.out.print(" Fila "+i+" Columna "+j+" es : " +this.TablaMonedas[i][j]+"\n");      
                     }else{
                      this.TablaMonedas[i][j] = Math.min(this.TablaMonedas[i-1][j], this.TablaMonedas[i][j - vectorMonedas[i]]+1);
                      // System.out.print(" Fila "+i+" Columna "+j+" es : " +this.TablaMonedas[i][j]+"\n");  
                     }
                    }
                } 
                // Agregar traza si está habilitada
                if (trace) {
                System.out.println("Fila " + i + " Columna " + j + " es : " + this.TablaMonedas[i][j]);
                }
            }       
        }
    }
    /**
     * Objetivo: Calcula la solución a partir de la tabla generada.
     * Proceso:
     * Utiliza la tabla para determinar la combinación óptima de monedas necesarias para alcanzar la cantidad deseada.
     */
    public void getSolution(){
        this.AmountCoins = 0;
        for(int x = 0; x < this.STmoneda; x++  ){
        this.vectorSolucion[x]=0;
        // System.out.print("Vector monedas lugar: "+ x +" "+  this.vectorSolucion[x]+"\n");
        }
        int i=this.STmoneda-1;
        int j=this.SCantidaDevolver;
        while(j > 0){
            if(i>0 && this.TablaMonedas[i][j]==this.TablaMonedas[i-1][j]){
                i = i-1;
            }else{
                this.vectorSolucion[i]=this.vectorSolucion[i]+1;
                this.vectorSolucionValue[this.AmountCoins]= this.vectorMonedas[i];
                this.AmountCoins ++;
                j=j-this.vectorMonedas[i];
            }
        }
    }
    /**
     * Objetivo: Imprime la solución en la consola.
     * Proceso:
     * Imprime la cantidad total de monedas y los valores de las monedas utilizadas.
     */
    public void printSolution(){
        System.out.print(this.AmountCoins+"\n");
        for(int i=0; i < this.AmountCoins; i++){
            System.out.print(this.vectorSolucionValue[i]+" ");
        }
    }
    /**
     * Objetivo: Escribe la solución en un archivo.
     * Parámetros:
     * outputFileName: Nombre del archivo de salida.
     */
    public void printSolutionToFile(String outputFileName) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(outputFileName))) {
            writer.println(this.AmountCoins);
            for (int i = 0; i < this.AmountCoins; i++) {
                writer.print(this.vectorSolucionValue[i] + " ");
            }
        } catch (IOException e) {
            System.err.println("Error al escribir en el archivo de salida: " + e.getMessage());
        }
    }
    /**
     * Objetivo: Muestra información de ayuda sobre la sintaxis del programa y las opciones disponibles.
     */
    public static void mostrarAyuda(){
        System.out.println("SINTAXIS:java -jar CambioDinamica.jar [-t][-h] [fichero entrada] [fichero salida]");
        System.out.println("-t Traza el algoritmo");
        System.out.println("-h Muestra esta ayuda");
        System.out.println("[fichero entrada] Nombre del fichero de entrada");
        System.out.println("[fichero salida] Nombre del fichero de salida");
        System.exit(0);
    }
    }