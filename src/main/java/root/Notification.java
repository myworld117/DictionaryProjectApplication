package root;

import javafx.scene.control.Alert;

public class Notification {
        public void forInfo(String title, String mainContent) {
            Alert notice = new Alert(Alert.AlertType.INFORMATION);
            notice.setHeaderText("Thông báo");
            notice.setContentText(mainContent);
            notice.setTitle(title);
            notice.showAndWait();
        }

    public void forWarning(String title, String mainContent) {
        Alert notice = new Alert(Alert.AlertType.WARNING);
        notice.setHeaderText("Thông báo");
        notice.setContentText(mainContent);
        notice.setTitle(title);
        notice.showAndWait();
    }

    public void forCofirm(String title, String mainContent) {
        Alert notice = new Alert(Alert.AlertType.CONFIRMATION);
        notice.setHeaderText("Thông báo");
        notice.setContentText(mainContent);
        notice.setTitle(title);
        notice.showAndWait();
    }
}
