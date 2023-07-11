package io.rotlabs.flakerandroidretrofit

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import io.rotlabs.flakerandroidapp.ui.theme.FlakerAndroidTheme
import io.rotlabs.flakerandroidapp.R as CompanionAppResource

@OptIn(ExperimentalMaterial3Api::class)
class FlakerActivity : ComponentActivity() {

    private val viewModel: FlakerViewModel by viewModels { FlakerViewModel.Factory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val rootModifier = Modifier.padding(16.dp)

            val state by viewModel.viewStateFlow.collectAsState()

            FlakerAndroidTheme {
                Scaffold(
                    topBar = { FlakerBar(state = state, onToggleChange = {}) }
                ) {
                    NetworkRequestList(modifier = rootModifier.padding(it), state = state)
                }
            }
        }
    }

    @Composable
    private fun NetworkRequestList(modifier: Modifier = Modifier, state: FlakerViewModel.ViewState) {
        LazyColumn(modifier = modifier) {
            items(state.networkRequests, key = null) {
            }
        }
    }

    @Composable
    private fun FlakerBar(
        modifier: Modifier = Modifier,
        state: FlakerViewModel.ViewState,
        onToggleChange: (Boolean) -> Unit
    ) {
        TopAppBar(
            modifier = modifier,
            title = {
                Text(text = stringResource(id = CompanionAppResource.string.companion_app_name))
            },
            actions = {
                Switch(
                    checked = state.isFlakerOn,
                    onCheckedChange = onToggleChange,
                    modifier = Modifier.wrapContentWidth()
                )
            }
        )
    }
}
