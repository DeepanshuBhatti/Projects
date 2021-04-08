package edu.deepanshu.algorithms

import org.junit.Assert.*
import org.junit.Test

class ShortestJobFirstTest {

    private var shortestJobFirst = ShortestJobFirst()

    @Test
    fun getAverageWaitingTime_whenInputs_shouldReturn() {
        shortestJobFirst.addProcess(1, "P1", 21, 0)
        shortestJobFirst.addProcess(2, "P2", 3, 1)
        shortestJobFirst.addProcess(3, "P3", 6, 2)
        shortestJobFirst.addProcess(4, "P4", 2, 3)

        shortestJobFirst.calculate()
        val averageWaitingTime = shortestJobFirst.getAverageWaitingTime()
        assertTrue(averageWaitingTime.equals(4.0))
    }

    @Test
    fun getAverageWaitingTime_whenPreemptive_shouldReturnCorrectValue() {
        shortestJobFirst.addProcess(1, "P1", 6, 2)
        shortestJobFirst.addProcess(2, "P2", 2, 5)
        shortestJobFirst.addProcess(3, "P3", 8, 1)
        shortestJobFirst.addProcess(4, "P4", 3, 0)
        shortestJobFirst.addProcess(5, "P5", 4, 4)

        shortestJobFirst.calculate()
        val averageWaitingTime = shortestJobFirst.getAverageWaitingTime()

        assertTrue(averageWaitingTime.equals(4.6))
    }

    @Test
    fun getAverageTurnAroundTime_whenPreemptive_shouldReturnCorrectValue() {
        shortestJobFirst.addProcess(1, "P1", 9, 0)
        shortestJobFirst.addProcess(2, "P2", 4, 1)
        shortestJobFirst.addProcess(3, "P3", 5, 2)
        shortestJobFirst.addProcess(4, "P4", 7, 3)
        shortestJobFirst.addProcess(5, "P5", 3, 4)

        shortestJobFirst.calculate()
        val averageWaitingTime = shortestJobFirst.getAverageTurnAroundTime()

        assertTrue(averageWaitingTime.equals(12.8))
    }

    @Test
    fun getAverageTurnAroundTime_whenNonPreemptive_shouldReturnCorrectValue() {
        shortestJobFirst.addProcess(1, "P1", 9, 0)
        shortestJobFirst.addProcess(2, "P2", 4, 0)
        shortestJobFirst.addProcess(3, "P3", 5, 0)
        shortestJobFirst.addProcess(4, "P4", 7, 0)
        shortestJobFirst.addProcess(5, "P5", 3, 0)

        shortestJobFirst.calculate()
        val averageWaitingTime = shortestJobFirst.getAverageTurnAroundTime()

        assertTrue(averageWaitingTime.equals(13.8))
    }
}