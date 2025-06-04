package Controlador;

import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.vecmath.Vector3f;

public class Conducta {
    
    public void girar(TransformGroup tg, float grados, String eje) {
        Transform3D leer = new Transform3D();
        Transform3D escribir = new Transform3D();

        tg.getTransform(leer);

        if (eje.equals("x")) {
            escribir.rotX(Math.PI / 180 * grados);
        } else if (eje.equals("y")) {
            escribir.rotY(Math.PI / 180 * grados);
        } else if (eje.equals("z")) {
            escribir.rotZ(Math.PI / 180 * grados);
        }

        leer.mul(escribir);
        tg.setTransform(leer);

    }
    
    public void mover(TransformGroup tg, float x, float y, float z){
        Transform3D leerBrazo = new Transform3D();
        Transform3D moverCuerpo = new Transform3D();

        tg.getTransform(leerBrazo);

        moverCuerpo.set(new Vector3f(x, y, z));
        leerBrazo.mul(moverCuerpo);
        tg.setTransform(leerBrazo);
        
    }
    
    public void escalar(TransformGroup tg,double es){
        Transform3D leer = new Transform3D();
        Transform3D escala = new Transform3D();

        tg.getTransform(leer);
        escala.setScale(es);
        leer.mul(escala);
        tg.setTransform(leer);

    }
}
