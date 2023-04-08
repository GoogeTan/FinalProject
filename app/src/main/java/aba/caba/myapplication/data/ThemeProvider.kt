package aba.caba.myapplication.data

interface ThemeProvider {
    val name : String
    val card : Card
    val good : List<Boolean?>
    fun submitAnswer(answer : Boolean) : ThemeProvider?
}