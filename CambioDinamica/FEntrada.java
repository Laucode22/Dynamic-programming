import java.util.Scanner;import java.util.Arrays;
import java.io.FileNotFoundException;
import java.io.File;

/**
 * Esta clase representa la entrada de datos y contiene métodos relacionados con la inicialización de valores
 * 
 * @author Laura Saltaren 
 */
public class FEntrada 
{
    // Instancia de las variables.
    private int Tmoneda;
    private int[] Vmoneda ;
    private int CantidaDevolver;

    /**
     * Objetivo: Inicializa las variables de entrada con los valores proporcionados.
     * Parámetros:
     * n: Número de tipos de monedas.
     * A: Valores de los diferentes tipos de monedas.
     * C: Cantidad a devolver.
     */
    public FEntrada(int n, int[]A, int C)
    {
        this.Tmoneda = n;
        this.Vmoneda = new int[Tmoneda];
        this.Vmoneda = A;
        this.CantidaDevolver = C;
        
    }
    /**
     * Objetivo: Proporciona acceso a los valores almacenados en la instancia de FEntrada.
     * Return: Devuelven la cantidad a devolver.
     */
    public int getCantidaDevolver(){
     return CantidaDevolver;
    }
    /**
     * Objetivo: Proporciona acceso a los valores almacenados en la instancia de FEntrada.
     * Return: Devuelven los valores de las monedas.
     */
    public int [] getVmoneda(){
     return Vmoneda;
    }
    /**
     * Objetivo: Proporciona acceso a los valores almacenados en la instancia de FEntrada.
     * Return: Devuelven el número de tipos de monedas.
     */
    public int getTmoneda(){
     return Tmoneda;  
    }
    /**
     * Objetivo: Devuelve un objeto Scanner para leer datos de entrada desde un archivo o la entrada estándar.
     * Parámetros:
     * args: Argumentos del programa.
     * startIndex: Índice para comenzar la lectura de argumentos.
     * Return: Un objeto Scanner configurado para leer desde un archivo o la entrada estándar.
     */
    public static Scanner getScanner(String[] args, int startIndex) {
        Scanner scanner = null;

        if (args.length > startIndex && !args[startIndex].equals("-t")) {
            // Intenta abrir el archivo si está presente
            try {
                System.out.print("\n Se ha leido el archivo desde entrada \n");
                String inputFile = args[startIndex];
                scanner = new Scanner(new File(inputFile));
            } catch (FileNotFoundException e) {
                System.err.println("\nError: No se pudo encontrar el archivo de entrada. Se utilizará la entrada estándar.");
                scanner = new Scanner(System.in);
            }
        }else{
            scanner = new Scanner(System.in);
        }


        return scanner;
    }
}
