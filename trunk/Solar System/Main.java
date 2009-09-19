import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.media.opengl.awt.GLJPanel;
import com.sun.opengl.util.*;


/**
 * Simply creates a frame for viewing OpenGL application in Java.
 * 
 * This file should only need to be modified if extra user interface 
 * capabilities are required.
 * 
 * @author Robert C. Duvall
 */
public class Main
{
    public static final Dimension SIZE = new Dimension(600, 600);
    public static final int FPS = 40;


    public static void main (String[] args)
    {
        // create OpenGL classes
    	final Scene scene = new Display(args);       // change this class for your project
        final GLJPanel canvas = new GLJPanel();
        final Animator animator = new FPSAnimator(canvas, FPS);
        final Listener listener = new Listener(scene, animator);

        // manage OpenGL canvas
        canvas.addGLEventListener(listener);
        canvas.addKeyListener(listener);
        canvas.setPreferredSize(SIZE);

        // create titled window to view animation
        JFrame frame = new JFrame(listener.getTitle());
        frame.addWindowListener(new WindowAdapter()
        {
            public void windowClosing (WindowEvent e)
            {
                // Run this on another thread than the AWT event queue to
                // make sure the call to Animator.stop() completes before
                // exiting (otherwise causes locks on some systems).
                new Thread(new Runnable()
                {
                    public void run ()
                    {
                        animator.stop();
                        System.exit(0);
                    }
                }).start();
            }
        });
        frame.getContentPane().add(canvas);
        frame.pack();
        frame.setVisible(true);

        // start thread to drive animation
        animator.start();
        // allow for key events
        canvas.requestFocus();
    }
}
