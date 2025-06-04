package Decoracion;

import Controlador.Conducta;
import Vista.Apariencia;
import Vista.Textura;
import com.sun.j3d.utils.geometry.Box;
import com.sun.j3d.utils.geometry.Primitive;
import java.util.ArrayList;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.vecmath.Vector3f;


public class Edificio {

    BranchGroup objRaiz = new BranchGroup();
    private Box CapaLibre;
    private Transform3D PosCapaLibre;

    private TransformGroup TGPosCapaLibre;

    private int paraTextura;
    private Textura textura;
    private Apariencia ap;

    private Conducta conducta;
    ArrayList<TransformGroup> listaObjetos = new ArrayList();
    ArrayList<Box> listaCajas = new ArrayList();

    public Edificio() {
        conducta = new Conducta();
        InitTerreno();
        InitEdifi();
    }

    private void InitTerreno() {
        this.conducta = new Conducta();
        this.paraTextura = Primitive.GENERATE_NORMALS + Primitive.GENERATE_TEXTURE_COORDS;
        this.textura = new Textura();
        this.ap = new Apariencia();

        /////Escenario y fondo
        this.CapaLibre = new Box(50f, 0.2f, 100f, ap.setColor(4, 65, 9));

        ////Creacion de T3D de Mov y Posicion
        this.PosCapaLibre = new Transform3D();

        this.PosCapaLibre.set(new Vector3f(44f, 0f, -48));

        //Creacion de TG de movimiento y posicion
        this.TGPosCapaLibre = new TransformGroup(PosCapaLibre);

        ///Permisos de movilidad
        conducta.girar(TGPosCapaLibre, 0, "y");

        ///Arbol de objetos
        objRaiz.addChild(TGPosCapaLibre);
        TGPosCapaLibre.addChild(CapaLibre);

    }

    private void InitEdifi() {
        Bloque EdificioF = new Bloque();
        EdificioF.setNombreTextura("Edificio\\F\\F_Frente.jpg");
        EdificioF.setTamBloque(0.8f, 0.5f, 2f);  // Tamaño original
        EdificioF.setPosBloque(-5f, 1.2f, 1f); //Nuevas coordenadas cerca del personaje 

        TransformGroup TGGEdificioF = EdificioF.getBloque();
        EdificioF.setTextura(0, "Edificio\\F\\F_Lado.jpg");
        EdificioF.setTextura(1, "Edificio\\F\\F_Lado.jpg");
        EdificioF.setTextura(3, "Edificio\\F\\F_Trasera.jpg");
        EdificioF.setTextura(2, "Edificio\\F\\F_Frente.jpg");
        listaObjetos.add(TGGEdificioF);
        listaCajas.add((Box) EdificioF.getBloque().getChild(0));
        // Aquí se añade el edificio al árbol de la escena
        objRaiz.addChild(TGGEdificioF);
    }

    public BranchGroup getEstructuras() {
        this.objRaiz.compile();
        return this.objRaiz;
    }

    public ArrayList getListaObj() {
        return listaObjetos;
    }

    public ArrayList getListaCajas() {
        return listaCajas;
    }
}
