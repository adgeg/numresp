package fr.octo.numresp

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.request

private const val tasksRoute = "http://10.0.2.2:3000/tasks"
private const val tasksCachedRoute = "http://10.0.2.2:3000/tasks-cached"
private const val ktorRoute = "https://ktor.io/"

class Repository(private val client: HttpClient) {
    suspend fun fetch() {
        println("🚀 BEFORE REQUEST")
        val response: HttpResponse = client.get(tasksCachedRoute)
        println("✅ RESPONSE body: ${response.body<String>()}")
        println("✅ RESPONSE size: ${response.body<String>().length}")
    }
}