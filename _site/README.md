
# SVGExtended

This library is developped for [Soby](https://github.com/poqudrof/Soby), and [PapARt](https://github.com/poqudrof/PapARt). 

## How to use ? 

Instead of creating a PShapeSVG from an XML, use a PShapeSVGExtended. 

``` java
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
```

## How to install ? 

The library already compiled, see the [release page](https://github.com/poqudrof/SVGExtended/releases).
