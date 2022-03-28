package com.kdw.mystorymedia.util

inline fun<T> safeCall(action: () -> Resource<T>) : Resource<T> {
    return try{
        action()
    } catch(e: Exception) {
       Resource.Error(e.message?: "알 수 없는 에러가 떴습니다")
    }
}