import javax.swing.*;
import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;

public class Movies {
    String title, plot, posterUrl, movieUrl;
    int movieIdInDataset, movieIdInTMDB, yearReleased;
    static ArrayList<Movies> movieList = new ArrayList<>();
    static ArrayList<Integer> movieIdList = new ArrayList<>();
    Movies() {
        movieIdListInitialization();
        movieRetrieveFromWeb();
        appendMovieObject();
    }

    Movies(String title, int movieIdInDataset, int yearReleased, int movieIdInTMDB, String plot, String posterUrl, String movieUrl) {
        this.title = title;
        this.movieIdInDataset = movieIdInDataset;
        this.yearReleased = yearReleased;
        this.movieIdInTMDB = movieIdInTMDB;
        this.plot = plot;
        this.posterUrl = posterUrl;
        this.movieUrl = movieUrl;
    }

    /*
    public static void main(String[] args) {
        new Movies();
        int count = 0;
        for (Movies movie: movieList) {
            if (movie.posterUrl.equals("null")) {
                count ++;
                System.out.println(movie.movieIdInTMDB);
            }
        }
        System.out.println(count);
    }

    static void replaceChar() {
        String line;
        ArrayList<String> tempList = new ArrayList<>();
        try {
            BufferedReader file = new BufferedReader(new FileReader("textfile/Movies.txt"));
            while ((line = file.readLine()) != null) {
                tempList.add(line.replaceAll(";", "§"));
            }
            file.close();

            BufferedWriter file2 = new BufferedWriter(new FileWriter("textfile/Movies.txt"));
            for (String line2: tempList) {
                file2.write(line2);
                file2.newLine();
            }
            file2.close();
        } catch (IOException ex) {
            System.out.println("System error.");
        }
    }

     */

