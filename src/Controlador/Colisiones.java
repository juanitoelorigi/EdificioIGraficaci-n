package Controlador;

import com.sun.j3d.utils.geometry.Box;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.vecmath.Vector3d;

public class Colisiones {

    /**
     * Comprueba si dos cajas (definidas por su posición, geometría Box y TransformGroup)
     * colisionan usando el método AABB (Axis-Aligned Bounding Box).
     * [VERSIÓN CON MENSAJES DE DEPURACIÓN]
     *
     * @param ubicacion Posición central del primer objeto (jugador).
     * @param caja1     Geometría Box del primer objeto (jugador).
     * @param tg2       TransformGroup del segundo objeto (obstáculo).
     * @param caja2     Geometría Box del segundo objeto (obstáculo).
     * @return true si hay colisión (solapamiento en los 3 ejes), false en caso contrario.
     */
      public boolean choco(Vector3d ubicacion, Box caja1, TransformGroup tg2, Box caja2) {
        System.out.println("--- Llamada a choco (v2) ---");
        if (ubicacion == null || caja1 == null || tg2 == null || caja2 == null) {
             System.out.println("  Error: Parámetro null en choco.");
             return false;
        }

        Vector3d centroA = ubicacion;
        Vector3d centroB = posicion(tg2); // Obtenemos la posición del objeto B
        // Asegurarse que las cajas tengan dimensiones válidas antes de usarlas
        if (caja1.getXdimension() <= 0 || caja1.getYdimension() <= 0 || caja1.getZdimension() <= 0 ||
            caja2.getXdimension() <= 0 || caja2.getYdimension() <= 0 || caja2.getZdimension() <= 0) {
            System.out.println("  Advertencia: Una de las cajas tiene dimensión cero o negativa.");
            // Podría ser válido no colisionar en este caso, o indicar un error.
        }
        Vector3d semiAnchoA = new Vector3d(caja1.getXdimension(), caja1.getYdimension(), caja1.getZdimension());
        Vector3d semiAnchoB = new Vector3d(caja2.getXdimension(), caja2.getYdimension(), caja2.getZdimension());


        System.out.println("  Player Pos (centroA): " + centroA);
        System.out.println("  Object Pos (centroB): " + centroB);
        System.out.println("  Player Half-Dims (semiAnchoA): " + semiAnchoA);
        System.out.println("  Object Half-Dims (semiAnchoB): " + semiAnchoB);

        // Calcular límites Min/Max para cada objeto y eje explícitamente
        double minAx = centroA.x - semiAnchoA.x; double maxAx = centroA.x + semiAnchoA.x;
        double minAy = centroA.y - semiAnchoA.y; double maxAy = centroA.y + semiAnchoA.y;
        double minAz = centroA.z - semiAnchoA.z; double maxAz = centroA.z + semiAnchoA.z;

        double minBx = centroB.x - semiAnchoB.x; double maxBx = centroB.x + semiAnchoB.x;
        double minBy = centroB.y - semiAnchoB.y; double maxBy = centroB.y + semiAnchoB.y;
        double minBz = centroB.z - semiAnchoB.z; double maxBz = centroB.z + semiAnchoB.z;

        // Realizar las comprobaciones de solapamiento por eje directamente
        // Condición: (MaxA >= MinB) && (MinA <= MaxB)
        boolean overlapX = (maxAx >= minBx) && (minAx <= maxBx);
        boolean overlapY = (maxAy >= minBy) && (minAy <= maxBy);
        boolean overlapZ = (maxAz >= minBz) && (minAz <= maxBz);

        // Calcular resultado final
        boolean resultado = overlapX && overlapY && overlapZ;

        // Imprimir resultado detallado de forma clara
        System.out.println("  Overlap Check -> X: " + overlapX + " | Y: " + overlapY + " | Z: " + overlapZ);
        System.out.println("  Resultado Final (X && Y && Z): " + resultado);

        return resultado;
    }
    /**
     * Obtiene el vector de posición (traslación) de un TransformGroup.
     * @param tg El TransformGroup cuya posición se quiere obtener.
     * @return Un Vector3d con la posición (x, y, z).
     */
    public Vector3d posicion(TransformGroup tg) {
        if (tg == null) {
            System.err.println("Error en Colisiones.posicion: TransformGroup es null.");
            return new Vector3d(0, 0, 0); // Devolver origen o lanzar excepción
        }
        Transform3D transformActual = new Transform3D();
        tg.getTransform(transformActual); // Obtener la matriz de transformación completa
        Vector3d vectorPosicion = new Vector3d();
        transformActual.get(vectorPosicion); // Extraer el componente de traslación de la matriz
        return vectorPosicion;
    }

}