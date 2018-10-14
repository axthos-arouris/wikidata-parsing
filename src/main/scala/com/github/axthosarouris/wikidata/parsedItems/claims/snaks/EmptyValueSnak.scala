package com.github.axthosarouris.wikidata.parsedItems.claims.snaks

import com.github.axthosarouris.wikidata.WikidataId
import com.github.axthosarouris.wikidata.parsedItems.claims.snaks.values.{EmptySnakValue, SnakValue}

case class EmptyValueSnak(snaktype: String, property: WikidataId, datatype: WikidataId) extends MainSnak {
  override def datavalue: SnakValue= EmptySnakValue
}
