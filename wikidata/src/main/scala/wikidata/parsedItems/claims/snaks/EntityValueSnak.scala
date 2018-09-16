package wikidata.parsedItems.claims.snaks

import wikidata.WikidataId
import wikidata.parsedItems.claims.snaks.values.EntityValue

case class EntityValueSnak(snaktype: String, property: WikidataId, datavalue: EntityValue, datatype: String) extends MainSnak
