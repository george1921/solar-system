package planets;

import java.util.ArrayList;

import javax.media.opengl.GL2;
import javax.media.opengl.glu.GLU;

import math.Vector3;

import com.sun.opengl.util.gl2.GLUT;

public class Sun implements ISpaceObject
{
	private double myAngle;
	private double myRotationSpeed;
	private double myDistance;
	private ISpaceObject myOrbitCenter;
	private Vector3 myRotationAxis;
	private double mySize;
	private ArrayList<ISpaceObject> myPlanets;
	
	public Sun(double rot, double dist, Vector3 rAxis, double size)
	{
		myAngle = 0;
		myRotationSpeed = rot;
		myDistance = dist;
		myRotationAxis = rAxis;
		mySize = size;
		myPlanets = new ArrayList<ISpaceObject>();
	}
	
	public void addPlanets(ArrayList<ISpaceObject> pList)
	{
		myPlanets = pList;
	}

	public void draw(GL2 gl, GLU glu, GLUT glut) 
	{
		gl.glRotated(myAngle, myRotationAxis.x, myRotationAxis.y, myRotationAxis.z);
		glut.glutWireSphere(mySize, 20, 20);	//radius, slices, stacks
		for(ISpaceObject planet: myPlanets)
		{
			gl.glPushMatrix();
			planet.draw(gl, glu, glut);
			gl.glPopMatrix();
		}
	}
	
	public void animate(GL2 gl, GLU glu, GLUT glut)
	{
		myAngle += myRotationSpeed;
		for(ISpaceObject planet: myPlanets)
		{
			planet.animate(gl, glu, glut);
		}
	}
}