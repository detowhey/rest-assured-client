package br.dev.henriquealmeida.rest_assured

import io.restassured.builder.RequestSpecBuilder
import io.restassured.filter.log.LogDetail
import io.restassured.http.ContentType
import io.restassured.specification.RequestSpecification

class RequestSpecificationFactory {

    companion object {
        @JvmStatic
        fun of(baseURL: String): RequestSpecification {
            val spec = RequestSpecBuilder()
            spec.setBaseUri(baseURL)
            spec.log(LogDetail.ALL)
            spec.addHeader("Content-Type", ContentType.JSON as String)
            return spec.build()
        }
    }
}