package planets;

import javax.media.opengl.GL2;
import javax.media.opengl.glu.GLU;

import math.Vector3;

import com.sun.opengl.util.gl2.GLUT;

public interface ISpaceObject 
{	
	public void setParameters(double rot, double dist, ISpaceObject oCenter, Vector3 rAxis, double size, String name, Vector3 oAxis, double oTilt, double rTilt, double oSpeed);
	
	public void add(ISpaceObject obj);
	
	public void draw(GL2 gl, GLU glu, GLUT glut);

	public void animate(GL2 gl, GLU glu, GLUT glut);

	public String getName();
	
	public String getParentName();

	public void drawAxis(GL2 gl, GLU glu, GLUT glut);

	public void transform(GL2 gl, GLU glu, GLUT glut);
	
	public double getRotationSpeed();
	
	public double getRotationAngle();
	
	public Vector3 getRotationAxis();
		
	public Vector3 getOrbitAxis();
	
	public double getOrbitAngle();
}
