package fr.octo.numresp

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.cache.HttpCache
import io.ktor.client.plugins.cache.storage.FileStorage
import io.ktor.client.plugins.cookies.CookiesStorage
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.http.ContentDisposition.Companion.File
import kotlinx.coroutines.launch
import java.io.File

class MainActivity : ComponentActivity() {
    private lateinit var httpClient: HttpClient
    private lateinit var repository: Repository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        httpClient = HttpClient(OkHttp) {
            install(HttpCache) {
                val cacheFile = File(application.cacheDir, "http_cache")
                publicStorage(FileStorage(cacheFile))
            }
            install(Logging) {
                logger = CustomAndroidHttpLogger
                level = LogLevel.ALL
            }
        }
        repository = Repository(httpClient)
        setContent {
            Column {
                MainScreen(repository)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        httpClient.close()
    }
}

@Composable
fun MainScreen(repository: Repository) {
    val scope = rememberCoroutineScope()
    Button(
        onClick = { scope.launch { repository.fetch() } }) {
        Text("Press me")
    }
}

private object CustomAndroidHttpLogger : Logger {
    private const val logTag = "CustomAndroidHttpLogger"

    override fun log(message: String) {
        Log.i(logTag, message)
    }
}