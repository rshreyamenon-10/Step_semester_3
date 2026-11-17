import java.util.HashMap;
import java.util.Map;

public class SmartHomePlatform {

    static abstract class Device {
        String id;
        boolean isOn = false;

        Device(String id) {
            this.id = id;
        }

        void turnOn() {
            isOn = true;
            System.out.println(id + " is ON");
        }

        void turnOff() {
            isOn = false;
            System.out.println(id + " is OFF");
        }

        void dim(int value) {
            System.out.println(id + " rejected: DIM unsupported");
        }

        void schedule(String time) {
            System.out.println(id + " rejected: SCHEDULE unsupported");
        }

        void energy() {
            System.out.println(id + " rejected: ENERGY unsupported");
        }
    }

    static class Light extends Device {
        Light(String id) {
            super(id);
        }

        @Override
        void dim(int value) {
            System.out.println(id + " dimmed to " + value);
        }

        @Override
        void schedule(String time) {
            System.out.println(id + " scheduled " + time);
        }
    }

    static class Fan extends Device {
        Fan(String id) {
            super(id);
        }

        @Override
        void schedule(String time) {
            System.out.println(id + " scheduled " + time);
        }
    }

    static class Plug extends Device {
        int energyVal;

        Plug(String id, int energyVal) {
            super(id);
            this.energyVal = energyVal;
        }

        @Override
        void energy() {
            System.out.println(id + " energy " + energyVal + " kWh");
        }
    }

    public static void main(String[] args) {
        Map<String, Device> devices = new HashMap<>();
        devices.put("L1", new Light("L1"));
        devices.put("F1", new Fan("F1"));
        devices.put("P1", new Plug("P1", 12));

        String[][] commands = {
            {"ON", "L1"},
            {"DIM", "L1", "40"},
            {"DIM", "F1", "30"},
            {"SCHEDULE", "F1", "22:00"},
            {"ENERGY", "P1"},
            {"ENERGY", "L1"}
        };

        for (String[] cmd : commands) {
            String action = cmd[0];
            String deviceId = cmd[1];
            Device d = devices.get(deviceId);
            if (d == null) continue;

            if (action.equals("ON")) {
                d.turnOn();
            } else if (action.equals("OFF")) {
                d.turnOff();
            } else if (action.equals("DIM")) {
                int val = Integer.parseInt(cmd[2]);
                d.dim(val);
            } else if (action.equals("SCHEDULE")) {
                String time = cmd[2];
                d.schedule(time);
            } else if (action.equals("ENERGY")) {
                d.energy();
            }
        }
    }
}
