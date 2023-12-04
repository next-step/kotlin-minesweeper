package controller

import controller.GameManager.prepareField
import controller.GameManager.runGame
import controller.GameManager.setCells
import view.printResult

fun main() {
    val field = prepareField()

    setCells(field)

    runGame(field)

    printResult(field.userWins())
}
