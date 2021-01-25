package br.dev.henriquealmeida.rest_assured

import io.restassured.response.Response

interface RestClient {

    fun get(resource: String): Response

    fun get(resource: String, headers: Map<String, Any>): Response

    fun get(resource: String, headers: Map<String?, Any?>, querryParams: Map<String, Any>): Response

    fun post(resource: String, body: Any): Response

    fun post(resource: String, headers: Map<String?, Any?>, body: Any): Response

    fun post(resource: String): Response

    fun put(resource: String, headers: Map<String, Any>, body: Any): Response

    fun delete(resource: String): Response

    fun delete(resource: String, headers: Map<String, Any>): Response
}
