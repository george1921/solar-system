package planets;

import java.util.ArrayList;

import javax.media.opengl.GL2;
import javax.media.opengl.glu.GLU;

import math.Vector3;

import com.sun.opengl.util.gl2.GLUT;

public class SpaceObject implements ISpaceObject 
{
	protected double myRotationAngle;
	protected double myRotationSpeed;
	protected double myDistance;
	protected ISpaceObject myOrbitCenter;
	protected Vector3 myRotationAxis;
	protected double mySize;
	protected ArrayList<ISpaceObject> mySatellites;
	protected String myName;
	protected Vector3 myOrbitAxis;
	protected double myOrbitTilt;
	protected double myRotationTilt;
	protected double myOrbitAngle;
	protected double myOrbitSpeed;
	protected boolean myShowOrbit;
	
	public SpaceObject()
	{
		myRotationAngle = 0;
		myRotationSpeed = 0;
		myDistance = 0;
		myOrbitCenter = null;
		myRotationAxis = null;
		mySize = 0;
		mySatellites = new ArrayList<ISpaceObject>();
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
		mySatellites = new ArrayList<ISpaceObject>();
		myName = name;
		myOrbitAxis = oAxis;
		myOrbitTilt = oTilt;
		myRotationTilt = rTilt;
		myOrbitSpeed = oSpeed;
	}
	
	public void add(ISpaceObject obj)
	{
		mySatellites.add(obj);
	}

	public void draw(GL2 gl, GLU glu, GLUT glut) 
	{	
		if(myShowOrbit)
		{
			for(ISpaceObject sat: mySatellites)
			{
				gl.glPushMatrix();
				sat.drawOrbit(gl, glu, glut);
				gl.glPopMatrix();
			}
		}
		
		gl.glPushMatrix();
		transform(gl, glu, glut);
		gl.glRotated(myRotationAngle, myRotationAxis.x, myRotationAxis.y, myRotationAxis.z);
		glut.glutWireSphere(mySize, 20, 20);	//radius, slices, stacks
		gl.glPopMatrix();
		
		for(ISpaceObject sat: mySatellites)
		{
			gl.glPushMatrix();
			sat.draw(gl, glu, glut);
			gl.glPopMatrix();
		}
	}
	
	public void drawOrbit(GL2 gl, GLU glu, GLUT glut)
	{
		myOrbitCenter.transform(gl, glu, glut);
		colorOrbit(gl, glu, glut);
		gl.glRotated(myOrbitTilt, 1, 0, 0);
		gl.glTranslated(-myDistance, 0, 0);
		glut.glutWireTorus(myDistance, myDistance, 100, 1);
	}
	
	public void transform(GL2 gl, GLU glu, GLUT glut)
	{
		myOrbitCenter.transform(gl, glu, glut);
		gl.glRotated(myOrbitTilt, 1, 0, 0);
		gl.glRotated(myOrbitAngle, myOrbitAxis.x, myOrbitAxis.y, myOrbitAxis.z);
		gl.glTranslated(myDistance, 0, 0);
		colorObject(gl, glu, glut);
	}
	
	public void animate(GL2 gl, GLU glu, GLUT glut)
	{
		myRotationAngle += myRotationSpeed;
		myOrbitAngle += myOrbitSpeed;
		for(ISpaceObject sat: mySatellites)
		{
			sat.animate(gl, glu, glut);
		}
	}

	public String getName() 
	{
		return myName;
	}

	public String getParentName() 
	{
		return myOrbitCenter.getName();
	}

	public Vector3 getOrbitAxis() 
	{
		return myOrbitAxis;
	}

	public Vector3 getRotationAxis() 
	{
		return myRotationAxis;
	}

	public double getRotationSpeed() 
	{
		return myRotationSpeed;
	}
	
	public double getRotationAngle()
	{
		return myRotationAngle;
	}

	public double getOrbitAngle() 
	{
		return myOrbitAngle;
	}

	public void toggleOrbit(boolean toggle) 
	{
		myShowOrbit = toggle;
	}

	public void colorObject(GL2 gl, GLU glu, GLUT glut) 
	{
		
	}

	public void colorOrbit(GL2 gl, GLU glu, GLUT glut) 
	{
		gl.glColor3d(255, 255, 255);
	}
}
