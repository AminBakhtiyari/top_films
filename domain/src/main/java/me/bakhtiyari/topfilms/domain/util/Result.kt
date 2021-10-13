package me.bakhtiyari.topfilms.domain.util

/**
 * handler network response from domain layer
 */
sealed class Result<out V, out E> {

    /**
     * a function to able us to return success result from the domain layer
     *
     * @return Result's success type with data <Result.success(<Any>)>
     */
    abstract fun success(): V?

    /**
     * a function to able us to return error result from the domain layer
     *
     * @return Result's error type with Exception <Result.error(<Any>)>
     */
    abstract fun error(): E?

    /**
     * a function to able us to return loading result from the domain layer
     *
     * @return Result's loading type with loading status  <Result.loading(<Boolean>)>
     */
    abstract fun loading(): Boolean


    /**
     * handel success in Result
     *
     * @param value: data of ResponseResource response <Any>
     *
     * @return a Result's success object < Result<Any, Any>>
     *
     * @see Result
     */
    class Success<V, E>(private val value: V) : Result<V, E>() {

        /**
         * set value parameter into success
         */
        override fun success(): V? = value

        /**
         * set <null> into error
         */
        override fun error(): E? = null

        /**
         * set <false> into loading
         */
        override fun loading() = false

    }

    /**
     * handel error in Result
     *
     * @param value: data of ResponseResource response <Any>
     *
     * @return a Result's error object < Result<Any, Any>>
     *
     * @see Result
     */
    class Error<V, E>(private val value: E) : Result<V, E>() {

        /**
         * set <null> into success
         */
        override fun success(): V? = null

        /**
         * set value parameter into error
         */
        override fun error(): E? = value

        /**
         * set <false> into loading
         */
        override fun loading() = false

    }


    /**
     * handel Loading in Result
     *
     * @return a Result's Loading object < Result<Any, Any>>
     *
     * @see Result
     */
    class Loading<V, E> : Result<V, E>() {

        /**
         * set <null> into success
         */
        override fun success(): V? = null

        /**
         * set <null> into error
         */
        override fun error(): E? = null

        /**
         * set <true> into loading
         */
        override fun loading() = true


    }

}