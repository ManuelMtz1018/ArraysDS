package com.example.arraysds.control

import java.util.*

class ArraysDS {
    companion object {

        var validPosition: List<ValidPosition> = Arrays.asList(
            ValidPosition(0, 0),
            ValidPosition(1, 0),
            ValidPosition(2, 0),
            ValidPosition(1, 1),
            ValidPosition(0, 2),
            ValidPosition(1, 2),
            ValidPosition(2, 2)
        )

        fun hourglassSum(arr: List<List<Int>>): Int {
            var acum = 0
            var isReachedLeft = false
            var maximumValue = 0
            val limitRouteForPattern = arr[0].size - 2
            for (col in 0 until limitRouteForPattern) {
                for (row in 0 until limitRouteForPattern) {
                    for (index in 0..arr[0].size) {
                        acum += arr[validPosition[index].y][validPosition[index].x]
                        if (col == 0 && row == 0)
                            maximumValue = acum
                        if (row != limitRouteForPattern - 1) {
                            if (isReachedLeft)
                                validPosition[index].decreaseX()
                            else
                                validPosition[index].increaseX()
                        }
                    }
                    if (acum > maximumValue)
                        maximumValue = acum
                    acum = 0
                }
                for (i in validPosition.indices)
                    validPosition[i].increaseY()
                isReachedLeft = !isReachedLeft
            }
            for (i in validPosition.indices)
                validPosition[i].returnInitialPositions()
            return maximumValue
        }
    }

    class ValidPosition(var x: Int, var y: Int) {
        var iniTialPositionX:Int=0
        var iniTialPositionY:Int=0
        init {
            iniTialPositionX=x
            iniTialPositionY=y
        }
        fun increaseX() {
            x += 1
        }
        fun decreaseX() {
            x -= 1
        }
        fun increaseY() {
            y += 1
        }
        fun returnInitialPositions(){
            x=iniTialPositionX
            y=iniTialPositionY
        }

    }
}