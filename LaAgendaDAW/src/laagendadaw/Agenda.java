package laagendadaw;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

public class Agenda implements Serializable {

    private ArrayList<Contactos> misContactos = new ArrayList();
    private String nombreAgenda;

    public Agenda(String nombreAgenda) {
        this.nombreAgenda = nombreAgenda;
    }

    public void registrarContactos() {
        Scanner variable = new Scanner(System.in);
        System.out.println("Introduce el nombre del nuevo contacto:");
        String nombre = variable.nextLine();
        System.out.println("Introduce los apellidos del nuevo contacto:");
        String apellidos = variable.nextLine();

        while (repetirContacto(nombre, apellidos)) {
            System.out.println("ERROR, no se puede tener mas de un contacto con el mismo nombre y apellido.");
            System.out.println("Introduce un nombre diferente para el nuevo contacto:");
            nombre = variable.nextLine();
            System.out.println("Introduce unos apellidos diferentes para el nuevo contacto:");
            apellidos = variable.nextLine();
        }

        System.out.println("Introduce el telefono del nuevo contacto:");
        String telefono = variable.nextLine();
        System.out.println("Introduce el e-mail del nuevo contacto:");
        variable = new Scanner(System.in);
        String email = variable.nextLine();
        System.out.println("Introduce la edad del nuevo contacto:");
        String edad = variable.nextLine();

        Contactos c1 = new Contactos(nombre, apellidos, telefono, email, edad);
        this.misContactos.add(c1);
    }

    public void verContactos() {
        for (Contactos ayuda : this.misContactos) {
            System.out.println("El contacto --> " + ayuda.getNombre() + " " + ayuda.getApellidos());
            System.out.println("Su telefono es: " + ayuda.getTelefono());
            System.out.println("Su e-mail es: " + ayuda.getEmail());
            System.out.println("Su edad es: " + ayuda.getEdad());
            System.out.println("-------------------------------------------------------------");
        }
    }

    public void buscarContacto() {
        Scanner variable = new Scanner(System.in);
        boolean interuptor = false;
        System.out.println("Introduzca el nombre del contacto a buscar:");
        String nombre = variable.nextLine();
        System.out.println("Introduzca los apellidos del contacto a buscar:");
        String apellidos = variable.nextLine();
        for (Contactos ayuda : this.misContactos) {
            if (nombre.equalsIgnoreCase(ayuda.getNombre()) && apellidos.equalsIgnoreCase(ayuda.getApellidos())) {
                System.out.println("El contacto --> " + ayuda.getNombre() + " " + ayuda.getApellidos());
                System.out.println("Su telefono es: " + ayuda.getTelefono());
                System.out.println("Su e-mail es: " + ayuda.getEmail());
                System.out.println("Su edad es: " + ayuda.getEdad());
                interuptor = true;
            }
        }
        if (interuptor == false) {
            System.out.println("Contacto no encontrado.");
        }
    }

    public boolean eliminarContactos() {
        Scanner variable = new Scanner(System.in);
        boolean interuptor = false;
        System.out.println("Introduzca el nombre del contacto a eliminar:");
        String nombre = variable.nextLine();
        System.out.println("Introduzca los apellidos del contacto a eliminar:");
        String apellidos = variable.nextLine();
        for (Contactos ayuda : this.misContactos) {
            if (nombre.equalsIgnoreCase(ayuda.getNombre()) && apellidos.equalsIgnoreCase(ayuda.getApellidos())) {
                this.misContactos.remove(ayuda);
                interuptor = true;
                System.out.println("Contacto eliminado con exito.");
                return true;
            }
        }
        if (interuptor == false) {
            System.out.println("Contacto no encontrado.");
        }
        return true;
    }

    public String getNombreAgenda() {
        return nombreAgenda;
    }

    public boolean repetirContacto(String nombre, String apellidos) {
        for (int x = 0; x < this.misContactos.size(); x++) {
            if (nombre.equalsIgnoreCase(this.misContactos.get(x).getNombre()) && apellidos.equalsIgnoreCase(this.misContactos.get(x).getApellidos())) {
                return true;
            }
        }
        return false;
    }
}
