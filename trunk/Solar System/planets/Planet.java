package planets;

import java.util.ArrayList;

import javax.media.opengl.GL2;
import javax.media.opengl.glu.GLU;

import math.Color;
import math.Vector3;

import com.sun.opengl.util.gl2.GLUT;

public class Planet extends SpaceObject
{
	private final Color PLANET_COLOR = new Color(0, 0, 255);
	public Planet()
	{
		super();
	}
	
	public void colorObject(GL2 gl, GLU glu, GLUT glut)
	{
		gl.glColor3d(0, 0, 255);
	}
}
