package laagendadaw;

import java.util.Scanner;

public class main {

    public static String primeraPresentacion() {
        Scanner variable = new Scanner(System.in);
        System.out.println("BIENVENIDO A LA AGENDA DAW");
        System.out.println("1. Crear un nuevo usuario");
        System.out.println("2. Logarse como usuario");
        String eleccion = variable.nextLine();
        return eleccion;
    }

    public static String segundaPresentacion() {
        Scanner variable = new Scanner(System.in);
        System.out.println("MENU DE LA AGENDA DAW");
        System.out.println("1. Registrar contacto");
        System.out.println("2. Ver contactos");
        System.out.println("3. Buscar contacto");
        System.out.println("4. Eliminar contacto");
        System.out.println("5. Salir");
        String eleccion = variable.nextLine();
        return eleccion;
    }

    public static void menuFinal() {
        Usuarios u1 = new Usuarios();
        Agenda a1 = null;
        boolean salida = false;
        do {
            switch (primeraPresentacion()) {
                case "1":
                    a1 = u1.crearUsuario();
                    boolean salida2 = false;
                    do {
                        switch (segundaPresentacion()) {
                            case "1":
                                a1.registrarContactos();
                                break;
                            case "2":
                                a1.verContactos();
                                break;
                            case "3":
                                a1.buscarContacto();
                                break;
                            case "4":
                                a1.eliminarContactos();
                                break;
                            case "5":
                                u1.guardarAgenda(a1);
                                salida2 = true;
                                break;
                            default:
                                System.out.println("Error, debes introducir del (1-5)");
                                break;
                        }
                    } while (salida2 == false);
                    salida = true;
                    break;
                case "2":
                    a1 = u1.logarseUsuario();
                    boolean salida3 = false;
                    if (a1 != null) {
                        do {
                            switch (segundaPresentacion()) {
                                case "1":
                                    a1.registrarContactos();
                                    break;
                                case "2":
                                    a1.verContactos();
                                    break;
                                case "3":
                                    a1.buscarContacto();
                                    break;
                                case "4":
                                    a1.eliminarContactos();
                                    break;
                                case "5":
                                    u1.guardarAgenda(a1);
                                    salida3 = true;
                                    break;
                                default:
                                    System.out.println("Error, debes introducir del (1-5)");
                                    break;
                            }
                        } while (salida3 == false);
                    }
                    salida = true;
                    break;
                default:
                    System.out.println("Error, debes introducir 1 o 2");
                    break;
            }
        } while (salida == false);
    }

    public static void main(String[] args) {
        menuFinal();
    }
}
