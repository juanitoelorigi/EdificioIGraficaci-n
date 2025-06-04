package Decoracion;

import Vista.Textura;
import com.sun.j3d.utils.geometry.Box;
import com.sun.j3d.utils.geometry.Primitive;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.vecmath.Vector3f;


public class Bloque {

    private float Ancho;
    private float Largo;
    private float Alto;

    private float PosX;
    private float PosY;
    private float PosZ;

    private final Transform3D PosBloque;
    private TransformGroup TGPosBloque;

    private final int paraTextura;
    Textura textura;
    String nombreTextura;

    private Box bloque;

    public Bloque() {
        this.Ancho = 0;
        this.Largo = 0;
        this.Alto = 0;
        this.PosX = 0;
        this.PosY = 0;
        this.PosZ = 0;
        this.PosBloque = new Transform3D();
        this.paraTextura = Primitive.GENERATE_NORMALS + Primitive.GENERATE_TEXTURE_COORDS;
        this.textura = new Textura();
        this.nombreTextura = "";
    }

    public TransformGroup getBloque() {
    this.bloque = new Box(Ancho, Alto, Largo, paraTextura, textura.crearTextura(this.nombreTextura));
    PosBloque.set(new Vector3f(this.PosX, this.PosY, this.PosZ));
    TGPosBloque = new TransformGroup(this.PosBloque);
    TGPosBloque.setCapability(TransformGroup.ALLOW_TRANSFORM_READ); // Agregar esta línea
    TGPosBloque.addChild(bloque);
    return this.TGPosBloque;
}

    public void setTamBloque(float Ancho, float Alto, float Largo) {
        this.Ancho = Ancho;
        this.Largo = Largo;
        this.Alto = Alto;
    }

    public void setPosBloque(float PosX, float PosY, float PosZ) {
        this.PosX = PosX;
        this.PosY = PosY;
        this.PosZ = PosZ;
    }

    public void setNombreTextura(String nombre) {
        this.nombreTextura = nombre;
    }

    public void setTextura(int cara, String nombre) {
        this.bloque.setAppearance(cara, this.textura.crearTextura(nombre));
    }
    
    public Box getFinal(){
        return this.bloque;
    }
    
    public void setChild(TransformGroup obj){
        this.bloque.addChild(obj);
    }

}
