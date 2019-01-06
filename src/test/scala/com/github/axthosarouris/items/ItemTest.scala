package com.github.axthosarouris.items

import java.nio.file.Paths

import com.github.axthosarouris.scala.commons.ResourceReader
import com.github.axthosarouris.wikidata.converters.ItemParser
import com.github.axthosarouris.wikidata.items.Item
import com.github.axthosarouris.wikidata.parsedItems.ParsedItem
import com.github.axthosarouris.wikidata.supportedLanguages
import org.scalatest.FlatSpec

class ItemTest extends FlatSpec with ResourceReader {

  private val itemParser: ItemParser[ParsedItem] = new ItemParser()
  private val jsonString: String = resourceAsString(Paths.get("belgium.json"))
  private val parsedItem: ParsedItem = itemParser.parseString(jsonString)

  "Item" should "have labels for all specified languages that are also available in " +
    "the original json file" in {
    val item: Item = Item.fromParsedItem(parsedItem)
    for {
      lang <- supportedLanguages
      langStr = lang.toString
      expected = parsedItem.labels.get(langStr)
    } {
      assert(expected.map(_.value) === item.labels.get(lang))
    }
  }

  "Item" should "be serialized and deserialized" in {
    val item = Item.fromParsedItem(parsedItem)
    val writer = new ItemParser[Item]
    val json = writer.serialize(item)

    val actualItem = writer.parseString(json)
    assert(actualItem === item)

  }

}
