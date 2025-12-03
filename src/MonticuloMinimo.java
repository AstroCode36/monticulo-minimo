import java.util.ArrayList;
import java.util.List;

public class MonticuloMinimo {
    private List<Integer> monticulo;

    public MonticuloMinimo() {
        monticulo = new ArrayList<>();
    }

    public MonticuloMinimo(int[] arreglo) {
        monticulo = new ArrayList<>();
        for (int num : arreglo) {
            monticulo.add(num);
        }
        heapificar();
    }

    // GETTERS
    public List<Integer> getMonticulo() { return monticulo; }
    public int getTamanio() { return monticulo.size(); }
    public boolean estaVacio() { return monticulo.isEmpty(); }

    // MÉTODOS FUNDAMENTALES

    // 1. INSERTAR
    public void insertar(int valor) {
        System.out.println("\n=== INSERTANDO valor " + valor + " ===");

        // Agregar al final
        monticulo.add(valor);
        System.out.println("Valor " + valor + " agregado al final del monticulo");

        // Aplicar ajusteAscendente desde la última posición
        ajusteAscendente(monticulo.size() - 1);

        System.out.println("Monticulo después de inserción: " + monticulo);
    }

    // 2. ELIMINAR MÍNIMO
    public Integer eliminarMinimo() {
        if (monticulo.isEmpty()) {
            System.out.println("Monticulo vacío, no se puede eliminar");
            return null;
        }

        System.out.println("\n=== ELIMINANDO MÍNIMO ===");

        // El mínimo está en la raíz (índice 0)
        int minimo = monticulo.get(0);
        System.out.println("Mínimo encontrado: " + minimo);

        // Mover último elemento a la raíz
        int ultimoElemento = monticulo.remove(monticulo.size() - 1);

        if (!monticulo.isEmpty()) {
            monticulo.set(0, ultimoElemento);
            System.out.println("Último elemento (" + ultimoElemento + ") movido a la raíz");

            // Aplicar ajusteDescendente desde la raíz
            ajusteDescendente(0);
        } else {
            System.out.println("Monticulo quedó vacío después de eliminar");
        }

        System.out.println("Monticulo después de eliminar mínimo: " + monticulo);
        return minimo;
    }

    // 3. VER MÍNIMO SIN ELIMINAR
    public Integer verMinimo() {
        if (monticulo.isEmpty()) {
            return null;
        }
        return monticulo.get(0);
    }

    // 4. HEAPIFICAR - Convertir arreglo en montículo
    public void heapificar(int[] arreglo) {
        System.out.println("\n=== HEAPIFICAR: Convirtiendo arreglo en monticulo ===");
        System.out.print("Arreglo original: [");
        for (int i = 0; i < arreglo.length; i++) {
            System.out.print(arreglo[i]);
            if (i < arreglo.length - 1) System.out.print(", ");
        }
        System.out.println("]");

        // Reemplazar monticulo actual con el nuevo arreglo
        monticulo.clear();
        for (int num : arreglo) {
            monticulo.add(num);
        }

        // Aplicar heapificar desde el último nodo interno hacia arriba
        heapificar();

        System.out.println("Monticulo resultante: " + monticulo);
    }

    private void heapificar() {
        // Empezar desde el último nodo interno (padre del último nodo)
        for (int i = (monticulo.size() / 2) - 1; i >= 0; i--) {
            ajusteDescendente(i);
        }
    }

    // 5. AJUSTE ASCENDENTE (también llamado upHeapify o sift-up)
    private void ajusteAscendente(int indice) {
        System.out.println("Aplicando ajuste ascendente desde índice " + indice);

        while (indice > 0) {
            int indicePadre = (indice - 1) / 2;

            System.out.println("  Comparando: monticulo[" + indice + "]=" + monticulo.get(indice) +
                    " con monticulo[" + indicePadre + "]=" + monticulo.get(indicePadre));

            // Si el hijo es mayor o igual que el padre, se cumple la propiedad de montículo
            if (monticulo.get(indice) >= monticulo.get(indicePadre)) {
                System.out.println("  Propiedad de monticulo cumplida, terminando ajuste ascendente");
                break;
            }

            // Si el hijo es menor que el padre, intercambiar
            System.out.println("  Intercambiando posiciones " + indice + " y " + indicePadre);
            intercambiar(indice, indicePadre);
            indice = indicePadre; // Continuar revisando hacia arriba
        }
    }

