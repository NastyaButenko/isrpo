package sample;

import com.sun.glass.ui.View;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.text.DecimalFormat;

public class Controller {

    public TextField inputX;
    public TextField inputA;
    public TextField inputB;
    public Button countButton;
    public Button cleanButton;
    public Button exitButton;
    public TextField resultFiled;
    public Text messageT;

    public void exitApp(ActionEvent actionEvent) {
        System.exit(1);
    }

    public void ClearFields(ActionEvent actionEvent) {
        inputX.clear();
        inputA.clear();
        inputB.clear();
    }

    public void Count(ActionEvent actionEvent) {
        //Вводим данные
        String s1 = inputA.getText();
        String s2 = inputB.getText();
        String s3 = inputX.getText();
        //Присваиваем его переменной класса
        double a = Double.parseDouble(s1);
        double b = Double.parseDouble(s2);
        double x = Double.parseDouble(s3);
//Счет
        double result = 0;
        if (x <= 7 && a == 0 && b == 0) {
            //messageT.setText("При X<=7 A и B  не могут одновременно равняться нулю!");
            messageT.setVisible(true);
            result=0;
        }  else if (x <= 7) {
            result = (x + 4) / a * a + b * b;
        } else if (x > 7) {
            result = x * Math.pow((a + b), 2);
        }
        DecimalFormat df=new DecimalFormat("#.#");

        //Используем - Например вставляем в другое текстовое поле
        resultFiled.setText(df.format(result));
    }
}
