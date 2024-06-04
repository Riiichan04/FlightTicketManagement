package utilities;

import model.*;

import java.io.*;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public interface FileConverter {
    //Create new File and save it
    static boolean convertFightToTxt(Flight flight) throws Exception {
        File file = new File("src/data/flight/" + flight.getId() + ".txt");
        if (file.createNewFile()) {
            PrintWriter writer = new PrintWriter(file);
            writer.println(flight.getId());
            writer.println(flight.getPlane().getId() + "|" + flight.getPlane().getName() + "|" + flight.getPlane().getBrand() + "|" + flight.getPlane().getWeight() + "|" + flight.getPlane().isLanding());
            writer.println(flight.getRoute().getDeparture() + "|" + flight.getRoute().getArrival());
            writer.println(flight.getDate().getDay() + "|" + flight.getDate().getMonth() + "|" + flight.getDate().getYear());
            writer.println(flight.getPlane().getSeatCount());
            flight.getListSeat().forEach((k, v) -> {
                writer.println(k + "|" + v);
            });
            writer.close();
            return true;
        }
        else return false;
    }

    static void convertAccountToTxt(Account account) throws Exception {
        FileWriter fw = new FileWriter("src/data/account.txt", true);
        fw.write(account.getUsername().trim() + "|" + account.getPassword().trim() + "|" + account.getInfo().getName() +"|" + account.getInfo().getId().trim() + "|"  + "|" + account.getInfo().getPosition().trim() + "|" + account.isChangedUsername() + "\n");
        fw.close();
    }

    static void deleteAccountInTxt(String username) throws Exception {
        File input = new File("src/data/account.txt");
        BufferedReader br = new BufferedReader(new FileReader(input));
        List<String> listLine = new LinkedList<>();
        String currentLine = "";
        while ((currentLine = br.readLine()) != null) {
            System.out.println(currentLine);
            String inputUsername = currentLine.split("\\|")[0];
            if(!inputUsername.equals(username)) listLine.add(currentLine);
        }
        PrintWriter pw = new PrintWriter(input);
        for (String line : listLine) {
            pw.println(line);
        }
        br.close();
        pw.close();
    }

    static boolean deleteFlight(Flight flight) {
        File f = new File("src/data/flight");
        File[] listFile = f.listFiles((dir, name) -> name.startsWith(flight.getId()) && name.endsWith("txt"));
        System.out.println(Arrays.toString(listFile));
        if (listFile == null) return false;
        else {
            try {
                if (!listFile[0].createNewFile()) return listFile[0].delete();
                else return false;
            }
            catch (Exception e) {
                return false;
            }
        }
    }

    static void updateAccount(Account account) throws Exception {
        File input = new File("src/data/account.txt");
        BufferedReader br = new BufferedReader(new FileReader(input));
        List<String> listLine = new LinkedList<>();
        String currentLine = "";
        while ((currentLine = br.readLine()) != null) {
            String inputUsername = currentLine.split("\\|")[3];
            System.out.println(inputUsername);
            if(!inputUsername.equals(account.getInfo().getId())) listLine.add(currentLine);
            else listLine.add(account.getUsername().trim() + "|" + account.getPassword().trim() + "|" + account.getInfo().getName() +"|" + account.getInfo().getId().trim() + "|"  + "|" + account.getInfo().getPosition().trim() + "|" + account.isChangedUsername());
        }
        PrintWriter pw = new PrintWriter(input);
        for (String line : listLine) {
            pw.println(line);
        }
        br.close();
        pw.close();
    }

    static boolean updateFlight(Flight flight) throws Exception {
        File file = new File("src/data/flight/" + flight.getId() + ".txt");
        if (!file.createNewFile()) {
//            file.delete();
            File temp = new File("src/data/flight/" + flight.getId() + ".txt");
            PrintWriter writer = new PrintWriter(temp);
            writer.println(flight.getId());
            writer.println(flight.getPlane().getId() + "|" + flight.getPlane().getName() + "|" + flight.getPlane().getBrand() + "|" + flight.getPlane().getWeight() + "|" + flight.getPlane().isLanding());
            writer.println(flight.getRoute().getDeparture() + "|" + flight.getRoute().getArrival());
            writer.println(flight.getDate().getDay() + "|" + flight.getDate().getMonth() + "|" + flight.getDate().getYear());
            writer.println(flight.getPlane().getSeatCount());
            flight.getListSeat().forEach((k, v) -> {
                if (v == null) {
                    writer.println(k + "|null");
                }
                else writer.println(k + "|" + v);
            });
            writer.close();
            return true;
        }
        else return false;
    }
}
