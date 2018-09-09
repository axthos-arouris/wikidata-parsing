package wikidata.parsedItems.claims.snaks

import wikidata.WikidataId
import wikidata.parsedItems.claims.snaks.values.{EmptySnakValue, SnakValue}

case class EmptyValueSnak(snaktype: String, property: WikidataId, datatype: WikidataId) extends MainSnak {
  override def datavalue: SnakValue= EmptySnakValue
}
