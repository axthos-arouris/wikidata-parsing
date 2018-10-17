package com.github.axthosarouris.wikidata

import org.json4s._
import org.json4s.jackson.JsonMethods._
import com.github.axthosarouris.wikidata.parsedItems.ParsedItem
import org.json4s.jackson.Serialization.write

class ItemParser() {


  implicit val formats = DefaultFormats

  def parseString(json: String): ParsedItem = {
    val parsedItem = parse(json)
    val item = parsedItem.extract[ParsedItem]
    item

  }

  def serialize(item: ParsedItem): String = {
    val json: String = write(item)
    json

  }

}
