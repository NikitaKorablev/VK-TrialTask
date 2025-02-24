package com.videoviewer.presentation.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.videoviwer.core.data.VideosData
import com.videoviwer.domain.usecases.GetTopPopularVideosUseCase
import com.videoviwer.domain.usecases.GetVideosFromSharedPrefsUseCase
import com.videoviwer.domain.usecases.SaveVideosToSharedPrefsUseCase
import com.videoviwer.presentation.viewmodel.VideosListViewModel
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class VideosListViewModelTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `test refreshVideosList`() = runBlockingTest {
        val viewModel = VideosListViewModel()

        viewModel.getTopPopularVideosUseCase = mockk<GetTopPopularVideosUseCase>()
        viewModel.saveVideosToSharedPrefsUseCase = mockk<SaveVideosToSharedPrefsUseCase>()

        val videosData = VideosData.CorrectData(emptyList())
        every {
            runBlocking { viewModel.getTopPopularVideosUseCase.execute() }
        } returns videosData

        viewModel.refreshVideosList()
        assert(viewModel.videosList.value == videosData)
    }

    @Test
    fun `test getTopVideos with invalid data`() = runBlockingTest {
        val viewModel = VideosListViewModel()

        viewModel.getVideosFromSharedPrefsUseCase = mockk<GetVideosFromSharedPrefsUseCase>()
        viewModel.getTopPopularVideosUseCase = mockk<GetTopPopularVideosUseCase>()

        val videosData = VideosData.InvalidData("Error")
        every { viewModel.getVideosFromSharedPrefsUseCase.execute() } returns videosData
        every {
            runBlocking { viewModel.getTopPopularVideosUseCase.execute() }
        } returns VideosData.CorrectData(emptyList())

        viewModel.getTopVideos()
        verify { viewModel.getVideosFromSharedPrefsUseCase.execute() }
        verify(exactly = 0) {
            runBlocking { viewModel.getTopPopularVideosUseCase.execute() }
        }
    }
}