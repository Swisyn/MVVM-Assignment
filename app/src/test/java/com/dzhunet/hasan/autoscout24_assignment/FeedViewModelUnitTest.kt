package com.dzhunet.hasan.autoscout24_assignment

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.dzhunet.hasan.autoscout24_assignment.data.network.responses.FeedResultModel
import com.dzhunet.hasan.autoscout24_assignment.data.repository.FeedListRepository
import com.dzhunet.hasan.autoscout24_assignment.data.repository.NotesRepository
import com.dzhunet.hasan.autoscout24_assignment.data.vo.Resource
import com.dzhunet.hasan.autoscout24_assignment.ui.feed.FeedViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.*
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner


/**
 * Created by [Dzhunet Hasan] on [01 Mar, 2021]. | [KOBIL Systems GmbH] | [dzhunet.hasan@kobil.com]
 */

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class FeedViewModelUnitTest {

    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    @Mock
    private lateinit var feedListRepository: FeedListRepository

    @Mock
    private lateinit var notesRepository: NotesRepository

    @Mock
    private lateinit var feedsObserver: Observer<Resource<MutableList<FeedResultModel>>>

    @Before
    fun setUp() {
    }

    @Test
    fun givenServerResponse200_whenFetch_shouldReturnSuccess() {
        testCoroutineRule.runBlockingTest {
            doReturn(
                MutableLiveData(
                    Resource<MutableList<FeedResultModel>>(
                        Resource.Status.SUCCESS,
                        mutableListOf(), null
                    )
                )
            )
                .`when`(feedListRepository)
                .getFeeds()

            val viewModel = FeedViewModel(feedListRepository, notesRepository)
            viewModel.getFeeds.observeForever(feedsObserver)
            verify(feedListRepository).getFeeds()
            verify(feedsObserver).onChanged(Resource.success(mutableListOf()))
            viewModel.getFeeds.removeObserver(feedsObserver)
        }
    }

    @Test
    fun givenServerResponseError_whenFetch_shouldReturnError() {
        testCoroutineRule.runBlockingTest {
            val errorMessage = "Server is not reachable!"
            doReturn(
                MutableLiveData(
                    Resource<MutableList<FeedResultModel>>(
                        Resource.Status.ERROR,
                        null, errorMessage
                    )
                )
            )
                .`when`(feedListRepository)
                .getFeeds()

            val viewModel = FeedViewModel(feedListRepository, notesRepository)
            viewModel.getFeeds.observeForever(feedsObserver)
            verify(feedListRepository).getFeeds()
            verify(feedsObserver).onChanged(Resource.error(errorMessage))
            viewModel.getFeeds.removeObserver(feedsObserver)
        }
    }

    @Test
    fun givenNotes_checkEquality() {
        testCoroutineRule.runBlockingTest {
            val exampleNotes = arrayOf("Example note 1", "Example note 2")

            val viewModel = spy(FeedViewModel(feedListRepository, notesRepository))

            doReturn(
                exampleNotes
            )
                .`when`(viewModel)
                .getVehicleNotes(vehicleId = 1)

            Assert.assertEquals(2, viewModel.getVehicleNotes(vehicleId = 1)?.size)
            Assert.assertEquals("Example note 1", viewModel.getVehicleNotes(vehicleId = 1)?.first())
        }
    }

    @After
    fun tearDown() {
    }

}