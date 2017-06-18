package flink.samples

import java.util

import org.apache.flink.streaming.api.scala._
import org.apache.flink.streaming.api.windowing.time.Time

object WindowWordCount {
  def main(args: Array[String]) {

    val env = StreamExecutionEnvironment.getExecutionEnvironment
    val text = env.socketTextStream("localhost", 9000)

    val counts = text.map(s => {
      println(s)
      // CPU intensive operation
      s + factor(121234123512523171l + s.size) + factor(121234123512523170l + s.size)
    }).flatMap { _.toLowerCase.split("\\W+") filter { _.nonEmpty } }
      .map { (_, 1) }
      .keyBy(0)
      .timeWindow(Time.seconds(5))
      .sum(1)

    counts.print

    env.execute("Window Stream WordCount")
  }

  def factor(num: Long) = {
    var n = num
    var i = 2
    while(i <= 2) {
      while (n % i == 0) {
        n /= i;
      }
      i = i + 1
    }
  }
}
