import java.util.Scanner;

public class Main {
    private static MonticuloMinimo monticulo = new MonticuloMinimo();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("=== SISTEMA DE MONTICULO MINIMO ===");
        System.out.println("Implementacion de monticulo minimo con ajuste ascendente y descendente");

        menuPrincipal();
    }

    private static void menuPrincipal() {
        int opcion;

        do {
            mostrarMenu();
            opcion = leerEntero("Seleccione una opcion: ");

            switch (opcion) {
                case 1:
                    insertarValor();
                    break;
                case 2:
                    eliminarMinimo();
                    break;
                case 3:
                    verMinimo();
                    break;
                case 4:
                    convertirArregloAMonticulo();
                    break;
                case 5:
                    buscarValor();
                    break;
                case 6:
                    mostrarMonticulo();
                    break;
                case 7:
                    mostrarComoArbol();
                    break;
                case 8:
                    verificarPropiedadMonticulo();
                    break;
                case 9:
                    ejecutarHeapSort();
                    break;
                case 10:
                    ejecutarPruebasAutomaticas();
                    break;
                case 11:
                    System.out.println("Gracias por usar el sistema! Hasta luego!");
                    break;
                default:
                    System.out.println("Opcion no valida. Intente nuevamente.");
            }

            if (opcion != 11) {
                System.out.println("\nPresione Enter para continuar...");
                scanner.nextLine();
            }

        } while (opcion != 11);

        scanner.close();
    }

    private static void mostrarMenu() {
        System.out.println("\n=== MENU PRINCIPAL - MONTICULO MINIMO ===");
        System.out.println("1. Insertar valor");
        System.out.println("2. Eliminar minimo");
        System.out.println("3. Ver minimo");
        System.out.println("4. Convertir arreglo a monticulo");
        System.out.println("5. Buscar valor");
        System.out.println("6. Mostrar monticulo como arreglo");
        System.out.println("7. Mostrar monticulo como arbol");
        System.out.println("8. Verificar propiedad de monticulo");
        System.out.println("9. Ejecutar HeapSort");
        System.out.println("10. Ejecutar pruebas automaticas");
        System.out.println("11. Salir");
    }

    private static void insertarValor() {
        System.out.println("\n=== INSERTAR VALOR EN MONTICULO ===");

        int valor = leerEntero("Ingrese el valor a insertar: ");
        monticulo.insertar(valor);
    }

    private static void eliminarMinimo() {
        System.out.println("\n=== ELIMINAR VALOR MINIMO ===");

        Integer minimo = monticulo.eliminarMinimo();

        if (minimo != null) {
            System.out.println("Valor minimo eliminado: " + minimo);
        }
    }

    private static void verMinimo() {
        System.out.println("\n=== VER VALOR MINIMO ===");

        Integer minimo = monticulo.verMinimo();

        if (minimo != null) {
            System.out.println("Valor minimo actual: " + minimo);
        } else {
            System.out.println("El monticulo esta vacio");
        }
    }

    private static void convertirArregloAMonticulo() {
        System.out.println("\n=== CONVERTIR ARREGLO A MONTICULO ===");

        System.out.print("Cuantos elementos tendra el arreglo? ");
        int n = leerEntero("");

        int[] arreglo = new int[n];

        System.out.println("Ingrese los elementos del arreglo:");
        for (int i = 0; i < n; i++) {
            arreglo[i] = leerEntero("Elemento " + (i + 1) + ": ");
        }

        monticulo.heapificar(arreglo);
    }

    private static void buscarValor() {
        System.out.println("\n=== BUSCAR VALOR EN MONTICULO ===");

        int valor = leerEntero("Ingrese el valor a buscar: ");
        boolean encontrado = monticulo.buscar(valor);

        if (encontrado) {
            System.out.println("El valor " + valor + " esta en el monticulo");
        } else {
            System.out.println("El valor " + valor + " NO esta en el monticulo");
        }
    }

    private static void mostrarMonticulo() {
        System.out.println("\n=== MONTICULO ACTUAL ===");
        System.out.println("Monticulo (como arreglo): " + monticulo);
        System.out.println("Tamano: " + monticulo.getTamanio());
        System.out.println("Esta vacio? " + monticulo.estaVacio());
    }

    private static void mostrarComoArbol() {
        monticulo.mostrarComoArbol();
    }

    private static void verificarPropiedadMonticulo() {
        System.out.println("\n=== VERIFICANDO PROPIEDAD DE MONTICULO ===");
        boolean esMonticuloValido = monticulo.verificarPropiedadMonticulo();

        if (esMonticuloValido) {
            System.out.println("El monticulo cumple con la propiedad de monticulo minimo");
        } else {
            System.out.println("ADVERTENCIA: El monticulo NO cumple con la propiedad de monticulo minimo");
        }
    }

    private static void ejecutarHeapSort() {
        System.out.println("\n=== EJECUTANDO HEAPSORT ===");
        monticulo.ordenarConHeapSort();
    }

    private static void ejecutarPruebasAutomaticas() {
        System.out.println("\n=== EJECUTANDO PRUEBAS AUTOMATICAS ===");

        // Guardar monticulo actual
        MonticuloMinimo monticuloOriginal = monticulo;

        // Crear nuevo monticulo para pruebas
        monticulo = new MonticuloMinimo();

        System.out.println("\n1. PRUEBA DE INSERCIONES SECUENCIALES");
        System.out.println("Insertando valores: 10, 5, 15, 3, 7, 12, 20");

        int[] valoresPrueba = {10, 5, 15, 3, 7, 12, 20};
        for (int valor : valoresPrueba) {
            monticulo.insertar(valor);
        }

        System.out.println("\n2. MOSTRANDO MONTICULO COMO ARBOL");
        monticulo.mostrarComoArbol();

        System.out.println("\n3. VERIFICANDO PROPIEDAD DE MONTICULO");
        monticulo.verificarPropiedadMonticulo();

        System.out.println("\n4. PRUEBA DE ELIMINACION MINIMA");
        System.out.println("Eliminando minimo 3 veces...");

        for (int i = 0; i < 3; i++) {
            Integer min = monticulo.eliminarMinimo();
            System.out.println("Eliminado: " + min);
        }

        System.out.println("\n5. MONTICULO DESPUES DE ELIMINACIONES");
        monticulo.mostrarComoArbol();

        System.out.println("\n6. PRUEBA DE HEAPIFICAR");
        System.out.println("Convirtiendo arreglo [9, 4, 2, 8, 1, 6, 3] en monticulo...");
        int[] arregloPrueba = {9, 4, 2, 8, 1, 6, 3};
        monticulo.heapificar(arregloPrueba);
        monticulo.mostrarComoArbol();

        System.out.println("\n7. PRUEBA DE HEAPSORT");
        monticulo.ordenarConHeapSort();

        System.out.println("\n=== PRUEBAS COMPLETADAS ===");
        System.out.println("Restaurando monticulo original...");
        monticulo = monticuloOriginal;
    }

    // Metodo auxiliar para leer enteros
    private static int leerEntero(String mensaje) {
        while (true) {
            try {
                System.out.print(mensaje);
                int valor = scanner.nextInt();
                scanner.nextLine(); // Limpiar buffer
                return valor;
            } catch (Exception e) {
                System.out.println("Error: Debe ingresar un numero entero valido.");
                scanner.nextLine(); // Limpiar buffer
            }
        }
    }
}
