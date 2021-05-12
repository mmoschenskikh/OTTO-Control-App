package ru.spbstu.ottocontrol.util

interface Observer<T> {
    fun onChange(data: T)
}

interface Observable<T> {
    fun subscribe(observer: Observer<T>)
    fun unsubscribe(observer: Observer<T>)
}