    // 6. AJUSTE DESCENDENTE (también llamado downHeapify o sift-down)
    private void ajusteDescendente(int indice) {
        System.out.println("Aplicando ajuste descendente desde índice " + indice);

        int tamanio = monticulo.size();

        while (true) {
            int indiceHijoIzquierdo = 2 * indice + 1;
            int indiceHijoDerecho = 2 * indice + 2;
            int indiceMenor = indice;

            // Encontrar el menor entre padre y sus hijos
            if (indiceHijoIzquierdo < tamanio && monticulo.get(indiceHijoIzquierdo) < monticulo.get(indiceMenor)) {
                indiceMenor = indiceHijoIzquierdo;
                System.out.println("  Hijo izquierdo es menor: monticulo[" + indiceHijoIzquierdo + "]=" + monticulo.get(indiceHijoIzquierdo));
            }

            if (indiceHijoDerecho < tamanio && monticulo.get(indiceHijoDerecho) < monticulo.get(indiceMenor)) {
                indiceMenor = indiceHijoDerecho;
                System.out.println("  Hijo derecho es menor: monticulo[" + indiceHijoDerecho + "]=" + monticulo.get(indiceHijoDerecho));
            }

            // Si el padre ya es el menor, se cumple la propiedad de montículo
            if (indiceMenor == indice) {
                System.out.println("  Propiedad de monticulo cumplida, terminando ajuste descendente");
                break;
            }

            // Intercambiar con el hijo menor
            System.out.println("  Intercambiando posiciones " + indice + " y " + indiceMenor);
            intercambiar(indice, indiceMenor);
            indice = indiceMenor; // Continuar revisando hacia abajo
        }
    }

    // 7. MÉTODO AUXILIAR PARA INTERCAMBIAR
    private void intercambiar(int i, int j) {
        int temporal = monticulo.get(i);
        monticulo.set(i, monticulo.get(j));
        monticulo.set(j, temporal);
    }

    // 8. BUSCAR UN VALOR
    public boolean buscar(int valor) {
        for (int i = 0; i < monticulo.size(); i++) {
            if (monticulo.get(i) == valor) {
                System.out.println("Valor " + valor + " encontrado en índice " + i);
                return true;
            }
        }
        System.out.println("Valor " + valor + " no encontrado en el monticulo");
        return false;
    }

    // 9. OBTENER ALTURA DEL MONTÍCULO
    public int getAltura() {
        if (monticulo.isEmpty()) return 0;
        return (int) (Math.log(monticulo.size()) / Math.log(2)) + 1;
    }

    // 10. MOSTRAR MONTÍCULO EN FORMATO DE ÁRBOL
    public void mostrarComoArbol() {
        if (monticulo.isEmpty()) {
            System.out.println("Monticulo vacío");
            return;
        }

        System.out.println("\n=== REPRESENTACIÓN DEL MONTÍCULO COMO ÁRBOL ===");
        System.out.println("Monticulo como arreglo: " + monticulo);
        System.out.println("Altura: " + getAltura());
        System.out.println();

        int altura = getAltura();
        int nivel = 0;
        int elementosPorNivel = 1;
        int indice = 0;

        while (indice < monticulo.size()) {
            // Calcular espacios para centrar
            int espacios = (int) Math.pow(2, altura - nivel - 1) - 1;

            // Imprimir espacios iniciales
            for (int i = 0; i < espacios; i++) {
                System.out.print("   ");
            }

            // Imprimir elementos del nivel
            for (int i = 0; i < elementosPorNivel && indice < monticulo.size(); i++) {
                System.out.printf("%3d", monticulo.get(indice));
                indice++;

                // Espacios entre elementos
                if (i < elementosPorNivel - 1 && indice < monticulo.size()) {
                    for (int j = 0; j < 2 * espacios + 1; j++) {
                        System.out.print("   ");
                    }
                }
            }

            System.out.println();
            nivel++;
            elementosPorNivel *= 2;

            // Imprimir conexiones si no es el último nivel
            if (indice < monticulo.size()) {
                imprimirConexiones(espacios, elementosPorNivel / 2);
            }
        }
    }

