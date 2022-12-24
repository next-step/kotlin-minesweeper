package model

data class Cell(var name: String = "C") {
    fun change(name: String) {
        this.name = name
    }
}
