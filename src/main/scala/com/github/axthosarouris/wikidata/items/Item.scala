package com.github.axthosarouris.wikidata.items

import com.github.axthosarouris.wikidata.parsedItems.{ParsedItem, ParsedItemLabel}
import com.github.axthosarouris.wikidata.{Language, WikidataId}

case class Item(id: WikidataId, labels: Map[Language, String])

object Item {

  def fromParsedItem(parsedItem: ParsedItem): Item = {
    val id = parsedItem.id
    val labels: Map[Language, String] = parsedItem.labels
      .map { case (_, label) => parseLabel(label) }
    Item(id, labels)

  }

  private def parseLabel(label: ParsedItemLabel) = {
    val lang = label.language
    label.language -> label.value
  }

}