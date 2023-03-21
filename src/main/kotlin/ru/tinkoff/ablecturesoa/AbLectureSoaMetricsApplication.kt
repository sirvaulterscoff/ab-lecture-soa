package ru.tinkoff.ablecturesoa

import io.micrometer.core.instrument.MeterRegistry
import org.slf4j.LoggerFactory
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import kotlin.concurrent.thread
import kotlin.random.Random
import kotlin.time.Duration.Companion.nanoseconds
import kotlin.time.ExperimentalTime
import kotlin.time.measureTime
import kotlin.time.toJavaDuration

@SpringBootApplication
class AbLectureSoaMetricsApplication

@OptIn(ExperimentalTime::class)
fun main(args: Array<String>) {
    val app = runApplication<AbLectureSoaMetricsApplication>(*args)
    val meterRegistry = app.getBean(MeterRegistry::class.java)
    val timer = meterRegistry.timer("ab.process.timer")
    val errorCounter = meterRegistry.counter("ab.process.errors")
    thread(isDaemon = true, start = true) {
        while (true) {
            measureTime {
                val long = Random.nextLong(50, 500)
                Thread.sleep(long)
                if ( long % 50 > 0) {
                    errorCounter.increment()
                }
            }.apply {
                timer.record(this.toJavaDuration())
            }
        }
    }
}
