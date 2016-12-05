/**
 * Load and Display a Shape. 
 * Illustration by George Brower. 
 * 
 * The loadShape() command is used to read simple SVG (Scalable Vector Graphics)
 * files and OBJ (Object) files into a Processing sketch. This example loads an
 * SVG file of a monster robot face and displays it to the screen. 
 */

import tech.lity.rea.svgextended.*;

PShape bot1, bot2;

void setup() {
  size(640, 660);
  // The file "bot1.svg" must be in the data folder
  // of the current sketch to load successfully
  bot1 = loadShape("bot2.svg");

  // The file "bot2.svg" must be located with "bot1.svg"
  bot2 = new PShapeSVGExtended(loadXML("bot2.svg"));
} 

void draw(){
  background(102);
  shape(bot1, 0, 0); 
  shape(bot2, 0, 320);
}
