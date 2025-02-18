package shop.fast_shop.view.component

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext

@Composable
fun ErrorText(
    errorResId: Int?,
    modifier: Modifier = Modifier,
    vararg formatArgs: Any
) {
    val context = LocalContext.current
    val errorMessage = errorResId?.let {
        context.getString(it, *formatArgs)
    }

    if (!errorMessage.isNullOrEmpty()) {
        Text(
            text = errorMessage,
            color = MaterialTheme.colorScheme.error,
            style = MaterialTheme.typography.bodySmall,
            modifier = modifier
        )
    }
}

// Nova sobrecarga para aceitar string direta
@Composable
fun ErrorText(
    text: String,
    modifier: Modifier = Modifier
) {
    if (text.isNotEmpty()) {
        Text(
            text = text,
            color = MaterialTheme.colorScheme.error,
            style = MaterialTheme.typography.bodySmall,
            modifier = modifier
        )
    }
}