package aba.caba.myapplication.data

data class IteratorProvider(
    val theme : Theme,
    val index : Int,
    val goodd: List<Boolean>) : ThemeProvider {
    override val card: Card = theme.stack.cards[index % theme.stack.cards.size]
    override val name: String = theme.name
    override val good: List<Boolean?> = goodd + List(theme.stack.cards.size * 3 - goodd.size) { null }
    override fun submitAnswer(answer: Boolean): ThemeProvider? {
        return if (index + 1 >= theme.stack.cards.size * 3) {
            null
        } else {
            IteratorProvider(
                theme, index + 1, goodd + listOf(answer)
            )
        }
    }
}