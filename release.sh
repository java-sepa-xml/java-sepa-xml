#!/bin/bash
mvn versions:set -DnewVersion=1.0
mvn clean deploy -P release
