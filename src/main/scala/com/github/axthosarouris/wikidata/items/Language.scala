package com.github.axthosarouris.wikidata.items

import com.github.axthosarouris.wikidata.items

object Language extends Enumeration {

  type Language = Value
  private lazy val valuesMap: Map[String, items.Language.Value] = buildParseMap()
  val en, fr, el, nb, nn, de, es, it = Value

  override def toString(): String = {
    super.toString().toLowerCase()

  }

  def parse(lang: String): Option[Language.Value] = {
    valuesMap.get(lang)
  }

  private def buildParseMap(): Map[String, Language.Value] = {
    values.map(value => value.toString -> value).toMap
  }


}