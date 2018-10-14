package com.github.axhtosarouris.wikidata.parsedItems.claims.snaks

import com.github.axhtosarouris.wikidata.WikidataId
import com.github.axhtosarouris.wikidata.parsedItems.claims.snaks.values.ExternalIdValue

case class ExternalIdSnak(snaktype: String, property: WikidataId,
                          datavalue: ExternalIdValue, datatype: String) extends MainSnak




