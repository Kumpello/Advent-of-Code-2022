package day_2

import java.io.File

fun main(args: Array<String>) {
    println(getResults())
}

fun getResults(): Int {
    val fileName = "src/main/resources/day_2/input"
    var totalScore = 0
    File(fileName).forEachLine { line ->
        totalScore += calculatePoints(line)
    }

    return totalScore
}

fun calculatePoints(line: String): Int {
    var score = 0
    when (line) {
        "A X" -> score = 3
        "A Y" -> score = 4
        "A Z" -> score = 8
        "B X" -> score = 1
        "B Y" -> score = 5
        "B Z" -> score = 9
        "C X" -> score = 2
        "C Y" -> score = 6
        "C Z" -> score = 7
    }
    return score
}

