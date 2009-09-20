package planets;

import java.util.ArrayList;

import javax.media.opengl.GL2;
import javax.media.opengl.glu.GLU;

import math.Vector3;

import com.sun.opengl.util.gl2.GLUT;

public class Sun implements ISpaceObject
{
	private double myRotationAngle;
	private double myRotationSpeed;
	private double myDistance;
	private ISpaceObject myOrbitCenter;
	private Vector3 myRotationAxis;
	private double mySize;
	private ArrayList<ISpaceObject> myPlanets;
	private String myName;
	private Vector3 myOrbitAxis;
	private double myOrbitTilt;
	private double myRotationTilt;
	private double myOrbitAngle;
	private double myOrbitSpeed;
	private boolean myShowOrbit;
	
	public Sun()
	{
		myRotationAngle = 0;
		myRotationSpeed = 0;
		myDistance = 0;
		myOrbitCenter = null;
		myRotationAxis = null;
		mySize = 0;
		myPlanets = new ArrayList<ISpaceObject>();
		myName = null;
		myOrbitAxis = null;
		myOrbitTilt = 0;
		myRotationTilt = 0;
		myOrbitAngle = 0;
		myOrbitSpeed = 0;
		myShowOrbit = true;
	}
		
	public void setParameters(double rot, double dist, ISpaceObject oCenter, Vector3 rAxis, double size, String name, Vector3 oAxis, double oTilt, double rTilt, double oSpeed)
	{
		myRotationSpeed = rot;
		myDistance = dist;
		myOrbitCenter = oCenter;
		myRotationAxis = rAxis;
		mySize = size;
		myPlanets = new ArrayList<ISpaceObject>();
		myName = name;
		myOrbitAxis = oAxis;
		myOrbitTilt = oTilt;
		myRotationTilt = rTilt;
		myOrbitSpeed = oSpeed;
	}
	
	public void add(ISpaceObject obj)
	{
		myPlanets.add(obj);
	}

	public void draw(GL2 gl, GLU glu, GLUT glut) 
	{	
		if(myShowOrbit)
		{
			for(ISpaceObject planet: myPlanets)
			{
				gl.glPushMatrix();
				planet.drawAxis(gl, glu, glut);
				gl.glPopMatrix();
			}
		}
		
		gl.glPushMatrix();
		transform(gl, glu, glut);
		gl.glRotated(myRotationAngle, myRotationAxis.x, myRotationAxis.y, myRotationAxis.z);
		glut.glutWireSphere(mySize, 20, 20);	//radius, slices, stacks
		gl.glPopMatrix();
		
		for(ISpaceObject planet: myPlanets)
		{
			gl.glPushMatrix();
			planet.draw(gl, glu, glut);
			gl.glPopMatrix();
		}
	}
	
	public void drawAxis(GL2 gl, GLU glu, GLUT glut)
	{
		
	}
	
	public void transform(GL2 gl, GLU glu, GLUT glut)
	{
		gl.glRotated(myOrbitTilt, 1, 0, 0);
		gl.glRotated(myOrbitAngle, myOrbitAxis.x, myOrbitAxis.y, myOrbitAxis.z);
		gl.glTranslated(myDistance, 0, 0);
		gl.glColor3d(255, 255, 0);
	}
	
	public void animate(GL2 gl, GLU glu, GLUT glut)
	{
		myRotationAngle += myRotationSpeed;
		for(ISpaceObject planet: myPlanets)
		{
			planet.animate(gl, glu, glut);
		}
	}

	public String getName() 
	{
		return myName;
	}

	public String getParentName() 
	{
		return null;
	}

	public Vector3 getOrbitAxis() {
		// TODO Auto-generated method stub
		return myOrbitAxis;
	}

	public Vector3 getRotationAxis() {
		// TODO Auto-generated method stub
		return myRotationAxis;
	}

	public double getRotationSpeed() {
		// TODO Auto-generated method stub
		return myRotationSpeed;
	}
	
	public double getRotationAngle()
	{
		return myRotationAngle;
	}

	public double getOrbitAngle() {
		// TODO Auto-generated method stub
		return myOrbitAngle;
	}

	public void toggleOrbit(boolean toggle) 
	{
		myShowOrbit = toggle;
	}
}