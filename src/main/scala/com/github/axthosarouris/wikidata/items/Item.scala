package com.github.axthosarouris.wikidata.items

import com.github.axthosarouris.wikidata.WikidataId
import com.github.axthosarouris.wikidata.items.Language.Language
import com.github.axthosarouris.wikidata.parsedItems.{ParsedItem, ParsedItemLabel}

case class Item(id: WikidataId, labels: Map[Language, String])

object Item {

  def fromParsedItem(parsedItem: ParsedItem): Item = {
    val id = parsedItem.id
    val labels: Map[Language.Value, String] = parsedItem.labels
      .flatMap { case (_, label) => parseLabel(label) }
    Item(id, labels)

  }

  private def parseLabel(label: ParsedItemLabel) = {
    val lang = Language.parse(label.language)
    lang.map(l => l -> label.value)
  }

}