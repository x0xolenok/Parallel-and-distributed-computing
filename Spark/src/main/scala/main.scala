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
    val graph = GraphLoader.edgeListFile(sc, "E:\\future generations\\4 course 1 semester\\graph к ЛР 3.txt", canonicalOrientation = false)
        .partitionBy(PartitionStrategy.RandomVertexCut)

        val triCounts = graph.triangleCount().vertices

        val totalTriangles = triCounts.map(_._2).reduce(_ + _) / 3
        println(s"Total number of triangles: $totalTriangles")

    spark.stop()
  }
}
