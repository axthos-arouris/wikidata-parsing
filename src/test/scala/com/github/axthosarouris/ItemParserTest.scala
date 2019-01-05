package com.github.axthosarouris

import java.nio.file.Paths

import com.github.axthosarouris.scala.commons.ResourceReader
import com.github.axthosarouris.wikidata.constants.Languages
import com.github.axthosarouris.wikidata.converters.ItemParser
import com.github.axthosarouris.wikidata.parsedItems.claims.snaks._
import com.github.axthosarouris.wikidata.parsedItems.claims.snaks.values.{EntityValue, ExternalIdValue, StringValue}
import com.github.axthosarouris.wikidata.parsedItems.{ParsedItem, ParsedItemLabel}
import org.scalatest.FlatSpec

//@RunWith(classOf[JUnitRunner])
class ItemParserTest extends FlatSpec with ResourceReader {

  val parser: ItemParser[ParsedItem] = new ItemParser[ParsedItem]()
  val json: String = resourceAsSeq(Paths.get("hydrogen.json")).toList.head

  "ParsedItem" should "have an id" in {

    val item = parser.parseString(json)
    assert(item.id === "Q556")
  }

  "ParsedItem" should "have  labels" in {

    val item: ParsedItem = parser.parseString(json)
    assert(item.labels.isInstanceOf[Map[String, ParsedItemLabel]])
    val label: Option[ParsedItemLabel] = item.labels.get(Languages.English)
    assert(label.isDefined)
    assert(label.get.language == Languages.English)
    assert(label.get.value === "hydrogen")

  }

  it should "not have have empty snak" in {
    val item = parser.parseString(json)
    val snaks: List[MainSnak] = item.claims.values.flatten.map(claim => claim.mainsnak).toList

    snaks.foreach {
      case _: EmptyValueSnak => fail("No empty snaks allowed")
      case snak: MainSnak => assert(snak.datavalue.value.isInstanceOf[Any])
    }

    val dataValues: List[MainSnak] = snaks.filter {
      case _: EmptyValueSnak => true
      case _ => false
    }

    assert(dataValues.isEmpty)
  }

  it should "have at least one EntityValue snak" in {
    val item: ParsedItem = parser.parseString(json)
    val snaks: List[MainSnak] = item.claims.values.flatten.toList.map(claim => claim.mainsnak)
    val entityValues: List[EntityValue] = snaks.collect {
      case snak: EntityValueSnak => snak.datavalue
    }

    assert(entityValues.nonEmpty)

  }

  it should "have at least one StringValue snak" in {
    val item: ParsedItem = parser.parseString(json)
    val snaks: List[MainSnak] = item.claims.values.flatten.toList.map(claim => claim.mainsnak)
    val entityValues: List[StringValue] = snaks.collect {
      case snak: StringValueSnak => snak.datavalue
    }
    assert(entityValues.nonEmpty)

  }

  it should "have at least one ExtenrnalIdValue snak" in {
    val item: ParsedItem = parser.parseString(json)
    val snaks: List[MainSnak] = item.claims.values.flatten.toList.map(claim => claim.mainsnak)
    val entityValues: List[ExternalIdValue] = snaks.collect {
      case snak: ExternalIdSnak => snak.datavalue
    }
    assert(entityValues.nonEmpty)

  }

  it should "contain GenericSnaks only for time,quantities and commonsmedia" in {
    val item = parser.parseString(json)
    val snaks = item.claims.values.flatten.toList.map(claim => claim.mainsnak)
    val genericSnaks = snaks
      .filter(snak => snak.isInstanceOf[GenericValueSnak])
      .filter(snak => !snak.datatype.equals("quantity"))
      .filter(snak => !snak.datatype.equals("time"))
      .filter(snak => !snak.datatype.equals("commonsMedia"))

    assert(genericSnaks.isEmpty)
  }

}
