package planets;

import java.util.ArrayList;

import javax.media.opengl.GL2;
import javax.media.opengl.glu.GLU;

import math.Color;
import math.Vector3;

import com.sun.opengl.util.gl2.GLUT;

public class Sun extends SpaceObject
{
	private final Color SUN_COLOR = new Color(255, 255, 0);
	public Sun()
	{
		super();
	}
	
	public void drawOrbit(GL2 gl, GLU glu, GLUT glut)
	{
		//no axis
	}
	
	public void transform(GL2 gl, GLU glu, GLUT glut)
	{
		gl.glRotated(myOrbitTilt, 1, 0, 0);
		gl.glRotated(myOrbitAngle, myOrbitAxis.x, myOrbitAxis.y, myOrbitAxis.z);
		gl.glTranslated(myDistance, 0, 0);
		gl.glColor3d(SUN_COLOR.r, SUN_COLOR.g, SUN_COLOR.b);
	}
	
	public void animate(GL2 gl, GLU glu, GLUT glut)
	{
		myRotationAngle += myRotationSpeed;
		for(ISpaceObject sat: mySatellites)
		{
			sat.animate(gl, glu, glut);
		}
	}

	public String getParentName() 
	{
		return null;
	}
	
	public void colorObject(GL2 gl, GLU glu, GLUT glut)
	{
		gl.glColor3d(255, 255, 0);
	}
}