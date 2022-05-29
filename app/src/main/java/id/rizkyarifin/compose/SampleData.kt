package id.rizkyarifin.compose

class SampleData {
    companion object {
        val conversationSample = listOf(Message("Jason", "Hello, There"),
            Message("Red", "I'm fine thank you, how about you ?"),
            Message("Barbara", "The conversation is getting more interesting. It's time to play with animations! You will add the ability to expand a message to show a longer one, animating both the content size and the background color. To store this local UI state, you need to keep track of whether a message has been expanded or not. "))
    }
}