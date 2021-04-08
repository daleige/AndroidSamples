package com.cyq.bluetooth

import android.bluetooth.BluetoothDevice
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.cyq.bluetooth.MyAdapter.MyViewHolder

/**
 * @author chenyq113@midea.com
 * @describe 蓝牙列表适配器
 * @time 2021/4/2 15:56
 */
class MyAdapter(private val mList: List<BluetoothDevice>) : RecyclerView.Adapter<MyViewHolder>() {

    var itemClickListener: OnItemClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_ble_device_name, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        if (!TextUtils.isEmpty(mList[position].name)) {
            holder.mDeviceName.text = mList[position].name
        } else {
            holder.mDeviceAddress.text = "未知设备名"
        }
        holder.mDeviceAddress.text = mList[position].address
        holder.itemView.setOnClickListener {
            itemClickListener?.onItemClick(position, holder.itemView)
        }
    }

    override fun getItemCount(): Int {
        return mList.size
    }

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val mDeviceName: TextView = itemView.findViewById(R.id.tvDeviceName)
        val mDeviceAddress: TextView = itemView.findViewById(R.id.tvDeviceAddress)
    }

    interface OnItemClickListener{
        fun onItemClick(position: Int, view: View)
    }
}