package service;

import java.util.List;

import dto.MovieDto;

public interface MovieService {
	
	public void createMovie(MovieDto movieDto);
	
	public List<MovieDto> getMovieList();
	
	public void updateMovie(int movieNumber, String movieName, String genre, int rating, String review);
	
	public void deleteMovie(int index);
	
}
