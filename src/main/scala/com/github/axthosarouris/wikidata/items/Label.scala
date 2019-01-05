package com.github.axthosarouris.wikidata.items

import com.github.axthosarouris.wikidata.items.Language.Language

case class Label(label: String, language: Language)

object Label {

  def fromParsedLabel(parsedLabel: com.github.axthosarouris.wikidata.parsedItems.ParsedItemLabel): Option[Label] = {
    val language: Option[Language.Value] = Language.parse(parsedLabel.language)
    val labelString = parsedLabel.value
    language.map(Label(labelString, _))

  }

}
