package teste;

import conta.javafx.tela.TransferenciaFrm;
import conta.javafx.utils.PropertiesUtils;
import javafx.scene.Node;
import javafx.scene.control.DialogPane;
import javafx.stage.Stage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

@ExtendWith(ApplicationExtension.class)
public class JavaFxTest {

    @Start
    public void start(Stage stage) throws ClassNotFoundException {
        System.getProperties().setProperty("spring.profiles.active", "test");
        String build = (String) PropertiesUtils.loadProperties().get("build");
        Class<?> classBuild = Class.forName(build);

        String[] args = new String[0];
        ApplicationContext spring = new SpringApplicationBuilder()
                .sources(classBuild)
                .run(args);
        var form = spring.getBean(TransferenciaFrm.class);
        form.mostrar(stage);
    }

    @Test
    public void contextLoads() {
    }

    @Test
    public void validarTransferencia(FxRobot robot){
        robot.clickOn("#tfDebito");
        robot.write("10");

        robot.clickOn("#tfCredito");
        robot.write("20");

        robot.clickOn("#tfValor");
        robot.write("20.00", 5);

        robot.clickOn("#bt");

        Node dialogPane = robot.lookup("#alert").query();
        Assertions.assertEquals(((DialogPane) dialogPane).getContentText(), "Transferencia feita com sucesso!");

        //verifyThat("Transferencia feita com sucesso!", NodeMatchers.isVisible());
    }

}
