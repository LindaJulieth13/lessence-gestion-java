import dao.PerfumeDAO;
import modelo.Perfume;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class App {

    public static void main(String[] args) {

        Scanner entrada = new Scanner(System.in);
        PerfumeDAO dao = new PerfumeDAO();
        boolean continuar = true;

        while (continuar) {

            System.out.println("\n===== SISTEMA L'ESSENCE =====");
            System.out.println("1. Registrar perfume");
            System.out.println("2. Consultar perfumes");
            System.out.println("3. Actualizar perfume");
            System.out.println("4. Eliminar perfume");
            System.out.println("5. Salir");
            System.out.print("Seleccione una opcion: ");

            String opcion = entrada.nextLine();

            try {

                switch (opcion) {

                    case "1":
                        registrarPerfume(entrada, dao);
                        break;

                    case "2":
                        consultarPerfumes(dao);
                        break;

                    case "3":
                        actualizarPerfume(entrada, dao);
                        break;

                    case "4":
                        eliminarPerfume(entrada, dao);
                        break;

                    case "5":
                        continuar = false;
                        System.out.println("Gracias por utilizar L'ESSENCE.");
                        break;

                    default:
                        System.out.println("Opcion no valida.");
                }

            } catch (SQLException e) {
                System.out.println("Error en la base de datos: " + e.getMessage());

            } catch (NumberFormatException e) {
                System.out.println("Error: debes ingresar un valor numerico valido.");
            }
        }

        entrada.close();
    }

    // REGISTRAR
    private static void registrarPerfume(Scanner entrada, PerfumeDAO dao)
            throws SQLException {

        System.out.println("\n--- REGISTRAR PERFUME ---");

        System.out.print("Nombre: ");
        String nombre = entrada.nextLine();

        System.out.print("Marca: ");
        String marca = entrada.nextLine();

        System.out.print("Categoria: ");
        String categoria = entrada.nextLine();

        System.out.print("Precio: ");
        double precio = Double.parseDouble(entrada.nextLine());

        System.out.print("Stock: ");
        int stock = Integer.parseInt(entrada.nextLine());

        Perfume perfume = new Perfume(nombre, marca, categoria, precio, stock);

        if (dao.insertar(perfume)) {
            System.out.println("Perfume registrado correctamente.");
        }
    }

    // CONSULTAR
    private static void consultarPerfumes(PerfumeDAO dao)
            throws SQLException {

        System.out.println("\n--- LISTADO DE PERFUMES ---");

        List<Perfume> perfumes = dao.listar();

        if (perfumes.isEmpty()) {
            System.out.println("No hay perfumes registrados.");
        } else {
            for (Perfume perfume : perfumes) {
                System.out.println(perfume);
            }
        }
    }

    // ACTUALIZAR
    private static void actualizarPerfume(Scanner entrada, PerfumeDAO dao)
            throws SQLException {

        System.out.println("\n--- ACTUALIZAR PERFUME ---");

        System.out.print("Ingrese el ID del perfume: ");
        int id = Integer.parseInt(entrada.nextLine());

        System.out.print("Nuevo nombre: ");
        String nombre = entrada.nextLine();

        System.out.print("Nueva marca: ");
        String marca = entrada.nextLine();

        System.out.print("Nueva categoria: ");
        String categoria = entrada.nextLine();

        System.out.print("Nuevo precio: ");
        double precio = Double.parseDouble(entrada.nextLine());

        System.out.print("Nuevo stock: ");
        int stock = Integer.parseInt(entrada.nextLine());

        if (dao.actualizar(id, nombre, marca, categoria, precio, stock)) {
            System.out.println("Perfume actualizado correctamente.");
        } else {
            System.out.println("No se encontro un perfume con ese ID.");
        }
    }

    // ELIMINAR
    private static void eliminarPerfume(Scanner entrada, PerfumeDAO dao)
            throws SQLException {

        System.out.println("\n--- ELIMINAR PERFUME ---");

        System.out.print("Ingrese el ID del perfume: ");
        int id = Integer.parseInt(entrada.nextLine());

        System.out.print("¿Esta seguro de eliminarlo? (S/N): ");
        String confirmacion = entrada.nextLine();

        if (confirmacion.equalsIgnoreCase("S")) {

            if (dao.eliminar(id)) {
                System.out.println("Perfume eliminado correctamente.");
            } else {
                System.out.println("No se encontro un perfume con ese ID.");
            }

        } else {
            System.out.println("Operacion cancelada.");
        }
    }
}