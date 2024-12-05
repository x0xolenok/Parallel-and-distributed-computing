import org.apache.spark.graphx.{GraphLoader, PartitionStrategy}

import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions._
import java.io.File

object main {
  def main(args: Array[String]): Unit = {

    val spark = SparkSession
      .builder()
      .appName(s"${this.getClass.getSimpleName}")
      .master("local[*]")
      .config("spark.driver.extraJavaOptions", "--add-opens java.base/java.nio=ALL-UNNAMED --add-opens java.base/sun.nio.ch=ALL-UNNAMED")
      .getOrCreate()
    val sc = spark.sparkContext
//    val graph = GraphLoader.edgeListFile(sc, "E:\\future generations\\4 course 1 semester\\graph к ЛР 3.txt", canonicalOrientation = false)
//        .partitionBy(PartitionStrategy.RandomVertexCut)
//
//        val triCounts = graph.triangleCount().vertices
//
//        val totalTriangles = triCounts.map(_._2).reduce(_ + _) / 3
//        println(s"Total number of triangles: $totalTriangles")
    import spark.implicits._

    val dataPath = "E:\\spark_lab6\\20_newsgroup"

    def cleanText(text: String): String = {
      text.toLowerCase
        .replaceAll("(?i)path:.*", "")
        .replaceAll("(?i)newsgroups:.*", "")
        .replaceAll("(?i)writes:.*", "")
        .replaceAll("(?i)from:.*", "")
        .replaceAll("(?i)lines:.*", "")
        .replaceAll("(?i)date:.*", "")
        .replaceAll("(?i)References:.*", "")
        .replaceAll("(?i)Organization:.*", "")
        .replaceAll("(?i)Nntp-Posting-Host:.*", "")
        .replaceAll("(?i)Sender.*", "")
        .replaceAll("(?i)Message-ID.*", "")
        .replaceAll("(?i)Xref.*", "")
        .replaceAll("'","\'")
        .replaceAll("(?is)begin\\s+\\d+\\s+\\S+.*?end", "")
        .replaceAll("[^a-z']", " ")
    }

    val files = sc.wholeTextFiles(dataPath + "/*")
      .map { case (filePath, content) =>
        val filename = filePath.split("/").last.split("\\.").head
        val cleanedContent = cleanText(content)
        (filename, cleanedContent)
      }.toDF("docId", "content")


    val words = files
      .select($"docId", explode(split($"content", "\\s+")).as("word"))
      .filter($"word".rlike("^[a-z']+$") && !$"word".startsWith("\'"))


    val invertedIndex = words
      .groupBy("word")
      .agg(
        count("*").as("total_count"),
        collect_set("docId").as("documents")
      )
      .select(
        $"word",
        $"total_count",
        concat_ws(" ", $"documents").as("doc_list")
      )

    invertedIndex.coalesce(1)
      .write
      .mode("overwrite")
      .option("header", "true")
      .csv("E:\\spark_lab6\\output_data")

    spark.stop()
  }
}
