package aba.caba.myapplication.data

data class Stack(val cards : List<Card>) {
    fun withCard(index : Int, card : Card) : Stack {
        return Stack(cards.subList(0, index) + listOf(card) + cards.subList(index + 1, cards.size))
    }
}