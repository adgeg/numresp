package fr.octo.numresp

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.statement.HttpResponse

private const val tasksRoute = "http://10.0.2.2:3000/tasks"
private const val ktorRoute = "https://ktor.io/"

class Repository(private val client: HttpClient) {
    suspend fun fetch() {
        val response: HttpResponse = client.get(tasksRoute)
        println("✅ status: ${response.status}")
        println("✅ body: ${response.body<String>()}")
        println("✅ size: ${response.body<String>().length}")
    }
}