package com.github.axhtosarouris.wikidata.parsedItems.claims.snaks

import com.github.axhtosarouris.wikidata.WikidataId
import com.github.axhtosarouris.wikidata.parsedItems.claims.snaks.values.{EmptySnakValue, SnakValue}

case class EmptyValueSnak(snaktype: String, property: WikidataId, datatype: WikidataId) extends MainSnak {
  override def datavalue: SnakValue= EmptySnakValue
}
