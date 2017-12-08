#!/bin/bash
rm *.java
javacc EventTraceParser.jj
cp *.java ../java/tr/edu/boun/cmpe/mas/akin/hammurabi/event/parser/etr
rm *.java
javacc SocialProtocolParser.jj
cp *.java ../java/tr/edu/boun/cmpe/mas/akin/hammurabi/protocol/parser
rm *.java