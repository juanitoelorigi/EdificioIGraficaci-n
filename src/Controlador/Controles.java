package Controlador;

import Decoracion.roblox;
import Decoracion.Edificio;
import Decoracion.Personaje;
import Vista.Universo;
import com.sun.j3d.utils.geometry.Box;
import java.awt.AWTException;
import java.awt.Dimension;
import static java.awt.Frame.MAXIMIZED_BOTH;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.media.j3d.Canvas3D;
import javax.media.j3d.TransformGroup;
import javax.swing.JFrame;
import javax.vecmath.Vector3d;

public final class Controles {

    Universo universe;  ///
    Canvas3D canvas;
    Robot robot;  ///////El robot será necesario para centrar el mouse principalmente
    Conducta Cond;

    float grados;   /////Valor que va de 0 a 360 grados
    float AnguloV; //////Valor que va de 0 a 45 grados y de 45 a 0 grados

    float aux;

    Personaje pers;
    Colisiones colision = new Colisiones();
    Box medidaPersonaje;
    ArrayList<TransformGroup> ListaObjetos;  //////Lista de TransformGroups para usarse en colisiones
    ArrayList<Box> ListaCajas; //////Lista de Boxes para las colisiones

    Vector3d Ubicacion; //////Ubicacion del personaje

    boolean caminandoAdelante;
    boolean caminandoAtras;

    //////////////Para manipular el universo se reciven 4 paámetros:
    /*
    Universo. Del universo se extrae el canvas para maniplación de teclas 
    JFrame. Del marco se extrae sus medidas para saber si la ventana se maximizo o está en estado normal y de esta forma centrar el mouse
    Personaje. Del personaje se extrae su posición y algunos de sus objetos para habilitar las colisiones y su movimiento
    Estrucutras. De las estructuras se extraen sus objetos, tales como TransformGroup o boxes
     */
    public Controles(Universo universe, JFrame marco, Personaje pers, Edificio edificio) throws AWTException {
        this.universe = universe;
        this.canvas = universe.getCanvas();
        this.robot = new Robot();
        this.Cond = new Conducta();
        this.grados = 0;
        this.AnguloV = 0;
        this.aux = 0;
        this.pers = pers;
        caminandoAdelante = false;
        caminandoAtras = false;

        Ubicacion = pers.getUbicacion();
        medidaPersonaje = pers.getCuerpo();
        ListaObjetos = edificio.getListaObj();
        ListaCajas = edificio.getListaCajas();

        AKL();
        AML(marco, 1.5f);
    }

