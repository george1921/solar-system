import javax.media.opengl.*;
import javax.media.opengl.glu.*;

import math.Point3;
import math.Vector3;
import planets.ISpaceObject;
import planets.Planet;
import planets.SolarSystem;
import planets.Sun;

import com.sun.opengl.util.gl2.GLUT;

import java.awt.event.KeyEvent;
import java.util.*;


/**
 * Display a simple scene to demonstrate OpenGL.
 * 
 * @author Robert C. Duvall
 */
public class Display extends Scene
{
	// constants
    public static final Point3 DEFAULT_CAMERA_FROM = new Point3(65, 13, 3);
    public static final Point3 DEFAULT_CAMERA_TO = new Point3(0, 0, 0);
    public static final Point3 DEFAULT_CAMERA_UP = new Point3(0, 1, 0);
    
	// animation state
    private Point3 myCamFrom;
    private Point3 myCamTo;
    private Point3 myCamUp;
	private SolarSystem ss = new SolarSystem();

    /**
     * Create the scene with the given arguments.
     * 
     * For example, the number of teapots to display.
     * 
     * @param args command-line arguments
     */
    public Display (String[] args)
    {
        setDefaultCamera();
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
	        myCamFrom.x,  myCamFrom.y,  myCamFrom.z,            // from position
	        myCamTo.x,  myCamTo.y,  myCamTo.z,            // to position
	        myCamUp.x,  myCamUp.y,  myCamUp.z);           // up direction
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
    

    /**
     * Respond to the press of a key.
     * 
     * @param keyCode Java code representing pressed key
     */
    public void keyPressed (int keyCode)
    {
    	Vector3 viewDir = getViewDir();
    	Vector3 upDir = new Vector3(myCamUp);
    	upDir.normalize();
    	
    	Vector3 rightDir = new Vector3();
    	rightDir.cross(viewDir, upDir);
    	rightDir.normalize();
    	
        switch (keyCode) {
		    case KeyEvent.VK_I: // Zoom In
		    	myCamFrom.sub(viewDir);
		    	break;
		    case KeyEvent.VK_O: // Zoom out;
		    	myCamFrom.add(viewDir);
		    	break;
		    // Camera movement: Current implementation rotates around the spot.
            case KeyEvent.VK_LEFT:
            	myCamTo.sub(rightDir);
            	break;
            case KeyEvent.VK_RIGHT:
            	myCamTo.add(rightDir);
            	break;
            case KeyEvent.VK_UP:
            	myCamTo.add(upDir);
            	break;
            case KeyEvent.VK_DOWN:
            	myCamTo.sub(upDir);
            	break;
        }
    }


    /**
     * Respond to the release of a key.
     * 
     * @param keyCode Java code representing released key
     */
    public void keyReleased (int keyCode)
    {
    	
        switch (keyCode)
        {
            // toggle animation running
            case KeyEvent.VK_H:
                // hide orbits
                break;
            case KeyEvent.VK_R:
            	// restart simulation
            	break;
            // Reset camera view
            case KeyEvent.VK_C:
            	setDefaultCamera();
            	break;
        }
    }


    /**
     * Respond to the press and release of an alphanumeric key.
     * 
     * @param key text representing typed key
     */
    public void keyTyped (int keyCode)
    {
        // by default, do nothing
    }
    
    private Vector3 getViewDir() {
    	Vector3 viewDir = new Vector3();
    	viewDir.sub(myCamFrom, myCamTo);
    	viewDir.normalize();
    	return viewDir;
    }
    
	private void setDefaultCamera() {
		myCamFrom = DEFAULT_CAMERA_FROM;
        myCamTo = DEFAULT_CAMERA_TO;
        myCamUp = DEFAULT_CAMERA_UP;
	}
	
	public void resetDisplay()
	{
		
	}
}
