package com.github.axthosarouris.wikidata

import java.io.File
import java.nio.charset.StandardCharsets

import com.github.axthosarouris.wikidata.parsedItems.ParsedItem
import org.json4s._
import org.json4s.jackson.Serialization.write
import org.slf4j.{Logger, LoggerFactory}

import scala.io.{BufferedSource, Source}
import scala.util.{Failure, Success, Try}

class FileParser(f: File) {
  val logger: Logger = LoggerFactory.getLogger("FileParser")
  val file = f
  val itemParser = new ItemParser()

  implicit val formats = DefaultFormats

  private def fileAsStream(): BufferedSource = {
    Source.fromFile(file, StandardCharsets.UTF_8.displayName())
  }

  def parseFile(): Unit = {
    val source: BufferedSource = fileAsStream()
    val lines: Iterator[String] = source.getLines()
  }


  def parseLine(line: String): Option[ParsedItem] = {
    Try(itemParser.parseString(line)) match {
      case Success(v) => Some(v)
      case Failure(e) => logger.warn(s"Line $line could not be parsed. Exception: $e"); None
    }
  }

  def writeItem(item: ParsedItem): String = {
    val json: String = write(item)
    json

  }

}


