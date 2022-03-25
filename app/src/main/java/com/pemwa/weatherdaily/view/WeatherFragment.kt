package com.pemwa.weatherdaily.view

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.google.android.gms.location.*
import com.pemwa.weatherdaily.databinding.FragmentWeatherBinding
import com.pemwa.weatherdaily.util.Constants.Companion.LOCATION_PERMISSION_CODE
import com.pemwa.weatherdaily.util.NetworkResult
import com.pemwa.weatherdaily.viewmodel.WeatherViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.util.concurrent.TimeUnit

/**
 * A simple [Fragment] subclass.
 */
@AndroidEntryPoint
class WeatherFragment : Fragment() {

    private val viewModel by viewModels<WeatherViewModel>()
    private lateinit var binding: FragmentWeatherBinding

    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient
    private lateinit var locationRequest: LocationRequest
    private lateinit var locationCallback: LocationCallback

    /**
     * Inflates the view
     */
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentWeatherBinding.inflate(layoutInflater)
        return binding.root
    }

    /**
     * Takes some actions when the view is created
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fusedLocationProviderClient =
            LocationServices.getFusedLocationProviderClient(requireContext())
        locationRequest = LocationRequest.create().apply {
            interval = TimeUnit.SECONDS.toMillis(60)
            fastestInterval = TimeUnit.SECONDS.toMillis(30)
            maxWaitTime = TimeUnit.MINUTES.toMillis(2)
            priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        }
        locationCallback = object : LocationCallback() {
            override fun onLocationResult(locationResult: LocationResult) {
                super.onLocationResult(locationResult)
                locationResult.lastLocation.let { currentLocation ->
                    fetchCurrentWeather(
                        currentLocation.latitude.toString(),
                        currentLocation.longitude.toString()
                    )
                    fetchForecastWeather(
                        currentLocation.latitude.toString(),
                        currentLocation.longitude.toString()
                    )
                }
            }
        }

        getLastLocation()
    }

    /**
     * checks if location permissions are granted
     * then gets last known location and call the fetch weather methods with returned location
     */
    private fun getLastLocation() {
        Looper.myLooper()?.let {
            if (ActivityCompat.checkSelfPermission(
                    requireContext(),
                    Manifest.permission.ACCESS_FINE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                    requireContext(),
                    Manifest.permission.ACCESS_COARSE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                ActivityCompat.requestPermissions(
                    requireActivity(),
                    arrayOf(
                        Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.ACCESS_COARSE_LOCATION
                    ),
                    LOCATION_PERMISSION_CODE
                )
                return
            }

            fusedLocationProviderClient.lastLocation.addOnCompleteListener(requireActivity()) { task ->
                val location = task.result
                if (location == null) {
                    fusedLocationProviderClient.requestLocationUpdates(locationRequest, locationCallback,
                        it
                    )
                } else {
                    fetchCurrentWeather(
                        location.latitude.toString(),
                        location.longitude.toString()
                    )
                    fetchForecastWeather(
                        location.latitude.toString(),
                        location.longitude.toString()
                    )
                }
            }
        }
    }

    /**
     * Invokes fetching of the current weather
     * @param lat current location latitude
     * @param lon current location longitude
     */
    private fun fetchCurrentWeather(lat: String, lon: String) {
        viewModel.fetchCurrentWeather(lat, lon)
        viewModel.currentWeather.observe(viewLifecycleOwner, { response ->
            when (response) {
                is NetworkResult.Loading -> {
                    Log.d("CURRENT_WEATHER", "Loading...")
                }
                is NetworkResult.Success -> {
                    Log.d("CURRENT_WEATHER", "${response.data}")
                    Toast.makeText(
                        requireContext(),
                        response.data.toString(),
                        Toast.LENGTH_SHORT
                    ).show()
                }
                is NetworkResult.Error -> {
                    Log.d("CURRENT_WEATHER", "${response.data}")
                    Toast.makeText(
                        requireContext(),
                        response.message,
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        })
    }

    /**
     * Invokes fetching of the weather forecast
     * @param lat current location latitude
     * @param lon current location longitude
     */
    private fun fetchForecastWeather(lat: String, lon: String) {
        viewModel.fetchForecastWeather(lat, lon)
        viewModel.forecastWeather.observe(viewLifecycleOwner, { response ->
            when (response) {
                is NetworkResult.Loading -> {
                    Log.d("FORECAST_WEATHER", "Loading...")
                }
                is NetworkResult.Success -> {
                    Log.d("FORECAST_WEATHER", "${response.data}")
                    Toast.makeText(
                        requireContext(),
                        response.data.toString(),
                        Toast.LENGTH_SHORT
                    ).show()
                }
                is NetworkResult.Error -> {
                    Log.d("FORECAST_WEATHER", "$response")
                    Toast.makeText(
                        requireContext(),
                        response.message,
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        })
    }
}