package model;

import utilities.FileConverter;
import utilities.FileLoader;
import utilities.JDialogCreator;

import java.util.List;

public class RemoveFlight extends FlightStrategy {
    public RemoveFlight() throws Exception {
    }

    @Override
    public JDialogCreator execute(Flight flight) throws Exception {
        if (FileConverter.deleteFlight(flight)) {
            return new JDialogCreator("Xóa chuyến bay thành công");
        } else {
            return new JDialogCreator("Không tìm thấy chuyến bay cần xóa");
        }
    }
}
