# SVGExtended

Processing library to extend the support of SVGs: image and text support.

It is developped for [Soby](https://github.com/poqudrof/Soby), and [PapARt](https://github.com/poqudrof/PapARt). 

Project page by RealityTech: https://rea.lity.tech/SVGExtended

## How to use ? 

Instead of creating a PShapeSVG from an XML, use a PShapeSVGExtended. 

``` java
XML imageXML = loadXML("image.svg");
PShape shape = new PShapeSVGExtended(imageXML);
```

## How to install ? 

The library already compiled, see the [release page](https://github.com/poqudrof/SVGExtended/releases).
