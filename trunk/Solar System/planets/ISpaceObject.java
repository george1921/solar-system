package planets;

import javax.media.opengl.GL2;
import javax.media.opengl.glu.GLU;

import com.sun.opengl.util.gl2.GLUT;

public interface ISpaceObject 
{	
	public void draw(GL2 gl, GLU glu, GLUT glut);

	public void animate(GL2 gl, GLU glu, GLUT glut);
}
