import javax.media.opengl.*;
import javax.media.opengl.glu.*;

import math.Point3;
import math.Vector3;
import planets.ISpaceObject;
import planets.Planet;
import planets.SolarSystem;
import planets.Sun;

import com.sun.opengl.util.gl2.GLUT;
import java.util.*;


/**
 * Display a simple scene to demonstrate OpenGL.
 * 
 * @author Robert C. Duvall
 */
public class Display extends Scene
{
    // animation state
    private float myAngle = 0.0f;
	SolarSystem ss = new SolarSystem();

    /**
     * Create the scene with the given arguments.
     * 
     * For example, the number of teapots to display.
     * 
     * @param args command-line arguments
     */
    public Display (String[] args)
    {
        // do nothing
    }


    /**
     * @return title for this scene
     */
    public String getTitle ()
    {
        return "A solar system!";
    }


    /**
     * Draw all of the objects to display.
     */
    public void display (GL2 gl, GLU glu, GLUT glut)
    {
    	ss.draw(gl, glu, glut);
    }


    /**
     * Set the camera's view of the scene.
     */
    public void setCamera (GL2 gl, GLU glu, GLUT glut)
    {
        glu.gluLookAt(
	        65,  13,  3,            // from position
		    0,  0,  0,            // to position
		    0,  1,  0);           // up direction
    }


    /**
     * Animate the scene by changing its state slightly.
     * 
     * For example, the angle of rotation of the shapes.
     */
    public void animate (GL2 gl, GLU glu, GLUT glut)
    {
    	ss.animate(gl, glu, glut);
        // animate model by spinning it a few degrees each time
//        myAngle += 4;
    }
}
