package at.ac.fhcampuswien.fhmdb;

import at.ac.fhcampuswien.fhmdb.models.Movie;
import at.ac.fhcampuswien.fhmdb.ui.MovieCell;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXListView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class HomeController implements Initializable {
    @FXML
    public JFXButton searchBtn;

    @FXML
    public TextField searchField;

    @FXML
    public JFXListView movieListView;

    @FXML
    public JFXComboBox genreComboBox;

    @FXML
    public JFXButton sortBtn;

    public List <Movie> allMovies = Movie.initializeMovies();

    private final ObservableList <Movie> observableMovies = FXCollections.observableArrayList();   // automatically updates corresponding UI elements when underlying data changes
    private final ObservableList <Movie.Genre> observableGenre = FXCollections.observableArrayList(Movie.Genre.values());

    int buttonClicks = 0;

    private List <Movie> filteredMovies = new ArrayList<>();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        observableMovies.addAll(allMovies);         // add dummy data to observable list

        // initialize UI stuff
        movieListView.setItems(observableMovies);   // set data of observable list to list view
        movieListView.setCellFactory(movieListView -> new MovieCell()); // use custom cell factory to display data

        // TODO add genre filter items with genreComboBox.getItems().addAll(...)
        genreComboBox.setPromptText("Filter by Genre");
        genreComboBox.getItems().addAll(observableGenre);

        // TODO add event handlers to buttons and call the regarding methods
        // either set event handlers in the fxml file (onAction) or add them here
        searchBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                buttonClicks++;
                if(buttonClicks == 1){
                    if(genreComboBox.getValue() != null && searchField.getText().isEmpty()){
                        getMovies(genreComboBox.getValue());
                    } else if(!searchField.getText().isEmpty() && genreComboBox.getValue() == null){
                        getMovies(searchField.getText());
                    }  else {
                        getMovies(genreComboBox.getValue(), searchField.getText());
                    }
                    for(Movie movie: filteredMovies){ //TODO: Remove (for testing only)
                        System.out.println(movie.getTitle());
                    }
                } else if (buttonClicks == 2){
                    genreComboBox.setValue(null);
                    searchField.clear();
                    filteredMovies.clear();
                    buttonClicks = 0;
                }
            }
        });

        // Sort button example:
        sortBtn.setOnAction(actionEvent -> {
            if(sortBtn.getText().equals("Sort (asc)")) {
                // TODO sort observableMovies ascending
                //observableMovies.sort(Object, Comparator.compareSomething(movie.....)
                sortBtn.setText("Sort (desc)");
            } else {
                // TODO sort observableMovies descending
                sortBtn.setText("Sort (asc)");
            }
        });
    }

    public List <Movie> getMovies(Object object){
        for(Movie movie: observableMovies){
            for(int i = 0; i < movie.getListGenres().size(); i++){
                if(movie.getListGenres().get(i).equals(object) && !filteredMovies.contains(movie)){
                    filteredMovies.add(movie);
                }
            }
        }
        return filteredMovies;
    }

    public List <Movie> getMovies(String string){
        for(Movie movie: observableMovies){
            if(movie.getTitle().toLowerCase().contains(string.toLowerCase()) || movie.getDescription().toLowerCase().contains(string.toLowerCase())){
                filteredMovies.add(movie);
            }
        }
        return filteredMovies;
    }

    public List <Movie> getMovies(Object object, String string){
        getMovies(object);
        for(int i = 0; i < filteredMovies.size(); i++){
            if (!filteredMovies.get(i).getTitle().toLowerCase().contains(string.toLowerCase()) && !filteredMovies.get(i).getDescription().toLowerCase().contains(string.toLowerCase())) {
                filteredMovies.remove(i);
                i--;
            }
        }
        return filteredMovies;
    }
}