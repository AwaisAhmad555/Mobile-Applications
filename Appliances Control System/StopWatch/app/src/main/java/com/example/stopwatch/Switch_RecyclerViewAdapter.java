package com.example.stopwatch;

import android.app.AlertDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.UUID;

public class Switch_RecyclerViewAdapter extends RecyclerView.Adapter<Switch_RecyclerViewAdapter.MyViewHolder> {


    public String address = "initial value address" , name = "initial value name" ;

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }



    public String getAddress() {
        return address;
    }



    public String getName() {
        return name;
    }




    public BluetoothAdapter bluetoothAdapter = null;
    public BluetoothSocket bluetoothSocket = null;

    public Set<BluetoothDevice> pairedDevices;

    public static final UUID myUUID = UUID.fromString("00001101-0000-1000-8000-0805F9B34FB");



    public Switch mySwitch;

    public String SwitchIndex;

    public void setMySwitch(Switch mySwitch) {
        this.mySwitch = mySwitch;
    }

    public Switch getMySwitch() {
        return mySwitch;
    }

    public String getSwitchIndex() {
        return SwitchIndex;
    }

    public void setSwitchIndex(String switchIndex) {
        SwitchIndex = switchIndex;
    }






    public Context switch_activity_context;
    public List<switch_recycler_items> switch_list;


    public Switch_RecyclerViewAdapter(Context switch_activity_context, List<switch_recycler_items> switch_list) {
        this.switch_activity_context = switch_activity_context;
        this.switch_list = switch_list;

    }




    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;

        LayoutInflater inflater = LayoutInflater.from(switch_activity_context);

        view = inflater.inflate(R.layout.activity_switch_items,parent,false);


        return new Switch_RecyclerViewAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, int position) {

        String result = switch_list.get(position).getAppliance_name().substring(switch_list.get(position).getAppliance_name().length() - 1, switch_list.get(position).getAppliance_name().length() + 0);

        //checking s character in last to remove. for this substring(first_index, last_index) is used and
        // substring is stored in result variable.

        //holder.Appliances_Name.setText(switch_list.get(position).getAppliance_name());

        if (result.equals("s")) {

        holder.Appliances_Name.setText(switch_list.get(position).getAppliance_name().replace(result,""));

         }

        else {

            holder.Appliances_Name.setText(switch_list.get(position).getAppliance_name());

        }

        holder.Appliances_Index.setText(switch_list.get(position).getIndex());


        final String index_number = switch_list.get(position).getIndex();

        final Switch newswitch = (Switch) holder.aSwitch;

        newswitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Toast.makeText(switch_activity_context,"clicked",Toast.LENGTH_SHORT).show();

                if(newswitch.isChecked()){

                    AlertDialog.Builder message = new AlertDialog.Builder(switch_activity_context);

                    message.setMessage(holder.Appliances_Name.getText() + " " + index_number + " has been turned ON !");
                    message.setNegativeButton("ok",null);
                    message.create();
                    message.show();

                    if(index_number == Integer.toString(1)) {

                        led_on_off("a");

                    }

                    if(index_number == Integer.toString(2)) {

                        led_on_off("c");

                    }
                }

                else {

                    AlertDialog.Builder message = new AlertDialog.Builder(switch_activity_context);

                    message.setMessage(holder.Appliances_Name.getText() + " " + index_number + " has been turned OFF !");
                    message.setNegativeButton("ok", null);
                    message.create();
                    message.show();

                    if(index_number == Integer.toString(1)) {

                    led_on_off("b");

                }
                    if(index_number == Integer.toString(2)) {

                        led_on_off("d");

                    }


                }


            }
        });



        setSwitchIndex(index_number);

        setMySwitch(holder.aSwitch);

        try {

          //  bluetoothAdapterFunction();

        }


        catch (Exception e) {}


    }

    public void bluetoothAdapterFunction() throws IOException {

        final Switch newSwitch = (Switch) getMySwitch(); //getting switch from OnbindViewholder function through variable getter function
        final String switch_index_number = getSwitchIndex();//getting switch index from OnbindViewHolder using getter function

        bluetooth_connect_device();


        newSwitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



            }

        });


    }

    public void led_on_off(String f) {

        try{

            if(bluetoothSocket != null){

                bluetoothSocket.getOutputStream().write(f.toString().getBytes());

                Toast.makeText(switch_activity_context,"Signal Successfully sent",Toast.LENGTH_SHORT).show();


            }

        }
        catch (Exception e){

            Toast.makeText(switch_activity_context,e.getMessage(),Toast.LENGTH_SHORT).show();

        }


    }


    public void bluetooth_connect_device() throws IOException {

        try {
            bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
            address = bluetoothAdapter.getAddress();
            name = bluetoothAdapter.getName();

            pairedDevices = bluetoothAdapter.getBondedDevices();

            if (pairedDevices.size() > 0) {

                for (BluetoothDevice bt : pairedDevices) {

                    address = bt.getAddress().toString();
                    name = bt.getName().toString();

                   // setName(name);
                   // setAddress(address);

                    Toast.makeText(switch_activity_context, "Connected",Toast.LENGTH_SHORT).show();

                }


            }

            /*

            AlertDialog.Builder message = new AlertDialog.Builder(switch_activity_context);

            message.setMessage("Connection Success" + "Try portion is Working");
            message.setNegativeButton("OK",null);
            message.create();
            message.show();

             */

        }

        catch (Exception we){

            /*


            AlertDialog.Builder message = new AlertDialog.Builder(switch_activity_context);

            message.setMessage("Connection Failed");
            message.setNegativeButton("OK",null);
            message.create();
            message.show();

            */



        }

        try {

            //bluetoothSocket = null;

            bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
            BluetoothDevice device = bluetoothAdapter.getRemoteDevice(address);

            bluetoothSocket = device.createInsecureRfcommSocketToServiceRecord(myUUID);
            bluetoothSocket.connect();
        }

        catch (Exception e){

         /*   Toast.makeText(switch_activity_context,e.getMessage(),Toast.LENGTH_SHORT).show();

            AlertDialog.Builder message = new AlertDialog.Builder(switch_activity_context);

            message.setMessage("Connection Failed\n" + "Error : " + e.getMessage());
            message.setNegativeButton("OK",null);
            message.create();
            message.show();

          */

            bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
            BluetoothDevice device = bluetoothAdapter.getRemoteDevice(address);

            bluetoothSocket = device.createInsecureRfcommSocketToServiceRecord(myUUID);
            bluetoothSocket.connect();

        }


            try {


                AlertDialog.Builder message = new AlertDialog.Builder(switch_activity_context);

                message.setMessage("Connection Succesfull ! \n\n" + "Device Name : " + name + "\nAddress : "+ address);
                message.setNegativeButton("OK",null);
                message.create();
                message.show();



            }

            catch (Exception e){

                AlertDialog.Builder message = new AlertDialog.Builder(switch_activity_context);

                message.setMessage("Connection Failed ! \n\n" + "Device Name : " + name + "\n Address : "+ address);
                message.setNegativeButton("OK",null);
                message.create();
                message.show();


            }


    }




    @Override
    public int getItemCount() {
        return switch_list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView Appliances_Name;
        TextView Appliances_Index;
        Switch aSwitch;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            Appliances_Name = (TextView) itemView.findViewById(R.id.appliances_text1);
            Appliances_Index = (TextView) itemView.findViewById(R.id.appliances_index);
            aSwitch = (Switch) itemView.findViewById(R.id.Switch1);




        }
    }




  public String outName() throws IOException{

      try {

          bluetoothAdapter = null;
          bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
          address = bluetoothAdapter.getAddress();
          name = bluetoothAdapter.getName();

          pairedDevices = bluetoothAdapter.getBondedDevices();

          if (pairedDevices.size() > 0) {

              for (BluetoothDevice bt : pairedDevices) {

                  address = bt.getAddress().toString();
                  name = bt.getName().toString();

                 // setName(name);
                 // setAddress(address);


            //      Toast.makeText(switch_activity_context, "Connected",Toast.LENGTH_SHORT).show();

              }


          }

      }

      catch (Exception we){}

       //   bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
         // BluetoothDevice device = bluetoothAdapter.getRemoteDevice(address);

         // bluetoothSocket = device.createInsecureRfcommSocketToServiceRecord(myUUID);
         // bluetoothSocket.connect();



          try {

              //String Name = name;

          }

          catch (Exception e){

             // String Name = name;

          }



      String Name = name;

      return Name;

  }


    public String outAddress() throws IOException{

        try {

            bluetoothAdapter = null;
            bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
            address = bluetoothAdapter.getAddress();
            name = bluetoothAdapter.getName();

            pairedDevices = bluetoothAdapter.getBondedDevices();

            if (pairedDevices.size() > 0) {

                for (BluetoothDevice bt : pairedDevices) {

                    address = bt.getAddress().toString();
                    name = bt.getName().toString();

                  //  setName(name);
                  //  setAddress(address);

                }


            }

        }

        catch (Exception we){ }

        //    bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
       //     BluetoothDevice device = bluetoothAdapter.getRemoteDevice(address);

        //    bluetoothSocket = device.createInsecureRfcommSocketToServiceRecord(myUUID);
       //     bluetoothSocket.connect();



            try {

              //  String Address = address;

            }

            catch (Exception e){

               // String Address = address;

            }





      String Address = address;

      return Address;
    }


}
