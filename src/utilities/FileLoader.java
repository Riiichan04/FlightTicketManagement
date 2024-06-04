package utilities;

import model.*;
import model.Date;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.*;

public interface FileLoader {
    static Map<String, Account> loadAccount() throws Exception {
        Map<String, Account> listAccount = new HashMap<>();
        File file = new File("src/data/account.txt");
        BufferedReader br = new BufferedReader(new FileReader(file));
        String line = "";
        while ((line = br.readLine()) != null) {
            String[] arr = line.split("\\|");
            switch (arr[4]) {
                case "manager" ->
                        listAccount.put(arr[0], new ManagerAccount(arr[0], arr[1], new Employee(arr[2], arr[3], arr[4]), arr[5].equals("true")));
                case "staff" ->
                        listAccount.put(arr[0], new StaffAccount(arr[0], arr[1], new Employee(arr[2], arr[3], arr[4]), arr[5].equals("true")));
            }
        }
        return listAccount;
    }

    static List<Flight> loadFlight() throws Exception {
        List<Flight> listFlight = new LinkedList<>();
        File f = new File("src/data/flight");
        File[] listFile = f.listFiles();
        if (listFile == null) return null;
        for (File file : listFile) {
            BufferedReader br = new BufferedReader(new FileReader(file));
            listFlight.add(createFlight(br));
        }
        return listFlight;
    }

    static Account findAccount(String username) throws Exception {
        File file = new File("src/data/account.txt");
        BufferedReader br = new BufferedReader(new FileReader(file));
        String line = "";
        while ((line = br.readLine()) != null) {
            String[] arr = line.split("\\|");
            if (arr[0].equals(username)) {
                switch (arr[4]) {
                    case "manager" ->
                            new ManagerAccount(arr[0], arr[1], new Employee(arr[2], arr[3], arr[4]), arr[5].equals("true"));
                    case "staff" ->
                            new StaffAccount(arr[0], arr[1], new Employee(arr[2], arr[3], arr[4]), arr[5].equals("true"));
                }
            }
        }
        return null;
    }

    static Flight findFlight(String id) {
        try {
            File f = new File("src/data/flight");
            File[] listFile = f.listFiles((dir, name) -> name.startsWith(id) && name.endsWith("txt"));
            if (listFile == null) return null;
            else {
                //Create Flight
                BufferedReader br = new BufferedReader(new FileReader(listFile[0]));
                return createFlight(br);
            }
        }
       catch (Exception e) {
            return null;
       }
    }

    static Flight createFlightFromFile(File file) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            return createFlight(br);
        }
        catch (Exception e) {
            System.out.println("Không load chuyến bay được");
            return null;
        }
    }

    static Flight createFlight(BufferedReader br) {
        try {
            //Create Flight
            Flight flight = new Flight();
            //Set Id
            flight.setId(br.readLine());
            String[] planeArr = br.readLine().split("\\|");
            //Set Route
            String[] arr = br.readLine().split("\\|");
            flight.setRoute(new Route(arr[0], arr[1]));
            //Set Date
            arr = br.readLine().split("\\|");
            flight.setDate(new Date(Integer.parseInt(arr[0]), Integer.parseInt(arr[1]), Integer.parseInt(arr[2])));
            //Set Plane
            int seatCount = Integer.parseInt(br.readLine());
            flight.setPlane(new Plane(planeArr[0], planeArr[1], planeArr[2], seatCount, Double.parseDouble(planeArr[3]), planeArr[4].equals("true")));
            //Set ListSeat
            Map<String, String> listSeat = new HashMap<>();
            String line = "";
            while ((line = br.readLine()) != null) {
                String[] arrLine = line.split("\\|");
                if ((arrLine[1].equals("null"))) listSeat.put(arrLine[0], null);
                else listSeat.put(arrLine[0], arrLine[1]);
            }
            flight.setListSeat(listSeat);

            return flight;
        }
        catch (Exception e) {
            return null;
        }
    }

}
