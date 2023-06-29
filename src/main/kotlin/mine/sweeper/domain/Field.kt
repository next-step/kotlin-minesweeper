package mine.sweeper.domain

enum class Field(val value: Char) {
    SAFE_FIELD('C'),
    MINE_FIELD('*'),
}
