package com.github.axthosarouris.wikidata.parsedItems.claims.snaks

import com.github.axthosarouris.wikidata.WikidataId
import com.github.axthosarouris.wikidata.parsedItems.claims.snaks.contants.SnakDatatypes
import com.github.axthosarouris.wikidata.parsedItems.claims.snaks.values._

trait MainSnak{


  def snaktype:String
  def property:WikidataId
  def datavalue:SnakValue
  def datatype:String

}

object MainSnak{

  def apply(snaktype:String,property:WikidataId,datavalue:Option[Map[String,Any]],datatype:String):MainSnak={
    val snakValue: Option[SnakValue] =datavalue.map(value=>selectSnakValue(datatype,value))


    snakValue match{
      case Some(e:EntityValue)=>EntityValueSnak(snaktype,property,e,datatype)
      case Some(s:StringValue)=>StringValueSnak(snaktype,property,s,datatype)
      case Some(g:GenericValue)=>GenericValueSnak(snaktype,property,g,datatype)
      case Some(eid:ExternalIdValue)=> ExternalIdSnak(snaktype,property,eid,datatype)
      case None=> EmptyValueSnak(snaktype,property,datatype)
    }

  }


  def selectSnakValue(datatype:String,datavalue:Map[String,Any]): SnakValue =datatype match{
    case SnakDatatypes.WikibaseItem=> EntityValue(datavalue)
    case SnakDatatypes.String=>StringValue(datavalue)
    case SnakDatatypes.ExternalId=> ExternalIdValue(datavalue)
    case _=>GenericValue(datavalue)
  }


  def createSnak(snakType:String, property:WikidataId,datavalue:SnakValue,datatype:String): MainSnak= datavalue match{
    case value:EntityValue=>EntityValueSnak(snakType,property,value,datatype)
    case value:StringValue=>StringValueSnak(snakType,property,value,datatype)
  }

}
//case class  MainSnak(snaktype:String, property:WikidataId, datavalue:Option[DataValue], datatype:WikidataId)




