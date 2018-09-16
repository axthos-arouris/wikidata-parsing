package wikidata.parsedItems.claims.snaks

import wikidata.WikidataId
import wikidata.parsedItems.claims.snaks.values.{SnakValue, StringValue}

case class StringValueSnak(snaktype: String, property: WikidataId, datavalue: StringValue, datatype: String) extends MainSnak
