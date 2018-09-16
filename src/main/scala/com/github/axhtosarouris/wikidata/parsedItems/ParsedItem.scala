package com.github.axhtosarouris.wikidata.parsedItems

import com.github.axhtosarouris.wikidata.WikidataId
import com.github.axhtosarouris.wikidata.parsedItems.claims.Claim

case class ParsedItem(id: WikidataId,
                      labels: Map[String, Label],
                      descriptions: Map[String, Description],
                      aliases: Map[String, List[Alias]],
                      claims: Map[String, List[Claim]]

                     )
