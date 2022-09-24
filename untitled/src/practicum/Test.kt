package practicum

class Test<in T, out V> {
    fun check(t: T) {}
    fun check(): V? {
        return null
    }
//    fun check2(v: V){ // не работает и не должно
//
//    }
}