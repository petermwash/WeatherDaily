import android.app.Application
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.pemwa.weatherdaily.TestCoroutineRule
import com.pemwa.weatherdaily.data.api.RemoteDataSource
import com.pemwa.weatherdaily.data.api.WeatherService
import com.pemwa.weatherdaily.data.repository.WeatherRepository
import com.pemwa.weatherdaily.model.CurrentWeather
import com.pemwa.weatherdaily.model.Main
import com.pemwa.weatherdaily.model.Weather
import com.pemwa.weatherdaily.util.NetworkResult
import com.pemwa.weatherdaily.viewmodel.WeatherViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.Response

/**
 * Weather repository test class
 */
@RunWith(MockitoJUnitRunner::class)
@ExperimentalCoroutinesApi
class WeatherRepositoryTest {

    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    private val testDispatcher = TestCoroutineDispatcher()
    private val testLat = "1"
    private val testLon = "36"
    private val appId = "this_is_app_id"
    private val remoteDataSource = Mockito.mock(RemoteDataSource::class.java)
    private val weatherRepository = WeatherRepository(remoteDataSource)
    private val fakeApp = Mockito.mock(Application::class.java)
    private val weatherViewModel = WeatherViewModel(weatherRepository, fakeApp)
    private val weatherService = Mockito.mock(WeatherService::class.java)

    /**
     * test repository can get current weather
     */
    @Test
    fun test_getCurrentWeather() = testDispatcher.runBlockingTest {
        val currentWeather = CurrentWeather(
            200,
            "Name",
            listOf(Weather(1, "Rain", "Rainy", "0d")),
            Main(23.5F, 23.0F, 22.8F, 23.7F, 10, 36)
        )
        val response = Response.success(currentWeather)
        val channel = Channel<Response<CurrentWeather>>()
        val flow = channel.consumeAsFlow().first()
        `when`(weatherService.getCurrentWeather(testLat, testLon, appId)).thenReturn(flow)

        this.launch {
            channel.send(response)
        }

        weatherRepository.getCurrentWeather(testLat, testLon)
        Assert.assertFalse(weatherViewModel.currentWeather.value?.data == null)
        Assert.assertEquals(currentWeather, weatherViewModel.currentWeather.value?.data)
    }
}
