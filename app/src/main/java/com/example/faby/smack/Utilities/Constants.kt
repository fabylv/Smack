package com.example.faby.smack.Utilities


const val BASE_URL = "https://chattychat3.herokuapp.com/v1/"
const val URL_REGISTER = "${BASE_URL}account/register"
const val URL_LOGIN = "${BASE_URL}account/login"
const val URL_CREATE_USER =  "${ BASE_URL}user/add"
const val URL_GET_USER = "${BASE_URL}user/byEmail/"

//broadcast constants
const val BROADCAST_USER_DATA_CHANGE = "BROADCAST_USER_DATA_CHANGE"