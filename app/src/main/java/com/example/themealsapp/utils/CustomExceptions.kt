package com.example.themealsapp.utils

class NullResponse(message: String = "Couldn't fetch from Internet. Check Your Internet Connection"): Exception(message)
class FailureResponse(message: String?): Exception(message)