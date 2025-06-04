package Vista;

import Decoracion.Edificio;
import Decoracion.Personaje;
import com.sun.j3d.utils.universe.SimpleUniverse;
import java.awt.GraphicsConfiguration;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.Canvas3D;
import javax.media.j3d.TransformGroup;

/**
 *
 * @author juani
 */
public class Universo {
    private final SimpleUniverse universe;
    private final Canvas3D canvas;
    private Escena Escena;
    private TransformGroup viewtrans;
    Edificio Edificio;
    
    public Universo(double width, double height) {
        
        GraphicsConfiguration config = SimpleUniverse.getPreferredConfiguration();
        canvas = new Canvas3D(config);
        canvas.setBounds(0,0,(int)width,(int)height);
        universe = new SimpleUniverse(canvas);
        Escena = new Escena(universe, viewtrans);
        BranchGroup Obj = Escena.getEscene();
        universe.getViewingPlatform().setNominalViewingTransform();
        universe.getViewer().getView().setBackClipDistance(100.0); //Ver distancia
        universe.addBranchGraph(Obj);
        Edificio = Escena.getEstructuras();
    }
    
    public Canvas3D getCanvas(){
        return this.canvas;
    }
    
    public TransformGroup getRotMundo(){
        return this.Escena.getRotMundo();
    }
    
    public TransformGroup getPosMundo(){
        return this.Escena.getPosMundo();
    }

    
    
    public Personaje getPersonaje(){
        return Escena.getPersonaje();
    }
    
    public Edificio getEstructuras(){
        return Edificio;
    }
}
