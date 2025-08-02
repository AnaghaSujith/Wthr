package com.wthrapp.now

import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

class MainActivity : AppCompatActivity() {

    val API_KEY = "3a3fe9945a38edc37c34319214f993cd" // Replace with your own API key

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val etCity = findViewById<EditText>(R.id.cityInput)
        val btnFetch = findViewById<Button>(R.id.getWeatherBtn)
        val tvResult = findViewById<TextView>(R.id.weatherResult)
        val ivIcon = findViewById<ImageView>(R.id.weatherIcon)

        btnFetch.setOnClickListener {
            val city = etCity.text.toString()
            if (city.isNotEmpty()) {
                getWeather(city, tvResult, ivIcon)
            } else {
                Toast.makeText(this, "Please enter a city", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun getWeather(city: String, resultView: TextView, iconView: ImageView) {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.openweathermap.org/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(WeatherService::class.java)
        val call = service.getWeather(city, API_KEY, "metric")

        call.enqueue(object : Callback<WeatherResponse> {
            override fun onResponse(call: Call<WeatherResponse>, response: Response<WeatherResponse>) {
                if (response.isSuccessful) {
                    val data = response.body()
                    val result = """
                        City: ${data?.name}
                        Temp: ${data?.main?.temp}°C
                        Weather: ${data?.weather?.get(0)?.description}
                        Wind: ${data?.wind?.speed} m/s
                    """.trimIndent()
                    resultView.text = result

                    // Show the weather icon
                    iconView.setImageResource(R.drawable.ic_weather_sun_rain)  // Replace with your icon name
                    iconView.visibility = View.VISIBLE
                } else {
                    resultView.text = "City not found"
                    iconView.visibility = View.GONE
                }
            }

            override fun onFailure(call: Call<WeatherResponse>, t: Throwable) {
                resultView.text = "Error: ${t.message}"
                iconView.visibility = View.GONE
            }
        })
    }
}

// Retrofit API interface
interface WeatherService {
    @GET("data/2.5/weather")
    fun getWeather(
        @Query("q") city: String,
        @Query("appid") apiKey: String,
        @Query("units") units: String
    ): Call<WeatherResponse>
}

// Data models
data class WeatherResponse(
    val name: String,
    val main: Main,
    val weather: List<Weather>,
    val wind: Wind
)

data class Main(val temp: Float)
data class Weather(val description: String, val icon: String)
data class Wind(val speed: Float)
