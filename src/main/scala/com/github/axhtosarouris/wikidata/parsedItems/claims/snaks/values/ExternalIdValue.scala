package com.github.axhtosarouris.wikidata.parsedItems.claims.snaks.values

case class ExternalIdValue(`type`: String, value: String) extends SnakValue


object ExternalIdValue {

  def apply(map: Map[String, Any]): ExternalIdValue = {
    val value = map.get("value").map(v => v.toString)
    val `type` = map.get("type").map(v => v.toString)
    if (value.isDefined && `type`.isDefined) {
      new ExternalIdValue(value.get, `type`.get)
    }
    else {
      throw new IllegalArgumentException(s"${map.toString()} is not a ExternalIdValue")
    }
  }


}