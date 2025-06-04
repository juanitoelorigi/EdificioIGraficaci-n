package Decoracion;

import Vista.Apariencia;
import com.sun.j3d.utils.geometry.Box;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.vecmath.Vector3d;
import javax.vecmath.Vector3f;


public class Personaje {

    BranchGroup BG_Personaje;
    Apariencia ap = new Apariencia();
    roblox Roblox;
    Vector3d Ubicacion = new Vector3d(-4, 1, 4);
    /////ESTE VALOR DE UBICACION DEPENDE DE EL VALOR DE LA POSICION DE CAPA LIBRE EN LA CLASE ESTRUCTURAS este valor se multiplica por -1

    Box cuerpo;
    
    public Personaje(TransformGroup TGMov) {
         Roblox = new roblox();
        BG_Personaje = new BranchGroup();
        
        Transform3D T3D_Pos = new Transform3D();
        T3D_Pos.set(new Vector3f(-0.0f, 0.5f, 0));
        
        TransformGroup TG_PosPersonaje = new TransformGroup(T3D_Pos);
       cuerpo = new Box(0.03f, 0.03f, 0.02f, ap.setColor(55, 100, 150));
        
        BG_Personaje.addChild(TG_PosPersonaje);
        TG_PosPersonaje.addChild(cuerpo);
        cuerpo.addChild(Roblox.getbgRaiz());
        
        BG_Personaje.compile();
    }

    public BranchGroup getBG_Personaje() {  
        return this.BG_Personaje;
    }
    
    public Vector3d getUbicacion(){
        return Ubicacion;
    }
    
    public Box getCuerpo(){
        return cuerpo;
    }

    public roblox getEnd(){
        return Roblox;
    }
    
}
