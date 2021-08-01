package com.goforer.lukohsplash.data.repository.remote.user

import androidx.paging.PagingData
import com.goforer.lukohsplash.data.repository.paging.source.user.UserLikesPagingSource
import com.goforer.lukohsplash.data.repository.remote.RepositoryTest
import com.goforer.lukohsplash.data.source.model.entity.photo.response.Photo
import com.goforer.test.kit.QueryTool
import com.goforer.test.kit.flow.test
import io.mockk.junit5.MockKExtension
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.ObsoleteCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@ObsoleteCoroutinesApi
@ExperimentalCoroutinesApi
@ExtendWith(MockKExtension::class)
@RunWith(MockitoJUnitRunner::class)
class GetUserLikesRepositoryTest : RepositoryTest() {
    @Before
    fun setup() {
        setBaseRepository(
            GetUserLikesRepository(UserLikesPagingSource()),
            QueryTool.getQuery(0, "0")
        )
    }

    @Test
    fun workTest() {
        runBlockingTest {
            coroutinesTestRule.managedCoroutineScope.launch {
                repository.doWork(this, defaultQuery).test(this) {
                    this.assertValue {
                        @Suppress("UNCHECKED_CAST")
                        (it.getData() as? PagingData<Photo>) is PagingData<Photo>
                    }
                }
            }
        }
    }
}