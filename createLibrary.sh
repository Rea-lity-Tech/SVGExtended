#!/bin/bash 

SKETCHBOOK=sketches
TMP=tmp
NAME=SVGExtended

VERSION=3.2.1

mkdir $TMP
mkdir $TMP/$NAME 
mkdir $TMP/$NAME/library
mkdir $TMP/$NAME/examples


echo "Copy Library"
# Library
cp target/$NAME-$VERSION.jar $TMP/$NAME/library/$NAME.jar


echo "Copy the sources" 
# copy the source also
cp -R src $TMP/$NAME/
cp -R pom.xml $TMP/$NAME/
cp -R test $TMP/$NAME/

echo "Copy the JavaDoc" 
cp -R target/site/apidocs $TMP/$NAME/

echo "Copy the Examples" 
cp -R examples/* $TMP/$NAME/examples/

echo "Create the archive..." 
cd $TMP

tar -zcf $NAME.tgz $NAME

mv $NAME.tgz  .. 
cd .. 

echo "Clean " 
rm -rf $TMP


echo "Creation OK" 