    ////Add Key Listeners
    public void AKL() {/////////////////////////////////Añade los listeners de las teclas
        this.canvas.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                char keyChar = e.getKeyChar();
                roblox roblox = pers.getEnd(); /////Creamos nuestro enderman dentro de esta sección para que se pueda mover
                roblox.Caminar();  //////Independientemente de la tecla presionada el enderman moverá sus piernas y brazos pero no se moverá de su posición
                if (keyChar == 'W' || keyChar == 'w') {  //////Booleanos utilizados para saber si esta avanzando o retrocediendo el objeto
                    caminandoAdelante = true;
                    caminandoAtras = false;
                    InCol(); ////Revisa si hay colisiones
                } else if (keyChar == 'S' || keyChar == 's') {
                    caminandoAdelante = false;
                    caminandoAtras = true;
                    InCol();
                }
            }

        });
    }

    //////Add mouse motion Listener
    public void AML(JFrame marco, float giro) {
        this.canvas.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                int centroX = marco.getWidth() / 2; // Usar el ancho del marco para calcular el centro
                int margen = 10; // Margen para activar el giro

                if ((marco.getExtendedState() & MAXIMIZED_BOTH) == MAXIMIZED_BOTH) {
                    centroX = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth() / 2;
                }

                // Girar a la derecha si el mouse está a la derecha del centro
                if (e.getX() > centroX + margen) {
                    centrarMouse();
                    GirarCamaraHorizontal(giro, true);
                } // Girar a la izquierda si el mouse está a la izquierda del centro
                else if (e.getX() < centroX - margen) {
                    centrarMouse();
                    GirarCamaraHorizontal(-giro, false);
                }
            }
        });
    }

    ///Método que calcula los giros de la cámara
    public void GirarCamaraHorizontal(float grados, boolean giroDerecha) {
    this.Cond.girar(this.universe.getRotMundo(), grados, "y");

    // Actualizar el ángulo principal (0-360 grados)
    this.grados += grados;
    if (this.grados >= 360) this.grados -= 360;
    if (this.grados < 0) this.grados += 360;

    // Calcular el ángulo vertical (limitar entre -45 y 45)
    this.aux += grados;
    this.aux = Math.max(-45, Math.min(45, this.aux)); // Limitar el ángulo vertical
    this.AnguloV = Math.abs(this.aux);
}

    ////Método que permite la "caminata" del personaje, en este caso hacia delante 
    ////Explicación:
    /*
    Existen 4 casos principales:
    Vista Frontal: 0 grados
    Vista Derecha: 90 grados
    Vista Trasera: 180 grados
    Vista Izquierda:  270 grados
    
     en estos casos es sencillo el calculo del movimiento que debe de tener el personaje, pues como su nombre indica, el personaje
     se moverá adelante, atrás, izquierda o derecha, sin embargo debido a la estructura de mi universo, esto se de debe simular, pues el que se mueve en 
    realidad es el terreno y el personaje no se mueve de su ubicación original
    
    
    Por otro lado, como si se tratase de una brujula, tenemos otros 4 casos:
    NE: 45 grados
    SE: 135 grados
    SW: 225 grados
    NW: 315 grados
    
    en estos casos se les asigna un porcentaje de movimiento, según sea el caso, será asignado en "x" o en "y"
    
    Entonces, los demás casos de movimiento se les aplicará el mismo procedimiento. Por ejemplo:
    
    Medidas del 1 al 44 sobre NE hasta llegar a 45 grados:
    movimiento del mundo = (x=Porcentaje del movimiento, y constante , z=Movimiento completo)
    
    Medidas del 44 al 1 sobre NE hasta llegar a los 90 grados:
    movimiento del mundo = (x=Movimiento completo, y constante , z=Porcentaje del movimiento) 
     */
    public void CaminarAdelante(float coords) { ////Coords nos indica cuanto se va a mover el personaje
        float aux = 0;
        if (grados == 0) { /////Caso de 0 grados
            caminar(0, 0, coords); ////Unicamente caminará en z
            Ubicacion.z = Ubicacion.z + coords * -1; /////Se hace un ajuste a la ubicación del personaje, pues el personaje no comparte las medidas de los edificios al no ser hijo del mismo suelo
        } else if (grados == 90) { ////Caso 90 grados, viendo a la derecha
            caminar(coords * -1, 0, 0); ////El mundo retrocede en x
            Ubicacion.x = Ubicacion.x + coords; /////Se hace el ajuste sobre la posicion del personaje
        } else if (grados == 180) {
            caminar(0, 0, coords * -1);
            Ubicacion.z = Ubicacion.z + coords;
        } else if (grados == 270) {
            caminar(coords, 0, 0);
            Ubicacion.x = Ubicacion.x + coords * -1;
        } else if (grados > 0 && grados <= 45) {
            aux = porcentaje(this.AnguloV, 45, coords) * -1;
            caminar(aux, 0, coords);
            Ubicacion.x = Ubicacion.x + aux * -1;
            Ubicacion.z = Ubicacion.z + coords * -1;
        } else if (grados > 45 && grados <= 90) {
            aux = porcentaje(this.AnguloV, 45, coords);
            caminar(coords * -1, 0, aux);
            Ubicacion.x = Ubicacion.x + coords;
            Ubicacion.z = Ubicacion.z + aux * -1;
        } else if (grados > 90 && grados <= 135) {
            aux = porcentaje(this.AnguloV, 45, coords) * -1;
            caminar(coords * -1, 0, aux);
            Ubicacion.x = Ubicacion.x + coords;
            Ubicacion.z = Ubicacion.z + aux * -1;
        } else if (grados > 135 && grados <= 180) {
            aux = porcentaje(this.AnguloV, 45, coords) * -1;
            caminar(aux, 0, coords * -1);
            Ubicacion.x = Ubicacion.x + aux * -1;
            Ubicacion.z = Ubicacion.z + coords;
        } else if (grados > 180 && grados <= 225) {
            aux = porcentaje(this.AnguloV, 45, coords);
            caminar(aux, 0, coords * -1);
            Ubicacion.x = Ubicacion.x + aux * -1;
            Ubicacion.z = Ubicacion.z + coords;
        } else if (grados > 225 && grados <= 270) {
            aux = porcentaje(this.AnguloV, 45, coords) * -1;
            caminar(coords, 0, aux);
            Ubicacion.x = Ubicacion.x + coords * -1;
            Ubicacion.z = Ubicacion.z + aux * -1;
        } else if (grados > 270 && grados <= 315) {
            aux = porcentaje(this.AnguloV, 45, coords);
            caminar(coords, 0, aux);
            Ubicacion.x = Ubicacion.x + coords * -1;
            Ubicacion.z = Ubicacion.z + aux * -1;
        } else if (grados > 315 && grados <= 360) {
            aux = porcentaje(this.AnguloV, 45, coords);
            caminar(aux, 0, coords);
            Ubicacion.x = Ubicacion.x + aux * -1;
            Ubicacion.z = Ubicacion.z + coords * -1;
        }
    }

    ////Como se explicó anterirormente, este método trabaja de la misma forma, solo invirtiendo las medidas
    public void CaminarAtras(float coords) {
        float aux = 0;
        if (grados == 0) {
            caminar(0, 0, coords * -1);
            Ubicacion.z = Ubicacion.z + coords;
        } else if (grados == 90) {
            caminar(coords, 0, 0);
            Ubicacion.x = Ubicacion.x + coords * -1;
        } else if (grados == 180) {
            caminar(0, 0, coords);
            Ubicacion.z = Ubicacion.z + coords * -1;
        } else if (grados == 270) {
            caminar(coords * -1, 0, 0);
            Ubicacion.x = Ubicacion.x + coords;
        } else if (grados > 0 && grados <= 45) {
            aux = porcentaje(AnguloV, 45, coords);
            caminar(aux, 0, coords * -1);
            Ubicacion.x = Ubicacion.x + aux * -1;
            Ubicacion.z = Ubicacion.z + coords;
        } else if (grados > 45 && grados <= 90) {
            aux = porcentaje(AnguloV, 45, coords) * -1;
            caminar(coords, 0, aux);
            Ubicacion.x = Ubicacion.x + coords * -1;
            Ubicacion.z = Ubicacion.z + aux * -1;
        } else if (grados > 90 && grados <= 135) {
            aux = porcentaje(AnguloV, 45, coords);
            caminar(coords, 0, aux);
            Ubicacion.x = Ubicacion.x + coords * -1;
            Ubicacion.z = Ubicacion.z + aux * -1;
        } else if (grados > 135 && grados <= 180) {
            aux = porcentaje(AnguloV, 45, coords);
            caminar(aux, 0, coords);
            Ubicacion.x = Ubicacion.x + aux * -1;
            Ubicacion.z = Ubicacion.z + coords * -1;
        } else if (grados > 180 && grados <= 225) {
            aux = porcentaje(AnguloV, 45, coords) * -1;
            caminar(aux, 0, coords);
            Ubicacion.x = Ubicacion.x + aux * -1;
            Ubicacion.z = Ubicacion.z + coords * -1;
        } else if (grados > 225 && grados <= 270) {
            aux = porcentaje(AnguloV, 45, coords);
            caminar(coords * -1, 0, aux);
            Ubicacion.x = Ubicacion.x + coords;
            Ubicacion.z = Ubicacion.z + aux * -1;
        } else if (grados > 270 && grados <= 315) {
            aux = porcentaje(AnguloV, 45, coords) * -1;
            caminar(coords * -1, 0, aux);
            Ubicacion.x = Ubicacion.x + coords;
            Ubicacion.z = Ubicacion.z + aux * -1;
        } else if (grados > 315 && grados <= 360) {
            aux = porcentaje(AnguloV, 45, coords) * -1;
            caminar(aux, 0, coords * -1);
            Ubicacion.x = Ubicacion.x + aux * -1;
            Ubicacion.z = Ubicacion.z + coords;
        }
    }

    /////Este metodo permite extraer el porcentaje que se desea para mover al personaje (terreno)
    public float porcentaje(float valor1, float valor2, float valor3) {
        if (valor1 == 0) {
            valor1 = 45; ////se trabaja sobre fragmentos de 45 grados, en caso de ser más precisos es posible que se necesite una medida más pequeña
        }
        valor1 /= (valor2 / 100);
        valor1 /= 100;
        valor1 *= valor3;
        return valor1;
    }

    /////Método para centrar el mouse
    public void centrarMouse() {
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    int centerX = (int) screenSize.getWidth() / 2;
    int centerY = (int) screenSize.getHeight() / 2;
    robot.mouseMove(centerX, centerY);
}

    /////Ejecuta la "caminata" del personaje a través de los cálculos ejecutados anteriormente
    public void caminar(float x, float y, float z) {
        Cond.mover(universe.getPosMundo(), x, y, z);
    }

    /////Revisa si hubo colisiones
    public void InCol() {
    Vector3d personajePos = new Vector3d(0, 0, 0); // Posición correcta del personaje
    for (int i = 0; i < ListaObjetos.size(); i++) {
        if (caminandoAdelante) {
            if (colision.choco(personajePos, medidaPersonaje, ListaObjetos.get(i), ListaCajas.get(i))) {
                CaminarAtras(0.5f);
            } else {
                CaminarAdelante(0.1f / ListaObjetos.size());
            }
        } else if (caminandoAtras) {
            if (colision.choco(personajePos, medidaPersonaje, ListaObjetos.get(i), ListaCajas.get(i))) {
                CaminarAdelante(0.5f);
            } else {
                CaminarAtras(0.1f / ListaObjetos.size());
            }
        }
    }
}

}

