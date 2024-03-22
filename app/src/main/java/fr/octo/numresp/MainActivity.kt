package fr.octo.numresp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    private lateinit var httpClient: HttpClient
    private lateinit var repository: Repository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        httpClient = HttpClient(Android)
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