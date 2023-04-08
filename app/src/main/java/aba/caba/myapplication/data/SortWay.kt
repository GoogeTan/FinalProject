package aba.caba.myapplication.data

enum class SortWay(val text : String) {
    Filter("Стандартный") {
        override fun provider(theme: Theme): ThemeProvider {
            TODO("Not yet implemented")
        }
    },
    Cycle("Циклический") {
        override fun provider(theme: Theme): ThemeProvider {
            return IteratorProvider(theme, 0, listOf())
        }
    },
    Laytner("Система Лейтнера") {
        override fun provider(theme: Theme): ThemeProvider {
            TODO()
        }
    };
    abstract fun provider(theme: Theme) : ThemeProvider
}