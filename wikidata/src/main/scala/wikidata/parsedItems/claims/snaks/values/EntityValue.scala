package wikidata.parsedItems.claims.snaks.values

case class EntityValue(value:String, `type`:String) extends SnakValue



object EntityValue{

  def apply(map:Map[String,Any]): StringValue ={
    val value=map.get("value").map(v=>v.toString)
    val `type`=map.get("type").map(v=>v.toString)
    if(value.isDefined && `type`.isDefined){
      new StringValue(value.get,`type`.get)
    }
    else {

      throw new IllegalArgumentException(s"${map.toString()} is not a DataValue" )
    }
  }

}


