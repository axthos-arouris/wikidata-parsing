package com.github.axhtosarouris.wikidata.parsedItems.claims.snaks

import com.github.axhtosarouris.wikidata.WikidataId
import com.github.axhtosarouris.wikidata.parsedItems.claims.snaks.values.EntityValue

case class EntityValueSnak(snaktype: String, property: WikidataId, datavalue: EntityValue, datatype: String) extends MainSnak
