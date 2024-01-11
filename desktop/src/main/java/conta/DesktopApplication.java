package conta;

import conta.javafx.AdaptadorJavaFx;
import javafx.application.Application;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DesktopApplication {

    public static void main(String[] args) {
        Application.launch(AdaptadorJavaFx.class, args);
    }
}
