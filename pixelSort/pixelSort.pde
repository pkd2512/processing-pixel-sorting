// load image
PImage img, pImg;
int count = 0;
void settings() {
    size(displayHeight, displayHeight);
}
void setup() {
    background(51);
    // fullScreen();
    frameRate(12);

    // load the image and copy to a new image object
    img = loadImage("Broadway_Boogie_Woogie.jpg");
    pImg = img.copy();
    // image(img, 0, 0, displayHeight, displayHeight);

    img.loadPixels();
    
}

void draw() {

    process(img, pImg, displayHeight, displayHeight, count);
    


    int pixelCount = img.height;
    // print(pixelCount);
    if (count < pixelCount) {
        count ++;
    }
    else{
        noLoop();
    }
}

void process(PImage img, PImage pImg, float width, float height, int count) {
    img.loadPixels(); 
    pImg.loadPixels();
// int[] newPixels = sort(img.pixels);

    // for (int y = 0; y < count; y++) {
        for (int x = 0; x < img.width; x++) {

            // int loc = y + x*img.height;

            // image processing
            int[] newPixels = subset(img.pixels, count*img.width, img.width);
            newPixels = sort(newPixels, img.width);
            newPixels = reverse(newPixels);

            // The functions red(), green(), and blue() pull out the 3 color components from a pixel.
            float r = red(newPixels[x]);
            float g = green(newPixels[x]);
            float b = blue(newPixels[x]);

            
            // Set the display pixel to the image pixel
            pImg.set(count, x, color(r,g,b)); 
        }
    // }
    // display the result image
    image(pImg, 0, 0, width, height);
}