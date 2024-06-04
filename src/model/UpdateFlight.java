package model;

import utilities.FileConverter;
import utilities.JDialogCreator;

import java.util.Arrays;
import java.util.List;

public class UpdateFlight extends FlightStrategy {
    public UpdateFlight() {
    }

    @Override
    public JDialogCreator execute(Flight flight, ListFlight listFlight) {
        JDialogCreator result;
        try {
            FileConverter.convertFightToTxt(flight);
            if (flight.getPlane().getSeatCount() != flight.getListSeat().size()) {
                result = new JDialogCreator("Bạn cần nhập đủ thông tin");
                return result;
            }
            Flight currentFlight = listFlight.getListFlight().stream().filter(obj -> obj.getId().equals(flight.getId())).toList().getFirst();
            listFlight.getListFlight().set(listFlight.getListFlight().indexOf(currentFlight), flight);
            if (FileConverter.updateFlight(flight)) {
                result = new JDialogCreator("Cập nhật chuyến bay thành công!");
                result.setStatus(true);
                return result;
            }
            else {
                result = new JDialogCreator("Mã chuyên bay không tồn tại");
                return result;
            }
        } catch (Exception e) {
            result = new JDialogCreator("Bạn cần nhập đủ thông tin");
            return result;
        }
    }
}
