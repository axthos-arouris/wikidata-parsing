package com.github.axthosarouris.wikidata.parsedItems.claims.snaks.values

trait SnakValue {
  def value: Any

  def `type`: String
}

case object EmptySnakValue extends SnakValue {
  def value: Null = null

  def `type`: Null = null
}
