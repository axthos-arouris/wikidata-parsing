package com.github.axthosarouris.wikidata.converters

import org.json4s.jackson.JsonMethods.parse
import org.json4s.jackson.Serialization.write
import org.json4s.{DefaultFormats, Formats, _}
class ItemParser[T <: AnyRef] {

  implicit val formats: Formats = DefaultFormats

  def parseString(json: String)(implicit m: Manifest[T]): T = {
    val parsedItem = parse(json)
    val item = parsedItem.extract[T]
    item

  }

  def serialize(item: T): String = {
    val json: String = write(item)
    json

  }

}
