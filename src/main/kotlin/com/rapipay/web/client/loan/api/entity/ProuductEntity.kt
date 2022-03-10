package com.rapipay.web.client.loan.api.entity

data class ProuductEntity(
    var productId: Int,
    var productName: String,
    var productPrice: Long,
    var rateOfInterest: Double,
    var tenure: Int,
    var createdBy: String,
    var updatedBy: String
)