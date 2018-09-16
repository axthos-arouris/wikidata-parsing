package com.github.axhtosarouris.wikidata.parsedItems.claims.snaks

import com.github.axhtosarouris.wikidata.WikidataId
import com.github.axhtosarouris.wikidata.parsedItems.claims.snaks.values.{SnakValue, StringValue}

case class StringValueSnak(snaktype: String, property: WikidataId, datavalue: StringValue, datatype: String) extends MainSnak
