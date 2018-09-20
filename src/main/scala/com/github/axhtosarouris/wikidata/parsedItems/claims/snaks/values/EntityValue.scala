package com.github.axhtosarouris.wikidata.parsedItems.claims.snaks.values

import com.github.axhtosarouris.wikidata.WikidataId


case class EntityValue(value:WikidataId, `type`:String) extends SnakValue



object EntityValue{

  def apply(map:Map[String,Any]): EntityValue ={
    val value=map.get("value").map(v=>v.toString)
    val `type`=map.get("type").map(v=>v.toString)
    if(value.isDefined && `type`.isDefined){
      new EntityValue(value.get,`type`.get)
    }
    else {

      throw new IllegalArgumentException(s"${map.toString()} is not a DataValue" )
    }
  }

}


