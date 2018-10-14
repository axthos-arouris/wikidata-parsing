package com.github.axthosarouris.wikidata.parsedItems.claims.snaks

import com.github.axthosarouris.wikidata.WikidataId
import com.github.axthosarouris.wikidata.parsedItems.claims.snaks.values.EntityValue

case class EntityValueSnak(snaktype: String, property: WikidataId, datavalue: EntityValue, datatype: String) extends MainSnak
