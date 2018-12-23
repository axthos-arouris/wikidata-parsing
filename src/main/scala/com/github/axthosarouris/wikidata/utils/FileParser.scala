package com.github.axthosarouris.wikidata.utils

import java.io.{BufferedWriter, File, FileInputStream, FileOutputStream, OutputStreamWriter}
import java.util.zip.{GZIPInputStream, GZIPOutputStream}

import com.github.axthosarouris.wikidata.ItemParser
import com.github.axthosarouris.wikidata.parsedItems.ParsedItem

import scala.io.Source

class FileParser(val inputFile: File) {

  val inputStream: Stream[String] = fileToStream(inputFile)
  val streamParser: StreamParser = new StreamParser(inputStream)
  val itemParser: ItemParser = new ItemParser()

  def parseFile(): Stream[ParsedItem] = {
    streamParser.parseStream()
  }

  def writeToFile(itemStream: Stream[ParsedItem], outputFile: File): Unit = {
    val writer = initFileWriter(outputFile)
    itemStream.foreach(item => writeLine(writer, item))
    writer.flush()
    writer.close()

  }

  private def writeLine(writer: BufferedWriter, item: ParsedItem): Unit = {
    writer.write(itemParser.serialize(item))
    writer.newLine()
    writer.flush()
  }

  private def initFileWriter(outputFile: File): BufferedWriter = {
    val writer = new BufferedWriter(
      new OutputStreamWriter(
        new GZIPOutputStream(new FileOutputStream(outputFile))))
    writer
  }

  private def fileToStream(file: File): Stream[String] = {
    if (file.getName.endsWith("gz") || file.getName.endsWith(".gzip")) {
      Source.fromInputStream(new GZIPInputStream(new FileInputStream(file))).getLines().toStream
    } else {
      Source.fromFile(file).getLines().toStream
    }

  }

}
