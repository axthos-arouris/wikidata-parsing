package com.github.axthosarouris.wikidata.parsedItems

import com.github.axthosarouris.wikidata.parsedItems.claims.Claim
import com.github.axthosarouris.wikidata.{Language, WikidataId}

case class ParsedItem(
                       id: WikidataId,
                       labels: Map[Language, ParsedItemLabel],
                       descriptions: Map[String, ParsedItemDescription],
                       aliases: Map[String, List[Alias]],
                       claims: Map[String, List[Claim]])
