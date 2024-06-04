package model;

import utilities.FileConverter;
import utilities.FileLoader;
import utilities.JDialogCreator;

import java.util.List;

public class RemoveFlight extends FlightStrategy {
    public RemoveFlight() {
    }

    @Override
    public JDialogCreator execute(Flight flight, ListFlight listFlight) {
        JDialogCreator result;
        if (FileConverter.deleteFlight(flight)) {
            listFlight.getListFlight().remove(flight);
            result = new JDialogCreator("Xóa chuyến bay thành công");
            result.setStatus(true);
            return result;
        } else {
            result = new JDialogCreator("Không tìm thấy chuyến bay cần xóa");
            return result;
        }
    }
}
