package com.github.axthosarouris.wikidata.utils

import com.github.axthosarouris.wikidata.converters.ItemParser
import com.github.axthosarouris.wikidata.parsedItems.ParsedItem
import org.json4s.DefaultFormats
import org.slf4j.{Logger, LoggerFactory}

import scala.util.{Failure, Success, Try}

class StreamParser(val inputStream: Stream[String]) {

  @transient
  val logger: Logger = LoggerFactory.getLogger("StreamParser")

  @transient
  val itemParser = new ItemParser()

  @transient
  implicit val formats: DefaultFormats.type = DefaultFormats

  def parseStream(): Stream[ParsedItem] = inputStream.flatMap(line => parseLine(line))

  def parseLine(line: String): Option[ParsedItem] = {
    Try(itemParser.parseString(line)) match {
      case Success(v) => Some(v)
      case Failure(e) => logger.warn(s"Line $line could not be parsed. Exception: $e"); None
    }
  }

}

