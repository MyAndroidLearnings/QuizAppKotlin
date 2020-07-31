package com.example.studentquizapp.Handler

import android.content.Context
import android.util.Log
import com.android.volley.AuthFailureError
import com.android.volley.DefaultRetryPolicy
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import org.json.JSONObject
import java.io.UnsupportedEncodingException
import java.util.HashMap
import kotlin.jvm.Throws

class ApiHandler {

    companion object {

        //region Getting Asynchoronous Get API Calls
        //purpose : It method is used to hit the get call
        fun getAyncNetworkCall(url: String, context: Context, listener: ServiceNetworkListener) {
            try {
                val queue = Volley.newRequestQueue(context)
                val jsonObjectRequest = JsonObjectRequest(Request.Method.GET, url, null,
                    Response.Listener { response ->
                        // TODO: Handle response
                        listener.onResponse(response)
                    }, Response.ErrorListener { error ->
                        // TODO: Handle error
                        listener.onError(error.toString())
                    })
                queue.add(jsonObjectRequest)

            } catch (e: Exception) {
                Log.d("getapi", e.toString())
            }
        }//endregion

        //region Getting Asynchrounous Post API Calls
        //purpose : It method is called when hit the post api
        fun postAsyncNetworkCall(url: String, context: Context, requestBody: String?, listener: ServiceNetworkListener) {
            try {
                val queue = Volley.newRequestQueue(context)
                val jsonObjectRequest = object :
                    JsonObjectRequest(Request.Method.POST, url, null,
                        Response.Listener { response ->
                            // TODO: Handle response
                            listener.onResponse(response)
                        }, Response.ErrorListener { error ->
                            // TODO: Handle error
                            listener.onError(error.toString())
                        }) {

                    @Throws(AuthFailureError::class)
                    override fun getHeaders(): Map<String, String> {
                        val headers = HashMap<String, String>()
                        headers["Content-Type"] = "application/json"
                        return headers
                    }

                    override fun getBody(): ByteArray? {
                        try {
                            return requestBody?.toByteArray(charset("utf-8"))
                        } catch (e: UnsupportedEncodingException) {
                            Log.d("postapi", e.toString())
                            return null
                        }
                    }
                }
                val retryPolicy = DefaultRetryPolicy(30000,
                    DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                    DefaultRetryPolicy.DEFAULT_BACKOFF_MULT)
                jsonObjectRequest.setRetryPolicy(retryPolicy)


                queue.add(jsonObjectRequest)
            } catch (e: Exception) {
                Log.d("postapi", e.toString())
            }
        }//endregion

    }

}
 //region ServiceNetworkListener
interface ServiceNetworkListener {
    fun onError(message: String)
    fun onResponse(response: JSONObject)
}

