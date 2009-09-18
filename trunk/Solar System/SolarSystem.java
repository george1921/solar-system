import javax.media.opengl.*;
import javax.media.opengl.glu.*;

import math.Point3;
import math.Vector3;
import planets.ISpaceObject;
import planets.Planet;
import planets.Sun;

import com.sun.opengl.util.gl2.GLUT;
import java.util.*;


/**
 * Display a simple scene to demonstrate OpenGL.
 * 
 * @author Robert C. Duvall
 */
public class SolarSystem extends Scene
{
    // animation state
    private float myAngle = 0.0f;
	Sun sun = new Sun(0.5, 0, new Vector3(0, 1, 0), 1);
	Planet planet = new Planet(3, 2, new Vector3(0, 1, 0), 0.3);
	Planet planet2 = new Planet(-2, 4, new Vector3(-0.241, 1, 1.42), 0.8);


    /**
     * Create the scene with the given arguments.
     * 
     * For example, the number of teapots to display.
     * 
     * @param args command-line arguments
     */
    public SolarSystem (String[] args)
    {
        // do nothing
    }


    /**
     * @return title for this scene
     */
    public String getTitle ()
    {
        return "A spinning teapot!";
    }


    /**
     * Draw all of the objects to display.
     */
    public void display (GL2 gl, GLU glu, GLUT glut)
    {
    	ArrayList<ISpaceObject> list = new ArrayList<ISpaceObject>();
    	list.add(planet);
    	list.add(planet2);
    	sun.addPlanets(list);
    	sun.draw(gl, glu, glut);
    }


    /**
     * Set the camera's view of the scene.
     */
    public void setCamera (GL2 gl, GLU glu, GLUT glut)
    {
        glu.gluLookAt(
	        0,  10,  0,            // from position
		    0,  0,  0,            // to position
		    0,  0,  1);           // up direction
    }


    /**
     * Animate the scene by changing its state slightly.
     * 
     * For example, the angle of rotation of the shapes.
     */
    public void animate (GL2 gl, GLU glu, GLUT glut)
    {
    	sun.animate(gl, glu, glut);
        // animate model by spinning it a few degrees each time
//        myAngle += 4;
    }
}
