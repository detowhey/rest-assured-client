package br.qa.henriquealmeida.rest_assured

import io.restassured.RestAssured
import io.restassured.RestAssured.given
import io.restassured.parsing.Parser
import io.restassured.response.Response
import io.restassured.specification.RequestSpecification
import java.net.URL

class RestClientImplementation(private val requestSpecification: RequestSpecification) : RestClient {

    init {
        RestAssured.defaultParser = Parser.JSON
    }

    override fun get(resource: String): Response = given().spec(requestSpecification).get(resource)

    override fun get(resource: String, headers: Map<String?, Any?>): Response =
        given().spec(requestSpecification).headers(headers).get(resource)

    override fun get(resource: String, headers: Map<String?, Any?>, querryParams: Map<String, Any>): Response =
        given().spec(requestSpecification).headers(headers).queryParams(querryParams).get(resource)

    override fun post(resource: String, body: Any): Response =
        given().spec(requestSpecification).body(body).post(resource)

    override fun post(resource: String, headers: Map<String?, Any?>, body: Any): Response =
        given().spec(requestSpecification).headers(headers).body(body).post(resource)

    override fun post(resource: String): Response = given().spec(requestSpecification).post(resource)

    override fun put(resource: String, headers: Map<String, Any>, body: Any): Response =
        given().spec(requestSpecification).headers(headers).body(body).put(resource)

    override fun put(resource: String, body: Any): Response =
        given().spec(requestSpecification).body(body).put(resource)

    override fun delete(resource: String): Response = given().spec(requestSpecification).delete(resource)

    override fun delete(resource: String, headers: Map<String, Any>): Response =
        given().spec(requestSpecification).headers(headers).delete(resource)

    override fun patch(
        resource: String,
        headers: Map<String, Any>,
        path: String,
        querryParams: Map<String, Any>
    ): Response = given().spec(requestSpecification).headers(headers).patch(path, querryParams)

    override fun patch(resource: String, url: URL): Response = given().spec(requestSpecification).patch(url)
}
