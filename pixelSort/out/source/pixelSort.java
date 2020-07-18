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
int count;
String activeProcess;

public void settings() {
    size(displayHeight, displayHeight);
}
public void setup() {
    background(51);
    // fullScreen();
    frameRate(30);

    // load the image and copy to a new image object
    // img = loadImage("Red_Tree.jpg");
    img = loadImage("Broadway_Boogie_Woogie.jpg");
    pImg = img.copy();
    // image(img, 0, 0, displayHeight, displayHeight);
    count = img.height-1;
    img.loadPixels();
    
}

public void draw() {
    activeProcess = "processClusterEvenOdd";
    processCluster(img, pImg, displayHeight, displayHeight, count);
    
    int pixelCount = img.height;
    if (count > 0) {
        count --;
    }
    else{
        noLoop();
    }
}
public void mousePressed() {
    saveFrame("./exports/"+activeProcess+"Image-####.png");
}
public void processPastel(PImage img, PImage pImg, float width, float height, int count) {
    img.loadPixels(); 
    pImg.loadPixels();

    for (int x = 0; x < img.width; x++) {

        // image processing
        int[] newPixels = subset(img.pixels, count*img.width, img.width);
        newPixels = sort(newPixels, round(img.width*(sin(random(HALF_PI,PI)))));
        // newPixels = reverse(newPixels);

        // The functions red(), green(), and blue() pull out the 3 color components from a pixel.
        float r = red(newPixels[x]);
        float g = green(newPixels[x]);
        float b = blue(newPixels[x]);

        
        // Set the display pixel to the image pixel
        pImg.set(x, count, color(r,g,b)); 

    }
    // display the result image
    image(pImg, 0, 0, width, height);
}

public void processHatched(PImage img, PImage pImg, float width, float height, int count) {
    img.loadPixels(); 
    pImg.loadPixels();
// int[] newPixels = sort(img.pixels);

    // for (int y = 0; y < count; y++) {
        for (int x = 0; x < img.width; x++) {

            // int loc = y + x*img.height;

            // image processing
            int[] newPixels = subset(img.pixels, count*img.width, img.width);
            newPixels = sort(newPixels, round(img.width));
            newPixels = reverse(newPixels);

            // The functions red(), green(), and blue() pull out the 3 color components from a pixel.
            float r = red(newPixels[x]);
            float g = green(newPixels[x]);
            float b = blue(newPixels[x]);

            
            // Set the display pixel to the image pixel
            pImg.set(count^5, (x^3), color(r,g,b)); 
            // pImg.set((abs(count-x^2)), x, color(r,g,b)); 

        }
    // }
    // display the result image
    // delay(1000);
    image(pImg, 0, 0, width, height);
}

public void processSprinkles(PImage img, PImage pImg, float width, float height, int count) {
    img.loadPixels(); 
    pImg.loadPixels();
// int[] newPixels = sort(img.pixels);

    // for (int y = 0; y < count; y++) {
        for (int x = 0; x < img.width; x++) {

            // int loc = y + x*img.height;

            // image processing
            int[] newPixels = subset(img.pixels, count*img.width, img.width);
            // newPixels = sort(newPixels, round(img.width));
            // newPixels = reverse(newPixels);

            // The functions red(), green(), and blue() pull out the 3 color components from a pixel.
            float r = red(newPixels[x]);
            float g = green(newPixels[x]);
            float b = blue(newPixels[x]);

            
            // Set the display pixel to the image pixel
            pImg.set(round(x), round(count*sin(random(HALF_PI,PI))),  color(r,g,b)); 
            // pImg.set((abs(count-x^2)), x, color(r,g,b)); 

        }
    // }
    // display the result image
    // delay(1000);
    image(pImg, 0, 0, width, height);
}

