package Decoracion;

import Controlador.Conducta;
import Vista.Apariencia;
import com.sun.j3d.utils.geometry.Box;
import com.sun.j3d.utils.geometry.Sphere;
import javax.media.j3d.Appearance;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.vecmath.Vector3f;


public class Mundo {

    Apariencia ap;
    Conducta cond;
    BranchGroup BG_Mundo;

    TransformGroup TG_PosCentro;
    TransformGroup TG_RotCentro;

    Sphere centro;
    Edificio edificio;

    public Mundo() {
        BG_Mundo = new BranchGroup();
        ap = new Apariencia();
        cond = new Conducta();

        NewCentro(0.01f, ap.setColor(0, 150, 150));
        TransformGroup Terreno = NewTerreno(0, 2, 1, 2, ap.setColor(100, 100, 50));
        TransformGroup glob = new TransformGroup();

        BG_Mundo.addChild(glob);
        glob.addChild(TG_RotCentro);
        centro.addChild(Terreno);

    }

    private void NewCentro(float radio, Appearance NewAp) {
        centro = new Sphere(radio, NewAp);

        Transform3D T3D_PosCentro = new Transform3D();
        Transform3D T3D_RotCentro = new Transform3D();

        T3D_PosCentro.set(new Vector3f(0, 0f, 0));

        TG_PosCentro = new TransformGroup(T3D_PosCentro);
        TG_RotCentro = new TransformGroup(T3D_RotCentro);

        DarPermisos(TG_PosCentro);
        DarPermisos(TG_RotCentro);

        TG_RotCentro.addChild(TG_PosCentro);
        TG_PosCentro.addChild(centro);
    }

    private TransformGroup NewTerreno(float Rotacion, float x, float y, float z, Appearance NewAp) {
        // Se eliminan las líneas relacionadas al Box Terreno
        Transform3D T3D_RotMundo = new Transform3D();
        Transform3D T3D_PosMundo = new Transform3D();
        T3D_PosMundo.set(new Vector3f(0, 0, 0));

        TransformGroup TG_RotMundo = new TransformGroup(T3D_RotMundo);
        TransformGroup TG_PosMundo = new TransformGroup(T3D_PosMundo);

        cond.girar(TG_RotMundo, Rotacion, "y");

        TG_PosMundo.addChild(TG_RotMundo);

        // Se crea el edificio y se añade directamente al grupo de rotación
        edificio = new Edificio();
        TG_RotMundo.addChild(edificio.getEstructuras());

        return TG_PosMundo;
    }

    private void DarPermisos(TransformGroup tg) {
        tg.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
        tg.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
    }

    public BranchGroup getBG_Mundo() {
        BG_Mundo.compile();
        return this.BG_Mundo;
    }

    public TransformGroup getRotMundo() {
        return TG_RotCentro;
    }

    public TransformGroup getPosMundo() {
        return TG_PosCentro;
    }

    public Edificio getEstructuras() {
        return edificio;
    }

}
