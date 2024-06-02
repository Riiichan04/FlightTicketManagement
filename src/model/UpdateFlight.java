package model;

import utilities.FileConverter;
import utilities.JDialogCreator;

import java.util.List;

public class UpdateFlight extends FlightStrategy{
	@Override
	public JDialogCreator execute(Flight flight) throws Exception {
        if (!FileConverter.convertFightToTxt(flight) || flight.getPlane().getSeatCount() != flight.getListSeat().size()) {
            return new JDialogCreator("Bạn cần nhập đủ thông tin");
        }
        else {
            this.getListFlight().add(flight);
            FileConverter.updateFlight(flight);
            return new JDialogCreator("Cập nhật chuyến bay thành công!");
        }
	}
}
