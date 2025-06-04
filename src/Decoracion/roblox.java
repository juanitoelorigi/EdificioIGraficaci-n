package Decoracion;

import Vista.Apariencia;
import Vista.Textura;
import Controlador.Conducta;
import com.sun.j3d.utils.behaviors.mouse.MouseRotate;
import com.sun.j3d.utils.geometry.Box;
import com.sun.j3d.utils.geometry.Primitive;
import com.sun.j3d.utils.geometry.Sphere;
import javax.media.j3d.BoundingSphere;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.vecmath.Point3d;
import javax.vecmath.Vector3d;
import javax.vecmath.Vector3f;

public class roblox {

    int paraTextura = Primitive.GENERATE_NORMALS + Primitive.GENERATE_TEXTURE_COORDS;
    Textura textura = new Textura();
    
    int pasos = 0;
    Conducta cond = new Conducta();
    BranchGroup bgRaiz = new BranchGroup();
    TransformGroup tgHomDer;
    TransformGroup tgRotHomDer;
    TransformGroup tgRotBraDer;
    TransformGroup tgHomIzq;
    TransformGroup tgRotHomIzq;
    TransformGroup tgRotBraIzq;
    TransformGroup tgUpperLegDer;
    TransformGroup tgRotHipLegDer;
    TransformGroup tgRotRodiLegDer;
    TransformGroup tgUpperLegIzq;
    TransformGroup tgRotHipLegIzq;
    TransformGroup tgRotRodiLegIzq;
    TransformGroup tgMundo;
    TransformGroup moverMouse;

    // Nuevos grupos para las rotaciones del torso (panza) y la cabeza
    TransformGroup tgRotPanza;
    TransformGroup tgRotCabeza;
 
    public roblox() {
        
        // ===================== Configuración del Mouse =====================
        moverMouse = new TransformGroup();
        moverMouse.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        moverMouse.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
        BoundingSphere mouseBounds = new BoundingSphere(new Point3d(), 1000.0);
        MouseRotate myMouseRotate = new MouseRotate();
        myMouseRotate.setTransformGroup(moverMouse);
        myMouseRotate.setSchedulingBounds(mouseBounds);
        moverMouse.addChild(myMouseRotate);

        // ===================== Color =====================
        Apariencia c = new Apariencia();

        // ===================== Declaraciones Transform3D =====================
        Transform3D t3dCinto = new Transform3D();
        Transform3D t3dHomDer = new Transform3D();
        Transform3D t3dBrazoDer = new Transform3D();
        Transform3D t3dRotHomDer = new Transform3D();
        Transform3D t3dRotBraDer = new Transform3D();
        t3dRotHomDer.set(new Vector3f(0.22f, 0.20f, 0.0f));
        t3dBrazoDer.set(new Vector3f(0.0f, -0.10f, 0.0f));
        t3dCinto.set(new Vector3f(0.0f, -0.265f, 0.0f));
        t3dHomDer.set(new Vector3f(0.125f, -0.1f, 0.0f));
        t3dRotBraDer.set(new Vector3f(0.0f, -0.10f, 0.0f));

        // Para la cabeza
        Transform3D t3dCabeza = new Transform3D();
        t3dCabeza.set(new Vector3f(0.0f, 0.35f, 0.0f));

        // Para brazo izquierdo
        Transform3D t3dRotHomIzq = new Transform3D();
        Transform3D t3dHomIzq = new Transform3D();
        Transform3D t3dBrazoIzq = new Transform3D();
        Transform3D t3dRotBraIzq = new Transform3D();
        t3dRotHomIzq.set(new Vector3f(-0.22f, 0.20f, 0.0f));
        t3dHomIzq.set(new Vector3f(-0.125f, -0.1f, 0.0f));
        t3dBrazoIzq.set(new Vector3f(0.0f, -0.10f, 0.0f));
        t3dRotBraIzq.set(new Vector3f(0.0f, -0.10f, 0.0f));

        
        // Para pierna derecha
        Transform3D t3dHipLegDer = new Transform3D();
        t3dHipLegDer.set(new Vector3f(0.1f, 0.03f, 0.0f));
        Transform3D t3dUpperLegDer = new Transform3D();
        t3dUpperLegDer.set(new Vector3f(0.0f, -0.2f, 0.0f));
        Transform3D t3dKneeLegDer = new Transform3D();
        t3dKneeLegDer.set(new Vector3f(0.0f, -0.01f, 0.0f));
        Transform3D t3dLowerLegDer = new Transform3D();
        t3dLowerLegDer.set(new Vector3f(0.0f, -0.2f, 0.0f));

        // Para pierna izquierda
        Transform3D t3dHipLegIzq = new Transform3D();
        t3dHipLegIzq.set(new Vector3f(-0.1f, 0.03f, 0.0f));
        Transform3D t3dUpperLegIzq = new Transform3D();
        t3dUpperLegIzq.set(new Vector3f(0.0f, -0.2f, 0.0f));
        Transform3D t3dKneeLegIzq = new Transform3D();
        t3dKneeLegIzq.set(new Vector3f(0.0f, -0.01f, 0.0f));
        Transform3D t3dLowerLegIzq = new Transform3D();
        t3dLowerLegIzq.set(new Vector3f(0.0f, -0.2f, 0.0f));

        // ===================== Declaraciones TransformGroup =====================
        // Grupo principal de la parte superior (panza)
        TransformGroup tgPanza = new TransformGroup();
        tgPanza.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);