    static void movieIdListInitialization() {
        try {
            BufferedReader file = new BufferedReader(new FileReader("textfile/Movies.txt"));
            String line;
            while ((line = file.readLine()) != null) {
                line = line.replaceAll("\"", "");
                String[] lineComponents = line.split("§");
                movieIdList.add(Integer.parseInt(lineComponents[0]));
            }
            file.close();
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "IO error while initializing list. Please inspect source files.");
        }
    }

    static void movieRetrieveFromWeb() {
        try {
            String lineInFile;
            BufferedReader movieFile = new BufferedReader(new FileReader("textfile/Movies.csv"));
            BufferedWriter movieTextFile = new BufferedWriter(new FileWriter("textfile/Movies.txt", true));
            while ((lineInFile = movieFile.readLine()) != null) {
                String[] lineArray = lineInFile.split("\";\"");
                for (int i = 0; i < lineArray.length; i ++) {
                    lineArray[i] = lineArray[i].replaceAll("\"", "");
                }
                if (!movieIdList.contains(Integer.parseInt(lineArray[0]))) {
                    int movieId = Integer.parseInt(lineArray[4]);
                    String newMovieInfo = lineArray[0] + "§" + lineArray[1] + "§" + lineArray[2] + "§" + movieId + "§"
                            + plotRetrieve(movieId) + "§" + posterRetrieve(movieId) + "§" + movieRetrieve(movieId);
                    movieTextFile.append(newMovieInfo);
                    movieTextFile.newLine();
                }
            }
            movieFile.close();
            movieTextFile.close();
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "IO Operation Error while retrieving movie information. Please inspect system.");
        }
    }

    static void appendMovieObject() {
        try {
            BufferedReader readMovieTextFile = new BufferedReader(new FileReader("textfile/Movies.txt"));
            String line;
            while ((line = readMovieTextFile.readLine()) != null) {
                String[] lineArray = line.split("§");
                Movies movie = new Movies(lineArray[1], Integer.parseInt(lineArray[0]), Integer.parseInt(lineArray[2]), Integer.parseInt(lineArray[3]),
                        lineArray[4], lineArray[5], lineArray[6]);
                movieList.add(movie);
            }
            readMovieTextFile.close();
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "IO error while creating objects. Please inspect system.");
        }
    }

    // Code from: https://developer.themoviedb.org/reference/movie-images (with modification)
    static String plotRetrieve(int TMDBid) {
        String plot = null;
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://api.themoviedb.org/3/movie/" + TMDBid + "?language=en-US"))
                    .header("accept", "application/json")
                    .header("Authorization", "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIxMzQ0ZGY4YTcwYjdlY2Q1NzI1Y2NkMjQwMzY3OTFkMSIsInN1YiI6IjY1ZGM5MDY0MDc1Mjg4MDE3ZGI0MGIwNSIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.9mRXh5UFdS8_qVcRhVImdKd1Cm50Hio4aGA-TOcKZxg")
                    .method("GET", HttpRequest.BodyPublishers.noBody())
                    .build();
            HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
            StringBuilder output = new StringBuilder(response.body());
            int indexOfOverview = output.indexOf("\"overview\":\"") + 12;
            if (indexOfOverview - 12 == -1) {
                return null;
            } else {
                output.delete(0, indexOfOverview);
                int indexOfLastChar = output.indexOf("\"");
                output.delete(indexOfLastChar, output.length());
                plot = output.toString();
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error in retrieving movie. Please inspect " + TMDBid);
        }
        return plot;
    }

    static String returnPosterResponse(int TMDBid) {
        String responseText = null;
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://api.themoviedb.org/3/movie/" + TMDBid + "/images"))
                    .header("accept", "application/json")
                    .header("Authorization", "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIxMzQ0ZGY4YTcwYjdlY2Q1NzI1Y2NkMjQwMzY3OTFkMSIsInN1YiI6IjY1ZGM5MDY0MDc1Mjg4MDE3ZGI0MGIwNSIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.9mRXh5UFdS8_qVcRhVImdKd1Cm50Hio4aGA-TOcKZxg")
                    .method("GET", HttpRequest.BodyPublishers.noBody())
                    .build();
            HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
            responseText = response.body();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Cannot retrieve poster. Please inspect movie " + TMDBid);
        }
        return responseText;
    }

    static String posterRetrieve(int TMDBid) {
        String posterLink = null;
        try {
            String output = returnPosterResponse(TMDBid);
            URI movieURL = getUri(output);
            if (movieURL == null) {
                return null;
            } else {
                posterLink = movieURL.toString();
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Invalid image. Please check movie ID " + TMDBid);
        }
        return posterLink;
    }

    private static URI getUri(String output) throws URISyntaxException {
        String search = "\"posters\":[{";
        int index = output.indexOf(search);
        if (index == -1) {
            return null;
        } else {
            StringBuilder remainingText = new StringBuilder(output);
            remainingText.delete(0, index);
            search = "\"file_path\":\"";
            index = remainingText.indexOf(search) + search.length();
            remainingText.delete(0, index);
            remainingText.delete(remainingText.indexOf("\""), remainingText.length());
            String url = "https://image.tmdb.org/t/p/original" + remainingText;
            return new URI(url);
        }
    }

    static String movieRetrieve(int TMDBid) {
        String url = null;
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://api.themoviedb.org/3/movie/" + TMDBid + "/videos"))
                    .header("accept", "application/json")
                    .header("Authorization", "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIxMzQ0ZGY4YTcwYjdlY2Q1NzI1Y2NkMjQwMzY3OTFkMSIsInN1YiI6IjY1ZGM5MDY0MDc1Mjg4MDE3ZGI0MGIwNSIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.9mRXh5UFdS8_qVcRhVImdKd1Cm50Hio4aGA-TOcKZxg")
                    .method("GET", HttpRequest.BodyPublishers.noBody())
                    .build();
            HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
            StringBuilder output = new StringBuilder(response.body());
            int indexOfTrailer = output.indexOf("\"type\":\"Trailer\"");
            if (indexOfTrailer == -1) {
                indexOfTrailer = output.indexOf("\"type\":\"Teaser\"");
            }
            if (indexOfTrailer == -1) {
                return null;
            } else {
                output.delete(indexOfTrailer, output.length());
                int indexOfKey = output.lastIndexOf("\"key\":\"");
                output.delete(0, indexOfKey + 7);
                int indexOfKeyEnd = output.indexOf("\"");
                output.delete(indexOfKeyEnd, output.length());
                url = "https://www.youtube.com/watch?v=" + output;
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error in retrieving video. Please inspect movie " + TMDBid);
        }
        return url;
    }
}
