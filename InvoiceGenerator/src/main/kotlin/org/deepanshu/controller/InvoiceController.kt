package org.deepanshu.controller

import org.deepanshu.service.InvoiceService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class InvoiceController(private val invoiceService: InvoiceService) {

    @GetMapping("/InvoiceService/getGst")
    fun getGstService(): List<Int> {
        return invoiceService.getGst()
    }

}