#!/bin/sh

export JAVA_OPTS=-Xmx8g

scala -cp build/libs/com.github.axthosarouris.wikidata-parser-0.1-all.jar \
 com.github.axthosarouris.wikidata.application.Application \
 /home/orestis/wikidata/wikidata-20181008-all.json.gz

