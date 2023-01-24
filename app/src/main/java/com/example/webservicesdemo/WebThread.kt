package com.example.webservicesdemo

import android.os.AsyncTask
import android.os.Message
import java.lang.reflect.InvocationHandler
import android.os.Handler

class WebThread (
    var handler: Handler?
        ):AsyncTask<String,Any?,ArrayList<User>?>() {
    override fun doInBackground(vararg params: String?): ArrayList<User>? {
        return WebUtil.getAllUsers()
    }

    override fun onPostExecute(result: ArrayList<User>?) {
        super.onPostExecute(result)
        var message = Message()
        message.obj = result
        handler?.handleMessage(message)
    }
}