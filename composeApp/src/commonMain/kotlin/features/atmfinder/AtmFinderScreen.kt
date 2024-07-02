package features.atmfinder

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier


@Composable
fun AtmFinderScreen(
    viewModel: AtmFinderViewModel,
) {
    val atms = viewModel.atms.collectAsState().value

    Column(Modifier.fillMaxSize()) {
        Text(text = atms.joinToString())
    }
}

