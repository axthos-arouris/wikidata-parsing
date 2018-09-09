package wikidata.parsedItems.claims.snaks

import wikidata.WikidataId
import wikidata.parsedItems.claims.snaks.values.{EntityValue, SnakValue, StringValue}

trait MainSnak{


  def snaktype:String
  def property:WikidataId
  def datavalue:SnakValue
  def datatype:String


}

object MainSnak{

  def apply(snaktype:String,property:WikidataId,datavalue:Option[Map[String,Any]],datatype:WikidataId):MainSnak={
    datavalue match{
      case Some(value)=>GenericValueSnak(snaktype,property,value,datatype)
      case None=> EmptyValueSnak(snaktype,property,datatype)
    }

  }


  def selectValue(snakType:String,datavalue:Map[String,Any])=snakType match{
    case SnakTypes.WikibaseItem=> EntityValue(datavalue)
    case SnakTypes.String=>StringValue(datavalue)
  }

}
//case class  MainSnak(snaktype:String, property:WikidataId, datavalue:Option[DataValue], datatype:WikidataId)




