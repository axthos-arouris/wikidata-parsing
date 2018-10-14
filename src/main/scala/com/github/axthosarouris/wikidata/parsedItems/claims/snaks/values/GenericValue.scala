package com.github.axthosarouris.wikidata.parsedItems.claims.snaks.values

case  class GenericValue(`type`: String, value: Map[String,Any]) extends SnakValue


object GenericValue {

  def apply(map: Map[String, Any]) = {
    val ttype = map.get("type").map(_.toString).getOrElse("unknown")
    new GenericValue(ttype, map)

  }

}
