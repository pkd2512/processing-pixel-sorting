import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class pixelSort extends PApplet {

// load image
PImage img, pImg;
int countX, countY = 0;
public void settings() {
    size(displayHeight, displayHeight);
}
public void setup() {
    background(51);
    // fullScreen();
    frameRate(12);

    // load the image and copy to a new image object
    img = loadImage("Broadway_Boogie_Woogie.jpg");
    pImg = img.copy();
    // image(img, 0, 0, displayHeight, displayHeight);

    img.loadPixels();
    
}

public void draw() {

    process(img, pImg, displayHeight, displayHeight, countX, countY);
    


    if (countY < img.height) {
        countY ++;
    }
    if (countX < img.width) {
        countX ++;
    }

    if ((countY >= img.height) && (countX >= img.width)) {
        noLoop();
    }
}

public void process(PImage img, PImage pImg, float width, float height, int countY, int countX) {
    img.loadPixels(); 
    pImg.loadPixels();
// int[] newPixels = sort(img.pixels);

    // for (int y = 0; y < count; y++) {
        // for (int x = 0; x < count; x++) {

            // int loc = y + x*img.height;

            // image processing
            int[] newPixels = subset(img.pixels, countY*img.width, countX);
            newPixels = sort(newPixels, countX);
            newPixels = reverse(newPixels);

            // The functions red(), green(), and blue() pull out the 3 color components from a pixel.
            float r = red(newPixels[countX]);
            float g = green(newPixels[countX]);
            float b = blue(newPixels[countX]);

            
            // Set the display pixel to the image pixel
            pImg.set(countY, countX, color(r,g,b)); 
        // }
    // }
    // display the result image
    image(pImg, 0, 0, width, height);
}
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "--present", "--window-color=#666666", "--stop-color=#cccccc", "pixelSort" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
