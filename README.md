# 🌦 Wthr App

A simple Android app built with Kotlin that fetches and displays the current weather of any city using the [OpenWeatherMap API](https://openweathermap.org/).

##  Features

- Enter any city name and get:
  - Current temperature in °C
  - Weather description
  - Wind speed
- Simple UI with clean output
- Retrofit for network calls
- XML layout
- Error handling for invalid inputs

##  Tech Stack

- Kotlin
- Android SDK
- Retrofit2
- OpenWeatherMap API
- ViewBinding (optional)


##  How to Run

1. **Clone this repository**  
   ```bash
   git clone https://github.com/AnaghaSujith/Wthr-app.git
2. **Open with Android Studio**
3. Add your API key

    - Go to MainActivity.kt
    - Replace the placeholder with your OpenWeatherMap API key:
      -val API_KEY = "3a3fe9945a38edc37c34319214f993cd"
4. Run the app on emulator or physical device.

## App UI
<img width="300" height="450" alt="image" src="https://github.com/user-attachments/assets/0389e0c2-4485-420f-b7d0-a995b2192439" />


##  API Reference
```bash
    OpenWeatherMap API docs: https://openweathermap.org/current
```
##  Notes

- You need an active internet connection to fetch weather data.
- Make sure your app has INTERNET permission in AndroidManifest.xml:
```bash
<uses-permission android:name="android.permission.INTERNET"/>
```
