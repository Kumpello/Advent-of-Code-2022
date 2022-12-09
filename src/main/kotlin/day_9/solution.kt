package day_9

import java.io.File
import java.lang.Integer.max
import kotlin.math.abs

const val fileName = "src/main/resources/day_9/input"

fun main(args: Array<String>) {
    println(getTailLocationsNumber())
}

fun getTailLocationsNumber(): Int {
    val lines = readFileAsLinesUsingUseLines(fileName)
    var head = Position(0,0)
    var tail = Position(0,0)
    val locationList: MutableSet<Position> = mutableSetOf()

    lines.forEach { line ->
        var values = line.split(' ')
        for (i in 0 until values[1].toInt()) {
            when(values[0].toCharArray()[0]) {
                'L' -> {
                    head.x--
                }
                'R' -> {
                    head.x++
                }
                'D' -> {
                    head.y--
                }
                'U' -> {
                    head.y++
                }
            }
            if (max(abs(tail.x - head.x), abs(tail.y - head.y)) > 1) {
                if (head.x > tail.x) {
                    tail.x++
                }
                if (head.x < tail.x) {
                    tail.x--
                }
                if (head.y > tail.y) {
                    tail.y++
                }
                if (head.y < tail.y) {
                    tail.y--
                }
            }
            locationList.add(tail.copy())
        }
    }
    return locationList.size
}

data class Position(var x:Int, var y: Int)

fun readFileAsLinesUsingUseLines(fileName: String): List<String> = File(fileName).useLines { it.toList() }
