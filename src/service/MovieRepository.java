package service;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import dto.MovieDto;

public class MovieRepository implements MovieService{
	
	private List<MovieDto> movieDtos = new ArrayList<>();

	@Override
	public void createMovie(MovieDto movieDto) {
		movieDtos.add(movieDto);
		saveData();
		
	}
	private void saveData() {
		try (PrintWriter writer = new PrintWriter(new FileWriter("movie_data.txt"))){
			for(MovieDto movieDto : movieDtos) {
				writer.println(movieDto.getMovieNumber() + "," + movieDto.getMovieName() + ","+ movieDto.getGenre() + ","+ movieDto.getRating() + ","+ movieDto.getReview() + ",");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
