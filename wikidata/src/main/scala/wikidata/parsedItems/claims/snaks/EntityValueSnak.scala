package wikidata.parsedItems.claims.snaks

import wikidata.parsedItems.claims.snaks.values.EntityValue

case class EntityValueSnak(snaktype:String, property:String,datavalue:EntityValue,datatype:String)extends MainSnak
