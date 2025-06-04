package Controlador;

import com.fazecast.jSerialComm.*;

public class Arduino{

public void Arduino() {
        SerialPort puerto = SerialPort.getCommPort("COM4");
        puerto.setBaudRate(9600);
        puerto.setNumDataBits(8);
        puerto.setNumStopBits(1);
        puerto.setParity(SerialPort.NO_PARITY);

        if (puerto.openPort()) {
            System.out.println("Puerto abierto correctamente.");
            
        } else {
            System.out.println("Error al abrir el puerto.");
            return;
        }

        puerto.addDataListener(new SerialPortDataListener() {
            @Override
            public int getListeningEvents() {
                return SerialPort.LISTENING_EVENT_DATA_AVAILABLE;
            }
            
            @Override
            public void serialEvent(SerialPortEvent event) {
                if (event.getEventType() != SerialPort.LISTENING_EVENT_DATA_AVAILABLE) return;
                byte[] buffer = new byte[puerto.bytesAvailable()];
                puerto.readBytes(buffer, buffer.length);
                String mensaje = new String(buffer).trim();
                System.out.println("Dato recibido: " + mensaje);
                
                try {
                    
                } catch (NumberFormatException ex) {
                    System.out.println("Error al parsear el valor");
                }
            }
            
            
        });
    }
}