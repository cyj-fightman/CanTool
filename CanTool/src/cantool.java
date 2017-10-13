import gnu.io.SerialPort;
import serialException.NoSuchPort;
import serialException.NotASerialPort;
import serialException.PortInUse;
import serialException.SerialPortParameterFailure;
import serialException.TooManyListeners;
import serialPort.SerialTool;

public class cantool {
	public static void main(String[] args) throws TooManyListeners, SerialPortParameterFailure, NotASerialPort, NoSuchPort, PortInUse {
		 try{
			 SerialPort serialPort = SerialTool.openPort("COM3", 115200);
			 SerialTool.addListener(serialPort, new SerialListener(serialPort));
		 }catch(Exception e){
			 
			 e.printStackTrace();
		 }finally{
			 
		 }
		
	}
}
