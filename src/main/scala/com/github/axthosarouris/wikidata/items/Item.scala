package com.github.axthosarouris.wikidata.items

import com.github.axthosarouris.wikidata.parsedItems.{ParsedItem, ParsedItemLabel}
import com.github.axthosarouris.wikidata.{Language, WikidataId, supportedLanguages}

case class Item(id: WikidataId, labels: Map[Language, String])

object Item {

  def fromParsedItem(parsedItem: ParsedItem): Item = {
    val id = parsedItem.id
    val labels: Map[Language, String] = parsedItem.labels
      .flatMap { case (_, label) => parseLabel(label) }
    Item(id, labels)

  }

  private def parseLabel(label: ParsedItemLabel): Option[(Language, String)] = {
    if (supportedLanguages.contains(label.language)) {
      Some(label.language -> label.value)
    }
    else {
      None
    }


  }

}