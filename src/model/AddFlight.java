package model;

import utilities.FileConverter;
import utilities.FileLoader;
import utilities.JDialogCreator;

public class AddFlight extends FlightStrategy {
    public AddFlight() {
    }

    @Override
    public JDialogCreator execute(Flight flight, ListFlight listFlight) {
        JDialogCreator result;
        try {
            FileConverter.convertFightToTxt(flight);
            if (flight.getPlane().getSeatCount() != flight.getListSeat().size()) {
                result = new JDialogCreator("Bạn cần nhập đủ thông tin");
                result.setStatus(false);
                return result;
            } else {
                listFlight.getListFlight().add(flight);
                result = new JDialogCreator("Thêm chuyến bay thành công!");
                result.setStatus(true);
                return result;
            }
        } catch (Exception e) {
            result = new JDialogCreator("Bạn cần nhập đủ thông tin");
            result.setStatus(false);
            return result;
        }
    }
}
