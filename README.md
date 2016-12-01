# SVGExtended

Processing library to extend the support of SVGs, for now there is image and text that is added. 

It is developped for [Soby](https://github.com/poqudrof/Soby), and [PapARt](https://github.com/poqudrof/PapARt). 

## How to use ? 

Instead of creating a PShapeSVG from an XML, use a PShapeSVGExtended. 

``` java
XML imageXML = loadXML("image.svg");
PShape shape = new PShapeSVGExtended(imageXML);
```

## How to install ? 

The library already compiled, see the [release page](https://github.com/poqudrof/SVGExtended/releases).
