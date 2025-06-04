package Vista;

import javax.media.j3d.Appearance;
import javax.media.j3d.ColoringAttributes;
import javax.vecmath.Color3f;

/**
 *
 * @author juani
 */
public class Apariencia {
    Appearance appColor;
    
    public Appearance setColor(int a, int b, int c ){
        appColor = new Appearance();
        Color3f color = new Color3f(a/255f, b/255f, c/255f);
        ColoringAttributes atributosColor = new ColoringAttributes(color,ColoringAttributes.SHADE_FLAT);
        appColor.setColoringAttributes(atributosColor);
        return appColor;
        
    }
}
