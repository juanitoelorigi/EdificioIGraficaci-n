package Vista;

import Controlador.Conducta;
import Decoracion.Edificio;
import Decoracion.Mundo;
import Decoracion.Personaje;
import com.sun.j3d.utils.geometry.Sphere;
import com.sun.j3d.utils.universe.PlatformGeometry;
import com.sun.j3d.utils.universe.SimpleUniverse;
import javax.media.j3d.Appearance;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.vecmath.Vector3f;

/**
 *
 * @author juani
 */
public class Escena {

    BranchGroup NewEscene;
    Apariencia ap = new Apariencia();
    Conducta Cond = new Conducta();
    Personaje pers;
    Mundo mundo = new Mundo();
    Edificio Edificio;

    public Escena(SimpleUniverse universe, TransformGroup viewtrans) {
        NewEscene = new BranchGroup();
        pers = new Personaje(this.mundo.getPosMundo());
        Edificio = mundo.getEstructuras();
        viewtrans = universe.getViewingPlatform().getViewPlatformTransform();
        PlatformGeometry platformGeom = new PlatformGeometry();
        universe.getViewingPlatform().setPlatformGeometry(platformGeom);

        Sphere fondo = new Sphere(-40f, ap.setColor(120, 150, 235));
        TransformGroup tgFondo = new TransformGroup(new Transform3D());

        TransformGroup Centro = NewCentro(0.1f, 0, -0.2f, 1.5f, ap.setColor(150, 100, 100));
        Cond.escalar(Centro, 0.3);
        Cond.girar(Centro, 5, "x");

        NewEscene.addChild(Centro);
        Centro.addChild(mundo.getBG_Mundo());
        Centro.addChild(pers.getBG_Personaje());
        tgFondo.addChild(fondo);
        Centro.addChild(tgFondo);

    }

    /////Este objeto es el centro del universo, el personaje y el terreno deberán ser hijos de este
    private TransformGroup NewCentro(float radio, float x, float y, float z, Appearance NewAp) {
        TransformGroup TGPosCentro = new TransformGroup();
        Sphere CENTRO = new Sphere(radio, NewAp);
        Transform3D T3D_PosCentro = new Transform3D();
        T3D_PosCentro.set(new Vector3f(x, y, z));
        TGPosCentro.setTransform(T3D_PosCentro);
        TGPosCentro.addChild(CENTRO);
        return TGPosCentro;
    }

    public BranchGroup getEscene() {
        return NewEscene;
    }

    public TransformGroup getRotMundo() {
        return this.mundo.getRotMundo();
    }

    public TransformGroup getPosMundo() {
        return this.mundo.getPosMundo();
    }

    public Personaje getPersonaje() {
        return pers;
    }
    
    public Edificio getEstructuras(){
        return Edificio;
    }
}
