package scala.wikidata.parsedItems

import java.nio.file.Paths

import org.junit.runner.RunWith
import org.scalatest.FlatSpec
import org.scalatest.junit.JUnitRunner
import utils.IOUtils
import wikidata.ItemParser
import wikidata.constants.Languages
import wikidata.parsedItems.Label
import wikidata.parsedItems.claims.Claim
import wikidata.parsedItems.claims.snaks.{EmptyValueSnak, EntityValueSnak, GenericValueSnak, MainSnak}

@RunWith(classOf[JUnitRunner])
class ItemParserTest  extends FlatSpec with IOUtils{


  val  parser=new ItemParser()

  "ParsedItem" should "have an id" in{
    val json: String =resourceFileAsSeq(Paths.get("hydrogen.txt")).toList(0)
    val item=parser.parseString(json)
    assert(item.id==="Q556")
  }


  "ParsedItem" should "have an labels" in{
    val json: String =resourceFileAsSeq(Paths.get("hydrogen.txt")).toList(0)
    val item=parser.parseString(json)
    assert(item.labels.isInstanceOf[Map[String,Label]])
    val label: Option[Label] =item.labels.get(Languages.English)
    assert(label.isDefined)
    assert(label.get.language==Languages.English)
    assert(label.get.value==="hydrogen")

  }


  "ParsedItem" should "have not have empy snak datavalueslabels" in{
    val json: String =resourceFileAsSeq(Paths.get("hydrogen.txt")).toList(0)
    val item=parser.parseString(json)
    val claims: List[(String, Claim)] =item.claims.toList.flatMap {
      case (label, claimList) => {claimList.map(claim => (label, claim))}
    }
    val snaks: List[(String, MainSnak)] =claims.map{
      case (id,claim)=>(id,claim.mainsnak)
    }
    snaks.foreach{
      case (label:String,snak:EntityValueSnak)=> assert(snak.datavalue.value!==null)
      case (label:String,snak:EmptyValueSnak)=> fail("No empty snaks allowed")

    }
//    val dataValues: List[String] =snaks.map{case(_,mainSnak: GenericValueSnak)=>mainSnak.datavalue.value.get("type")}
//      .flatMap(opt=>opt.map(obj=>obj.toString))
    val dataValues: List[(String, String)] =snaks.map{case(_,mainSnak: EntityValueSnak)=>{
      val datatype=mainSnak.datatype
      val valuetype=mainSnak.datavalue.`type`
      (datatype,valuetype)
    }}

    assert(dataValues.nonEmpty)
  }

}
