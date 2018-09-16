package com.github.axhtosarouris.wikidata.parsedItems.claims.snaks.values

trait SnakValue{
   def value:Any
}


case object EmptySnakValue extends SnakValue{
  def value: Null =null
}
