package scala.wikidata.parsedItems

import java.nio.file.Paths

import org.junit.runner.RunWith
import org.scalatest.FlatSpec
import org.scalatest.junit.JUnitRunner
import utils.IOUtils
import wikidata.ItemParser
import wikidata.constants.Languages
import wikidata.parsedItems.claims.snaks.values.{EntityValue, GenericValue, SnakValue, StringValue}
import wikidata.parsedItems.claims.snaks._
import wikidata.parsedItems.{Label, ParsedItem}

@RunWith(classOf[JUnitRunner])
class ItemParserTest extends FlatSpec with IOUtils {


  val parser = new ItemParser()
  val json: String = resourceFileAsSeq(Paths.get("hydrogen.txt")).toList.head

  "ParsedItem" should "have an id" in {

    val item = parser.parseString(json)
    assert(item.id === "Q556")
  }


  "ParsedItem" should "have  labels" in {

    val item: ParsedItem = parser.parseString(json)
    assert(item.labels.isInstanceOf[Map[String, Label]])
    val label: Option[Label] = item.labels.get(Languages.English)
    assert(label.isDefined)
    assert(label.get.language == Languages.English)
    assert(label.get.value === "hydrogen")

  }


  "ParsedItem" should "have not have empty snak " in {
    val item = parser.parseString(json)
    val snaks: List[MainSnak] = item.claims.values.flatten.map(claim => claim.mainsnak).toList

    snaks.foreach {
      case _: EmptyValueSnak => fail("No empty snaks allowed")
      case snak: MainSnak => assert(snak.datavalue.value !== null)

    }

    val dataValues: List[MainSnak] = snaks.filter {
      case snak:EmptyValueSnak=>true
      case _=>false
    }

    assert(dataValues.isEmpty)
  }


  "ParsedItem" should "have at least one EntityValue snak" in {
    val item: ParsedItem = parser.parseString(json)
    val snaks: List[MainSnak] = item.claims.values.flatten.toList.map(claim => claim.mainsnak)
    val entityValues: List[EntityValue] =snaks.collect{
      case snak:EntityValueSnak=> snak.datavalue
    }

    assert(entityValues.nonEmpty)



  }


  "ParsedItem" should "have at least one StringValue snak" in {
    val item: ParsedItem = parser.parseString(json)
    val snaks: List[MainSnak] = item.claims.values.flatten.toList.map(claim => claim.mainsnak)
    val entityValues: List[StringValue] =snaks.collect{
      case snak:StringValueSnak=> snak.datavalue
    }
    assert(entityValues.nonEmpty)



  }

}
