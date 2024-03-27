
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;
/**
 * Esta clase representa la entrada del programa 
 * y la ejecución principal del algoritmo de cambio dinámico
 * Objetivo: Punto de entrada principal del programa.
 * Verificación de Argumentos: Comprueba la cantidad correcta de argumentos y maneja las opciones -t (traza) y -h (ayuda).
 * Creación de Instancia de FSalida: Inicializa una instancia de la clase FSalida que gestionará la lógica principal.
 * Lectura de Datos de Entrada: Lee el número de tipos de monedas, los valores de las monedas y la cantidad a devolver.
 * Resolución del Problema: Llama a métodos de la instancia de FSalida para resolver el problema y mostrar o escribir la solución.
 */
public class CambioDinamica {
    public static void main(String[] args) {
        // Verificar la cantidad correcta de argumentos
        if (args.length > 4) {
            FSalida.mostrarAyuda();
            return;
        }

        FSalida ficheroSalida;
        boolean trace = false;

        // Verificar la opción de traza
        int startIndex = 0;
        if (args.length > 0 && args[0].equals("-t")) {
            trace = true;
            startIndex = 1;
        }

        // Verificar la opción de ayuda
        if (args.length > startIndex && args[startIndex].equals("-h")) {
            FSalida.mostrarAyuda();
            return;
        }

        // Crear Scanner para leer el archivo de entrada o datos por pantalla
        Scanner scanner = FEntrada.getScanner(args, startIndex);
        // Leer el número de tipos de monedas
        int n = scanner.nextInt();

        // Leer los valores de las monedas en el conjunto A
        int[] A = new int[n];
        for (int i = 0; i < n; i++) {
            A[i] = scanner.nextInt();
        }

        // Leer el valor de la cantidad a devolver C
        int C = scanner.nextInt();

        ficheroSalida = new FSalida(n, A, C);
        ficheroSalida.setTable(trace);
        ficheroSalida.getSolution();

        // Nombres de los archivos de salida
        String outputFile = (args.length > (startIndex + 1)) ? args[startIndex + 1] : null;
        if (outputFile != null) {
            System.out.print("\n Se ha escrito en el archivo de salida \n");
            ficheroSalida.printSolutionToFile(outputFile);
        } else {
            System.out.print("\n Se va a imprimir la solucion a continuacion: \n");
            ficheroSalida.printSolution();
        }
    }
    
    
}
