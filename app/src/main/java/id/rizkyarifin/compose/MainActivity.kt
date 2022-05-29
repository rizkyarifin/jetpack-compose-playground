package id.rizkyarifin.compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import id.rizkyarifin.compose.ui.theme.ComposePlayGroundTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            // A surface container using the 'background' color from the theme
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colors.background
            ) {
                MessageCard(Message("Android", "Jetpack Compose"))
            }
        }
    }
}

@Composable
fun MessageCard(message: Message) {
    ComposePlayGroundTheme {
        Row(modifier = Modifier.padding(all = 8.dp)) {
            Image(
                painter = painterResource(id = R.drawable.ic_launcher_background),
                contentDescription = "Launcher Image!",
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .border(width = 1.5.dp, MaterialTheme.colors.secondary, CircleShape)
            )
            Spacer(modifier = Modifier.width(8.dp))

            var isExpanded by remember { mutableStateOf(false) }
            val surfaceColor = if (isExpanded) MaterialTheme.colors.primary
            else MaterialTheme.colors.surface

            Column (modifier = if (message.body.length > 100) Modifier.clickable { isExpanded = !isExpanded } else Modifier.clickable { }) {
                Text(
                    text = message.author,
                    color = MaterialTheme.colors.secondaryVariant,
                    style = MaterialTheme.typography.subtitle2)

                Spacer(modifier = Modifier.height(4.dp))

                Surface(
                    shape = MaterialTheme.shapes.medium,
                    elevation = 1.dp,
                    color = surfaceColor,
                    modifier = Modifier.animateContentSize().padding(1.dp)) {
                    Text(
                        text = message.body,
                        modifier = Modifier.padding(all = 4.dp),
                        maxLines = if (isExpanded) Int.MAX_VALUE else 1,
                        style = MaterialTheme.typography.body2)
                }
            }
        }
    }
}

@Composable
fun Conversation(messages : List<Message>) {
    LazyColumn {
        items(messages) { message ->
            MessageCard(message = message)
        }
    }
}

data class Message(val author: String, val body: String)

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    Conversation(SampleData.conversationSample)
}