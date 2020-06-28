package org.deepanshu.service

import org.springframework.stereotype.Service

@Service
class InvoiceService {
    fun getGst(): List<Int> {
        return listOf(5, 12, 18, 28)
    }
}