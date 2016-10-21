/* -*- mode: java; c-basic-offset: 2; indent-tabs-mode: nil -*- */

/*
  Part of the Processing project - http://processing.org

  Copyright (c) 2006-10 Ben Fry and Casey Reas

  This library is free software; you can redistribute it and/or
  modify it under the terms of the GNU Lesser General Public
  License version 2.1 as published by the Free Software Foundation.

  This library is distributed in the hope that it will be useful,
  but WITHOUT ANY WARRANTY; without even the implied warranty of
  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
  Lesser General Public License for more details.

  You should have received a copy of the GNU Lesser General
  Public License along with this library; if not, write to the
  Free Software Foundation, Inc., 59 Temple Place, Suite 330,
  Boston, MA  02111-1307  USA
*/

package tech.lity.rea.svgextended;

import java.awt.Image;
import java.awt.color.ColorSpace;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;
import javax.xml.bind.DatatypeConverter;

import processing.core.PConstants;
import processing.core.PGraphics;
import processing.core.PImage;
import processing.core.PShape;


public class PShapeExtended extends PShape {

    PGraphics g;
    protected String imagePath;

    
  /**
   * @nowebref
   */
  public PShapeExtended() {
    this.family = GROUP;
  }


  /**
   * @nowebref
   */
  public PShapeExtended(int family) {
    this.family = family;
  }


  /**
   * @nowebref
   */
  public PShapeExtended(PGraphics g, int family) {
    this.g = g;
    this.family = family;

    // Style parameters are retrieved from the current values in the renderer.
    textureMode = g.textureMode;

    colorMode(g.colorMode,
              g.colorModeX, g.colorModeY, g.colorModeZ, g.colorModeA);

    // Initial values for fill, stroke and tint colors are also imported from
    // the renderer. This is particular relevant for primitive shapes, since is
    // not possible to set their color separately when creating them, and their
    // input vertices are actually generated at rendering time, by which the
    // color configuration of the renderer might have changed.
    fill = g.fill;
    fillColor = g.fillColor;

    stroke = g.stroke;
    strokeColor = g.strokeColor;
    strokeWeight = g.strokeWeight;
    strokeCap = g.strokeCap;
    strokeJoin = g.strokeJoin;

    tint = g.tint;
    tintColor = g.tintColor;

    setAmbient = g.setAmbient;
    ambientColor = g.ambientColor;
    specularColor = g.specularColor;
    emissiveColor = g.emissiveColor;
    shininess = g.shininess;

    sphereDetailU = g.sphereDetailU;
    sphereDetailV = g.sphereDetailV;

//    bezierDetail = pg.bezierDetail;
//    curveDetail = pg.curveDetail;
//    curveTightness = pg.curveTightness;

    rectMode = g.rectMode;
    ellipseMode = g.ellipseMode;

//    normalX = normalY = 0;
//    normalZ = 1;
//
//    normalMode = NORMAL_MODE_AUTO;

    // To make sure that the first vertex is marked as a break.
    // Same behavior as in the immediate mode.
//    breakShape = false;

    if (family == GROUP) {
      // GROUP shapes are always marked as ended.
//      shapeCreated = true;
      // TODO why was this commented out?
    }
  }


  public PShapeExtended(PGraphics g, int kind, float... params) {
    this(g, PRIMITIVE);
    setKind(kind);
    setParams(params);
  }

