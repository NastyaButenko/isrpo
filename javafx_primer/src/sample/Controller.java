package sample;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.TextFieldTableCell;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Controller {
    @FXML
    TableColumn<Robot, String> col1;
    @FXML
    TableColumn<Robot, String> col2;
    @FXML
    TableColumn<Robot, String> col3;
    @FXML
    TableColumn<Robot, String> col4;
    @FXML
    TableColumn<Robot, String> col5;
    @FXML
    private Button randomButton;
    @FXML
    TableView<Robot> tableView;
    @FXML
    private Button buttonStart;
    @FXML
    private Label MaxLabel;
    @FXML
    private Label MinLabel;
    @FXML
    ObservableList<Robot> items = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        tableView.setEditable(true);
        tableView.itemsProperty().setValue(items);
        // Заполняем ObservableList значениями по умолчанию
        for (int i = 0; i < 5; i++) {
            items.add(new Robot());
        }
        //Реализовываем отображение элементов первого столбца, а также их изменение при двойном щелчке
        col1.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getNum1()));
        col1.setCellFactory(TextFieldTableCell.forTableColumn());
        col1.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Robot, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Robot, String> event) {
                ((Robot) event.getTableView().getItems().get(event.getTablePosition().getRow())).setNum1(event.getNewValue());
            }
        });

        //Реализовываем отображение элементов второго столбца, а также их изменение при двойном щелчке
        col2.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getNum2()));
        col2.setCellFactory(TextFieldTableCell.forTableColumn());
        col2.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Robot, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Robot, String> event) {
                ((Robot) event.getTableView().getItems().get(event.getTablePosition().getRow())).setNum2(event.getNewValue());
            }
        });

        //Реализовываем отображение элементов третьего столбца, а также их изменение при двойном щелчке
        col3.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getNum3()));
        col3.setCellFactory(TextFieldTableCell.forTableColumn());
        col3.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Robot, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Robot, String> event) {
                ((Robot) event.getTableView().getItems().get(event.getTablePosition().getRow())).setNum3(event.getNewValue());
            }
        });

        //Реализовываем отображение элементов четвертого столбца, а также их изменение при двойном щелчке
        col4.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getNum4()));
        col4.setCellFactory(TextFieldTableCell.forTableColumn());
        col4.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Robot, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Robot, String> event) {
                ((Robot) event.getTableView().getItems().get(event.getTablePosition().getRow())).setNum4(event.getNewValue());
            }
        });

        //Реализовываем отображение элементов пятого столбца, а также их изменение при двойном щелчке
        col5.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getNum5()));
        col5.setCellFactory(TextFieldTableCell.forTableColumn());
        col5.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Robot, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Robot, String> event) {
                ((Robot) event.getTableView().getItems().get(event.getTablePosition().getRow())).setNum5(event.getNewValue());
            }
        });
    }

    @FXML
    public void randomButton() {
        // Если в таблица уже заполнена, то очищаем ее.
        if (items.size() != 0) {
            for (int i = 0; i < 5; i++) {
                items.remove(0);
            }
        }
        for (int i = 0; i < 5; i++) {
            double num1 = (Math.random() * 201) - 100;
            double num2 = (Math.random() * 201) - 100;
            double num3 = (Math.random() * 201) - 100;
            double num4 = (Math.random() * 201) - 100;
            double num5 = (Math.random() * 201) - 100;
            items.add(new Robot(String.valueOf(num1), String.valueOf(num2), String.valueOf(num3), String.valueOf(num4), String.valueOf(num5)));
        }
        col1.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getNum1()));
        col2.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getNum2()));
        col3.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getNum3()));
        col4.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getNum4()));
        col5.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getNum5()));
    }

    @FXML
    public void buttonStartAction() {
        // Предостерегаем себя от ввода в ячейку строкового значения.
        try {
            // Список для хранения значений ячеек таблицы, а также поиска максимального и минимального значения
            ArrayList<Double> arrNum = new ArrayList<>();

            // Список для хранения отрицательных чисел и их замены на значение по модулю
            HashMap<String, Double> minusHashMap = new HashMap<>();

            // Список для хранения нулевых чисел и их замены на 1
            HashMap<String, Double> zeroHashMap = new HashMap<>();

            // Выполняем поиск и запись отрицательных и нулевых чисел, а также заполняем список arrNum.
            for (int i = 0; i < items.size(); i++) {

                // Выполняем поиск отрицательных чисел и производим их запись в список minusHashMap.
                if (Double.parseDouble(items.get(i).getNum1()) < 0) {
                    minusHashMap.put(String.valueOf(i), Double.parseDouble(items.get(i).getNum1()));
                }
                if (Double.parseDouble(items.get(i).getNum2()) < 0) {
                    minusHashMap.put(String.valueOf(i + "" + i), Double.parseDouble(items.get(i).getNum2()));
                }
                if (Double.parseDouble(items.get(i).getNum3()) < 0) {
                    minusHashMap.put(String.valueOf(i + "" + i + "" + i), Double.parseDouble(items.get(i).getNum3()));
                }
                if (Double.parseDouble(items.get(i).getNum4()) < 0) {
                    minusHashMap.put(i + "" + i + "" + i + "" + i, Double.parseDouble(items.get(i).getNum4()));
                }
                if (Double.parseDouble(items.get(i).getNum5()) < 0) {
                    minusHashMap.put(i + "" + i + "" + i + "" + i + "" + i, Double.parseDouble(items.get(i).getNum5()));
                }

                // Заполняем список arrNum.
                arrNum.add(Double.parseDouble(items.get(i).getNum1()));
                arrNum.add(Double.parseDouble(items.get(i).getNum2()));
                arrNum.add(Double.parseDouble(items.get(i).getNum3()));
                arrNum.add(Double.parseDouble(items.get(i).getNum4()));
                arrNum.add(Double.parseDouble(items.get(i).getNum5()));

                // Выполняем поиск нулей и записываем их в список zeroHashMap.
                if (Double.parseDouble(items.get(i).getNum1()) == 0.0) {
                    zeroHashMap.put(String.valueOf(i), 0.0);
                }
                if (Double.parseDouble(items.get(i).getNum2()) == 0.0) {
                    zeroHashMap.put(String.valueOf(i + "" + i), 0.0);
                }
                if (Double.parseDouble(items.get(i).getNum3()) == 0.0) {
                    zeroHashMap.put(String.valueOf(i + "" + i + "" + i), 0.0);
                }
                if (Double.parseDouble(items.get(i).getNum4()) == 0.0) {
                    zeroHashMap.put(i + "" + i + "" + i + "" + i, 0.0);
                }
                if (Double.parseDouble(items.get(i).getNum4()) == 0.0) {
                    zeroHashMap.put(i + "" + i + "" + i + "" + i + "" + i, 0.0);
                }
            }

            double minNum = arrNum.get(0);
            double maxNum = arrNum.get(0);

            // Выполняем поиск максимального числа.
            for (int i = 0; i < arrNum.size(); i++) {
                if (maxNum <= arrNum.get(i)) {
                    maxNum = arrNum.get(i);
                }
            }

            // Выполняем поиск минимального числа.
            for (int i = 0; i < arrNum.size(); i++) {
                if (minNum >= arrNum.get(i)) {
                    minNum = arrNum.get(i);
                }
            }


            MaxLabel.setText("Максимальный элемент: " + String.valueOf(maxNum));
            MinLabel.setText("Минимальный элемент: " + String.valueOf(minNum));

            if (minNum / 10 == maxNum) {

                // Заменяем все отрицательные числа на значения по модулю.
                for (Map.Entry entry : minusHashMap.entrySet()) {
                    String[] arr = String.valueOf(entry.getKey()).split("");
                    if (arr.length == 1) {
                        items.get(Integer.parseInt(arr[0])).setNum1(String.valueOf(Double.parseDouble(items.get(Integer.parseInt(arr[0])).getNum1()) * (-1)));
                    } else if (arr.length == 2) {
                        items.get(Integer.parseInt(arr[0])).setNum2(String.valueOf(Double.parseDouble(items.get(Integer.parseInt(arr[0])).getNum2()) * (-1)));
                    } else if (arr.length == 3) {
                        items.get(Integer.parseInt(arr[0])).setNum3(String.valueOf(Double.parseDouble(items.get(Integer.parseInt(arr[0])).getNum3()) * (-1)));
                    } else if (arr.length == 4) {
                        items.get(Integer.parseInt(arr[0])).setNum4(String.valueOf(Double.parseDouble(items.get(Integer.parseInt(arr[0])).getNum4()) * (-1)));
                    } else if (arr.length == 5) {
                        items.get(Integer.parseInt(arr[0])).setNum5(String.valueOf(Double.parseDouble(items.get(Integer.parseInt(arr[0])).getNum5()) * (-1)));
                    }
                }

                // Заменяем все нули на единицы.
                for (Map.Entry zeroEntry : zeroHashMap.entrySet()) {
                    String[] zeroArr = String.valueOf(zeroEntry.getKey()).split("");
                    if (zeroArr.length == 1) {
                        items.get(Integer.parseInt(zeroArr[0])).setNum1(String.valueOf(1.0));
                    } else if (zeroArr.length == 2) {
                        items.get(Integer.parseInt(zeroArr[0])).setNum2(String.valueOf(1.0));
                    } else if (zeroArr.length == 3) {
                        items.get(Integer.parseInt(zeroArr[0])).setNum3(String.valueOf(1.0));
                    } else if (zeroArr.length == 4) {
                        items.get(Integer.parseInt(zeroArr[0])).setNum4(String.valueOf(1.0));
                    } else if (zeroArr.length == 5) {
                        items.get(Integer.parseInt(zeroArr[0])).setNum5(String.valueOf(1.0));
                    }
                }
            }
        } catch (Exception e) {
            MaxLabel.setText("Максимальный элемент: ");
            MinLabel.setText("Минимальный элемент: ");
        }
        // Обновляем таблицу.
        tableView.refresh();
    }


}








