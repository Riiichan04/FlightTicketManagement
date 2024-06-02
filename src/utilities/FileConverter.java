package utilities;

import model.*;

import java.io.*;

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
            flight.getListSeat().forEach((k, v) -> writer.println(k + "|" + v));
            writer.close();
            return true;
        }
        else return false;
    }

    static void convertAccountToTxt(Account account) throws Exception {
        FileWriter fw = new FileWriter("src/data/account.txt", true);
        fw.write(account.getUsername() + "|" + account.getPassword() + "|" + account.getInfo().getId() + "|" + account.getInfo().getName() + "|" + account.getInfo().getPosition() + "|" + account.isChangedUsername() + "\n");
        fw.close();
    }

    static void deleteAccountInTxt(String username) throws Exception {
        File input = new File("src/data/account.txt");
        File temp = new File("src/data/tempAccount.txt");
        BufferedReader br = new BufferedReader(new FileReader(input));
        PrintWriter pw = new PrintWriter(temp);
        String currentLine = "";
        while ((currentLine = br.readLine()) != null) {
            String inputUsername = currentLine.split("\\|")[0];
            if (!inputUsername.equals(username)) pw.println(currentLine);
        }
        br.close();
        pw.close();
        boolean success = temp.renameTo(input);
    }

    static boolean deleteFlight(Flight flight) {
        File f = new File("src/data/flight");
        File[] listFile = f.listFiles((dir, name) -> name.startsWith(flight.getId()) && name.endsWith("txt"));
        if (listFile == null) return false;
        else {
            return listFile[0].delete();
        }
    }

    static void updateAccount(Account account) throws Exception {
        //Get input file and temp file
        File input = new File("src/data/account.txt");
        File temp = new File("src/data/tempAccount.txt");
        //Call new Reader and Writer
        BufferedReader br = new BufferedReader(new FileReader(input));
        PrintWriter pw = new PrintWriter(temp);
        String currentLine = "";
        //Read input file
        while ((currentLine = br.readLine()) != null) {
            //Get employee id of account
            String inputUsername = currentLine.split("\\|")[2];
            //If equals with input account's employee id
            if (inputUsername.equals(account.getInfo().getId())) {
                //Write with new info
                pw.println(account.getUsername() + "|" + account.getPassword() + "|" + account.getInfo().getId() + "|" + account.getInfo().getName() + "|" + account.getInfo().getPosition() + "|" + account.isChangedUsername());
            }
            //If not, just write current info
            else pw.println(currentLine);
        }
        br.close();
        pw.close();
        //Rename temp file to input file
        boolean success = temp.renameTo(input);
    }

    static boolean updateFlight(Flight flight) throws Exception {
        File file = new File("src/data/flight/" + flight.getId() + ".txt");
        if (!file.createNewFile()) {
            File temp = new File("src/data/flight/temp" + flight.getId() + ".txt");
            PrintWriter writer = new PrintWriter(temp);
            writer.println(flight.getId());
            writer.println(flight.getPlane().getId() + "|" + flight.getPlane().getName() + "|" + flight.getPlane().getBrand() + "|" + flight.getPlane().getWeight() + "|" + flight.getPlane().isLanding());
            writer.println(flight.getRoute().getDeparture() + "|" + flight.getRoute().getArrival());
            writer.println(flight.getDate().getDay() + "|" + flight.getDate().getMonth() + "|" + flight.getDate().getYear());
            writer.println(flight.getPlane().getSeatCount());
            flight.getListSeat().forEach((k, v) -> writer.println(k + "|" + v));
            writer.close();
            return file.renameTo(temp);
        }
        else return false;
    }
}
