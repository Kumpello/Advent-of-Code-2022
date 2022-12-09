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
    val tailLocations: Array<Array<Boolean>> = Array(10000) { Array(10000) { false } }
    var iHead = 5000
    var jHead = 5000
    var iTail = 5000
    var jTail = 5000
    lines.forEach { line ->
        var values = line.split(' ')
        for (i in 0 until values[1].toInt()) {
            if (max(abs(iTail - iHead), abs(jTail - jHead)) > 1) {
                if (iHead > iTail) {
                    iTail++
                }
                if (iHead < iTail) {
                    iTail--
                }
                if (jHead > jTail) {
                    jTail++
                }
                if (jHead < jTail) {
                    jTail--
                }
            }
            tailLocations[iTail][jTail] = true
            when(values[0].toCharArray()[0]) {
                'L' -> {
                    iHead--
                }
                'R' -> {
                    iHead++
                }
                'D' -> {
                    jHead++
                }
                'U' -> {
                    jHead--
                }
            }
        }
    }
    return countAllFields(tailLocations)
}

fun countAllFields(tailLocations: Array<Array<Boolean>>): Int {
    var size = 0
    tailLocations.forEach { row ->
        row.forEach { if(it) size++ }
    }
    return size
}

fun readFileAsLinesUsingUseLines(fileName: String): List<String> = File(fileName).useLines { it.toList() }
