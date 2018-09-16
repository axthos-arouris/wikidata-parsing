package wikidata.parsedItems

import wikidata.WikidataId
import wikidata.parsedItems.claims.Claim

case class ParsedItem(id: WikidataId,
                      labels: Map[String, Label],
                      descriptions: Map[String, Description],
                      aliases: Map[String, List[Alias]],
                      claims: Map[String, List[Claim]]

                     )
