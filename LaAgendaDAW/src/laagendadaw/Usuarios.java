package laagendadaw;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

public class Usuarios implements Serializable {

    private String nombre;
    private String clave;

    public Usuarios() {
        this.nombre = "";
        this.clave = "";
    }

    public Agenda crearUsuario() {
        Scanner variable = new Scanner(System.in);
        boolean ayuda;
        boolean ayuda2;

        do {
            ayuda = true;
            System.out.println("Introduzca el nombre de su nuevo usuario:");
            this.nombre = variable.nextLine();
            for (int x = 0; x < this.nombre.length(); x++) {
                if (this.nombre.charAt(x) == ' ') {
                    ayuda = false;
                }
            }
            if (ayuda == true && repetirUsuario(this.nombre)) {
                System.err.println("Error, has introducido un nombre de usuario existente.");
            } else if (ayuda == false) {
                System.err.println("Error, no se puede introducir espacios en el nombre.");
            }
        } while (ayuda == false || repetirUsuario(this.nombre));

        do {
            ayuda2 = true;
            System.out.println("Introduzca la clave de su nuevo usuario:");
            this.clave = variable.nextLine();
            for (int x = 0; x < this.clave.length(); x++) {
                if (this.clave.charAt(x) == ' ') {
                    ayuda2 = false;
                }
            }
            if (ayuda2 == false) {
                System.err.println("Error, no se puede introducir espacios en la clave.");
            }
        } while (ayuda2 == false);

        try {
            FileWriter miEscritor = new FileWriter("usuarios.txt", true);
            BufferedWriter miBuffer = new BufferedWriter(miEscritor);
            miBuffer.write(this.nombre);
            miBuffer.write(" ");
            miBuffer.close();
            miEscritor.close();
        } catch (IOException e) {
            System.err.println("ARCHIVO NO ENCONTRADO");
        }

        try {
            FileWriter miEscritor = new FileWriter("usuarios.txt", true);
            BufferedWriter miBuffer = new BufferedWriter(miEscritor);
            miBuffer.write(this.clave);
            miBuffer.write(" ");
            miBuffer.close();
            miEscritor.close();
        } catch (IOException e) {
            System.err.println("ARCHIVO NO ENCONTRADO");
        }

        Agenda a1 = new Agenda("agenda" + this.nombre + ".dat");
        return a1;
    }

    public Agenda logarseUsuario() {
        Scanner variable = new Scanner(System.in);
        try {
            FileReader miLector = new FileReader("usuarios.txt");
            BufferedReader miBuffer = new BufferedReader(miLector);
            String lectura = "";
            ArrayList<String> misUsuarios = new ArrayList();

            while (lectura != null) {
                try {
                    lectura = miBuffer.readLine();
                    String[] palabrasLinea = lectura.split(" ");
                    for (String palabra : palabrasLinea) {
                        misUsuarios.add(palabra);

                    }
                } catch (java.lang.NullPointerException ex) {
                }
            }

            int contador = 0;
            do {
                System.out.println("Introduzca el nombre de su usuario:");
                this.nombre = variable.nextLine();
                System.out.println("Introduzca la clave de su usuario:");
                this.clave = variable.nextLine();

                for (int x = 0; x < misUsuarios.size(); x = x + 2) {
                    if (this.nombre.equals(misUsuarios.get(x))) {
                        if (this.clave.equals(misUsuarios.get(x + 1))) {
                            String nombre = "agenda" + this.nombre + ".dat";
                            return cargarAgenda(nombre);
                        }
                    }
                }
                contador++;
            } while (contador != 3);
        } catch (IOException e) {
            System.err.println("ERROR, no puedes logarte porque hay ningun usuario en el sistema.");
        }
        return null;
    }

    public void guardarAgenda(Agenda laAgenda) {
        try {
            FileOutputStream miFlujoSalida = new FileOutputStream(laAgenda.getNombreAgenda());
            ObjectOutputStream guardarObjeto = new ObjectOutputStream(miFlujoSalida);
            guardarObjeto.writeObject(laAgenda);
            guardarObjeto.close();
            miFlujoSalida.close();
        } catch (IOException e) {
            System.out.println("No ha sido posible guardar la agenda: " + e.getMessage());
        }
    }

    public static Agenda cargarAgenda(String nombre) {
        Agenda laAgenda = null;
        try {
            FileInputStream miFlujoEntrada = new FileInputStream(nombre);
            ObjectInputStream objetoEntrada = new ObjectInputStream(miFlujoEntrada);
            laAgenda = (Agenda) objetoEntrada.readObject();
            objetoEntrada.close();
            miFlujoEntrada.close();
        } catch (FileNotFoundException e) {
            System.out.println("Archivo no encontrado: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Problemas al cargar la agenda: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println("Problemas al cargar la agenda: " + e.getMessage());
        }
        return laAgenda;
    }

    public boolean repetirUsuario(String nombre) {
        try {
            FileReader miLector = new FileReader("usuarios.txt");
            BufferedReader miBuffer = new BufferedReader(miLector);
            String lectura = "";
            ArrayList<String> misUsuarios = new ArrayList();

            while (lectura != null) {
                try {
                    lectura = miBuffer.readLine();
                    String[] palabrasLinea = lectura.split(" ");
                    for (String palabra : palabrasLinea) {
                        misUsuarios.add(palabra);

                    }
                } catch (java.lang.NullPointerException ex) {
                }
            }
            for (int x = 0; x < misUsuarios.size(); x = x + 2) {
                if (nombre.equalsIgnoreCase(misUsuarios.get(x))) {
                    return true;
                }
            }
        } catch (IOException e) {
        }
        return false;
    }
}