public void processSymmetry(PImage img, PImage pImg, float width, float height, int count) {
    img.loadPixels(); 
    pImg.loadPixels();
// int[] newPixels = sort(img.pixels);

    // for (int y = 0; y < count; y++) {
        for (int x = 0; x < img.width; x++) {

            // int loc = y + x*img.height;

            // image processing
            int[] newPixels = subset(img.pixels, count*img.width, img.width);
            newPixels = sort(newPixels, round(img.width));
            if(count%2 == 0) {
                newPixels = reverse(newPixels);
            }


            // The functions red(), green(), and blue() pull out the 3 color components from a pixel.
            float r = red(newPixels[x]);
            float g = green(newPixels[x]);
            float b = blue(newPixels[x]);

            
            // Set the display pixel to the image pixel
            pImg.set(round(x), round(count),  color(r,g,b)); 
            // pImg.set((abs(count-x^2)), x, color(r,g,b)); 

        }
    // }
    // display the result image
    // delay(1000);
    image(pImg, 0, 0, width, height);
}

public void processThread(PImage img, PImage pImg, float width, float height, int count) {
    img.loadPixels(); 
    pImg.loadPixels();
// int[] newPixels = sort(img.pixels);

    // for (int y = 0; y < count; y++) {
        for (int x = 0; x < img.width; x++) {

            // int loc = y + x*img.height;

            // image processing
            int[] newPixels = subset(img.pixels, count*img.width, img.width);
            // newPixels = sort(newPixels, round(img.width));
            if(count%2 == 0) {
                newPixels = reverse(newPixels);
            }


            // The functions red(), green(), and blue() pull out the 3 color components from a pixel.
            float r = red(newPixels[x]);
            float g = green(newPixels[x]);
            float b = blue(newPixels[x]);

            
            // Set the display pixel to the image pixel
            pImg.set(round(x), round(count),  color(r,g,b)); 
            // pImg.set((abs(count-x^2)), x, color(r,g,b)); 

        }
    // }
    // display the result image
    // delay(1000);
    image(pImg, 0, 0, width, height);
}

public void processCluster(PImage img, PImage pImg, float width, float height, int count) {
    img.loadPixels(); 
    pImg.loadPixels();
// int[] newPixels = sort(img.pixels);

    // for (int y = 0; y < count; y++) {
        for (int x = 0; x < img.width; x++) {

            // int loc = y + x*img.height;

            // image processing
            
            
            int[] part1 = subset(img.pixels, count*img.width, round(img.width/2));
            int[] part2 = subset(img.pixels, count*img.width + round(img.width/2), round(img.width/2));


            // EVEN
            // if(count%2 == 0) {
            //     part1 = sort(part1, round(img.width/2));
            //     part2 = sort(part2, round(img.width/2));
            // }
            // part1 = reverse(part1);
            // // part2 = reverse(part2);
            // int[] newPixels = concat(part1, part2);

            // EVEN-ODD
            if(count%2 == 0) {
                part1 = sort(part1, round(img.width/2));
                part2 = reverse(part2);
            }
            else{
                part2 = sort(part2, round(img.width/2));
                
            }
part1 = reverse(part1);
            // part2 = reverse(part2);
            int[] newPixels = concat(part1, part2);

            // The functions red(), green(), and blue() pull out the 3 color components from a pixel.
            float r = red(newPixels[x]);
            float g = green(newPixels[x]);
            float b = blue(newPixels[x]);

            
            // Set the display pixel to the image pixel
            pImg.set(round(x), round(count),  color(r,g,b)); 
            // pImg.set((abs(count-x^2)), x, color(r,g,b)); 

        }
    // }
    // display the result image
    // delay(1000);
    image(pImg, 0, 0, width, height);
}




//BASE

public void process(PImage img, PImage pImg, float width, float height, int count) {
    img.loadPixels(); 
    pImg.loadPixels();
// int[] newPixels = sort(img.pixels);

    // for (int y = 0; y < count; y++) {
        for (int x = 0; x < img.width; x++) {

            // int loc = y + x*img.height;

            // image processing
            int[] newPixels = subset(img.pixels, count*img.width, img.width);
            newPixels = sort(newPixels, round(img.width));
            // newPixels = reverse(newPixels);

            // The functions red(), green(), and blue() pull out the 3 color components from a pixel.
            float r = red(newPixels[x]);
            float g = green(newPixels[x]);
            float b = blue(newPixels[x]);

            
            // Set the display pixel to the image pixel
            pImg.set(round(x), round(count),  color(r,g,b)); 
            // pImg.set((abs(count-x^2)), x, color(r,g,b)); 

        }
    // }
    // display the result image
    // delay(1000);
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