  protected void drawPrimitive(PGraphics g) {
    if (kind == POINT) {
      g.point(params[0], params[1]);

    } else if (kind == LINE) {
      if (params.length == 4) {  // 2D
        g.line(params[0], params[1],
               params[2], params[3]);
      } else {  // 3D
        g.line(params[0], params[1], params[2],
               params[3], params[4], params[5]);
      }

    } else if (kind == TRIANGLE) {
      g.triangle(params[0], params[1],
                 params[2], params[3],
                 params[4], params[5]);

    } else if (kind == QUAD) {
      g.quad(params[0], params[1],
             params[2], params[3],
             params[4], params[5],
             params[6], params[7]);

    } else if (kind == RECT) {

      if (imagePath != null){
          loadImage(g);
      }
      if (image != null) {
        int oldMode = g.imageMode;
        g.imageMode(CORNER);
        g.image(image, params[0], params[1], params[2], params[3]);
        g.imageMode(oldMode);
      } else {
        int oldMode = g.rectMode;
        g.rectMode(rectMode);
        if (params.length == 4) {
          g.rect(params[0], params[1],
                 params[2], params[3]);
        } else if (params.length == 5) {
          g.rect(params[0], params[1],
                 params[2], params[3],
                 params[4]);
        } else if (params.length == 8) {
          g.rect(params[0], params[1],
                 params[2], params[3],
                 params[4], params[5],
                 params[6], params[7]);
        }
        g.rectMode(oldMode);
      }
    } else if (kind == ELLIPSE) {
      int oldMode = g.ellipseMode;
      g.ellipseMode(ellipseMode);
      g.ellipse(params[0], params[1],
                params[2], params[3]);
      g.ellipseMode(oldMode);

    } else if (kind == ARC) {
      int oldMode = g.ellipseMode;
      g.ellipseMode(ellipseMode);
      if (params.length == 6) {
        g.arc(params[0], params[1],
              params[2], params[3],
              params[4], params[5]);
      } else if (params.length == 7) {
        g.arc(params[0], params[1],
              params[2], params[3],
              params[4], params[5],
              (int) params[6]);
      }
      g.ellipseMode(oldMode);

    } else if (kind == BOX) {
      if (params.length == 1) {
        g.box(params[0]);
      } else {
        g.box(params[0], params[1], params[2]);
      }

    } else if (kind == SPHERE) {
      g.sphere(params[0]);
    }
  }

  private void loadImage(PGraphics g){

      if(this.imagePath.startsWith("data:image")){
          loadBase64Image();
      }

      if(this.imagePath.startsWith("file://")){
          loadFileSystemImage(g);
      }
      this.imagePath = null;
  }
  
  private void loadFileSystemImage(PGraphics g){
    imagePath = imagePath.substring(7);
    PImage loadedImage = g.parent.loadImage(imagePath);
    if(loadedImage == null){
      System.err.println("Error loading image file: " + imagePath);
    }else{
      setTexture(loadedImage);
    }
  }

 private void loadBase64Image(){
    String[] parts = this.imagePath.split(";base64,");
    String extension = parts[0].substring(11);
    String encodedData = parts[1];

    byte[] decodedBytes = DatatypeConverter.parseBase64Binary(encodedData);

    if(decodedBytes == null){
      System.err.println("Decode Error on image: " + imagePath.substring(0, 20));
      return;
    }
    
    Image awtImage = new ImageIcon(decodedBytes).getImage();

    if (awtImage instanceof BufferedImage) {
      BufferedImage buffImage = (BufferedImage) awtImage;
      int space = buffImage.getColorModel().getColorSpace().getType();
      if (space == ColorSpace.TYPE_CMYK) {
       return;
      }
    }

    PImage loadedImage = new PImage(awtImage);
    if (loadedImage.width == -1) {
      // error...
    }

    // if it's a .gif image, test to see if it has transparency
    if (extension.equals("gif") || extension.equals("png") ||
      extension.equals("unknown")) {
      checkAlpha(loadedImage);
    }
    
    setTexture(loadedImage);
  }
 
 // HACK: to access the checkAlpha method.
  private void checkAlpha(PImage img) {
    if (img.pixels == null) return;

    for (int i = 0; i < img.pixels.length; i++) {
      // since transparency is often at corners, hopefully this
      // will find a non-transparent pixel quickly and exit
      if ((img.pixels[i] & 0xff000000) != 0xff000000) {
        img.format = ARGB;
        break;
      }
    }
  }

  // . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .

}
