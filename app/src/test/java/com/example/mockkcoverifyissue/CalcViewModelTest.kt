package com.example.mockkcoverifyissue

import io.mockk.coVerify
import io.mockk.spyk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.rules.TestWatcher
import org.junit.runner.Description


class CalcViewModelTest {

    private lateinit var viewModel: CalcViewModel

    // Using this rule to host test containing coroutines
    @ExperimentalCoroutinesApi
    @get:Rule
    val coRoutineDispatcherRule = CoroutineDispatcherRule()

    @Before
    fun setup() {
        viewModel = spyk(CalcViewModel(), recordPrivateCalls = true)
    }

    @Test
    fun testSum() {
        viewModel.sum(1, 2)
        coVerify { viewModel["performActualSum"](1, 2) }
    }
}

@ExperimentalCoroutinesApi
class CoroutineDispatcherRule(private val dispatcher: TestCoroutineDispatcher = TestCoroutineDispatcher()) : TestWatcher() {
    override fun starting(description: Description?) {
        super.starting(description)
        Dispatchers.setMain(dispatcher)
    }

    override fun finished(description: Description?) {
        super.finished(description)
        Dispatchers.resetMain()
        dispatcher.cleanupTestCoroutines()
    }
}