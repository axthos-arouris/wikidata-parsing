package com.github.axthosarouris.wikidata.converters

import com.github.axthosarouris.wikidata.parsedItems.ParsedItem
import org.json4s.DefaultFormats
import org.json4s.jackson.JsonMethods.parse
import org.json4s.jackson.Serialization.write

class ItemParser() {

  implicit val formats: DefaultFormats.type = DefaultFormats

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