    private void imprimirConexiones(int espacios, int elementosAnteriores) {
        // Calcular espacios para las líneas de conexión
        int espaciosEntreLineas = espacios * 2 + 1;

        for (int linea = 0; linea < espacios; linea++) {
            // Espacios iniciales
            for (int i = 0; i < espacios - linea - 1; i++) {
                System.out.print("   ");
            }

            // Imprimir conexiones
            for (int i = 0; i < elementosAnteriores; i++) {
                System.out.print("  /");

                // Espacios entre conexiones
                for (int j = 0; j < 2 * linea + 1; j++) {
                    System.out.print("   ");
                }

                System.out.print("\\ ");

                // Espacios entre pares de conexiones
                if (i < elementosAnteriores - 1) {
                    for (int j = 0; j < 2 * (espacios - linea - 1); j++) {
                        System.out.print("   ");
                    }
                }
            }
            System.out.println();
        }
    }

    // 11. VERIFICAR PROPIEDAD DE MONTÍCULO
    public boolean verificarPropiedadMonticulo() {
        for (int i = 0; i < monticulo.size(); i++) {
            int indiceHijoIzquierdo = 2 * i + 1;
            int indiceHijoDerecho = 2 * i + 2;

            if (indiceHijoIzquierdo < monticulo.size() && monticulo.get(i) > monticulo.get(indiceHijoIzquierdo)) {
                System.out.println("ERROR: Propiedad de monticulo violada en índice " + i);
                System.out.println("  monticulo[" + i + "]=" + monticulo.get(i) + " > monticulo[" + indiceHijoIzquierdo + "]=" + monticulo.get(indiceHijoIzquierdo));
                return false;
            }

            if (indiceHijoDerecho < monticulo.size() && monticulo.get(i) > monticulo.get(indiceHijoDerecho)) {
                System.out.println("ERROR: Propiedad de monticulo violada en índice " + i);
                System.out.println("  monticulo[" + i + "]=" + monticulo.get(i) + " > monticulo[" + indiceHijoDerecho + "]=" + monticulo.get(indiceHijoDerecho));
                return false;
            }
        }

        System.out.println("[OK] Propiedad de monticulo mínimo verificada correctamente");
        return true;
    }

    // 12. ORDENAR USANDO HEAPSORT
    public List<Integer> ordenarConHeapSort() {
        System.out.println("\n=== APLICANDO HEAPSORT ===");

        if (monticulo.isEmpty()) {
            System.out.println("Monticulo vacío, nada que ordenar");
            return new ArrayList<>();
        }

        // Crear copia para no modificar el monticulo original
        List<Integer> copia = new ArrayList<>(monticulo);
        List<Integer> resultado = new ArrayList<>();

        System.out.println("Monticulo original: " + copia);

        // Construir monticulo mínimo
        MonticuloMinimo monticuloTemporal = new MonticuloMinimo();
        for (int num : copia) {
            monticuloTemporal.insertar(num);
        }

        System.out.println("Monticulo construido: " + monticuloTemporal.getMonticulo());

        // Extraer elementos en orden
        while (!monticuloTemporal.estaVacio()) {
            Integer min = monticuloTemporal.eliminarMinimo();
            resultado.add(min);
        }

        System.out.println("Arreglo ordenado: " + resultado);
        return resultado;
    }

    @Override
    public String toString() {
        return monticulo.toString();
    }
}