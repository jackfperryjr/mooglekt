package com.jackperryjr.mooglekt

import android.widget.TextView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.view.View
import android.content.Context

class MessageAdapter(internal var context: Context, private val messages: ArrayList<Message>) : BaseAdapter() {

    private val inflater: LayoutInflater
            = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    fun add(message: Message) {
        this.messages.add(message)
        notifyDataSetChanged()
    }

    override fun getCount(): Int {
        return messages.size
    }

    override fun getItem(position: Int): Any {
        return messages[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var convertView = convertView
        val holder = MessageViewHolder()
        val message = messages[position]

       if (message.isUser) { // this message was sent by us so let's create a basic chat bubble on the right
            convertView = inflater.inflate(R.layout.user_message, null)
            holder.messageBody = convertView.findViewById(R.id.message_body)
            convertView.setTag(holder)
            holder.messageBody!!.text = message.text
        } else { // this message was sent by someone else so let's create an advanced chat bubble on the left
            convertView = inflater.inflate(R.layout.their_message, null)
            holder.messageBody = convertView.findViewById(R.id.message_body)
            convertView.setTag(holder)
            holder.messageBody!!.text = message.text
        }

        return convertView
    }
}

internal class MessageViewHolder {
    var messageBody: TextView? = null
}