package com.github.axthosarouris.wikidata.utils

import java.io._
import java.util.zip.{GZIPInputStream, GZIPOutputStream}

import com.github.axthosarouris.wikidata.ItemParser
import com.github.axthosarouris.wikidata.parsedItems.ParsedItem

import scala.io.Source

class FileParser(val inputFile: File) {


  val inputStream: Stream[String] =fileToStream(inputFile)
  val streamParser: StreamParser=new StreamParser(inputStream)
  val itemParser:ItemParser=new ItemParser()


  def parseFile(): Stream[ParsedItem] ={
    streamParser.parseStream()
  }

  def writeToFile(itemStream:Stream[ParsedItem],outputFile:File): Unit ={
    val writer=initFileWriter(outputFile)
    var counter=0
    for{item<- itemStream}{
      writer.write(itemParser.serialize(item))
      writer.newLine()
      counter=(counter+1)%10
      if(counter==0){
        writer.flush()
      }
    }
    writer.flush()
    writer.close()

  }


  private def initFileWriter(outputFile:File): BufferedWriter ={
    val writer=new BufferedWriter(
      new OutputStreamWriter(
        new GZIPOutputStream(new FileOutputStream(outputFile))))
      writer
  }



  private def fileToStream(file: File): Stream[String] ={
   if(file.getName.endsWith("gz")|| file.getName.endsWith(".gzip")){
     Source.fromInputStream(new GZIPInputStream(new FileInputStream(file))).getLines().toStream
   } else{
     Source.fromFile(file).getLines().toStream
   }


  }




}
