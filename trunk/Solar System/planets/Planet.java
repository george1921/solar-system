package planets;

import java.util.ArrayList;

import javax.media.opengl.GL2;
import javax.media.opengl.glu.GLU;

import math.Vector3;

import com.sun.opengl.util.gl2.GLUT;

public class Planet implements ISpaceObject
{
	private double myAngle;
	private double myRotationSpeed;
	private double myDistance;
	private ISpaceObject myOrbitCenter;
	private Vector3 myRotationAxis;
	private double mySize;
	private ArrayList<ISpaceObject> myMoons;
	
	public Planet(double rot, double dist, Vector3 rAxis, double size)
	{
		myAngle = 0;
		myRotationSpeed = rot;
		myDistance = dist;
		myRotationAxis = rAxis;
		mySize = size;
		myMoons = new ArrayList<ISpaceObject>();
	}

	public void draw(GL2 gl, GLU glu, GLUT glut) 
	{
		gl.glRotated(myAngle, myRotationAxis.x, myRotationAxis.y, myRotationAxis.z);
		gl.glTranslated(myDistance, 0, 0);
		gl.glRotated(myAngle, myRotationAxis.x, myRotationAxis.y, myRotationAxis.z);
		glut.glutWireSphere(mySize, 10, 10);
	}
	
	public void animate(GL2 gl, GLU glu, GLUT glut)
	{
		myAngle += myRotationSpeed;
		for(ISpaceObject moon: myMoons)
		{
			moon.animate(gl, glu, glut);
		}
	}
}
