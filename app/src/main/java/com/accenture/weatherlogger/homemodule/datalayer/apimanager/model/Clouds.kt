package com.accenture.weatherlogger.homemodule.datalayer.apimanager.model
import com.google.gson.annotations.SerializedName

data class Clouds (
	@SerializedName("all") val all : Int
)