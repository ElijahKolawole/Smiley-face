import hypermedia.video.*;
import java.awt.Rectangle;
import java.awt.Image;

OpenCV opencv;
PImage smiles;

// contrast/brightness values
int contrast_value    = 0;
int brightness_value  = 0;

void setup() {

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



void draw() {

    // grab a new frame
    opencv.read();
    opencv.brightness( brightness_value );
    // proceed detection
    Rectangle[] faces = opencv.detect( 1.2, 2, OpenCV.HAAR_DO_CANNY_PRUNING, 40, 40 );
    // display the image
    image( opencv.image(), 0, 0 );
    // draw over face area(s)
    noFill();    
    stroke(255,0,0);
    for ( int i=0; i<faces.length; i++ ) {
    
       image(smiles, faces[i].x, faces[i].y, faces[i].width, faces[i].height);
    
    }
}



/**
 * Changes brightness values from dragged mouseY input
 */
void mouseDragged() {
    brightness_value = (int) map( mouseY, 0, width, -128, 128 );
}


