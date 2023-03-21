package ru.tinkoff.ablecturesoa

import org.slf4j.LoggerFactory
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import kotlin.concurrent.thread
import kotlin.random.Random
import kotlin.time.Duration.Companion.nanoseconds

@SpringBootApplication
class AbLectureSoaApplication

fun main(args: Array<String>) {
    val log = LoggerFactory.getLogger(AbLectureSoaApplication::class.java)
    runApplication<AbLectureSoaApplication>(*args)
    thread(isDaemon = true, start = true) {
        while (true) {
            val startTime = System.nanoTime()
            log.info("Starting operation at {}", startTime)
            val long = Random.nextLong(50, 500)
            Thread.sleep(long)
            if ( long % 50 > 0) {
                log.error("Operation failed", RuntimeException())
            }
            val milliseconds = (System.nanoTime() - startTime).nanoseconds.inWholeMilliseconds
            log.info("Completed operation in {} ms", milliseconds)
        }
    }
}
