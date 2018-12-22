package com.github.axthosarouris.wikidata.application

import java.io.File

import com.github.axthosarouris.wikidata.parsedItems.ParsedItem
import com.github.axthosarouris.wikidata.utils.FileParser

object Application {

  var inputFile: File = _
  var outputFile: File = _

  def main(args: Array[String]): Unit = {
    inputFile = new File(args(0))
    val parent = inputFile.getParent
    outputFile = new File(parent, "output.txt.gz")
    run()

  }

  def run(): Unit = {
    val fileParser = new FileParser(inputFile)
    val outputStream: Stream[ParsedItem] = fileParser.parseFile()
    fileParser.writeToFile(outputStream, outputFile)

  }

}
