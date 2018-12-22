package com.github.axthosarouris.wikidata.parsedItems.claims.snaks

import com.github.axthosarouris.wikidata.WikidataId
import com.github.axthosarouris.wikidata.parsedItems.claims.snaks.values.ExternalIdValue

case class ExternalIdSnak(snaktype: String, property: WikidataId,
                          datavalue: ExternalIdValue, datatype: String) extends MainSnak

