package com.example.faby.smack.Adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.faby.smack.Model.Message
import com.example.faby.smack.R
import com.example.faby.smack.Services.UserDataService
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*


class MessageAdapter(val context: Context, val messages: ArrayList<Message>) : RecyclerView.Adapter<MessageAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.message_list_view, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return messages.count()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder?.bindMessage(context, messages[position])
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val userImage = itemView?.findViewById<ImageView>(R.id.messageUserImage)
        val timeStamp = itemView?.findViewById<TextView>(R.id.timeStampLabel)
        val userName = itemView?.findViewById<TextView>(R.id.messageUserNameLabel)
        val messageBody = itemView?.findViewById<TextView>(R.id.messageBodyLabel)

        fun bindMessage(context: Context, message: Message) {
            val resourceId = context.resources.getIdentifier(message.userAvatar, "drawable", context.packageName)
            userImage?.setImageResource(resourceId)
            userImage?.setBackgroundColor(UserDataService.returnAvatarColor(message.userAvatarColor))
            userName?.text = message.userName
            timeStamp?.text = returnDateString(message.timeStamp)
            messageBody?.text = message.message
        }

        fun returnDateString(isoString: String) : String {

            //2018-09-12T16:29:53.390Z

            val isoFormatter = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault())
            isoFormatter.timeZone = TimeZone.getTimeZone("UTC")
            var convertedDate = Date()

            try{
                convertedDate = isoFormatter.parse(isoString)

            } catch(e: ParseException){
                Log.d("PARSE", "Cannot parse date")
            }

            val outDateString = SimpleDateFormat("E, MMM d yyyy '-' h:mm a", Locale.getDefault())
            return outDateString.format(convertedDate)
        }
    }

}