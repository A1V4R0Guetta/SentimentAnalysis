package controlador.main;

import modelo.datos.CarteraDatos;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Main implements Serializable {

    private static final long serialVersionUID = -1065341850225848464L;
    private static String nombreFichero = "autoguardado.bin";
    private static CarteraDatos estaSeraLaVariableAGuardar = new CarteraDatos();

    public static void init(){

        //////////////////////////////////////////////////////////////////////
        //                  IMPORTAMOS FICHERO DE DATOS                     //
        //////////////////////////////////////////////////////////////////////
        try {
            readObject(nombreFichero);
        } catch (IOException e) {
            System.out.print("No existe un fichero inicial.");
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
        //////////////////////////////////////////////////////////////////////
        //////////////////////////////////////////////////////////////////////

    }

    public static void destroy(){

        try {
            writeObject(nombreFichero);
        } catch (IOException e) {
            System.out.println("EXPORTACION FALLIDA: "+e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }

    }

    public static void main(String[] args){

        init();         //Para cargar el programa al iniciar

        //////////////////////////////////////////////////////////////////////////////////
        //                  L I S T O     P A R A     P R O G R A M A R                 //
        //////////////////////////////////////////////////////////////////////////////////



        destroy();      //Para guardar el programa al finalizar

    }

    public static void exportarPrograma(){
        Scanner scanner = new Scanner(System.in);
        String texto;
        String nombre = "";

        System.out.print("¿Desea guardar el programa en un fichero nuevo? (s/n): ");
        texto = scanner.nextLine();

        if (texto.equals("s")){
            System.out.print("Especificar nombre del fichero (Ej: nombre.bin): ");
            texto = scanner.nextLine();
            nombre = nombreFichero = texto;
        }else {                    //NOMBRE POR DEFECTO
            nombre = nombreFichero;
        }

        try {
            writeObject(nombre);
            System.out.println("EXPORTACION COMPLETADA");
        } catch (IOException e) {
            System.out.println("EXPORTACION FALLIDA: "+e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void writeObject(String nombre) throws IOException, ClassNotFoundException {

        FileOutputStream fos = new FileOutputStream(nombre);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(estaSeraLaVariableAGuardar);
        oos.close();
        fos.close();

    }

    private static void readObject(String nombre) throws IOException, ClassNotFoundException {

        FileInputStream fis = new FileInputStream(nombre);
        ObjectInputStream ois = new ObjectInputStream(fis);
        estaSeraLaVariableAGuardar = (CarteraDatos) ois.readObject();
        ois.close();
        fis.close();

    }

}
