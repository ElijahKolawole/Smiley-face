import processing.core.*; 
import processing.xml.*; 

import hypermedia.video.*; 
import java.awt.Rectangle; 
import java.awt.Image; 

import java.applet.*; 
import java.awt.Dimension; 
import java.awt.Frame; 
import java.awt.event.MouseEvent; 
import java.awt.event.KeyEvent; 
import java.awt.event.FocusEvent; 
import java.awt.Image; 
import java.io.*; 
import java.net.*; 
import java.text.*; 
import java.util.*; 
import java.util.zip.*; 
import java.util.regex.*; 

public class Smiley_face extends PApplet {






OpenCV opencv;
PImage smiles;

// contrast/brightness values
int contrast_value    = 0;
int brightness_value  = 0;



public void setup() {

    size( 640, 400 );

    // Smiley face image
    smiles = loadImage("Smiley.png");

    opencv = new OpenCV( this );
    // Opens video stream
    opencv.capture( width, height );
    // Load detection description (from haarcascade_frontalface_alt.xml)
    opencv.cascade( OpenCV.CASCADE_FRONTALFACE_ALT );  
    
    println( "Drag mouse on Y-axis inside this sketch window to change brightness" );

}


public void stop() {
    opencv.stop();
    super.stop();
}



public void draw() {

    // grab a new frame
    opencv.read();
    opencv.brightness( brightness_value );

    // proceed detection
    Rectangle[] faces = opencv.detect( 1.2f, 2, OpenCV.HAAR_DO_CANNY_PRUNING, 40, 40 );

    // display the image
    image( opencv.image(), 0, 0 );

    // draw over face area(s)
    noFill();    
    stroke(255,0,0);
    for( int i=0; i<faces.length; i++ ) {
       image(smiles, faces[i].x, faces[i].y, faces[i].width, faces[i].height);
    }
}



/**
 * Changes brightness values from dragged mouseY input
 */
public void mouseDragged() {
    brightness_value = (int) map( mouseY, 0, width, -128, 128 );
}


  static public void main(String args[]) {
    PApplet.main(new String[] { "--bgcolor=#FFFFFF", "Smiley_face" });
  }
}
