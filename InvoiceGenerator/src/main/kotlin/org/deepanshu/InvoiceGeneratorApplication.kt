package org.deepanshu

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
open class InvoiceGeneratorApplication

fun main(args: Array<String>) {
    SpringApplication.run(InvoiceGeneratorApplication::class.java, *args)
}