        tgRotPanza = new TransformGroup();
        tgRotPanza.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        tgRotPanza.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
        tgPanza.addChild(tgRotPanza);

        TransformGroup tgCinto = new TransformGroup(t3dCinto);

        tgMundo = new TransformGroup();
        tgMundo.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        

        tgRotHomDer = new TransformGroup(t3dRotHomDer);
        tgRotHomDer.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);

        tgHomDer = new TransformGroup(t3dHomDer);
        tgHomDer.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);

        TransformGroup tgBrazoDer = new TransformGroup(t3dBrazoDer);

        tgRotBraDer = new TransformGroup(t3dRotBraDer);
        tgRotBraDer.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);

        TransformGroup tgCabeza = new TransformGroup(t3dCabeza);
        tgCabeza.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);

        tgRotCabeza = new TransformGroup();
        tgRotCabeza.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        tgRotCabeza.addChild(tgCabeza);

        tgRotHomIzq = new TransformGroup(t3dRotHomIzq);
        tgRotHomIzq.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);

        tgHomIzq = new TransformGroup(t3dHomIzq);
        tgHomIzq.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);

        TransformGroup tgBrazoIzq = new TransformGroup(t3dBrazoIzq);

        tgRotBraIzq = new TransformGroup(t3dRotBraIzq);
        tgRotBraIzq.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);

        tgRotHipLegDer = new TransformGroup(t3dHipLegDer);
        tgRotHipLegDer.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);

        tgUpperLegDer = new TransformGroup(t3dUpperLegDer);
        tgUpperLegDer.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);

        tgRotRodiLegDer = new TransformGroup(t3dKneeLegDer);
        tgRotRodiLegDer.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);

        TransformGroup tgLowerLegDer = new TransformGroup(t3dLowerLegDer);
        tgLowerLegDer.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);

        tgRotHipLegIzq = new TransformGroup(t3dHipLegIzq);
        tgRotHipLegIzq.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);

        tgUpperLegIzq = new TransformGroup(t3dUpperLegIzq);
        tgUpperLegIzq.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        
        tgRotRodiLegIzq = new TransformGroup(t3dKneeLegIzq);
        tgRotRodiLegIzq.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);

        TransformGroup tgLowerLegIzq = new TransformGroup(t3dLowerLegIzq);
        tgLowerLegIzq.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        
        
        cond.girar(tgRotPanza, 180, "y");
        cond.escalar(tgRotPanza, 0.4);

        // ===================== Declaraciones de Geometrías =====================
        // Boxes
        Box bxMundo = new Box(2.0f, 0.80f, 4.0f, paraTextura, c.setColor(129, 175, 238));

        Box bxpanza = new Box(0.2f, 0.20f, 0.15f, c.setColor(34,98,185));
        bxpanza.setAppearance(0, c.setColor(15,69,142));

        Box bxCinto = new Box(0.2f, 0.06f, 0.15f, c.setColor(34,98,185));
        
        bxCinto.setAppearance(5, c.setColor(129, 175, 238));
        bxCinto.setAppearance(4, c.setColor(129, 175, 238));

        Box bxHomDer = new Box(0.125f, 0.1f, 0.17f, c.setColor(255, 222, 89));
        bxHomDer.setAppearance(1, c.setColor(255, 222, 89));
        Box bxBrazoDer = new Box(0.125f, 0.1f, 0.17f, c.setColor(255, 222, 89));
        bxBrazoDer.setAppearance(1, c.setColor(255, 222, 89));

        Box bxCabeza = new Box(0.15f, 0.15f, 0.15f, paraTextura, textura.crearTextura("FaceR.png"));
        bxCabeza.setAppearance(1, c.setColor(255, 222, 89));
        bxCabeza.setAppearance(2, c.setColor(255, 222, 89));
        bxCabeza.setAppearance(3, c.setColor(255, 222, 89));
        bxCabeza.setAppearance(4, c.setColor(255, 222, 89));
        bxCabeza.setAppearance(5, c.setColor(255, 222, 89));

        Box bxHomIzq = new Box(0.125f, 0.1f, 0.17f, paraTextura, c.setColor(255, 222, 89));
        Box bxBrazoIzq = new Box(0.125f, 0.1f, 0.17f, paraTextura, c.setColor(255, 222, 89));

        Box bxUpperLegDer = new Box(0.1f, 0.1f, 0.17f, c.setColor(131, 136, 59));
        Box bxLowerLegDer = new Box(0.1f, 0.1f, 0.17f, c.setColor(131, 136, 59));
        
        bxLowerLegDer.setAppearance(5, c.setColor(0, 0, 0));

        Box bxUpperLegIzq = new Box(0.1f, 0.1f, 0.17f, c.setColor(131, 136, 59));
        Box bxLowerLegIzq = new Box(0.1f, 0.1f, 0.17f, c.setColor(131, 136, 59));
        bxLowerLegIzq.setAppearance(5, c.setColor(0, 0, 0));

        // Spheres
        Sphere spRotBraDer = new Sphere(0.005f, c.setColor(255, 0, 0));
        Sphere spRotHomDer = new Sphere(0.005f, c.setColor(255, 0, 0));

        Sphere spRotHomIzq = new Sphere(0.005f, c.setColor(255, 0, 0));
        Sphere spRotBraIzq = new Sphere(0.005f, c.setColor(255, 0, 0));

        Sphere spHipLegDer = new Sphere(0.005f, c.setColor(255, 0, 0));
        Sphere spKneeLegDer = new Sphere(0.005f, c.setColor(255, 0, 0));

        Sphere spHipLegIzq = new Sphere(0.005f, c.setColor(255, 0, 0));
        Sphere spKneeLegIzq = new Sphere(0.005f, c.setColor(255, 0, 0));

        // ===================== Ensamblado de la Escena =====================
        bgRaiz.addChild(moverMouse);
        bgRaiz.addChild(tgMundo);
        tgMundo.addChild(bxMundo);

        moverMouse.addChild(tgPanza);

        tgRotPanza.addChild(bxpanza);

        tgRotPanza.addChild(tgRotCabeza);
        tgCabeza.addChild(bxCabeza);

        tgRotPanza.addChild(tgCinto);
        tgCinto.addChild(bxCinto);

        // Brazo derecho
        tgRotPanza.addChild(tgRotHomDer);
        tgRotHomDer.addChild(spRotHomDer);
        tgRotHomDer.addChild(tgHomDer);
        tgHomDer.addChild(bxHomDer);
        tgHomDer.addChild(tgRotBraDer);
        tgRotBraDer.addChild(tgBrazoDer);
        tgRotBraDer.addChild(spRotBraDer);
        tgBrazoDer.addChild(bxBrazoDer);

        // Brazo izquierdo
        tgRotPanza.addChild(tgRotHomIzq);
        tgRotHomIzq.addChild(spRotHomIzq);
        tgRotHomIzq.addChild(tgHomIzq);
        tgHomIzq.addChild(bxHomIzq);
        tgHomIzq.addChild(tgRotBraIzq);
        tgRotBraIzq.addChild(tgBrazoIzq);
        tgRotBraIzq.addChild(spRotBraIzq);
        tgBrazoIzq.addChild(bxBrazoIzq);

        // Pierna derecha
        tgCinto.addChild(tgRotHipLegDer);
        tgRotHipLegDer.addChild(spHipLegDer);
        tgRotHipLegDer.addChild(tgUpperLegDer);
        tgUpperLegDer.addChild(bxUpperLegDer);
        tgUpperLegDer.addChild(tgRotRodiLegDer);
        tgRotRodiLegDer.addChild(spKneeLegDer);
        tgRotRodiLegDer.addChild(tgLowerLegDer);
        tgLowerLegDer.addChild(bxLowerLegDer);

        // Pierna izquierda
        tgCinto.addChild(tgRotHipLegIzq);
        tgRotHipLegIzq.addChild(spHipLegIzq);
        tgRotHipLegIzq.addChild(tgUpperLegIzq);
        tgUpperLegIzq.addChild(bxUpperLegIzq);
        tgUpperLegIzq.addChild(tgRotRodiLegIzq);
        tgRotRodiLegIzq.addChild(spKneeLegIzq);
        tgRotRodiLegIzq.addChild(tgLowerLegIzq);
        tgLowerLegIzq.addChild(bxLowerLegIzq);
    }

