package model;

import utilities.JDialogCreator;

public interface AccountCommand {
    JDialogCreator execute(ListAccount listAccount);
}
