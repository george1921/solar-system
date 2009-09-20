package planets;

import java.util.ArrayList;

import javax.media.opengl.GL2;
import javax.media.opengl.glu.GLU;

import math.Vector3;

import com.sun.opengl.util.gl2.GLUT;

public class Moon extends SpaceObject
{
	public Moon()
	{
		super();
	}
	
	public void colorObject(GL2 gl, GLU glu, GLUT glut)
	{
		gl.glColor3d(190, 190, 190);
	}
}