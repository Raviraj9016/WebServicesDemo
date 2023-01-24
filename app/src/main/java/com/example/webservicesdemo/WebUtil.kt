package com.example.webservicesdemo

import android.util.Log
import org.json.JSONObject
import java.net.URL
import javax.net.ssl.HttpsURLConnection

class WebUtil {
    companion object{
        fun simpleHTTPRequest(targetURL : String){
            var url = URL(targetURL)
            var httpsURLConnection : HttpsURLConnection = url.openConnection() as HttpsURLConnection
            httpsURLConnection.connect()

            Log.e("tag","${httpsURLConnection.responseCode}")
            Log.e("tag","${httpsURLConnection.responseMessage}")
            Log.e("tag","${httpsURLConnection.contentLength}")
            Log.e("tag","${httpsURLConnection.contentEncoding}")

            var inStream = httpsURLConnection.inputStream
            var data = ByteArray(1024*1)
            var count = 0
            var buffer = StringBuffer()

            count = inStream.read(data)
            while (count != -1){
                buffer.append(String(data,0,count))
                count = inStream.read(data)
            }

            inStream.close()
            Log.e("tag",buffer.toString())
        }

        fun getAllUsers() : ArrayList<User>?{
            var url = URL("https://reqres.in/api/users?page=2")
            var httpsURLConnection : HttpsURLConnection = url.openConnection() as HttpsURLConnection
            httpsURLConnection.connect()

            Log.e("tag","${httpsURLConnection.responseCode}")

            if (httpsURLConnection.responseCode != 200){
                return null
            }
            var responseBuffer = StringBuffer()
            var count = 0
            var data = ByteArray(1024*1)

            var inStream = httpsURLConnection.inputStream
            count= inStream.read(data)
            while (count != -1){
                responseBuffer.append(String(data,0,count))
                count = inStream.read(data)
            }
            inStream.close()

            Log.e("tag","${responseBuffer.toString()}")

            var jsonRoot = JSONObject(responseBuffer.toString())

            if (jsonRoot.has("page")){
                Log.e("tag","${jsonRoot.getInt("page")}")
            }

            var jsonUserArray = jsonRoot.getJSONArray("data")
            var users = ArrayList<User>()
            for (i in 0 until jsonUserArray.length()-1){
                var jEachUser = jsonUserArray.getJSONObject(i)
                users.add(
                    User(
                        jEachUser.getInt("id"),
                        jEachUser.getString("email"),
                        jEachUser.getString("first_name"),
                        jEachUser.getString("last_name"),
                        jEachUser.getString("avatar")

                    )
                )
            }
            return users
        }
    }
}