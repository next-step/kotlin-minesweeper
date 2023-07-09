package domain

data class Location(val y: Int, val x: Int) {
    fun isSame(y: Int, x: Int): Boolean {
        return this.x == x && this.y == y
    }
}
