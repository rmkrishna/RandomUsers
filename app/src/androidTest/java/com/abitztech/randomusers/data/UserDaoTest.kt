package com.abitztech.randomusers.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.abitztech.randomusers.data.local.db.DB
import com.abitztech.randomusers.data.local.db.dao.UserDao
import com.abitztech.randomusers.data.local.db.entity.UserEntity
import kotlinx.coroutines.runBlocking
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.equalTo
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class UserDaoTest {
    private lateinit var database: DB
    private lateinit var userDao: UserDao

    private val userA = UserEntity("1", "A", "B", "a@a.com", "111", "111", "", "")
    private val userB = UserEntity("2", "B", "B", "a@a.com", "111", "111", "", "")
    private val userC = UserEntity("3", "C", "B", "a@a.com", "111", "111", "", "")

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun createDb() = runBlocking {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        database = Room.inMemoryDatabaseBuilder(context, DB::class.java).build()
        userDao = database.userDao()

        userDao.insert(listOf(userA, userB, userC))
    }

    @After
    fun closeDb() {
        database.close()
    }

    @Test
    fun testUsersCount() = runBlocking {
        val users = userDao.getAll()
        assertThat(users.size, equalTo(3))
    }
}