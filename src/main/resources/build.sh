#!/bin/bash
rm *.java
javacc EventTraceParser.jj
cp *.java ../java/tr/edu/boun/cmpe/mas/akin/hammurabi/event/parser
rm *.java
