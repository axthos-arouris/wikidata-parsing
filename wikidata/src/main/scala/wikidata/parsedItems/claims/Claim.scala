package wikidata.parsedItems.claims

import wikidata.parsedItems.claims.snaks.MainSnak

case class Claim (mainsnak:MainSnak,rank:String,id:String,`type`:String)
