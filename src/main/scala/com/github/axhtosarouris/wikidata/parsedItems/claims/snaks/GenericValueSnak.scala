package com.github.axhtosarouris.wikidata.parsedItems.claims.snaks

import com.github.axhtosarouris.wikidata.WikidataId
import com.github.axhtosarouris.wikidata.parsedItems.claims.snaks.values.GenericValue

case class GenericValueSnak(snaktype:String, property:WikidataId,datavalue:GenericValue,datatype:String)
  extends MainSnak


object GenericValueSnak{

  def apply(snaktype: String, property: WikidataId, datavalue: Map[String,Any], datatype: String): GenericValueSnak ={
    new GenericValueSnak(snaktype, property, GenericValue(datavalue), datatype)
  }

}




