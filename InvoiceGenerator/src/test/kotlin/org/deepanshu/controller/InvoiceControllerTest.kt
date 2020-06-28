package org.deepanshu.controller

import org.deepanshu.InvoiceGeneratorApplication
import org.junit.Test

import org.junit.Assert.*
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.http.HttpStatus
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner::class)
@SpringBootTest(
    classes = arrayOf(InvoiceGeneratorApplication::class),
    webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
class InvoiceControllerTest {

    @Autowired
    lateinit var testRestTemplate: TestRestTemplate

    @Test
    fun whenCalled_shouldReturnHello() {
        val result = testRestTemplate
            .getForEntity("/InvoiceService/getGst", String::class.java)

        assertNotNull(result)
        assertEquals(HttpStatus.OK, result?.statusCode)
        assertEquals("[5,12,18,28]", result?.body)
    }
}