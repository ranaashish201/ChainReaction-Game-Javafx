
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;

public class  MyController implements Initializable
{

public ComboBox myCombobox;

public void initialize1(URL url, ResourceBundle rb) {
}

public void setData(){

myCombobox.getItems().clear();

myCombobox.getItems().addAll(
           "3 Player Game",
           "4 Player Game","5 Player Game",
           "6 Player Game","7 Player Game",
           "8 Player Game");

}

@Override
public void initialize(URL location, ResourceBundle resources) {
	// TODO Auto-generated method stub
	
}
}