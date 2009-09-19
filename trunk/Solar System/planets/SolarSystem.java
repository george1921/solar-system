package planets;

import java.io.*;
import java.util.ArrayList;

import javax.media.opengl.GL2;
import javax.media.opengl.glu.GLU;

import math.Vector3;

import com.sun.opengl.util.gl2.GLUT;

public class SolarSystem 
{
	private ArrayList<ISpaceObject> myObjects;
	
	public SolarSystem()
	{
		myObjects = new ArrayList<ISpaceObject>();
		try
		{
			BufferedReader input = new BufferedReader(new FileReader("SolarSystem.txt"));
			while(input.ready())
			{
				String s = input.readLine();
				if(s.charAt(0) == '*'){}
				else
				{
					s.toLowerCase();
					String params[] = s.split("; ");
					String rAxisString[] = params[5].split(" ");
					String oAxisString[] = params[7].split(" ");
					
					double rot = Double.parseDouble(params[2]);
					double dist = Double.parseDouble(params[3]);
					ISpaceObject oCenter = get(params[4]);
					Vector3 rAxis = new Vector3(Double.parseDouble(rAxisString[0]), Double.parseDouble(rAxisString[1]), Double.parseDouble(rAxisString[2]));
					double size = Double.parseDouble(params[6]);
					String name = params[1];
					Vector3 oAxis = new Vector3(Double.parseDouble(oAxisString[0]), Double.parseDouble(oAxisString[1]), Double.parseDouble(oAxisString[2]));
					double oTilt = Double.parseDouble(params[8]);
					double rTilt = Double.parseDouble(params[9]);
					double oSpeed = Double.parseDouble(params[10]);
					
					try 
					{
						Class<?> c = Class.forName("planets."+params[0]);
						
						if(params.length != 11)
						{
							System.out.println("Incorrect parameters: " + params[1]);
							System.exit(0);
						}
						else
						{
							ISpaceObject obj = (ISpaceObject)c.newInstance();
							c.cast(obj);
							obj.setParameters(rot, dist, oCenter, rAxis, size, name, oAxis, oTilt, rTilt, oSpeed);
							myObjects.add(obj);
							if(!name.equals("none"))
							{
								add(obj);
							}
						}
					} 
					catch (ClassNotFoundException e) 
					{
						System.out.println("Class \"" + params[0] + "\" not found");
						e.printStackTrace();
						System.exit(0);
					} catch (InstantiationException e) 
					{
						System.out.println("Instantiation exception");
						e.printStackTrace();
					} catch (IllegalAccessException e) 
					{
						System.out.println("Illegal Access");
						e.printStackTrace();
					}
				}
			}
		}
		catch(IOException e)
		{
			System.out.println(e);
			System.out.println("Error locating file: SolarSystem.txt");
		}
	}
	
	public void draw(GL2 gl, GLU glu, GLUT glut)
	{
		myObjects.get(0).draw(gl, glu, glut);
	}
	
	private ISpaceObject get(String name)
	{
		for(ISpaceObject o: myObjects)
		{
			if(o.getName().equals(name))
				return o;
		}
		//TODO: error if doesn't exist
		return null;
	}
	
	private void add(ISpaceObject obj)
	{
		for(ISpaceObject o: myObjects)
		{
			if(o.getName().equals(obj.getParentName()))
			{
				o.add(obj);
			}
		}
	}
	
	public void animate(GL2 gl, GLU glu, GLUT glut)
	{
		myObjects.get(0).animate(gl, glu, glut);
	}
}
