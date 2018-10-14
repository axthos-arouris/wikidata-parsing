package com.github.axthosarouris.wikidata.parsedItems.claims

import com.github.axthosarouris.wikidata.parsedItems.claims.snaks.MainSnak

case class Claim (mainsnak:MainSnak,rank:String,id:String,`type`:String)
