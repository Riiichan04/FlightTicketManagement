package model;

import utilities.FileConverter;
import utilities.FileLoader;
import utilities.JDialogCreator;

public class AddFlight extends FlightStrategy {
    @Override
    public JDialogCreator execute(Flight flight) throws Exception {
        if (!FileConverter.convertFightToTxt(flight) || flight.getPlane().getSeatCount() != flight.getListSeat().size()) {
            return new JDialogCreator("Bạn cần nhập đủ thông tin");
        }
        else {
            this.getListFlight().add(flight);
            return new JDialogCreator("Thêm chuyến bay thành công!");
        }
    }
}
