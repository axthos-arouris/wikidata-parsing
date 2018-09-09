package wikidata.items

import wikidata.WikidataId
import wikidata.parsedItems.{Alias, Description, Label, ParsedItem}

case class Item(id:WikidataId,
                labels:List[Label],
                descriptions:List[Description],
                aliases: Map[String,List[Alias]])


object Item{
  def create(parsedItem:ParsedItem): Item ={
    val labels: List[Label] =parsedItem.labels.values.toList
    val descriptions=parsedItem.descriptions.values.toList
    val aliases=parsedItem.aliases
    Item(parsedItem.id,labels,descriptions,aliases)
  }

}


