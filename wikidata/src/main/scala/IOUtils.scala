package utils

import java.io._
import java.nio.file.{Path, Paths}
import java.util.zip.{GZIPInputStream, GZIPOutputStream}

import scala.io.{BufferedSource, Source}
import scala.util.{Failure, Success, Try}

trait IOUtils {


  def pathToResources(): Path ={
    Paths.get(getClass.getResource("/").getPath)
  }


  def resourceFileAsSeq(file: Path): Seq[String] = {

    val resource = readResource(file)
    resource.getLines().toList
  }


  def readFileAsSeq(file:File): Seq[String] ={
    val lines= if(file.getName.endsWith(".gz")){
      Try(
        Source.fromInputStream(new BufferedInputStream(new GZIPInputStream(new FileInputStream(file)))).getLines().toList)
    }
    else{
      Try(
        Source.fromInputStream(new BufferedInputStream(new FileInputStream(file))).getLines().toList)
    }
    lines match {
      case Success(s) => s
      case Failure(e) => throw e
    }
  }


  private def readResource(file: Path): BufferedSource = {

    val tryResource=if(file.toString.endsWith(".gz")){
      Try(
        Source.fromInputStream(new BufferedInputStream(new GZIPInputStream(resoureAsStream(file.toString))))
      )
    }
    else{
      Try(
        Source.fromInputStream(new BufferedInputStream(resoureAsStream(file.toString)))
      )
    }
    tryResource match {
      case Success(s) => s
      case Failure(e) => throw e
    }

  }


  def writeToFile(lines:Iterable[String], outputFile:File): Unit ={
    val outStream=new GZIPOutputStream(new FileOutputStream(outputFile))
    val writer=new BufferedWriter(new OutputStreamWriter(outStream));
    var counter=1
    for(line<-lines){
      writer.write(line)
      writer.newLine()
      counter=counter+1
      if(counter%100==0){
        writer.flush()
        counter=0
      }
    }
    writer.close()
  }



  private def resoureAsStream(absoluteFilename: String): InputStream = {

    val resource=this.getClass.getClassLoader.getResourceAsStream(absoluteFilename)
    resource
  }






}
