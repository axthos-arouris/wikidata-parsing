package com.github.axhtosarouris.wikidata

import org.json4s._
import org.json4s.jackson.JsonMethods._
import com.github.axhtosarouris.wikidata.parsedItems.ParsedItem

class ItemParser() {


  implicit val formats = DefaultFormats

  def parseString(json: String): ParsedItem = {
    val parsedItem = parse(json)
    val item = parsedItem.extract[ParsedItem]
    item

  }


}
