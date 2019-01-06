package com.github.axthosarouris

package object wikidata {

  type WikidataId = String
  type Language = String


  val supportedLanguages: Set[WikidataId] = Set("en", "fr", "el", "nb", "nn", "de", "es", "it")


}
