package planets;

import java.util.ArrayList;

import javax.media.opengl.GL2;
import javax.media.opengl.glu.GLU;

import math.Vector3;

import com.sun.opengl.util.gl2.GLUT;

public class Moon implements ISpaceObject
{
	private double myRotationAngle;
	private double myRotationSpeed;
	private double myDistance;
	private ISpaceObject myOrbitCenter;
	private Vector3 myRotationAxis;
	private double mySize;
	private ArrayList<ISpaceObject> mySatellites;
	private String myName;
	private Vector3 myOrbitAxis;
	private double myOrbitTilt;
	private double myRotationTilt;
	private double myOrbitAngle;
	private double myOrbitSpeed;
	private boolean myShowOrbit;
	
	public Moon()
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
				sat.drawAxis(gl, glu, glut);
				gl.glPopMatrix();
			}
		}
		
		gl.glPushMatrix();
		transform(gl, glu, glut);
		gl.glRotated(myRotationAngle, myRotationAxis.x, myRotationAxis.y, myRotationAxis.z);
		glut.glutWireSphere(mySize, 10, 10);
		gl.glPopMatrix();
		
		for(ISpaceObject sat: mySatellites)
		{
			gl.glPushMatrix();
			sat.draw(gl, glu, glut);
			gl.glPopMatrix();
		}
	}
	
	public void drawAxis(GL2 gl, GLU glu, GLUT glut)
	{
		myOrbitCenter.transform(gl, glu, glut);
		gl.glColor3d(255, 255, 255);
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
		gl.glColor3d(192, 192, 192);
	}
	
	public void animate(GL2 gl, GLU glu, GLUT glut)
	{
		myRotationAngle += myRotationSpeed;
		myOrbitAngle += myOrbitSpeed;
		for(ISpaceObject satellite: mySatellites)
		{
			satellite.animate(gl, glu, glut);
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
}