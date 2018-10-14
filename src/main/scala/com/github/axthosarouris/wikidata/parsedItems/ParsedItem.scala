package com.github.axthosarouris.wikidata.parsedItems

import com.github.axthosarouris.wikidata.WikidataId
import com.github.axthosarouris.wikidata.parsedItems.claims.Claim

case class ParsedItem(id: WikidataId,
                      labels: Map[String, Label],
                      descriptions: Map[String, Description],
                      aliases: Map[String, List[Alias]],
                      claims: Map[String, List[Claim]]

                     )
