package io.github.aimsio.meteo.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import dagger.android.support.AndroidSupportInjection.inject
import io.github.aimsio.meteo.base.BaseUTTest
import io.github.aimsio.meteo.data.network.WeatherApi
import io.github.aimsio.meteo.data.repository.CurrentRepository
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockWebServer
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.koin.core.context.startKoin
import java.net.HttpURLConnection

@RunWith(JUnit4::class)
class LoginRepositoryTest : BaseUTTest(){

    //Target
    private lateinit var currentRepository: CurrentRepository
    //Inject api service created with koin
    val weatherApi : WeatherApi by inject()
    //Inject Mockwebserver created with koin
    val mockWebServer : MockWebServer by inject()

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    val mNextValue = "https://api.openweathermap.org/current/data/2.5/weather"
    val mCity = "Vancouver"

    @Before
    fun start(){
        super.setUp()

        startKoin{ modules(configureTestAppComponent(getMockWebServerUrl()))}
    }

    @Test
    fun test_login_repo_retrieves_expected_data() =  runBlocking<Unit>{

        mockNetworkResponseWithFileContent("current_weather_success_resp.json", HttpURLConnection.HTTP_OK)
        currentRepository = CurrentRepository()

        val dataReceived = currentRepository.getCurrentWeather(mCity)

        assertNotNull(dataReceived)
        assertEquals(dataReceived.name, mCity)
    }
}