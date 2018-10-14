package com.github.axthosarouris.wikidata.parsedItems.claims.snaks

import com.github.axthosarouris.wikidata.WikidataId
import com.github.axthosarouris.wikidata.parsedItems.claims.snaks.values.{SnakValue, StringValue}

case class StringValueSnak(snaktype: String, property: WikidataId, datavalue: StringValue, datatype: String) extends MainSnak