public void girarTG(TransformGroup tg, String eje, int grados) {
        Transform3D leerBrazo = new Transform3D();
        Transform3D moverBrazo = new Transform3D();

        tg.getTransform(leerBrazo);
        if (eje.equals("X")) {
            moverBrazo.rotX(Math.PI / 180 * grados);
        }
        if (eje.equals("Y")) {
            moverBrazo.rotY(Math.PI / 180 * grados);
        }
        if (eje.equals("Z")) {
            moverBrazo.rotZ(Math.PI / 180 * grados);
        }
        leerBrazo.mul(moverBrazo);
        tg.setTransform(leerBrazo);
    }

    public void avanzarEnZ() {
        Transform3D t3dActual = new Transform3D();
        tgMundo.getTransform(t3dActual);

        Vector3f posiActual = new Vector3f();
        t3dActual.get(posiActual);

        Vector3f direLocal = new Vector3f(0.0f, 0.0f, 0.1f);
        Transform3D t3drotacion = new Transform3D(t3dActual);
        t3drotacion.setTranslation(new Vector3d(0.0f, 0.0f, 0.0f));

        t3drotacion.transform(direLocal);
        Float paso = 0.1f;
        direLocal.scale(paso);

        posiActual.add(direLocal);
        t3dActual.setTranslation(posiActual);
        tgMundo.setTransform(t3dActual);
    }

    public void Caminar() {
        if (pasos < 8) {
            girarTG(tgRotHipLegDer, "X", -5);
            girarTG(tgRotRodiLegDer, "X", 5);
            girarTG(tgRotHipLegIzq, "X", 5);
            girarTG(tgRotRodiLegIzq, "X", 5);
            avanzarEnZ();

            girarTG(tgHomDer, "X", 5);
            girarTG(tgRotBraDer, "X", -5);
            girarTG(tgHomIzq, "X", -5);
            girarTG(tgRotBraIzq, "X", -5);
            avanzarEnZ();
        } else if (pasos < 16) {
            girarTG(tgRotHipLegDer, "X", 5);
            girarTG(tgRotRodiLegDer, "X", -5);
            girarTG(tgRotHipLegIzq, "X", -5);
            girarTG(tgRotRodiLegIzq, "X", -5);
            avanzarEnZ();

            girarTG(tgHomDer, "X", -5);
            girarTG(tgRotBraDer, "X", 5);
            girarTG(tgHomIzq, "X", 5);
            girarTG(tgRotBraIzq, "X", 5);
            avanzarEnZ();

        } else if (pasos < 24) {
            girarTG(tgRotHipLegDer, "X", 5);
            girarTG(tgRotRodiLegDer, "X", 5);
            girarTG(tgRotHipLegIzq, "X", -5);
            girarTG(tgRotRodiLegIzq, "X", 5);
            avanzarEnZ();

            girarTG(tgHomDer, "X", -5);
            girarTG(tgRotBraDer, "X", -5);
            girarTG(tgHomIzq, "X", 5);
            girarTG(tgRotBraIzq, "X", -5);
            avanzarEnZ();
        } else if (pasos < 32) {
            girarTG(tgRotHipLegDer, "X", -5);
            girarTG(tgRotRodiLegDer, "X", -5);
            girarTG(tgRotHipLegIzq, "X", 5);
            girarTG(tgRotRodiLegIzq, "X", -5);
            avanzarEnZ();

            girarTG(tgHomDer, "X", 5);
            girarTG(tgRotBraDer, "X", 5);
            girarTG(tgHomIzq, "X", -5);
            girarTG(tgRotBraIzq, "X", 5);
            avanzarEnZ();
        } else {
            pasos = -1;
            avanzarEnZ();
        }
        pasos++;
    }
    //Obtener raiz
     public BranchGroup getbgRaiz(){
        this.bgRaiz.compile();
        return this.bgRaiz;
    }
  
}